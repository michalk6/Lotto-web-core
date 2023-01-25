package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.AllCheckedTicketsDto;
import pl.lotto.resultchecker.dto.CheckedTicketDto;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {
    ResultCheckerRepository repository = new ResultCheckerRepositoryInMemory();

    @Test
    void mockito() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        WinningNumberGeneratorFacade winningNumberGeneratorMock = mock(WinningNumberGeneratorFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberGeneratorMock, numberReceiverMock, repository);
        LocalDateTime now = LocalDateTime.now();
        //when
        when(numberReceiverMock.retrieveNumbersForCurrentDrawDate()).thenReturn(
                new AllNumbersDto(List.of(
                        new TicketDto(now, "example", Set.of(1, 2, 3, 4, 5, 6))
                ))
        );
        when(winningNumberGeneratorMock.drawWinningNumbers()).thenReturn(
                new WinningNumbersDto(Set.of(1, 2, 3, 4, 5, 6), now)
        );
        AllCheckedTicketsDto allCheckedTicketsDto = resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        //then
        CheckedTicketDto example = resultCheckerFacade.checkWinner("example");
        assertThat("example").isEqualTo("example");
    }
}