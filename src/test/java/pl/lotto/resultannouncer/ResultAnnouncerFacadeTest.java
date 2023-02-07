package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import pl.lotto.resultchecker.GameResult;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.CheckedTicketDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {
    @Test
    void checkWinner_shouldReturnCheckTicketDto_whenUserGivesExistingId() {
        //given
        ResultCheckerFacade resultCheckerFacadeMock = mock(ResultCheckerFacade.class);
        ResultAnnouncerFacade resultAnnouncer = new ResultAnnouncerFacade(resultCheckerFacadeMock);
        when(resultCheckerFacadeMock.checkWinner("lotteryId")).thenReturn(new CheckedTicketDto(null, "lotteryId", Set.of(1, 2, 3, 4, 5, 6), new GameResult(6)));
        //then
        assertThat(resultAnnouncer.checkWinner("lotteryId")).isEqualTo(new CheckedTicketDto(null, "lotteryId", Set.of(1, 2, 3, 4, 5, 6), new GameResult(6)));
    }
}