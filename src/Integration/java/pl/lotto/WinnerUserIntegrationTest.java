package pl.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.resultannouncer.ResultDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LottoGameSpringBootApp.class, IntegrationTestConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
@Testcontainers
public class WinnerUserIntegrationTest {
    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AdjustableIntegrationClock clock;

    @Autowired
    WinningNumberGeneratorFacade winningNumberGeneratorFacade;

    @Autowired
    ResultCheckerFacade resultCheckerFacade;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("lotto.number-generator-base-url", wireMockServer::baseUrl);
    }

    @DynamicPropertySource
    static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void winnerUserScenario() throws Exception {
        // step 1: user POST six unique numbers (1,2,3,4,5,6) to endpoint and today is 2.02.2023 T 12:00 and system returned lotteryId "example"
        // given
        // when
        ResultActions inputNumbersRequest = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "userNumbers" : [1,2,3,4,5,6]
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult inputNumbersRequestResult = inputNumbersRequest.andExpect(status().isOk()).andReturn();
        String inputNumbersRequestResultJson = inputNumbersRequestResult.getResponse().getContentAsString();
        System.out.println(inputNumbersRequestResultJson);
        InputNumbersDto result = objectMapper.readValue(inputNumbersRequestResultJson, InputNumbersDto.class);
        // step 2: user with id "example" tried to check result and system returned that draw didn't take place yet
        // given
        // when
        ResultActions checkWinnerRequest = mockMvc.perform(get("/checkWinner/" + result.ticket().lotteryId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult checkWinnerRequestResult = checkWinnerRequest.andExpect(status().isOk()).andReturn();
        String checkWinnerRequestResultJson = checkWinnerRequestResult.getResponse().getContentAsString();
        ResultDto resultDto = objectMapper.readValue(checkWinnerRequestResultJson, ResultDto.class);
        assertThat(resultDto.message()).isEqualTo("The draw has not yet taken place");
        // step 3: two days passed 4.02.2023 T 11:55
        clock.plusDays(2);
        // step 4: system generated winning numbers 1,2,3,4,5,6 for draw date 4.02.2023 T 11:55
        wireMockServer.stubFor(
                WireMock.get("/generateNumbers?amount=6&max=99")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("""
                                        {
                                        "numbers" : [1,2,3,4,5,6],
                                        "drawDate":"2023-02-18T12:00:00"
                                        }
                                        """)
                                .withStatus(200))
        );
//        wireMockServer.start();
//        configureFor("localhost", 8888);
        // "/generateNumbers?amount=6&max=99"
//        await().atMost(20, TimeUnit.SECONDS)
//                .pollInterval(Duration.ofSeconds(1L))
//                .until(() -> winningNumberGeneratorFacade.numbersAreAlreadyGeneratedForNextDrawDate());
        // step 5: system checked result for user with lotteryId "example"
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1L))
                .until(() -> resultCheckerFacade.ticketsAreCheckedForNextDrawDate());
        // step 6: user with id "example" checked result system returned 6 matches
        // given
        // when
        ResultActions checkWinnerRequestAfterDraw = mockMvc.perform(get("/checkWinner/" + result.ticket().lotteryId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult checkWinnerRequestAfterDrawResult = checkWinnerRequestAfterDraw.andExpect(status().isOk()).andReturn();
        String checkWinnerRequestAfterDrawResultJson = checkWinnerRequestAfterDrawResult.getResponse().getContentAsString();
        ResultDto resultDtoAfterDraw = objectMapper.readValue(checkWinnerRequestAfterDrawResultJson, ResultDto.class);
        assertThat(resultDtoAfterDraw.message()).isEqualTo("6");
    }
}
