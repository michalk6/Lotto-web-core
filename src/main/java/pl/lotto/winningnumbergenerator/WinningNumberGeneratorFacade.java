package pl.lotto.winningnumbergenerator;

import java.util.Set;

public class WinningNumberGeneratorFacade {
    private final WinningNumberGenerator winningNumberGenerator;

    public WinningNumberGeneratorFacade(WinningNumberGenerator winningNumberGenerator) {
        this.winningNumberGenerator = winningNumberGenerator;
    }

    public Set<Integer> drawWinningNumbers() {
        return winningNumberGenerator.drawWinningNumbers();
    }
}
