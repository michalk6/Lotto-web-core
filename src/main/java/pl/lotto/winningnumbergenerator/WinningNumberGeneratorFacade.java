package pl.lotto.winningnumbergenerator;

import org.springframework.stereotype.Component;
import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class WinningNumberGeneratorFacade {
    private final WinningNumberProvider winningNumberGenerator;
    private final WinningNumbersRepository repository;

    public WinningNumberGeneratorFacade(WinningNumberProvider winningNumberGenerator, WinningNumbersRepository repository) {
        this.winningNumberGenerator = winningNumberGenerator;
        this.repository = repository;
    }

    public WinningNumbersDto drawWinningNumbers() {
        Set<Integer> winningNumbers = winningNumberGenerator.drawWinningNumbers();
        WinningNumbers saved = repository.save(WinningNumbers.builder().numbers(winningNumbers).drawDate(LocalDateTime.now()).build());
        return WinningNumbersMapper.mapToDto(saved);
    }
}
