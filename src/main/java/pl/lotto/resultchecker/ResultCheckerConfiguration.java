package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class ResultCheckerConfiguration {
    public ResultCheckerFacade createForTests(
            WinningNumberService winningNumberService,
            NumberReceiverFacade numberReceiverMock,
            ResultCheckerRepository repository,
            ResultCheckerEventRepository eventRepository) {
        TicketChecker ticketChecker = new TicketChecker();
        ResultComparator resultComparator = new ResultComparator(ticketChecker);

        return new ResultCheckerFacade(winningNumberService, numberReceiverMock, resultComparator, repository, eventRepository);
    }

    @Bean
    public ResultCheckerFacade resultCheckerFacade(WinningNumberService winningNumberHttpService, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository, ResultCheckerEventRepository eventRepository) {
        return new ResultCheckerFacade(winningNumberHttpService, numberReceiver, comparator, repository, eventRepository);
    }
}
