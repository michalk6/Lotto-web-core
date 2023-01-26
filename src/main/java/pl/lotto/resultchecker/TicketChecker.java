package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.Set;

class TicketChecker {
    private final ResultCheckerRepository repository;

    TicketChecker(ResultCheckerRepository repository) {
        this.repository = repository;
    }

    CheckedTicket checkTicket(Set<Integer> winningNumbers, TicketDto toCheck) {
        Set<Integer> userNumbers = toCheck.userNumbers();
        int numberOfMatches = numberOfMatches(winningNumbers, userNumbers);
        return repository.save(new CheckedTicket(toCheck.lotteryId(), toCheck.drawDate(), toCheck.userNumbers(), new GameResult(numberOfMatches)));
    }

    private int numberOfMatches(Set<Integer> winningNumbers, Set<Integer> userNumbers) {
        int result = 0;
        for (int userNumber : userNumbers) {
            if (numberMatch(winningNumbers, userNumber))
                result++;
        }
        return result;
    }

    private boolean numberMatch(Set<Integer> winningNumbers, int userNumber) {
        return winningNumbers.stream().anyMatch(generated -> generated.equals(userNumber));
    }
}
