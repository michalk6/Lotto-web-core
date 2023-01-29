package pl.lotto.resultchecker;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ResultCheckerRepositoryInMemory implements ResultCheckerRepository {
    Map<String, CheckedTicket> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public CheckedTicket save(CheckedTicket ticket) {
        databaseInMemory.put(ticket.getLotteryId(), ticket);
        return ticket;
    }

    @Override
    public Optional<CheckedTicket> findCheckedTicketByLotteryId(String lotteryId) {
        return databaseInMemory.values()
                .stream()
                .filter(ticket -> ticket.getLotteryId().equals(lotteryId))
                .findFirst();
    }
}
