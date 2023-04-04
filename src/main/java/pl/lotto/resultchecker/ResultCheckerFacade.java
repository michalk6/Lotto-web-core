package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.infrastructure.services.winningnumberservice.WinningNumberService;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.AllCheckedTicketsDto;
import pl.lotto.resultchecker.dto.CheckedTicketDto;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ResultCheckerFacade {
    private final WinningNumberService winningNumberService;
    private final NumberReceiverFacade numberReceiver;
    private final ResultComparator comparator;
    private final ResultCheckerRepository repository;
    private final ResultCheckerEventRepository eventRepository;


    public AllCheckedTicketsDto checkAllTicketsForCurrentDrawDate() {
        Set<Integer> winningNumbers = retrieveWinningNumbersForCurrentDrawDate().numbers();
        List<Ticket> tickets = TicketMapper.mapToTicketList(retrieveNumbersForCurrentDrawDate().tickets());
        List<CheckedTicket> checkedTickets = comparator.checkTicketForSingleDraw(winningNumbers, tickets);
        repository.saveAll(checkedTickets);
        ResultCheckerEvent resultCheckerEvent = ResultCheckerEvent.builder()
                .drawDate(numberReceiver.getNextDrawDate())
                .build();
        eventRepository.save(resultCheckerEvent);
        List<CheckedTicketDto> checkedTicketDtos = CheckedTicketMapper.mapListToDto(checkedTickets);
        return new AllCheckedTicketsDto(checkedTicketDtos);
    }

    public boolean ticketsAreCheckedForNextDrawDate() {
        return eventRepository.existsResultCheckerEventByDrawDate(numberReceiver.getNextDrawDate());
    }

    private AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        return numberReceiver.retrieveNumbersForCurrentDrawDate();
    }

    private WinningNumbersDto retrieveWinningNumbersForCurrentDrawDate() {
        return winningNumberService.retrieveNumbersFromNumberGenerator();
//        return numberGenerator.retrieveNumbersForCurrentDrawDate();
    }

    public CheckedTicketDto checkWinner(String lotteryId) throws NoSuchDrawException {
        CheckedTicket ticket = repository.findCheckedTicketByLotteryId(lotteryId)
                .orElseGet(() -> findFromNumberReceiver(lotteryId));
        if (ticket.result() == null) {
            throw new NoSuchDrawException("The draw has not yet taken place");
        }
        return CheckedTicketMapper.mapToDto(ticket);
    }

    private CheckedTicket findFromNumberReceiver(String lotteryId) {
        TicketDto byLotteryId = numberReceiver.findByLotteryId(lotteryId);
        Ticket ticket = TicketMapper.mapToTicket(byLotteryId);
        return CheckedTicket.builder()
                .lotteryId(ticket.lotteryId())
                .drawDate(ticket.drawDate())
                .userNumbers(ticket.userNumbers())
                .result(null)
                .build();
    }
}
