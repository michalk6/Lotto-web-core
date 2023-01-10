package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

public record InputNumbersDto(List<String> messages,
                              LocalDateTime drawDate,
                              String lotteryId) {
}
