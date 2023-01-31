package pl.lotto.resultchecker;

public class NoSuchDrawDateException extends RuntimeException {
    public NoSuchDrawDateException(String message) {
        super(message);
    }
}
