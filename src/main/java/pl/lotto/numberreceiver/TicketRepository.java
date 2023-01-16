package pl.lotto.numberreceiver;

import java.util.List;

public interface TicketRepository {

    TicketDto save(TicketDto ticket);
    List<TicketDto> findAll();
}
