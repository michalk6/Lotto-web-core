package pl.lotto.numberreceiver;

import java.util.List;

public record InputNumbersDto(List<String> messages,
                              TicketDto ticket) {
}
