package pl.lotto.numberreceiver;

import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    public void f() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        // when
        String result = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6)).message();
        // then
        assertThat(result).isEqualTo("success");
    }
}
