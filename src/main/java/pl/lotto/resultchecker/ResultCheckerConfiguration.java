package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

public class ResultCheckerConfiguration {
    public ResultCheckerFacade createForTests(
            WinningNumberGeneratorFacade winningNumberGeneratorMock,
            NumberReceiverFacade numberReceiverMock,
            ResultCheckerRepository repository) {
        TicketChecker ticketChecker = new TicketChecker(repository);
        ResultComparator resultComparator = new ResultComparator(ticketChecker);
        return new ResultCheckerFacade(winningNumberGeneratorMock, numberReceiverMock, resultComparator, repository);
    }
}
