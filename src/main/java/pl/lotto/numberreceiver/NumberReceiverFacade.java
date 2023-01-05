package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class NumberReceiverFacade {

    LottoUserNumberValidator validator;

    public InputNumbersDto inputNumbers(Collection<Integer> userNumbers) {
        ValidationResult result = validator.validate(userNumbers);
        if (!result.isValid()) {
            return new InputNumbersDto("failure", null, null);
        }
        return new InputNumbersDto("success", LocalDateTime.now(), UUID.randomUUID().toString());
    }
}
