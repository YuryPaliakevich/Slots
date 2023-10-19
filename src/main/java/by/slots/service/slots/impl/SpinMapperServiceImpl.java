package by.slots.service.slots.impl;

import org.springframework.stereotype.Service;

import by.slots.domain.spin.SpinResult;
import by.slots.domain.dto.SpinResponse;
import by.slots.service.slots.SpinMapperService;

@Service
public class SpinMapperServiceImpl implements SpinMapperService {

    @Override
    public SpinResponse map(SpinResult spinResult) {
        return new SpinResponse(spinResult.getResultCombination(), spinResult.getWinningPlaces(), spinResult.getWinAmount());
    }

}
