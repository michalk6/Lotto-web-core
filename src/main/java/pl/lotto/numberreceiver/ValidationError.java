package pl.lotto.numberreceiver;

import static pl.minigames.lotto.LottoRules.NUMBER_OF_NUMBERS;

public enum ValidationError {

    DUPLICATED_NUMBER("You need to enter " + NUMBER_OF_NUMBERS + " unique numbers"),
    OUT_OF_BOUND("Numbers out of bound have been given, please try again");

    final String message;

    String getMessage() {
        return message;
    }

    ValidationError(String message) {
        this.message = message;
    }
}
