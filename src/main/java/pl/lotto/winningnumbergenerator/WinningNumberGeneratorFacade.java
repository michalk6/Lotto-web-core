package pl.lotto.winningnumbergenerator;

import java.util.Collection;

public class WinningNumberGeneratorFacade {
    private final WinningNumberGenerator winningNumberGenerator;

    public WinningNumberGeneratorFacade(WinningNumberGenerator winningNumberGenerator) {
        this.winningNumberGenerator = winningNumberGenerator;
    }

    public Collection<Integer> drawWinningNumbers() {
        return winningNumberGenerator.drawWinningNumbers();
    }
}
