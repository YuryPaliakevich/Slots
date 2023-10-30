package by.slots.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import by.slots.config.SlotsConfiguration;
import by.slots.config.exception.InvalidReelsAmountException;
import by.slots.domain.slot.reel.Reel;
import jakarta.annotation.PostConstruct;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "slots")
public class SlotsConfigurationProperties {

    private final List<Reel> reels;

    @PostConstruct
    public void slotsConfigurationPostConstruct() {
        if (reels.size() != SlotsConfiguration.REELS_AMOUNT) {
            throw new InvalidReelsAmountException(reels.size());
        }
    }

}
