package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

class TicketMapper {

    public static List<TicketDto> mapListToDto(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static TicketDto mapToDto(Ticket ticket) {
        return new TicketDto(ticket.drawDate(), ticket.lotteryId(), ticket.userNumbers());
    }
}
