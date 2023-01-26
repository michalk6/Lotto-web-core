package pl.lotto.winningnumbergenerator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WinningNumbersRepositoryInMemory implements WinningNumbersRepository {
    Map<String, WinningNumbers> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        databaseInMemory.put(winningNumbers.toString(), winningNumbers);
        return winningNumbers;
    }

    @Override
    public List<WinningNumbers> findAll() {
        return databaseInMemory.values().stream().toList();
    }
}
