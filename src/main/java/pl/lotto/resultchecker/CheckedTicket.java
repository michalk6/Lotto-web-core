package pl.lotto.resultchecker;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Builder
record CheckedTicket(@Id String lotteryId,
                    LocalDateTime drawDate,
                    Set<Integer> userNumbers,
                    GameResult result) {
}
