package by.slots.domain.math.probabilities;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class SingleCombinationProbabilitiesPerReelResult {

    private String combinationName;
    private int hits;
    private double payment;
    private double probability;
    private double totalPayments;

    private BigInteger cycle;
    private List<Long> s_metric;

}
