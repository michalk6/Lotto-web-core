package pl.lotto.resultchecker;

import java.util.Optional;

interface ResultCheckerRepository {
    CheckedTicket save(CheckedTicket ticket);

    Optional<CheckedTicket> findCheckedTicketByLotteryId(String lotteryId);
}
