package pl.lotto.numberreceiver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NumberReceiverRepositoryInMemory implements NumberReceiverRepository {
    Map<String, TicketDto> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public TicketDto save(TicketDto ticket) {
        databaseInMemory.put(ticket.lotteryId(), ticket);
        return ticket;
    }

    @Override
    public List<TicketDto> findAll() {
        return databaseInMemory.values().stream().toList();
    }
}
