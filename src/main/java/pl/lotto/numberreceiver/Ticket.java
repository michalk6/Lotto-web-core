package pl.lotto.numberreceiver;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Builder
record Ticket(@Id String lotteryId,
              LocalDateTime drawDate,
              Set<Integer> userNumbers) {
}
