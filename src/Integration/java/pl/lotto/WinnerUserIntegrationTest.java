package pl.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LottoGameSpringBootApp.class, IntegrationTestConfiguration.class})
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

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
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
        await().atMost(20, TimeUnit.SECONDS)
                .pollInterval(Duration.ofSeconds(1L))
                .until(() -> winningNumberGeneratorFacade.numbersAreAlreadyGeneratedForNextDrawDate());
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
