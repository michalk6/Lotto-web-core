package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.*;

public class NumberReceiverFacade {

    private final UserNumberValidator validator;
    private final NextDrawDateGenerator scheduler;
    private final NumberReceiverRepository repository;

    public NumberReceiverFacade(UserNumberValidator validator, NextDrawDateGenerator scheduler, NumberReceiverRepository repository) {
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
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), getNextDrawDate(), validatedNumbers);
        Ticket saved = repository.save(ticket);
        return new InputNumbersDto(List.of("success"), TicketMapper.mapToDto(saved));
    }

    public AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        List<Ticket> allByDrawDate = repository.findAllByDrawDate(scheduler.nextDrawDate());
        List<TicketDto> ticketDtos = TicketMapper.mapListToDto(allByDrawDate);
        return new AllNumbersDto(ticketDtos);
    }

    private LocalDateTime getNextDrawDate() {
        return scheduler.nextDrawDate();
    }

    public TicketDto findByLotteryId(String lotteryId) {
        Ticket ticket = repository.findTicketByLotteryId(lotteryId)
                .orElseThrow(() -> new NoSuchTicketException("Ticket with given id not found"));
        return TicketMapper.mapToDto(ticket);
    }
}
