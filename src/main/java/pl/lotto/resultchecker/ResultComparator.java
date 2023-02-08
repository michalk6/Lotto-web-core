package pl.lotto.resultchecker;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
class ResultComparator {
    private final TicketChecker ticketChecker;

    public ResultComparator(TicketChecker ticketChecker) {
        this.ticketChecker = ticketChecker;
    }
    List<CheckedTicket> checkTicketForSingleDraw(Set<Integer> winningNumbers, List<Ticket> toCheck) {
        return toCheck.stream()
                .map(ticket -> ticketChecker.checkTicket(winningNumbers, ticket))
                .toList();
    }
}
