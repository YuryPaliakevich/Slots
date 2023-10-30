package by.slots.domain.math;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SlotAnalyzerResult {

    // Matrix which is consisted of 0 and 1 to point where winning combination occurred
    private int[] winningCombination;

    private double winingAmount;

}
