package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    public void inputNumbers_shouldReturnSuccessMessage_andDrawDate_andLotteryId_whenUserEnteredSixCorrectNumbers() {
        // given
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(validator, nextDrawScheduler);
        // when
        List<String> result = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6)).messages();
        // then
        assertThat(result).isEqualTo(List.of("success"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsEveryErrorMessage_whenUserEnteredNumbersOutOfBound_andDuplicatedNumbers_andWrongAmountOfNumbers() {
        // given
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(validator, nextDrawScheduler);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 1, 1000)).messages();
        System.out.println(messages);
        // then
        assertThat(messages).contains("Wrong amount of numbers have been given");
        assertThat(messages).contains("Numbers out of bound have been given");
        assertThat(messages).contains("Duplicated numbers have been given");
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyWrongAmountOfNumbersMessage_whenUserEnteredWrongAmountOfNumbers() {
        // given
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(validator, nextDrawScheduler);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1,2,3,4,5)).messages();
        System.out.println(messages);
        // then
        assertThat(messages).isEqualTo(List.of("Wrong amount of numbers have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyNumbersOutOfBoundMessage_whenUserEnteredNumbersOutOfBound() {
        // given
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(validator, nextDrawScheduler);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1000, 1, 2,3,4,5)).messages();
        System.out.println(messages);
        // then
        assertThat(messages).isEqualTo(List.of("Numbers out of bound have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyDuplicatedNumbersMessage_whenUserEnteredDuplicatedNumbers() {
        // given
        UserNumberValidator validator = new UserNumberValidator();
        NextDrawScheduler nextDrawScheduler = new NextDrawScheduler();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(validator, nextDrawScheduler);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 1, 2,3,4,5)).messages();
        System.out.println(messages);
        // then
        assertThat(messages).isEqualTo(List.of("Duplicated numbers have been given"));
    }
}
