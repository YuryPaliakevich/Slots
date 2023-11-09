package by.slots.service.math.probabilities.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.properties.SlotsDistributionConfigurationProperties;
import by.slots.domain.math.probabilities.SingleCombinationProbabilitiesResult;
import by.slots.service.math.probabilities.SlotsProbabilitiesService;

@Service
public class SlotsProbabilitiesServiceImpl implements SlotsProbabilitiesService {

    @Autowired
    private SlotsDistributionConfigurationProperties distribution;

    @Override
    public List<SingleCombinationProbabilitiesResult> getResults() {
        final List<SingleCombinationProbabilitiesResult> result = new ArrayList<>();



        return result;
    }
}
