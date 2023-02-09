package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(Clock clock, NumberReceiverRepository repository) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawDateGenerator nextDrawScheduler = new NextDrawDateGenerator(clock);
        return new NumberReceiverFacade(validator, nextDrawScheduler, repository);
    }

    @Bean
    public NumberReceiverFacade numberReceiverFacade(NumberReceiverRepository repository, Clock clock) {
        return new NumberReceiverFacade(new UserNumberValidator(), new NextDrawDateGenerator(clock), repository);
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }
}
