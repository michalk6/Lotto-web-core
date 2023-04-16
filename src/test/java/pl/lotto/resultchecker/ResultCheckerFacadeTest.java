package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.AllCheckedTicketsDto;
import pl.lotto.resultchecker.dto.CheckedTicketDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {
    LocalDateTime dateForTest = LocalDateTime.now();
    ResultCheckerRepositoryInMemory repository = new ResultCheckerRepositoryInMemory();
    ResultCheckerEventRepositoryInMemory eventRepository = new ResultCheckerEventRepositoryInMemory();
    WinningNumberService winningNumberService = new WinningNumberServiceInMemory(dateForTest);

    @Test
    void checkWinner_shouldReturnTicketWithGameResultWithSixMatches_whenSearchingByProperId() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberService, numberReceiverMock, repository, eventRepository);
        when(numberReceiverMock.retrieveNumbersForCurrentDrawDate()).thenReturn(
                new AllNumbersDto(List.of(
                        new TicketDto(dateForTest, "example", Set.of(1, 2, 3, 4, 5, 6))
                ))
        );
        //when
        resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        //then
        CheckedTicketDto example = resultCheckerFacade.checkWinner("example");
        CheckedTicketDto expected = new CheckedTicketDto(dateForTest, "example", Set.of(1, 2, 3, 4, 5, 6), new GameResult(6));
        assertThat(example).isEqualTo(expected);
    }

    @Test
    void checkWinner_shouldThrowNoSuchDrawException_whenMethodIsCalledBeforeDraw() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberService, numberReceiverMock, repository, eventRepository);
        LocalDateTime now = LocalDateTime.now();
        String userId = "userId";
        when(numberReceiverMock.findByLotteryId(userId)).thenReturn(new TicketDto(now, userId, Set.of(1, 2, 3, 4, 5, 6)));
        //when
        //then
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner(userId)).isInstanceOf(NoSuchDrawException.class);
        assertThatThrownBy(() -> resultCheckerFacade.checkWinner(userId)).hasMessage("The draw has not yet taken place");
    }

    @Test
    void checkAllTicketsForCurrentDrawDate_shouldReturnCheckedTicketsFromCurrentDrawWithProperGameResult() {
        //given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberService, numberReceiverMock, repository, eventRepository);
        when(numberReceiverMock.retrieveNumbersForCurrentDrawDate()).thenReturn(
                new AllNumbersDto(List.of(
                        new TicketDto(dateForTest, "example", Set.of(1, 2, 3, 4, 5, 6)),
                        new TicketDto(dateForTest, "example1", Set.of(2, 3, 4, 5, 6, 7)),
                        new TicketDto(dateForTest, "example2", Set.of(3, 4, 5, 6, 7, 8))
                ))
        );
        //when
        AllCheckedTicketsDto allCheckedTicketsDto = resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        //then
        assertThat(allCheckedTicketsDto.checkedTickets()).containsExactlyInAnyOrder(
                new CheckedTicketDto(dateForTest, "example", Set.of(1, 2, 3, 4, 5, 6), new GameResult(6)),
                new CheckedTicketDto(dateForTest, "example1", Set.of(2, 3, 4, 5, 6, 7), new GameResult(5)),
                new CheckedTicketDto(dateForTest, "example2", Set.of(3, 4, 5, 6, 7, 8), new GameResult(4))
        );
    }

    @Test
    public void ticketsAreCheckedForNextDrawDate_shouldReturnFalse_IfIfCheckAllTicketsForCurrentDrawDateWasNotStarted() {
        // given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberService, numberReceiverMock, repository, eventRepository);
        // when
        // then
        assertThat(resultCheckerFacade.ticketsAreCheckedForNextDrawDate()).isFalse();
    }

    @Test
    public void ticketsAreCheckedForNextDrawDate_shouldReturnTrue_IfCheckAllTicketsForCurrentDrawDateWasStarted() {
        // given
        NumberReceiverFacade numberReceiverMock = mock(NumberReceiverFacade.class);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTests(winningNumberService, numberReceiverMock, repository, eventRepository);
        when(numberReceiverMock.retrieveNumbersForCurrentDrawDate()).thenReturn(new AllNumbersDto(Collections.emptyList()));
        when(numberReceiverMock.getNextDrawDate()).thenReturn(dateForTest);
        // when
        resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        // then
        assertThat(resultCheckerFacade.ticketsAreCheckedForNextDrawDate()).isTrue();
    }
}