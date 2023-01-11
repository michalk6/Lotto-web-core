package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerFacade {
    private ResultCheckerFacade resultChecker;

    public ResultAnnouncerFacade(ResultCheckerFacade resultChecker) {
        this.resultChecker = resultChecker;
    }
}
