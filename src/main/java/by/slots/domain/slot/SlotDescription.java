package by.slots.domain.slot;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlotDescription {

    private SlotType slotType;
    private List<SlotWinningValue> winningValues;

}
