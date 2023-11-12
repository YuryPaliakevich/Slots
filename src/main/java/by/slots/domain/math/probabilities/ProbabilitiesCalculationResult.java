package by.slots.domain.math.probabilities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProbabilitiesCalculationResult {

    private List<SingleCombinationProbabilitiesPerReelResult> singleCombinationProbabilitiesPerReelResults = new ArrayList<>();
    private BigInteger cycle;
    private List<Long> s_metric;

}
