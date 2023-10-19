package by.slots.service.slots;

import by.slots.domain.spin.SpinResult;
import by.slots.domain.dto.SpinResponse;

public interface SpinMapperService {

    SpinResponse map(SpinResult spinResult);

}
