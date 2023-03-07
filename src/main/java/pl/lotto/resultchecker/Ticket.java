package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;

record Ticket(String lotteryId,
        LocalDateTime drawDate,
        Set<Integer> userNumbers) {
}
