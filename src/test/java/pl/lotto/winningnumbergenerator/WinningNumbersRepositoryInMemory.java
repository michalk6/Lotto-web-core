package pl.lotto.winningnumbergenerator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class WinningNumbersRepositoryInMemory implements WinningNumbersRepository {
    Map<String, WinningNumbers> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        databaseInMemory.put(winningNumbers.toString(), winningNumbers);
        return winningNumbers;
    }

    @Override
    public <S extends WinningNumbers> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WinningNumbers> findById(LocalDateTime localDateTime) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(LocalDateTime localDateTime) {
        return false;
    }

    @Override
    public List<WinningNumbers> findAll() {
        return databaseInMemory.values().stream().toList();
    }

    @Override
    public Iterable<WinningNumbers> findAllById(Iterable<LocalDateTime> localDateTimes) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(LocalDateTime localDateTime) {

    }

    @Override
    public void delete(WinningNumbers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends LocalDateTime> localDateTimes) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinningNumbers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinningNumbers> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WinningNumbers> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinningNumbers> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumbers> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinningNumbers> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinningNumbers, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
