package by.slots.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpinResponse {

    private int[] resultCombination;
    private int[] winningPlaces;
    private double winAmount;

}
