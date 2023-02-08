package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ResultCheckerRepositoryInMemory implements ResultCheckerRepository {
    Map<String, CheckedTicket> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public CheckedTicket save(CheckedTicket ticket) {
        databaseInMemory.put(ticket.getLotteryId(), ticket);
        return ticket;
    }

    @Override
    public Optional<CheckedTicket> findCheckedTicketByLotteryId(String lotteryId) {
        return databaseInMemory.values()
                .stream()
                .filter(ticket -> ticket.getLotteryId().equals(lotteryId))
                .findFirst();
    }

    @Override
    public <S extends CheckedTicket> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CheckedTicket> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<CheckedTicket> findAll() {
        return null;
    }

    @Override
    public Iterable<CheckedTicket> findAllById(Iterable<String> strings) {
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
    public void delete(CheckedTicket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CheckedTicket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CheckedTicket> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CheckedTicket> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CheckedTicket> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckedTicket> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CheckedTicket> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CheckedTicket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
