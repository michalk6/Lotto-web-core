package pl.lotto.winningnumbergenerator;

import org.springframework.stereotype.Component;
import pl.lotto.gamerules.LottoRules;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
class WinningNumberGenerator implements WinningNumberProvider {
    private static final int NUMBER_OF_NUMBERS = LottoRules.NUMBER_OF_NUMBERS;
    private static final int MAX_NUMBER = LottoRules.MAX_NUMBER;
    private final Random random = new Random();

    public WinningNumberGenerator() {
    }

    @Override
    public Set<Integer> drawWinningNumbers() {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < NUMBER_OF_NUMBERS) {
            generatedNumbers.add(drawNumber());
        }
        return generatedNumbers;
    }

    private int drawNumber() {
        return random.nextInt(MAX_NUMBER) + 1;
    }
}
