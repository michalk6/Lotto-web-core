package pl.lotto.numberreceiver;

public enum ValidationError {

    DUPLICATED_NUMBER("You need to enter " + 6 + " unique numbers"),
    OUT_OF_BOUND("Numbers out of bound have been given, please try again");

    final String message;

    String getMessage() {
        return message;
    }

    ValidationError(String message) {
        this.message = message;
    }
}
