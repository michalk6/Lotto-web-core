package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumberGeneratorFacadeTest {
    @Test
    public void drawWinningNumbers_shouldReturnCollectionOfSixUniqueNumbers() {
        // given
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        WinningNumberGeneratorFacade winningNumberGeneratorFacade = new WinningNumberGeneratorFacade(winningNumberGenerator);
        // when
        Collection<Integer> numbersDrawn = winningNumberGeneratorFacade.drawWinningNumbers();
        // then
        Set<Integer> collect = new HashSet<>(numbersDrawn);

        assertThat(numbersDrawn).isEqualTo(collect);
        assertThat(numbersDrawn.size()).isEqualTo(6);
    }
}