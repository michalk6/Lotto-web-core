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


    public CheckedTicketDto checkWinner(String lotteryId) {
        return repository.findAll().stream()
                .map(CheckedTicketMapper::mapToDto)
                .filter(checkedTicketDto -> checkedTicketDto.checkById(lotteryId))
                .findFirst()
                .get(); //todo need to fix optional issue here
    }

    public AllCheckedTicketsDto checkAllTicketsForCurrentDrawDate() {
        Set<Integer> winningNumbers = retrieveWinningNumbersForCurrentDrawDate().numbers();
        List<TicketDto> tickets = retrieveNumbersForCurrentDrawDate().tickets();
        List<CheckedTicket> checkedTickets = comparator.checkTicketForSingleDraw(winningNumbers, tickets);
        List<CheckedTicketDto> checkedTicketDtos = CheckedTicketMapper.mapListToDto(checkedTickets);
        return new AllCheckedTicketsDto(checkedTicketDtos);
    }

    private AllNumbersDto retrieveNumbersForCurrentDrawDate() {
        return numberReceiver.retrieveNumbersForCurrentDrawDate();
    }

    private WinningNumbersDto retrieveWinningNumbersForCurrentDrawDate() {
        return numberGenerator.drawWinningNumbers();
    }
}
