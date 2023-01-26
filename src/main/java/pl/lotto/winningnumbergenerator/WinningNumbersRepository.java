package pl.lotto.winningnumbergenerator;

import java.util.List;

interface WinningNumbersRepository {
    WinningNumbers save(WinningNumbers winningNumbers);

    List<WinningNumbers> findAll();
}
