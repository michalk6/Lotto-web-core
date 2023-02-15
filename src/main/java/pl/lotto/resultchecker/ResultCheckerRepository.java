package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ResultCheckerRepository extends MongoRepository<CheckedTicket, String> {

    Optional<CheckedTicket> findCheckedTicketByLotteryId(String lotteryId);
}
