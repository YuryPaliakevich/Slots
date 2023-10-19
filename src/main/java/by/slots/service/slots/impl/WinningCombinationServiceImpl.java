package by.slots.service.slots.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.exception.InvalidWinningNumberException;
import by.slots.domain.slot.SlotType;
import by.slots.domain.slot.SlotWinningValue;
import by.slots.domain.slot.SlotsCompleteOverview;
import by.slots.service.slots.WinningCombinationService;

@Service
public class WinningCombinationServiceImpl implements WinningCombinationService {

    @Autowired
    private SlotsCompleteOverview slotsCompleteOverview;

    @Override
    public Double getWinningAmount(SlotType slotType, int numberOfPositions) {
        return calculateWinningAmount(slotType, numberOfPositions);
    }

    private Double calculateWinningAmount(SlotType type, int numberOfPositions) {
        return slotsCompleteOverview.getDescriptions().stream()
                .filter(slotDescription -> slotDescription.getSlotType().equals(type))
                .map(slotDescription -> {
                    Optional<SlotWinningValue> first = slotDescription.getWinningValues().stream()
                            .filter(slotWinningValue -> slotWinningValue.getLowerBound() <= numberOfPositions && slotWinningValue.getUpperBound() >= numberOfPositions).findFirst();
                    return first.map(SlotWinningValue::getWinAmount).orElse(0.0);
                }).findAny().orElseThrow(InvalidWinningNumberException::new);
    }
}
