package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class ResultCheckerEventRepositoryInMemory implements ResultCheckerEventRepository {
    Map<String, ResultCheckerEvent> databaseInMemory = new ConcurrentHashMap<>();
    @Override
    public boolean existsResultCheckerEventByDrawDate(LocalDateTime drawDate) {
        return databaseInMemory.values().stream()
                .anyMatch(event -> event.drawDate().equals(drawDate));
    }

    @Override
    public <S extends ResultCheckerEvent> S save(S entity) {
        ResultCheckerEvent newResultCheckerEvent = ResultCheckerEvent.builder().id(UUID.randomUUID().toString()).drawDate(entity.drawDate()).build();
        databaseInMemory.put(newResultCheckerEvent.id(), newResultCheckerEvent);
        return (S) newResultCheckerEvent;
    }

    @Override
    public <S extends ResultCheckerEvent> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ResultCheckerEvent> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<ResultCheckerEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<ResultCheckerEvent> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(ResultCheckerEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends ResultCheckerEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ResultCheckerEvent> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ResultCheckerEvent> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ResultCheckerEvent> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ResultCheckerEvent> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ResultCheckerEvent> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ResultCheckerEvent, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
