package pl.lotto.numberreceiver;

import java.util.List;

public record ValidationResult(boolean isValid, List<ValidationError> errors) {
}
