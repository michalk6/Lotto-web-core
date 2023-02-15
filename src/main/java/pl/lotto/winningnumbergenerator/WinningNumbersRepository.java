package pl.lotto.winningnumbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface WinningNumbersRepository extends MongoRepository<WinningNumbers, LocalDateTime> {

    List<WinningNumbers> findAll();
}
