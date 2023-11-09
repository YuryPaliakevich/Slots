package by.slots.config.properties;

import static by.slots.config.SlotsConfiguration.SLOTS_REELS_AMOUNT;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import by.slots.config.exception.InvalidDistributionSettingsException;
import by.slots.domain.slot.ReelDistribution;
import jakarta.annotation.PostConstruct;
import lombok.Data;

@ConfigurationProperties(prefix = "slots")
@Data
public class SlotsDistributionConfigurationProperties {


    private List<ReelDistribution> reels;

    @PostConstruct
    public void init() {
        if (reels.size() != SLOTS_REELS_AMOUNT) {
            final String message = String.format("Amount of reels: %s, amount of distributions: %s", SLOTS_REELS_AMOUNT, reels.size());
            throw new InvalidDistributionSettingsException(message);
        }
    }

}
