package by.slots.service.math.probabilities.impl;

import static by.slots.config.SlotsConfiguration.SLOTS_REELS_AMOUNT;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.math.BigIntegerMath;

import by.slots.config.properties.SlotsDistributionConfigurationProperties;
import by.slots.domain.math.probabilities.ProbabilitiesCalculationResult;
import by.slots.domain.math.probabilities.SingleCombinationProbabilitiesPerReelResult;
import by.slots.domain.slot.ReelDistribution;
import by.slots.domain.slot.SlotDistributionDescription;
import by.slots.domain.slot.SlotType;
import by.slots.service.math.probabilities.SlotsProbabilitiesService;

@Service
public class SlotsProbabilitiesServiceImpl implements SlotsProbabilitiesService {

    @Autowired
    private SlotsDistributionConfigurationProperties distribution;

    // The number of winning occurrences varies from 8 to 25
    // So we need to calculate probabilities for each possible value

    @Override
    public ProbabilitiesCalculationResult getResults() {
        final ProbabilitiesCalculationResult result = new ProbabilitiesCalculationResult();
        final List<Long> s_metric = generateS_metric();
        // CL = П Sj, sj - amount of stops per reel for corresponding slot type
        final BigInteger cycle = generateCycle(s_metric);
        result.setCycle(cycle);
        result.setS_metric(s_metric);

        final BigInteger n_factorial = BigIntegerMath.factorial(SLOTS_REELS_AMOUNT);

        for (int i = 8; i <= SLOTS_REELS_AMOUNT; i++) {
            final List<SingleCombinationProbabilitiesPerReelResult> singleCombinationProbabilitiesResult = calculateEachSlotTypeProbability(n_factorial, i);
            result.getSingleCombinationProbabilitiesPerReelResults().addAll(singleCombinationProbabilitiesResult);
        }

        return result;
    }

    private List<SingleCombinationProbabilitiesPerReelResult> calculateEachSlotTypeProbability(BigInteger n_factorial, int amountOfOccurrences) {
        final BigInteger amountOfCombinations = getAmountOfCombinations(n_factorial, amountOfOccurrences);
        final List<SingleCombinationProbabilitiesPerReelResult> results = new ArrayList<>();

        for (SlotType slotType: SlotType.values()) {
            final SingleCombinationProbabilitiesPerReelResult result = new SingleCombinationProbabilitiesPerReelResult();
            result.setSlotType(slotType);
            result.setNumberOfOccurrences(amountOfOccurrences);
            final int amountOfCombinationsLong = amountOfCombinations.intValue();

            List<Double> eachCombinationProbability = calculateAllCombinationsProbabilities(slotType, amountOfOccurrences);

            for (int i = 0; i < amountOfCombinationsLong; i++) {

            }

//            if ()

            result.setAmountOfPossibleCombinations(amountOfCombinations);
            results.add(result);
        }

        return results;
    }

    private List<Double> calculateAllCombinationsProbabilities(SlotType slotType, int amountOfOccurrences) {

    }

    // Amount of combinations formula:
    // C = n! / k! * (n - k)!
    private BigInteger getAmountOfCombinations(BigInteger n_factorial, int amountOfOccurrences) {
        final BigInteger k_factorial = BigIntegerMath.factorial(amountOfOccurrences);
        final BigInteger n_minus_k_factorial = BigIntegerMath.factorial(SLOTS_REELS_AMOUNT - amountOfOccurrences);
        return n_factorial.divide(k_factorial.multiply(n_minus_k_factorial));
    }

    private BigInteger generateCycle(List<Long> s_Metric) {
        BigInteger result = BigInteger.ONE;
        for (Long l : s_Metric) {
            result = result.multiply(BigInteger.valueOf(l));
        }
        return result;
    }

    private List<Long> generateS_metric() {
        final List<Long> result = new ArrayList<>();
        for (ReelDistribution reel : distribution.getReels()) {
            final long sum = reel.getDistribution().stream().mapToLong(SlotDistributionDescription::getAmountOfStops).sum();
            result.add(sum);
        }
        return result;
    }
}
