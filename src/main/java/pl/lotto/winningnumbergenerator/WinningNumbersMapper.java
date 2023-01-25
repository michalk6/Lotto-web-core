package pl.lotto.winningnumbergenerator;

import pl.lotto.winningnumbergenerator.dto.WinningNumbersDto;

class WinningNumbersMapper {

    public static WinningNumbersDto mapToDto(WinningNumbers winningNumbers) {
        return new WinningNumbersDto(winningNumbers.getNumbers(), winningNumbers.getDrawDate());
    }
}
