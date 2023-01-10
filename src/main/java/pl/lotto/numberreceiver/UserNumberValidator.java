package pl.lotto.numberreceiver;

import pl.lotto.gamerules.LottoRules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class UserNumberValidator {
    private final static int NUMBER_OF_NUMBERS = LottoRules.NUMBER_OF_NUMBERS;
    private final static int MAX_NUMBER = LottoRules.MAX_NUMBER;

    UserNumberValidator() {
    }

    ValidationResult validate(Collection<Integer> userNumbers) {
        List<ValidationError> errors = new ArrayList<>();
        boolean isValid = false;
        if (!properAmountOfNumbersGiven(userNumbers)) {
            errors.add(ValidationError.WRONG_AMOUNT_OF_NUMBERS);
        }
        if (!allInBound(userNumbers)) {
            errors.add(ValidationError.NUMBERS_OUT_OF_BOUND);
        }
        if (!uniqueNumbers(userNumbers)) {
            errors.add(ValidationError.DUPLICATED_NUMBERS);
        }
        if (errors.isEmpty()) {
            isValid = true;
        }
        return new ValidationResult(isValid, errors);
    }

    private static boolean properAmountOfNumbersGiven(Collection<Integer> userNumbers) {
        return userNumbers.size() == NUMBER_OF_NUMBERS;
    }

    private boolean allInBound(Collection<Integer> userNumbers) {
        return userNumbers.stream()
                .allMatch(this::numberIsInRange);
    }

    private boolean numberIsInRange(int toCheck) {
        return (toCheck >= 1 && toCheck <= MAX_NUMBER);
    }

    private boolean uniqueNumbers(Collection<Integer> userNumbers) {
        long distinct = userNumbers.stream().distinct().count();
        return distinct == userNumbers.size();
    }
}
