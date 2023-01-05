package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static pl.minigames.lotto.LottoRules.NUMBER_OF_NUMBERS;
import static pl.minigames.lotto.ValidationError.DUPLICATED_NUMBER;
import static pl.minigames.lotto.ValidationError.OUT_OF_BOUND;

class LottoUserNumberValidator {
    //    private final static int NUMBER_OF_NUMBERS = LottoRules.NUMBER_OF_NUMBERS;
    private final static int MAX_NUMBER = LottoRules.MAX_NUMBER;

    LottoUserNumberValidator() {
    }

    ValidationResult validate(Set<Integer> userNumbers) {
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

    //    LottoUserNumberValidator(NumberReaderProvider userNumberReader) {
//        this.userNumberReader = userNumberReader;

//    }
//    Set<Integer> validateEnteredNumbers() {
//        validateIfInputContainsEnoughElements(toValidate);
//        validateIfAllNumbersAreInRange(toValidate);
//        return toValidate;

//    }
//    private void validateIfInputContainsEnoughElements(Set<Integer> toValidate) {
//        int count = toValidate.size();
//        if (count < NUMBER_OF_NUMBERS)
////            throw new IllegalArgumentException("You need to enter " + NUMBER_OF_NUMBERS + " unique numbers");

//    }
//    private void validateIfAllNumbersAreInRange(Set<Integer> toValidate) {
//        boolean allMatch = toValidate.stream().allMatch(this::numberIsInRange);
//        if (!allMatch)
////            throw new IllegalArgumentException("Numbers out of bound have been given, please try again");

//    }

}
