package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {
    NumberReceiverRepository repository = new NumberReceiverRepositoryInMemory();
    LocalDateTime now = LocalDateTime.now();

    @Test
    public void inputNumbers_shouldReturnSuccessMessage_andDrawDate_andLotteryId_whenUserEnteredSixCorrectNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> result = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6)).messages();
        // then
        assertThat(result).isEqualTo(List.of("success"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsEveryErrorMessage_whenUserEnteredNumbersOutOfBound_andDuplicatedNumbers_andWrongAmountOfNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 1, 1000)).messages();
        // then
        assertThat(messages).contains("Wrong amount of numbers have been given");
        assertThat(messages).contains("Numbers out of bound have been given");
        assertThat(messages).contains("Duplicated numbers have been given");
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyWrongAmountOfNumbersMessage_whenUserEnteredToMuchNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6, 7)).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Wrong amount of numbers have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyWrongAmountOfNumbersMessage_whenUserEnteredNotEnoughNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5)).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Wrong amount of numbers have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyNumbersOutOfBoundMessage_whenUserEnteredNumbersOutOfBound() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1000, 1, 2, 3, 4, 5)).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Numbers out of bound have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyDuplicatedNumbersMessage_whenUserEnteredDuplicatedNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(now, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(List.of(1, 1, 2, 3, 4, 5)).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Duplicated numbers have been given"));
    }

    @Test
    @DisplayName("should return next saturday draw date when user played on friday")
    public void inputNumbers_shouldReturnNextSaturdayDrawDate_whenUserPlayedOnFriday() {
        // given
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 12, 11, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(friday, repository);
        // when
        LocalDateTime nextDrawDate = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6)).ticket().drawDate();
        // then
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, Month.JANUARY, 14, 12, 0);
        assertThat(nextDrawDate).isEqualTo(expectedDateTime);
    }

    @Test
    public void retrieveNumbersForNextDrawDate_shouldReturnListOfAllTicketsForNextDraw_whenItsFriday() {
        // given
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 12, 11, 0);
        LocalDateTime expectedDrawDate = LocalDateTime.of(2023, Month.JANUARY, 14, 12, 0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(friday, repository);
        NumberReceiverFacade numberReceiverFacade2 = new NumberReceiverConfiguration().createForTest(friday.plusDays(1), repository);
        NumberReceiverFacade numberReceiverFacade3 = new NumberReceiverConfiguration().createForTest(friday.plusDays(2), repository);
        InputNumbersDto inputNumbersDto1 = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 7));
        InputNumbersDto inputNumbersDto2 = numberReceiverFacade.inputNumbers(List.of(11, 22, 33, 44, 55, 76));
        InputNumbersDto inputNumbersDto3 = numberReceiverFacade2.inputNumbers(List.of(1, 33, 12, 11, 5, 99));
        InputNumbersDto inputNumbersDto4 = numberReceiverFacade3.inputNumbers(List.of(1, 33, 12, 11, 5, 99));
        //when
        AllNumbersDto allNumbersDto = numberReceiverFacade.retrieveNumbersForCurrentDrawDate();
        //then
        AllNumbersDto expected = new AllNumbersDto(List.of(
                (inputNumbersDto1.ticket()),
                (inputNumbersDto2.ticket()),
                (inputNumbersDto3.ticket())
        ));
        Set<TicketDto> allNumbersDtoSet = new HashSet<>(allNumbersDto.tickets());
        Set<TicketDto> expectedSet = new HashSet<>(expected.tickets());
        assertThat(allNumbersDtoSet).isEqualTo(expectedSet);
    }
}
