package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NoSuchTicketException;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.NumberReceiverRepositoryInMemory;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.CheckedTicketDto;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {
    ResultCheckerRepositoryInMemory repository = new ResultCheckerRepositoryInMemory();

    @Test
    void checkWinner_shouldReturnTicketWithGameResultWithSixMatches_whenSearchingByProperId() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        WinningNumberGeneratorFacade winningNumberGeneratorMock = mock(WinningNumberGeneratorFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberGeneratorMock, numberReceiverMock, repository);
        LocalDateTime now = LocalDateTime.now();
        when(numberReceiverMock.retrieveNumbersForCurrentDrawDate()).thenReturn(
                new AllNumbersDto(List.of(
                        new TicketDto(now, "example", Set.of(1, 2, 3, 4, 5, 6))
                ))
        );
        when(winningNumberGeneratorMock.drawWinningNumbers()).thenReturn(
                new WinningNumbersDto(Set.of(1, 2, 3, 4, 5, 6), now)
        );
        //when
        resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        //then
        CheckedTicketDto example = resultCheckerFacade.checkWinner("example");
        assertThat(example).isEqualTo(new CheckedTicketDto(now, "example", Set.of(1, 2, 3, 4, 5, 6), new GameResult(6)));
    }

    @Test
    void checkWinner_shouldThrowNoSuchDrawException_whenMethodIsCalledBeforeDraw() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        WinningNumberGeneratorFacade winningNumberGeneratorMock = mock(WinningNumberGeneratorFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberGeneratorMock, numberReceiverMock, repository);
        LocalDateTime now = LocalDateTime.now();
        String userId = "userId";
        when(numberReceiverMock.findByLotteryId(userId)).thenReturn(new TicketDto(now, userId, Set.of(1, 2, 3, 4, 5, 6)));
        //when
        //then
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner(userId)).isInstanceOf(NoSuchDrawException.class);
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner(userId)).hasMessage("The draw has not yet taken place");
    }

    @Test
    void checkWinner_shouldThrowNoSuchTicketException_whenMethodIsCalledWithIncorrectId() {
        //given
        WinningNumberGeneratorFacade winningNumberGeneratorMock = mock(WinningNumberGeneratorFacade.class);
        NumberReceiverRepositoryInMemory numberReceiverRepository = mock(NumberReceiverRepositoryInMemory.class);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(null, null, numberReceiverRepository);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberGeneratorMock, numberReceiverFacade, repository);
        when(numberReceiverRepository.findTicketByLotteryId(anyString())).thenReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner("otherId")).isInstanceOf(NoSuchTicketException.class);
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner("otherId")).hasMessage("Ticket with given id not found");
    }
}