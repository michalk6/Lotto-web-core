package pl.lotto.infrastructure.controller.resulultanouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.ResultDto;

@RestController
@AllArgsConstructor
public class ResultAnnouncerRestController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    @GetMapping("/checkWinner/{id}")
    public ResponseEntity<ResultDto> checkWinner(@PathVariable String id) {
        ResultDto resultDto = resultAnnouncerFacade.checkWinner(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
    }
}
