package pl.lotto.infrastructure.scheduler.resultchecker;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

@Component
@Log4j2
@AllArgsConstructor
public class ResultCheckerScheduler {
    private final ResultCheckerFacade resultCheckerFacade;
    private final WinningNumberGeneratorFacade winningNumberGeneratorFacade;

    @Scheduled(cron = "${lotto.result-checker.lotteryRunOccurrence}")
    public void checkResultsForCurrentDraw() {
        log.info("result checker scheduler started");
        if (winningNumberGeneratorFacade.numbersAreAlreadyGeneratedForNextDrawDate())
            resultCheckerFacade.checkAllTicketsForCurrentDrawDate();
        log.info("result checker scheduler finished");
    }
}
