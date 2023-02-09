package pl.lotto.numberreceiver.dto;

import java.util.Collection;

public record InputNumbersRequestDto(Collection<Integer> userNumbers) {
}
