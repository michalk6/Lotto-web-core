package pl.lotto.infrastructure.scheduler.winningnumbergenerator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

@Component
@Log4j2
@AllArgsConstructor
public class WinningNumberGeneratorScheduler {
    private final WinningNumberGeneratorFacade winningNumberGeneratorFacade;

    @Scheduled(cron = "${lotto.number-generator.lotteryRunOccurrence}")
    public void generateWinningNumbers() {
        log.info("scheduler started");
        winningNumberGeneratorFacade.drawWinningNumbers();
        log.info("scheduler finished");
    }


}
