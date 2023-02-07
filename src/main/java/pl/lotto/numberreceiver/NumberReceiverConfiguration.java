package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(Clock clock, NumberReceiverRepository repository) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler(clock);
        return new NumberReceiverFacade(validator, nextDrawScheduler, repository);
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

//    @Bean
//    public NumberReceiverFacade numberReceiverFacade(Clock clock, NumberReceiverRepository repository) {
//        return new NumberReceiverFacade()
//    }
}
