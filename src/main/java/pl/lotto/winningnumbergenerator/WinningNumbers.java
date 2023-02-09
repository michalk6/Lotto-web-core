package pl.lotto.winningnumbergenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Set;

@Document
class WinningNumbers {
    private Set<Integer> numbers;
    @Id
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
