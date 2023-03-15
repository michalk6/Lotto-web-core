package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.AdjustableClock;
import pl.lotto.numberreceiver.dto.AllNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersRequestDto;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberReceiverFacadeTest {
    NumberReceiverRepository repository = new NumberReceiverRepositoryInMemory();
    Clock clock = Clock.systemDefaultZone();

    @Test
    public void inputNumbers_shouldReturnSuccessMessage_andDrawDate_andLotteryId_whenUserEnteredSixCorrectNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> result = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 2, 3, 4, 5, 6))).messages();
        // then
        assertThat(result).isEqualTo(List.of("success"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsEveryErrorMessage_whenUserEnteredNumbersOutOfBound_andDuplicatedNumbers_andWrongAmountOfNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 1, 1000))).messages();
        // then
        assertThat(messages).contains("Wrong amount of numbers have been given");
        assertThat(messages).contains("Numbers out of bound have been given");
        assertThat(messages).contains("Duplicated numbers have been given");
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyWrongAmountOfNumbersMessage_whenUserEnteredToMuchNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 2, 3, 4, 5, 6, 7))).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Wrong amount of numbers have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyWrongAmountOfNumbersMessage_whenUserEnteredNotEnoughNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 2, 3, 4, 5))).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Wrong amount of numbers have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyNumbersOutOfBoundMessage_whenUserEnteredNumbersOutOfBound() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1000, 1, 2, 3, 4, 5))).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Numbers out of bound have been given"));
    }

    @Test
    public void inputNumbers_shouldReturnListOfErrorMessagesWhichContainsOnlyDuplicatedNumbersMessage_whenUserEnteredDuplicatedNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        List<String> messages = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 1, 2, 3, 4, 5))).messages();
        // then
        assertThat(messages).isEqualTo(List.of("Duplicated numbers have been given"));
    }

    @Test
    @DisplayName("should return next saturday draw date when user played on friday")
    public void inputNumbers_shouldReturnNextSaturdayDrawDate_whenUserPlayedOnFriday() {
        // given
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 12, 11, 0);
        AdjustableClock clock = new AdjustableClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        LocalDateTime nextDrawDate = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(Set.of(1, 2, 3, 4, 5, 6))).ticket().drawDate();
        // then
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, Month.JANUARY, 14, 12, 0);
        assertThat(nextDrawDate).isEqualTo(expectedDateTime);
    }

    @Test
    public void retrieveNumbersForNextDrawDate_shouldReturnListOfAllTicketsForNextDraw_whenItsFriday() {
        // given
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 10, 9, 0);
        AdjustableClock clock = new AdjustableClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        InputNumbersDto inputNumbersDto1 = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 2, 3, 4, 5, 7)));
        clock.plusDays(1);
        InputNumbersDto inputNumbersDto2 = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(11, 22, 33, 44, 55, 76)));
        clock.plusDays(4);
        InputNumbersDto inputNumbersDto3 = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 33, 13, 6, 5, 99)));
        clock.plusDays(1);
        InputNumbersDto inputNumbersDto4 = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 33, 12, 11, 5, 99)));
        //when
        AllNumbersDto allNumbersDto = numberReceiverFacade.retrieveNumbersForCurrentDrawDate();
        //then
        assertThat(allNumbersDto.tickets()).containsExactlyInAnyOrder(inputNumbersDto3.ticket(), inputNumbersDto4.ticket());
    }

    @Test
    void findByLotteryId_shouldReturnMatchingTicketDto_whenMethodIsCalledWithExistentId() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        InputNumbersDto inputNumbersDto = numberReceiverFacade.inputNumbers(new InputNumbersRequestDto(List.of(1, 2, 3, 4, 5, 6)));
        String generatedId = inputNumbersDto.ticket().lotteryId();
        //when
        //then
        assertThat(numberReceiverFacade.findByLotteryId(generatedId)).isEqualTo(inputNumbersDto.ticket());
    }

    @Test
    void findByLotteryId_shouldThrowNoSuchTicketException_whenMethodIsCalledWithNonexistentId() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        //when
        //then
        assertThatThrownBy(() -> numberReceiverFacade.findByLotteryId("nonexistent")).isInstanceOf(NoSuchTicketException.class);
        assertThatThrownBy(() -> numberReceiverFacade.findByLotteryId("nonexistent")).hasMessage("Ticket with given id not found");
    }

    @Test
    void getNextDrawDate_shouldReturnNextSaturdayDate() {
        // given
        LocalDateTime friday = LocalDateTime.of(2023, Month.JANUARY, 10, 9, 0);
        AdjustableClock clock = new AdjustableClock(friday.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().createForTest(clock, repository);
        // when
        LocalDateTime nextDrawDate = numberReceiverFacade.getNextDrawDate();
        // then
        LocalDateTime expected = LocalDateTime.of(2023, Month.JANUARY, 14, 12, 0);
        assertThat(nextDrawDate).isEqualTo(expected);
    }
}
