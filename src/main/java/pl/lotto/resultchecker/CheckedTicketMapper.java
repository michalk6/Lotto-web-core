package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.CheckedTicketDto;

import java.util.List;
import java.util.stream.Collectors;

class CheckedTicketMapper {

    public static List<CheckedTicketDto> mapListToDto(List<CheckedTicket> tickets) {
        return tickets.stream()
                .map(CheckedTicketMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static CheckedTicketDto mapToDto(CheckedTicket ticket) {
        return new CheckedTicketDto(ticket.getDrawDate(), ticket.getLotteryId(), ticket.getUserNumbers(), ticket.getResult());
    }
}
