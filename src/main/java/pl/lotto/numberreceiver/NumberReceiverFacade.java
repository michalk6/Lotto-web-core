package pl.lotto.numberreceiver;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class NumberReceiverFacade {

    private final UserNumberValidator validator;
    private final NextDrawScheduler scheduler;

    public NumberReceiverFacade(UserNumberValidator validator, NextDrawScheduler scheduler) {
        this.validator = validator;
        this.scheduler = scheduler;
    }

    public InputNumbersDto inputNumbers(Collection<Integer> userNumbers) {
        ValidationResult result = validator.validate(userNumbers);
        if (!result.isValid()) {
            return new InputNumbersDto(result.errorMessage(), null, null);
        }
        return new InputNumbersDto(List.of("success"), scheduler.nextDrawDate(), UUID.randomUUID().toString());
    }
}
