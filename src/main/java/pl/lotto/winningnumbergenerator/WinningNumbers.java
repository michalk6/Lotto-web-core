package pl.lotto.winningnumbergenerator;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Builder
record WinningNumbers(Set<Integer> numbers,
                      @Indexed(unique = true) LocalDateTime drawDate,
                      @Id String id) {
}
