package pl.lotto.numberreceiver;

import java.time.LocalDateTime;

public record InputNumbersDto(String message,
                              LocalDateTime drawDate,
                              String lotteryId) {
}
