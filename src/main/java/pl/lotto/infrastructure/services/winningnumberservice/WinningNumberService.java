package pl.lotto.infrastructure.services.winningnumberservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.lotto.gamerules.LottoRules;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

@Service
@AllArgsConstructor
public class WinningNumberService {
    private static final int NUMBER_OF_NUMBERS = LottoRules.NUMBER_OF_NUMBERS;
    private static final int MAX_NUMBER = LottoRules.MAX_NUMBER;
    private final WebClient winningNumberWebClient;
    private final NumberReceiverFacade numberReceiverFacade;

    public WinningNumbersDto retrieveNumbersFromNumberGenerator() {
        return winningNumberWebClient
                .method(HttpMethod.GET)
                .uri("/generateNumbers?amount=" + NUMBER_OF_NUMBERS + "&max=" + MAX_NUMBER)
                .bodyValue(numberReceiverFacade.getNextDrawDate())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WinningNumbersDto.class)
                .block();
    }
}
