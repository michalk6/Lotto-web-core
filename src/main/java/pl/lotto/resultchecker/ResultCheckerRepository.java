package pl.lotto.resultchecker;

import java.util.List;

interface ResultCheckerRepository {
    CheckedTicketDto save(CheckedTicketDto ticket);

    List<CheckedTicketDto> findAll();
}
