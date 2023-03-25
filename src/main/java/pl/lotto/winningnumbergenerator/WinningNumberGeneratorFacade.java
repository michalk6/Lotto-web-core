package pl.lotto.winningnumbergenerator;

import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class WinningNumberGeneratorFacade {
    private final WinningNumberProvider winningNumberGenerator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersRepository repository;

    public WinningNumberGeneratorFacade(WinningNumberProvider winningNumberGenerator, NumberReceiverFacade numberReceiverFacade, WinningNumbersRepository repository) {
        this.winningNumberGenerator = winningNumberGenerator;
        this.numberReceiverFacade = numberReceiverFacade;
        this.repository = repository;
    }

    public WinningNumbersDto drawWinningNumbers() {
        Set<Integer> winningNumbers = winningNumberGenerator.drawWinningNumbers();
        WinningNumbers saved = repository.save(WinningNumbers.builder()
                .numbers(winningNumbers)
                .drawDate(getNextDrawDate())
                .build());
        return WinningNumbersMapper.mapToDto(saved);
    }

    public WinningNumbersDto retrieveNumbersForCurrentDrawDate() {
        WinningNumbers winningNumbers = repository.findWinningNumbersByDrawDate(getNextDrawDate()).orElseThrow(() -> new RuntimeException(""));
        return WinningNumbersMapper.mapToDto(winningNumbers);
    }

    public boolean numbersAreAlreadyGeneratedForNextDrawDate() {
        LocalDateTime drawDate = getNextDrawDate();
        return repository.existsByDrawDate(drawDate);
    }

    private LocalDateTime getNextDrawDate() {
        return numberReceiverFacade.getNextDrawDate();
    }
}
