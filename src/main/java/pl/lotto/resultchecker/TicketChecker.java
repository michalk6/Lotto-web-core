package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.TicketDto;

import java.util.Set;

class TicketChecker {
    private final ResultCheckerRepository repository;

    TicketChecker(ResultCheckerRepository repository) {
        this.repository = repository;
    }

    CheckedTicketDto checkIfTicketIsWon(Set<Integer> winningNumbers, TicketDto toCheck) {
        Set<Integer> userNumbers = toCheck.userNumbers();
        boolean isWon = userNumbers.equals(winningNumbers);
        return repository.save(new CheckedTicketDto(isWon, toCheck));
    }
}
