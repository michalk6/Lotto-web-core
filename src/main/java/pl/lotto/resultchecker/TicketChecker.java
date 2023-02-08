package pl.lotto.resultchecker;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
class TicketChecker {

    TicketChecker() {
    }

    CheckedTicket checkTicket(Set<Integer> winningNumbers, Ticket toCheck) {
        Set<Integer> userNumbers = toCheck.getUserNumbers();
        int numberOfMatches = numberOfMatches(winningNumbers, userNumbers);
        return new CheckedTicket(toCheck.getLotteryId(), toCheck.getDrawDate(), toCheck.getUserNumbers(), new GameResult(numberOfMatches));
    }

    private int numberOfMatches(Set<Integer> winningNumbers, Set<Integer> userNumbers) {
        HashSet<Integer> matchingNumbers = new HashSet<>(userNumbers);
        matchingNumbers.retainAll(winningNumbers);
        return matchingNumbers.size();
    }
}
