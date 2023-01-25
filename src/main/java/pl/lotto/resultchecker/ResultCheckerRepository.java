package pl.lotto.resultchecker;

import java.util.List;

interface ResultCheckerRepository {
    CheckedTicket save(CheckedTicket ticket);

    List<CheckedTicket> findAll();
}
