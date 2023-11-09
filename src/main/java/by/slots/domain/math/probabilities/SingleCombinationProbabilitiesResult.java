package by.slots.domain.math.probabilities;

import lombok.Data;

@Data
public class SingleCombinationProbabilitiesResult {

    private String combinationName;
    private int hits;
    private double payment;
    private double probability;
    private double totalPayments;

}
