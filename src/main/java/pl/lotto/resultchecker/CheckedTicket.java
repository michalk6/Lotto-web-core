package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;

public class CheckedTicket {
    private String lotteryId;
    private LocalDateTime drawDate;
    private Set<Integer> userNumbers;
    private GameResult result;

    public CheckedTicket(String lotteryId, LocalDateTime drawDate, Set<Integer> userNumbers, GameResult result) {
        this.lotteryId = lotteryId;
        this.drawDate = drawDate;
        this.userNumbers = userNumbers;
        this.result = result;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }

    public Set<Integer> getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(Set<Integer> userNumbers) {
        this.userNumbers = userNumbers;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }
}
