package by.slots.domain.slot.reel;

import by.slots.domain.slot.SlotType;
import lombok.Data;

@Data
public class ReelsStop {

    private SlotType stop;

    // Determines probability chance
    private Double weight = 1.0;

}
