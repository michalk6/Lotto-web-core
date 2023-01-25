package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.CheckedTicketDto;

public class ResultAnnouncerFacade {
    private ResultCheckerFacade resultChecker;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker) {
        this.resultChecker = resultChecker;
    }

    public CheckedTicketDto checkWinner(String lotteryId) {
        return resultChecker.checkWinner(lotteryId);
    }
}
