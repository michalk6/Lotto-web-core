package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record InputNumbersDto(List<String> messages,
                              LocalDateTime drawDate,
                              String lotteryId,
                              Set<Integer> userNumbers) {
}
