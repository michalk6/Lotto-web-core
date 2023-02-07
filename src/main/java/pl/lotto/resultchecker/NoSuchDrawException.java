package pl.lotto.resultchecker;

public class NoSuchDrawException extends RuntimeException {
    public NoSuchDrawException(String message) {
        super(message);
    }
}
