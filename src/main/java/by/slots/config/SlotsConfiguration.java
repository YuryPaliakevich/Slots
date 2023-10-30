package by.slots.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.slots.domain.slot.SlotDescription;
import by.slots.domain.slot.SlotType;
import by.slots.domain.slot.SlotWinningValue;
import by.slots.domain.slot.SlotsCompleteOverview;


// TODO: Move to yml config file
@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "by.slots.config.properties")
public class SlotsConfiguration {

    public static final int SLOT_TYPE_AMOUNT = 7;

    public static final int REELS_AMOUNT = 30;

    @Bean
    public SlotsCompleteOverview slotsCompleteOverview() {

        final SlotsCompleteOverview slotsCompleteOverview = new SlotsCompleteOverview();

        slotsCompleteOverview.getDescriptions().add(createCherryDescription());
        slotsCompleteOverview.getDescriptions().add(createWatermelonDescription());
        slotsCompleteOverview.getDescriptions().add(createMelonDescription());
        slotsCompleteOverview.getDescriptions().add(createBananaDescription());
        slotsCompleteOverview.getDescriptions().add(createPlumDescription());
        slotsCompleteOverview.getDescriptions().add(createOrangeDescription());
        slotsCompleteOverview.getDescriptions().add(createLemonDescription());

        return slotsCompleteOverview;
    }

    private SlotDescription createCherryDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 200));
        winningValues.add(new SlotWinningValue(10, 11, 100));
        winningValues.add(new SlotWinningValue(8, 9, 40));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.CHERRY)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createWatermelonDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 100));
        winningValues.add(new SlotWinningValue(10, 11, 40));
        winningValues.add(new SlotWinningValue(8, 9, 10));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.WATERMELON)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createMelonDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 60));
        winningValues.add(new SlotWinningValue(10, 11, 20));
        winningValues.add(new SlotWinningValue(8, 9, 8));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.MELON)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createBananaDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 48));
        winningValues.add(new SlotWinningValue(10, 11, 8));
        winningValues.add(new SlotWinningValue(8, 9, 6));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.BANANA)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createPlumDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 40));
        winningValues.add(new SlotWinningValue(10, 11, 6));
        winningValues.add(new SlotWinningValue(8, 9, 4));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.PLUM)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createOrangeDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 32));
        winningValues.add(new SlotWinningValue(10, 11, 4.80));
        winningValues.add(new SlotWinningValue(8, 9, 3.20));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.ORANGE)
                .winningValues(winningValues)
                .build();
    }

    private SlotDescription createLemonDescription() {
        final List<SlotWinningValue> winningValues = new ArrayList<>();
        winningValues.add(new SlotWinningValue(12, 30, 20));
        winningValues.add(new SlotWinningValue(10, 11, 4));
        winningValues.add(new SlotWinningValue(8, 9, 2));
        winningValues.add(new SlotWinningValue(0, 7, 0));

        return SlotDescription.builder()
                .slotType(SlotType.LEMON)
                .winningValues(winningValues)
                .build();
    }

}
