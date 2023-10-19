package by.slots.domain.slot;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SlotsCompleteOverview {

    private List<SlotDescription> descriptions = new ArrayList<>();

}
