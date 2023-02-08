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
    public NumberReceiverFacade numberReceiverFacade(UserNumberValidator validator, NextDrawScheduler scheduler, NumberReceiverRepository repository) {
        return new NumberReceiverFacade(validator, scheduler, repository);
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

//    @Bean
//    NextDrawScheduler scheduler() {
//        return new NextDrawScheduler(clock());
//    }

//    @Bean
//    UserNumberValidator validator() {
//        return new UserNumberValidator();
//    }
}
