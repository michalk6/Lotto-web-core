package pl.lotto.numberreceiver;

import java.util.List;

record ValidationResult(boolean isValid, List<ValidationError> errors) {

    List<String> errorMessage() {
        return errors.stream()
                .map(ValidationError::getMessage)
                .toList();
    }
}
