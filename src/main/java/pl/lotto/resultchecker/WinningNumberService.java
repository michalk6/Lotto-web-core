package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.WinningNumbersDto;

public interface WinningNumberService {
    WinningNumbersDto retrieveNumbersFromNumberGenerator();
}
