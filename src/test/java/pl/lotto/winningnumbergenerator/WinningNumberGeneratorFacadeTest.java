package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumberGeneratorFacadeTest {
    @Test
    public void drawWinningNumbers_shouldReturnCollectionOfSixUniqueNumbers() {
        // given
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        WinningNumbersRepositoryForTest repository = new WinningNumbersRepositoryForTest();
        WinningNumberGeneratorFacade winningNumberGeneratorFacade = new WinningNumberGeneratorFacade(winningNumberGenerator, repository);
        // when
        Set<Integer> numbersDrawn = winningNumberGeneratorFacade.drawWinningNumbers().numbers();
        // then
        assertThat(numbersDrawn.size()).isEqualTo(6);
    }
}