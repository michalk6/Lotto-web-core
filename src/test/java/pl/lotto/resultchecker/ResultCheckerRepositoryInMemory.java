package pl.lotto.resultchecker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultCheckerRepositoryInMemory implements ResultCheckerRepository {
    Map<String, CheckedTicket> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public CheckedTicket save(CheckedTicket ticket) {
        return null;
    }

    @Override
    public List<CheckedTicket> findAll() {
        return databaseInMemory.values().stream().toList();
    }
}
