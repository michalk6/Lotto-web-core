package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NumberReceiverFacade {

    private final UserNumberValidator validator;
    private final NextDrawScheduler scheduler;
    private final Map<String, TicketDto> databaseInMemory = new ConcurrentHashMap<>();

    public NumberReceiverFacade(UserNumberValidator validator, NextDrawScheduler scheduler) {
        this.validator = validator;
        this.scheduler = scheduler;
    }

    public InputNumbersDto inputNumbers(Collection<Integer> userNumbers) {
        ValidationResult result = validator.validate(userNumbers);
        if (!result.isValid()) {
            return new InputNumbersDto(result.errorMessage(), null);
        }
        Set<Integer> validatedNumbers = new HashSet<>(userNumbers);
        TicketDto ticket = new TicketDto(scheduler.nextDrawDate(), UUID.randomUUID().toString(), validatedNumbers);
        // save to database
//        databaseInMemory.put()
        return new InputNumbersDto(List.of("success"), ticket);
    }

    public AllNumbersDto retrieveNumbersForNextDrawDate() {
        // read from database
//        databaseInMemory.values().stream()
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 12, 11, 0);
        return new AllNumbersDto(List.of(new TicketDto(friday, "1", Set.of(1, 2, 3, 4, 5, 6))));
    }
}
