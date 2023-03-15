package pl.lotto.winningnumbergenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.NumberReceiverFacade;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class WinningNumberGeneratorFacadeTest {
    @Test
    public void drawWinningNumbers_shouldReturnCollectionOfSixUniqueNumbers() {
        // given
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        WinningNumbersRepositoryInMemory repository = new WinningNumbersRepositoryInMemory();
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        WinningNumberGeneratorFacade winningNumberGeneratorFacade = new WinningNumberGeneratorFacade(winningNumberGenerator, numberReceiverFacade, repository);
        // when
        Set<Integer> numbersDrawn = winningNumberGeneratorFacade.drawWinningNumbers().numbers();
        // then
        assertThat(numbersDrawn.size()).isEqualTo(6);
    }
}