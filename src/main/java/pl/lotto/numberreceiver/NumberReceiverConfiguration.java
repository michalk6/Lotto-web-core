package pl.lotto.numberreceiver;

import java.time.LocalDateTime;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(LocalDateTime testDateTime, NumberReceiverRepository repository) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler(testDateTime);
        return new NumberReceiverFacade(validator, nextDrawScheduler, repository);
    }
}
