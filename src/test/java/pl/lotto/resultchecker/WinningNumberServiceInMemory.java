package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
class WinningNumberServiceInMemory implements WinningNumberService {
    private LocalDateTime drawDate;

    @Override
    public WinningNumbersDto retrieveNumbersFromNumberGenerator() {
        return new WinningNumbersDto(Set.of(1, 2, 3, 4, 5, 6), drawDate);
    }
}
