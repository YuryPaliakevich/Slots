package by.slots.service.slots;

import by.slots.domain.SlotsResults;
import by.slots.domain.dto.SpinResponse;

public interface SlotsMapperService {

    SpinResponse map(SlotsResults slotsResults);

}
