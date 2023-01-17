package pl.lotto.resultchecker;

import java.util.List;

public record AllCheckedTicketsDto(List<CheckedTicketDto> checkedTickets) {
}
