package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pl.lotto.numberreceiver.ValidationError.DUPLICATED_NUMBER;
import static pl.lotto.numberreceiver.ValidationError.OUT_OF_BOUND;

class LottoUserNumberValidator {
    private final static int NUMBER_OF_NUMBERS = 6;
    private final static int MAX_NUMBER = 99;

    LottoUserNumberValidator() {
    }

    ValidationResult validate(Collection<Integer> userNumbers) {
        List<ValidationError> errors = new ArrayList<>();
        if (userNumbers.size() < NUMBER_OF_NUMBERS) {
            errors.add(DUPLICATED_NUMBER);
        }
        boolean allMatch = userNumbers.stream()
                .allMatch(this::numberIsInRange);
        if (!allMatch) {
            errors.add(OUT_OF_BOUND);
        }
        if (!errors.isEmpty()) {
            String resultMessage = errors.stream()
                    .map(error -> error.message)
                    .collect(Collectors.joining(","));
            return new ValidationResult(false, resultMessage);
        }
        return new ValidationResult(true, "all good");
    }

    private boolean numberIsInRange(int toCheck) {
        return (toCheck >= 1 && toCheck <= MAX_NUMBER);
    }
}
