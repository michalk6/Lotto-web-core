package pl.lotto.infrastructure.services.winningnumberservice;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.gamerules.LottoRules;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.WinningNumberService;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Log4j2
public class WinningNumberHttpService implements WinningNumberService {
    private static final int NUMBER_OF_NUMBERS = LottoRules.NUMBER_OF_NUMBERS;
    private static final int MAX_NUMBER = LottoRules.MAX_NUMBER;
    private final WebClient winningNumberWebClient;
    private final NumberReceiverFacade numberReceiverFacade;

    @Override
    public WinningNumbersDto retrieveNumbersFromNumberGenerator() {
        String drawDate = numberReceiverFacade.getNextDrawDate().format(DateTimeFormatter.ISO_DATE_TIME).strip();
        String uri = "/generateNumbers?amount=" + NUMBER_OF_NUMBERS + "&max=" + MAX_NUMBER + "&drawDate=" + drawDate;
        return winningNumberWebClient
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(WinningNumbersDto.class)
                .doOnError(throwable -> log.error("Cannot access number generator"))
                .block();
    }
}
