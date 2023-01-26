package pl.lotto.resultchecker.dto;

import pl.lotto.resultchecker.GameResult;

import java.time.LocalDateTime;
import java.util.Set;

public record CheckedTicketDto(LocalDateTime drawDate,
                               String lotteryId,
                               Set<Integer> userNumbers,
                               GameResult result) {
    public boolean checkById(String givenId) {
        return this.lotteryId().equals(givenId);
    }
}
