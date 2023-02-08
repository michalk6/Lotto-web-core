package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ResultCheckerRepository extends MongoRepository<CheckedTicket, String> {

    CheckedTicket save(CheckedTicket ticket);

    Optional<CheckedTicket> findCheckedTicketByLotteryId(String lotteryId);
}
