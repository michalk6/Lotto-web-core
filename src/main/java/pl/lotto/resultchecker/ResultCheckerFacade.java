package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.winningnumbergenerator.WinningNumberGeneratorFacade;

public class ResultCheckerFacade {
    private WinningNumberGeneratorFacade numberGenerator;
    private NumberReceiverFacade numberReceiver;
    private NumbersComparator numbersComparator;

    public ResultCheckerFacade(WinningNumberGeneratorFacade numberGenerator, NumberReceiverFacade numberReceiver, NumbersComparator numbersComparator) {
        this.numberGenerator = numberGenerator;
        this.numberReceiver = numberReceiver;
        this.numbersComparator = numbersComparator;
    }

}
