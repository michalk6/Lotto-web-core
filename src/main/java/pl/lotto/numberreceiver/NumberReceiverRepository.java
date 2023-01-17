package pl.lotto.numberreceiver;

import java.util.List;

interface NumberReceiverRepository {

    TicketDto save(TicketDto ticket);
    List<TicketDto> findAll();
}
