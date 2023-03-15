package pl.lotto.winningnumbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
interface WinningNumbersRepository extends MongoRepository<WinningNumbers, String> {

    List<WinningNumbers> findAll();

    boolean existsByDrawDate(LocalDateTime drawDate);

    Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime drawDate);
}
