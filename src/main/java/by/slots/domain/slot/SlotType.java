package by.slots.domain.slot;

public enum SlotType {

    CHERRY, LEMON, ORANGE, PLUM, BANANA, MELON, WATERMELON;

    public static SlotType fromNumber(int number) {
        return switch (number) {
            case 0 -> SlotType.CHERRY;
            case 1 -> SlotType.LEMON;
            case 2 -> SlotType.ORANGE;
            case 3 -> SlotType.PLUM;
            case 4 -> SlotType.BANANA;
            case 5 -> SlotType.MELON;
            case 6 -> SlotType.WATERMELON;
            default -> null;
        };
    }

}
