package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

public class ResultCheckerFacade {
    private WinningNumberGeneratorFacade numberGenerator;
    private NumberReceiverFacade numberReceiver;
    private ResultComparator comparator;
    private ResultCheckerRepository repository;

    public ResultCheckerFacade(WinningNumberGeneratorFacade numberGenerator, NumberReceiverFacade numberReceiver, ResultComparator comparator, ResultCheckerRepository repository) {
        this.numberGenerator = numberGenerator;
        this.numberReceiver = numberReceiver;
        this.comparator = comparator;
        this.repository = repository;
    }

    CheckedTicketDto checkWinner(String lotteryId) {
        return repository.findAll().stream()
                .filter(checkedTicketDto -> checkedTicketDto.checkById(lotteryId))
                .findFirst()
                .get();
    }
}
