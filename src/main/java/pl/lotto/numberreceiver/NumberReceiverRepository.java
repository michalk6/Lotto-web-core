package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface NumberReceiverRepository {

    Ticket save(Ticket ticket);

    List<Ticket> findAllByDrawDate(LocalDateTime drawDate);

    Optional<Ticket> findTicketByLotteryId(String lotteryId);
}
