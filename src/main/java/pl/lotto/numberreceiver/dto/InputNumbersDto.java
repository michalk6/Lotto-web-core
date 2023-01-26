package pl.lotto.numberreceiver.dto;

import java.util.List;

public record InputNumbersDto(List<String> messages,
                              TicketDto ticket) {
}
