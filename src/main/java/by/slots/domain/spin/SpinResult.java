package by.slots.domain.spin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpinResult {

    private int[][] resultCombination;
    private int[][] winningPlaces;
    private double winAmount;

}
