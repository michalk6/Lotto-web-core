package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.infrastructure.services.winningnumberservice.WinningNumberHttpService;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class ResultCheckerConfiguration {
    public ResultCheckerFacade createForTests(
            WinningNumberHttpService winningNumberHttpServiceMock,
            NumberReceiverFacade numberReceiverMock,
            ResultCheckerRepository repository,
            ResultCheckerEventRepository eventRepository) {
        TicketChecker ticketChecker = new TicketChecker();
        ResultComparator resultComparator = new ResultComparator(ticketChecker);

        return new ResultCheckerFacade(winningNumberHttpServiceMock, numberReceiverMock, resultComparator, repository, eventRepository);
    }

    @Bean
    public ResultCheckerFacade resultCheckerFacade(WinningNumberService winningNumberHttpService, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository, ResultCheckerEventRepository eventRepository) {
        return new ResultCheckerFacade(winningNumberHttpService, numberReceiver, comparator, repository, eventRepository);
    }
}
