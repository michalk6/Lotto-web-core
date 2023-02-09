package pl.lotto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.lotto.numberreceiver.dto.InputNumbersDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WinnerUserIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void winnerUserScenario() throws Exception {
        // step 1: user POST six unique numbers (1,2,3,4,5,6) to endpoint and today is 2.02.2023 T 12:00 and system returned lotteryId "example"
        // given
        // when
        ResultActions perform = mockMvc.perform(post("/inputNumbers")
                .content("""
                        {
                        "userNumbers" : [1,2,3,4,5,6]
                        }
                        """)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        InputNumbersDto result = objectMapper.readValue(json, InputNumbersDto.class);
        // step 2: user with id "example" tried to check result and system returned that draw didn't take place yet
        // step 3: two days passed 4.02.2023 T 12:00
        // step 4: system generated winning numbers 1,2,3,4,5,6 for draw date 4.02.2023 T 12:00
        // step 5: system checked result for user with lotteryId "example"
        // step 6: user with id "example" checked result system returned 6 matches

    }
}
