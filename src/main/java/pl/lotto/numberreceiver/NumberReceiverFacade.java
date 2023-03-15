package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersRequestDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.*;

public class NumberReceiverFacade {

    private final UserNumberValidator validator;
    private final NextDrawDateGenerator drawDateGenerator;
    private final NumberReceiverRepository repository;

    public NumberReceiverFacade(UserNumberValidator validator, NextDrawDateGenerator scheduler, NumberReceiverRepository repository) {
        this.validator = validator;
        this.drawDateGenerator = scheduler;
        this.repository = repository;
    }

    public InputNumbersDto inputNumbers(InputNumbersRequestDto request) {
        Collection<Integer> userNumbers = RequestMapper.mapRequestDtoToCollection(request);
        ValidationResult result = validator.validate(userNumbers);
        if (!result.isValid()) {
            return new InputNumbersDto(result.errorMessage(), null);
        }
        Set<Integer> validatedNumbers = new HashSet<>(userNumbers);
        Ticket ticket = Ticket.builder()
                .lotteryId(UUID.randomUUID().toString())
                .drawDate(getNextDrawDate())
                .userNumbers(validatedNumbers)
                .build();
        Ticket saved = repository.save(ticket);
        return new InputNumbersDto(List.of("success"), TicketMapper.mapToDto(saved));
    }

    public AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        List<Ticket> allByDrawDate = repository.findAllByDrawDate(drawDateGenerator.nextDrawDate());
        List<TicketDto> ticketDtos = TicketMapper.mapListToDto(allByDrawDate);
        return new AllNumbersDto(ticketDtos);
    }

    public LocalDateTime getNextDrawDate() {
        return drawDateGenerator.nextDrawDate();
    }

    public TicketDto findByLotteryId(String lotteryId) {
        Ticket ticket = repository.findTicketByLotteryId(lotteryId)
                .orElseThrow(() -> new NoSuchTicketException("Ticket with given id not found"));
        return TicketMapper.mapToDto(ticket);
    }
}
