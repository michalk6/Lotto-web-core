package pl.lotto.resultannouncer;

import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NoSuchTicketException;
import pl.lotto.resultchecker.NoSuchDrawException;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.CheckedTicketDto;

@Component
public class ResultAnnouncerFacade {
    private ResultCheckerFacade resultChecker;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker) {
        this.resultChecker = resultChecker;
    }

    public ResultDto checkWinner(String lotteryId) {
        try {
            CheckedTicketDto checkedTicketDto = resultChecker.checkWinner(lotteryId);
            return new ResultDto(String.valueOf(checkedTicketDto.result().numberOfMatches()));
        } catch (NoSuchDrawException | NoSuchTicketException e) {
            return new ResultDto(e.getMessage());
        }
    }
}
