package pl.lotto.numberreceiver;

import pl.lotto.gamerules.LottoRules;

public enum ValidationError {

    DUPLICATED_NUMBER("Duplicated numbers have been given"),
    NOT_ENOUGH_NUMBERS("You need to enter " + LottoRules.NUMBER_OF_NUMBERS),
    OUT_OF_BOUND("Numbers out of bound have been given, please try again");

    final String message;

    String getMessage() {
        return message;
    }

    ValidationError(String message) {
        this.message = message;
    }
}
