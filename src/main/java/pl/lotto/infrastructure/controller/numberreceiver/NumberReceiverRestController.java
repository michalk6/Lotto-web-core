package pl.lotto.infrastructure.controller.numberreceiver;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.numberreceiver.dto.InputNumbersRequestDto;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class NumberReceiverRestController {
    final NumberReceiverFacade numberReceiverFacade;


    @PostMapping("/inputNumbers")
    ResponseEntity<InputNumbersDto> inputNumbers(@RequestBody InputNumbersRequestDto request) {
        return ResponseEntity.ok(numberReceiverFacade.inputNumbers(request.userNumbers()));
    }
}
