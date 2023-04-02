package pl.lotto.infrastructure.scheduler.winningnumbergenerator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.infrastructure.services.winningnumberservice.WinningNumberService;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

@Component
@Log4j2
@AllArgsConstructor
public class WinningNumberGeneratorScheduler {
    private final WinningNumberService winningNumberService;

    @Scheduled(cron = "${lotto.number-generator.lotteryRunOccurrence}")
    public void generateWinningNumbers() {
        log.info("winning number generator scheduler started");
        WinningNumbersDto winningNumbersDto = winningNumberService.retrieveNumbersFromNumberGenerator();
        log.info("winning number generator scheduler finished" + winningNumbersDto);
    }


}

