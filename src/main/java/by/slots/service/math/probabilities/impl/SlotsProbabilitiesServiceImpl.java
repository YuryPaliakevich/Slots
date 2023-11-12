package by.slots.service.math.probabilities.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.properties.SlotsDistributionConfigurationProperties;
import by.slots.domain.math.probabilities.SingleCombinationProbabilitiesPerReelResult;
import by.slots.domain.slot.ReelDistribution;
import by.slots.domain.slot.SlotDistributionDescription;
import by.slots.service.math.probabilities.SlotsProbabilitiesService;

@Service
public class SlotsProbabilitiesServiceImpl implements SlotsProbabilitiesService {

    @Autowired
    private SlotsDistributionConfigurationProperties distribution;

    @Override
    public List<SingleCombinationProbabilitiesPerReelResult> getResults() {
        final List<SingleCombinationProbabilitiesPerReelResult> result = new ArrayList<>();

        for (final ReelDistribution reel : distribution.getReels()) {
            final SingleCombinationProbabilitiesPerReelResult singleCombinationProbabilitiesResult = new SingleCombinationProbabilitiesPerReelResult();
            final List<Long> s_metric = generateS_metric();
            // CL = П Sj, sj - amount of stops per reel for corresponding slot type
            final BigInteger cycle = generateCycle(s_metric);
            singleCombinationProbabilitiesResult.setCycle(cycle);
            singleCombinationProbabilitiesResult.setS_metric(s_metric);

            result.add(singleCombinationProbabilitiesResult);
        }

        return result;
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
