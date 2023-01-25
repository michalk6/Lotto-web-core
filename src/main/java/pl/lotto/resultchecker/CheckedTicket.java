package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;

class CheckedTicket {
    private String lotteryId;
    private LocalDateTime drawDate;
    private Set<Integer> userNumbers;
    private GameResult result;

    CheckedTicket(String lotteryId, LocalDateTime drawDate, Set<Integer> userNumbers, GameResult result) {
        this.lotteryId = lotteryId;
        this.drawDate = drawDate;
        this.userNumbers = userNumbers;
        this.result = result;
    }

    String getLotteryId() {
        return lotteryId;
    }

    void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    LocalDateTime getDrawDate() {
        return drawDate;
    }

    void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }

    Set<Integer> getUserNumbers() {
        return userNumbers;
    }

    void setUserNumbers(Set<Integer> userNumbers) {
        this.userNumbers = userNumbers;
    }

    GameResult getResult() {
        return result;
    }

    void setResult(GameResult result) {
        this.result = result;
    }
}
