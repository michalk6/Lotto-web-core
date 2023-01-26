package pl.lotto.resultchecker.dto;

import java.util.List;

public record AllCheckedTicketsDto(List<CheckedTicketDto> checkedTickets) {
}
