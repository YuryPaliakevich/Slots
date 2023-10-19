package by.slots.domain.slot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SlotWinningValue {
    
    private int lowerBound;
    private int upperBound;
    private double winAmount;
    
}
