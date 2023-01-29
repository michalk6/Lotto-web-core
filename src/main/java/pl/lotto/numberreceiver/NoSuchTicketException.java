package pl.lotto.numberreceiver;

public class NoSuchTicketException extends RuntimeException {
    public NoSuchTicketException(String message) {
        super(message);
    }
}
