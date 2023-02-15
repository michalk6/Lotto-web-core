package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.InputNumbersRequestDto;

import java.util.Collection;

class RequestMapper {
    public static Collection<Integer> mapRequestDtoToCollection(InputNumbersRequestDto request) {
        return request.userNumbers();
    }
}
