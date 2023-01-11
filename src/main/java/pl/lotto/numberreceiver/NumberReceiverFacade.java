package pl.lotto.numberreceiver;

import java.util.*;

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
            return new InputNumbersDto(result.errorMessage(), null, null, null);
        }
        Set<Integer> validatedNumbers = new HashSet<>(userNumbers);
        return new InputNumbersDto(List.of("success"), scheduler.nextDrawDate(), UUID.randomUUID().toString(), validatedNumbers);
    }
}
