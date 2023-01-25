package pl.lotto.winningnumbergenerator;

class WinningNumbersMapper {

    public static WinningNumbersDto mapToDto(WinningNumbers winningNumbers) {
        return new WinningNumbersDto(winningNumbers.getNumbers(), winningNumbers.getDrawDate());
    }
}
