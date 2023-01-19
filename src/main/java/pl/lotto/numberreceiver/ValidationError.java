package pl.lotto.numberreceiver;

enum ValidationError {

    DUPLICATED_NUMBERS("Duplicated numbers have been given"),
    WRONG_AMOUNT_OF_NUMBERS("Wrong amount of numbers have been given"),
    NUMBERS_OUT_OF_BOUND("Numbers out of bound have been given");

    final String message;

    String getMessage() {
        return message;
    }

    ValidationError(String message) {
        this.message = message;
    }
}
