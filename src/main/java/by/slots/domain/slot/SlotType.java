package by.slots.domain.slot;

import lombok.Getter;

@Getter
public enum SlotType {

    CHERRY(0), LEMON(1), ORANGE(2), PLUM(3), BANANA(4), MELON(5), WATERMELON(6);

    private final int id;

    SlotType(int id) {
        this.id = id;
    }

}
