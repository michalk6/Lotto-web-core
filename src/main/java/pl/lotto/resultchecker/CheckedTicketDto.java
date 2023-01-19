package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

public record CheckedTicketDto(boolean isWon, TicketDto ticket) {
    boolean checkById(String givenId) {
        return this.ticket.lotteryId().equals(givenId);
    }
}
