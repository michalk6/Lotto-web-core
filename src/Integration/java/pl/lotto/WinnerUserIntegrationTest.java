package pl.lotto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WinnerUserIntegrationTest {
    @Test
    void winnerUserScenario() {
        // step 1: user POST six unique numbers (1,2,3,4,5,6) to endpoint and today is 2.02.2023 T 12:00 and system returned lotteryId "example"
        // step 2: user with id "example" tried to check result and system returned that draw didn't take place yet
        // step 3: two days passed 4.02.2023 T 12:00
        // step 4: system generated winning numbers 1,2,3,4,5,6 for draw date 4.02.2023 T 12:00
        // step 5: system checked result for user with lotteryId "example"
        // step 6: user with id "example" checked result system returned 6 matches

    }
}
