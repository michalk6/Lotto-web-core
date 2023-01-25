package pl.lotto.winningnumbergenerator;

import java.time.LocalDateTime;
import java.util.Set;

class WinningNumbers {
    private Set<Integer> numbers;
    private LocalDateTime drawDate;

    WinningNumbers(Set<Integer> numbers, LocalDateTime drawDate) {
        this.numbers = numbers;
        this.drawDate = drawDate;
    }

    Set<Integer> getNumbers() {
        return numbers;
    }

    void setNumbers(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    LocalDateTime getDrawDate() {
        return drawDate;
    }

    void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }
}
