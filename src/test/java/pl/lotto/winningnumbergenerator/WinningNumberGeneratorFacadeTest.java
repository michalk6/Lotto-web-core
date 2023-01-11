package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumberGeneratorFacadeTest {
    @Test
    public void drawWinningNumbers_shouldReturnCollectionOfSixUniqueNumbers() {
        // given
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        WinningNumberGeneratorFacade winningNumberGeneratorFacade = new WinningNumberGeneratorFacade(winningNumberGenerator);
        // when
        Set<Integer> numbersDrawn = winningNumberGeneratorFacade.drawWinningNumbers();
        // then
        assertThat(numbersDrawn.size()).isEqualTo(6);
    }
}