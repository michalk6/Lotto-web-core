package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

class Ticket {
    private String lotteryId;
    private LocalDateTime drawDate;
    private Set<Integer> userNumbers;

    public Ticket(String lotteryId, LocalDateTime drawDate, Set<Integer> userNumbers) {
        this.lotteryId = lotteryId;
        this.drawDate = drawDate;
        this.userNumbers = userNumbers;
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
}
