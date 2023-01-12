package pl.lotto.numberreceiver;

import java.time.LocalDateTime;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade createForTest(LocalDateTime now) {
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler(now);
        return new NumberReceiverFacade(validator, nextDrawScheduler);
    }
}
