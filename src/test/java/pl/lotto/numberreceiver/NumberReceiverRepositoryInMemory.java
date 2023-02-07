package pl.lotto.numberreceiver;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class NumberReceiverRepositoryInMemory implements NumberReceiverRepository {
    Map<String, Ticket> databaseInMemory = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        databaseInMemory.put(ticket.getLotteryId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllByDrawDate(LocalDateTime drawDate) {
        return databaseInMemory.values().stream()
                .filter(ticket -> ticket.getDrawDate().equals(drawDate))
                .toList();
    }

    @Override
    public Optional<Ticket> findTicketByLotteryId(String lotteryId) {
        return databaseInMemory.values().stream()
                .filter(ticket -> ticket.getLotteryId().equals(lotteryId))
                .findFirst();
    }

    @Override
    public List saveAll(Iterable entities) {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAllById(Iterable iterable) {

    }

    @Override
    public void deleteAll(Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List findAll(Sort sort) {
        return null;
    }

    @Override
    public Page findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List findAll(Example example, Sort sort) {
        return null;
    }

    @Override
    public List findAll(Example example) {
        return null;
    }

    @Override
    public List insert(Iterable entities) {
        return null;
    }

    @Override
    public Object insert(Object entity) {
        return null;
    }

    @Override
    public Optional findOne(Example example) {
        return Optional.empty();
    }

    @Override
    public Page findAll(Example example, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Example example) {
        return 0;
    }

    @Override
    public boolean exists(Example example) {
        return false;
    }

    @Override
    public Object findBy(Example example, Function queryFunction) {
        return null;
    }
}
