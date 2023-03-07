package pl.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LottoGameSpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(LottoGameSpringBootApp.class, args);
    }
}
