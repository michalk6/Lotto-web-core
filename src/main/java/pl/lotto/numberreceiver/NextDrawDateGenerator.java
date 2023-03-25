package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

class NextDrawDateGenerator {
    Clock clock;

    NextDrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    LocalDateTime nextDrawDate() {
        LocalDateTime currentTime = currentTime();
        if (currentTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentTime.getHour() >= 12) {
            return currentTime
                    .with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                    .withHour(12)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);
        }
        return currentTime
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .withHour(12)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    LocalDateTime currentTime() {
        return LocalDateTime.now(clock);
    }
}
