package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(Clock clock, NumberReceiverRepository repository) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler(clock);
        return new NumberReceiverFacade(validator, nextDrawScheduler, repository);
    }
}
