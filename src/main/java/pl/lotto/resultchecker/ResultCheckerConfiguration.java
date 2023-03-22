package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

@Configuration
public class ResultCheckerConfiguration {
    public ResultCheckerFacade createForTests(
            WinningNumberGeneratorFacade winningNumberGeneratorMock,
            NumberReceiverFacade numberReceiverMock,
            ResultCheckerRepository repository,
            ResultCheckerEventRepository eventRepository) {
        TicketChecker ticketChecker = new TicketChecker();
        ResultComparator resultComparator = new ResultComparator(ticketChecker);

        return new ResultCheckerFacade(winningNumberGeneratorMock, numberReceiverMock, resultComparator, repository, eventRepository);
    }

    @Bean
    public ResultCheckerFacade resultCheckerFacade(WinningNumberGeneratorFacade numberGenerator, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository, ResultCheckerEventRepository eventRepository) {
        return new ResultCheckerFacade(numberGenerator, numberReceiver, comparator, repository, eventRepository);
    }
}
