package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.AllCheckedTicketsDto;
import pl.lotto.resultchecker.dto.CheckedTicketDto;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.util.List;
import java.util.Set;

public class ResultCheckerFacade {
    private final WinningNumberGeneratorFacade numberGenerator;
    private final NumberReceiverFacade numberReceiver;
    private final ResultComparator comparator;
    private final ResultCheckerRepository repository;

    public ResultCheckerFacade(WinningNumberGeneratorFacade numberGenerator, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository) {
        this.numberGenerator = numberGenerator;
        this.numberReceiver = numberReceiver;
        this.comparator = comparator;
        this.repository = repository;
    }


    public AllCheckedTicketsDto checkAllTicketsForCurrentDrawDate() {
        Set<Integer> winningNumbers = retrieveWinningNumbersForCurrentDrawDate().numbers();
        List<Ticket> tickets = TicketMapper.mapToTicketList(retrieveNumbersForCurrentDrawDate().tickets());
        List<CheckedTicket> checkedTickets = comparator.checkTicketForSingleDraw(winningNumbers, tickets);
        checkedTickets.forEach(repository::save);
        List<CheckedTicketDto> checkedTicketDtos = CheckedTicketMapper.mapListToDto(checkedTickets);
        return new AllCheckedTicketsDto(checkedTicketDtos);
    }

    private AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        return numberReceiver.retrieveNumbersForCurrentDrawDate();
    }

    private WinningNumbersDto retrieveWinningNumbersForCurrentDrawDate() {
        return numberGenerator.drawWinningNumbers();
    }

    public CheckedTicketDto checkWinner(String lotteryId) throws NoSuchDrawException {
        CheckedTicket ticket = repository.findCheckedTicketByLotteryId(lotteryId)
                .orElseGet(() -> findFromNumberReceiver(lotteryId));
        if (ticket.getResult() == null) {
            throw new NoSuchDrawException("The draw has not yet taken place");
        }
        return CheckedTicketMapper.mapToDto(ticket);
    }

    private CheckedTicket findFromNumberReceiver(String lotteryId) {
        TicketDto byLotteryId = numberReceiver.findByLotteryId(lotteryId);
        Ticket ticket = TicketMapper.mapToTicket(byLotteryId);
        return new CheckedTicket(ticket.getLotteryId(), ticket.getDrawDate(), ticket.getUserNumbers(), null);
    }
}
