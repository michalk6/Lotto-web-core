package pl.lotto.winningnumbergenerator;

import java.util.Set;

public class WinningNumberGeneratorFacade {
    private final WinningNumberProvider winningNumberGenerator;

    public WinningNumberGeneratorFacade(WinningNumberProvider winningNumberGenerator) {
        this.winningNumberGenerator = winningNumberGenerator;
    }

    public Set<Integer> drawWinningNumbers() {
        return winningNumberGenerator.drawWinningNumbers();
    }
}
