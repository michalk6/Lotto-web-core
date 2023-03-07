package pl.lotto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
public class IntegrationTestConfiguration {

    @Bean
    @Primary
    public AdjustableIntegrationClock clock() {
        LocalDateTime friday = LocalDateTime.of(2023, Month.FEBRUARY, 16, 11, 55);
        return new AdjustableIntegrationClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    }

}
