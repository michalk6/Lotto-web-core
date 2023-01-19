package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;
import java.util.Set;

class ResultComparator {
    private TicketChecker ticketChecker;

    public ResultComparator(TicketChecker ticketChecker) {
        this.ticketChecker = ticketChecker;
    }
    AllCheckedTicketsDto checkTicketOfTheLastDraw(Set<Integer> winningNumbers, List<TicketDto> toCheck) {
        return new AllCheckedTicketsDto(toCheck.stream()
                .map(ticket -> ticketChecker.checkIfTicketIsWon(winningNumbers, ticket))
                .toList());
    }
}
