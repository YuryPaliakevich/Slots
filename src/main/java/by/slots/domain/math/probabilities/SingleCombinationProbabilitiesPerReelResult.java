package by.slots.domain.math.probabilities;

import java.math.BigInteger;

import by.slots.domain.slot.SlotType;
import lombok.Data;

@Data
public class SingleCombinationProbabilitiesPerReelResult {

    private SlotType slotType;
    private int numberOfOccurrences;
    private int hits;
    private double payment;
    private double probability;
    private double totalPayments;
    private BigInteger amountOfPossibleCombinations;

}
