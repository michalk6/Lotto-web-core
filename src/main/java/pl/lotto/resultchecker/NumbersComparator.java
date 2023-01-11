package pl.lotto.resultchecker;

import java.util.Set;

class NumbersComparator {
    boolean compareNumberSets(Set<Integer> winningNumbers, Set<Integer> userNumbers) {
        return userNumbers.equals(winningNumbers);
    }
}
