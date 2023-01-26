package pl.lotto.numberreceiver;

import java.time.Clock;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(Clock clock, NumberReceiverRepository repository) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler(clock);
        return new NumberReceiverFacade(validator, nextDrawScheduler, repository);
    }
}
