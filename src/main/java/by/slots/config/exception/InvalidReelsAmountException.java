package by.slots.config.exception;

import by.slots.config.SlotsConfiguration;

public class InvalidReelsAmountException extends RuntimeException {

    public InvalidReelsAmountException(Integer reelsAmount) {
        super(String.format("Expected reels amount, %s, actual reels amount: %s", SlotsConfiguration.REELS_AMOUNT, reelsAmount));
    }
}
