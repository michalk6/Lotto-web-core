package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface NumberReceiverRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findAllByDrawDate(LocalDateTime drawDate);

    Optional<Ticket> findTicketByLotteryId(String lotteryId);
}
