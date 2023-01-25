package pl.lotto.winningnumbergenerator;

import java.util.List;

public class WinningNumbersRepositoryForTest implements WinningNumbersRepository{
    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        return winningNumbers;
    }

    @Override
    public List<WinningNumbers> findAll() {
        return null;
    }
}
