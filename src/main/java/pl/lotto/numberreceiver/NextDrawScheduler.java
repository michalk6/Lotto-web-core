package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

class NextDrawScheduler {

    LocalDateTime nextDrawDate() {
        return LocalDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(12)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }
}
