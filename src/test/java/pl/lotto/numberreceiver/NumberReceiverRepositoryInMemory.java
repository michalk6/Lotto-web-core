package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class NumberReceiverRepositoryInMemory implements NumberReceiverRepository {
    Map<String, Ticket> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        databaseInMemory.put(ticket.getLotteryId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllByDrawDate(LocalDateTime drawDate) {
        return databaseInMemory.values().stream()
                .filter(ticket -> ticket.getDrawDate().equals(drawDate))
                .toList();
    }

    @Override
    public Optional<Ticket> findTicketByLotteryId(String lotteryId) {
        return databaseInMemory.values().stream()
                .filter(ticket -> ticket.getLotteryId().equals(lotteryId))
                .findFirst();
    }
}
