package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

public record TicketDto(LocalDateTime drawDate,
                        String lotteryId,
                        Set<Integer> userNumbers) {
}
