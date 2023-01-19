package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record TicketDto(LocalDateTime drawDate,
                        String lotteryId,
                        Set<Integer> userNumbers) {
}
