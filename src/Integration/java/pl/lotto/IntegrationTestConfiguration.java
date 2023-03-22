package pl.lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.lotto.winningnumbergenerator.WinningNumberProvider;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;

@Configuration
public class IntegrationTestConfiguration {

    @Bean
    @Primary
    public AdjustableIntegrationClock clock() {
        LocalDateTime friday = LocalDateTime.of(2023, Month.FEBRUARY, 16, 11, 55);
        return new AdjustableIntegrationClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

    @Bean
    @Primary
    public WinningNumberProvider winningNumberGenerator() {
        return () -> Set.of(1, 2, 3, 4, 5, 6);
    }

}
