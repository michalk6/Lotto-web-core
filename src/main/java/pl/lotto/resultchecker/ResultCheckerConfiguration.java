package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.infrastructure.services.winningnumberservice.WinningNumberService;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class ResultCheckerConfiguration {
    public ResultCheckerFacade createForTests(
            WinningNumberService winningNumberServiceMock,
            NumberReceiverFacade numberReceiverMock,
            ResultCheckerRepository repository,
            ResultCheckerEventRepository eventRepository) {
        TicketChecker ticketChecker = new TicketChecker();
        ResultComparator resultComparator = new ResultComparator(ticketChecker);

        return new ResultCheckerFacade(winningNumberServiceMock, numberReceiverMock, resultComparator, repository, eventRepository);
    }

    @Bean
    public ResultCheckerFacade resultCheckerFacade(WinningNumberService winningNumberService, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository, ResultCheckerEventRepository eventRepository) {
        return new ResultCheckerFacade(winningNumberService, numberReceiver, comparator, repository, eventRepository);
    }
}
