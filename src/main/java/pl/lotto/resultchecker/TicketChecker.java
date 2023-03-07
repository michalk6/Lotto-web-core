package pl.lotto.resultchecker;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
class TicketChecker {

    TicketChecker() {
    }

    CheckedTicket checkTicket(Set<Integer> winningNumbers, Ticket toCheck) {
        Set<Integer> userNumbers = toCheck.userNumbers();
        int numberOfMatches = numberOfMatches(winningNumbers, userNumbers);
        return CheckedTicket.builder()
                .lotteryId(toCheck.lotteryId())
                .drawDate(toCheck.drawDate())
                .userNumbers(toCheck.userNumbers())
                .result(new GameResult(numberOfMatches))
                .build();
    }

    private int numberOfMatches(Set<Integer> winningNumbers, Set<Integer> userNumbers) {
        HashSet<Integer> matchingNumbers = new HashSet<>(userNumbers);
        matchingNumbers.retainAll(winningNumbers);
        return matchingNumbers.size();
    }
}
