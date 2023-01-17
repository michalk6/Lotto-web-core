package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.*;

public class NumberReceiverFacade {

    private final UserNumberValidator validator;
    private final NextDrawScheduler scheduler;
    private final NumberReceiverRepository repository;

    public NumberReceiverFacade(UserNumberValidator validator, NextDrawScheduler scheduler, NumberReceiverRepository repository) {
        this.validator = validator;
        this.scheduler = scheduler;
        this.repository = repository;
    }

    public InputNumbersDto inputNumbers(Collection<Integer> userNumbers) {
        ValidationResult result = validator.validate(userNumbers);
        if (!result.isValid()) {
            return new InputNumbersDto(result.errorMessage(), null);
        }
        Set<Integer> validatedNumbers = new HashSet<>(userNumbers);
        TicketDto ticket = new TicketDto(getNextDrawDate(), UUID.randomUUID().toString(), validatedNumbers);
        repository.save(ticket);
        return new InputNumbersDto(List.of("success"), ticket);
    }

    public AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        List<TicketDto> ticketsForNextDrawDate = repository.findAll().stream()
                .filter(ticket -> ticket.drawDate().equals(getNextDrawDate()))
                .toList();
        return new AllNumbersDto(ticketsForNextDrawDate);
    }

    private LocalDateTime getNextDrawDate() {
        return scheduler.nextDrawDate();
    }

}
