package pl.lotto.resultchecker;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Builder
record ResultCheckerEvent(@Id String id,
                          @Indexed(unique = true) LocalDateTime drawDate) {
}
