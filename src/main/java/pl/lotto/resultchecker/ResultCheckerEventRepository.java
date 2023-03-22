package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
interface ResultCheckerEventRepository extends MongoRepository <ResultCheckerEvent, String> {
    boolean existsResultCheckerEventByDrawDate(LocalDateTime drawDate);
}
