package by.slots.service.slots;

import by.slots.domain.slot.SlotType;

public interface WinningCombinationService {

    Double getWinningAmount(SlotType slotType, int numberOfPositions);

}
