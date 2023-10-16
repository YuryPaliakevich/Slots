package by.slots.service.slots.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.SlotTypeConfiguration;
import by.slots.domain.slot.SlotPosition;
import by.slots.domain.slot.SlotType;
import by.slots.domain.spin.SpinResult;
import by.slots.service.slots.SpinService;
import by.slots.service.slots.WinningCombinationService;

@Service
public class SpinServiceImpl implements SpinService {

    private static final int SLOTS_MATRIX_ROW_SIZE = 5;
    private static final int SLOTS_MATRIX_COLUMN_SIZE = 5;

    @Autowired
    private WinningCombinationService winningCombinationService;

    // TODO: move to some other "state" service, make it thread-safe and method scoped
    // first integer as row, 2nd as column indexes
    private int[][] winningMatrix = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];
    private int [][] currentState = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];

    @Override
    public SpinResult spin(int stake) {
        generateMatrix();
        final double winningAmount = analyzeWinningCombinations();
        return new SpinResult(currentState, winningMatrix, winningAmount);
    }

    // returns total win
    private Double analyzeWinningCombinations() {
        final Map<SlotType, List<SlotPosition>> slotPositioning = new HashMap<>();

        for (int i = 0; i < SLOTS_MATRIX_ROW_SIZE; i++) {
            for (int j = 0; j < SLOTS_MATRIX_COLUMN_SIZE; j++) {
                final SlotType type = idToSlotType(currentState[i][j]);
                if (!slotPositioning.containsKey(type)) {
                    slotPositioning.put(type, new ArrayList<>());
                }
                slotPositioning.get(type).add(new SlotPosition(i, j));
            }
        }

        double totalWin = 0;

        for (Map.Entry<SlotType, List<SlotPosition>> entry : slotPositioning.entrySet()) {
            final Double winningAmount = winningCombinationService.getWinningAmount(entry.getKey(), entry.getValue().size());
            if (winningAmount > 0) {
                entry.getValue().forEach(slotPosition -> winningMatrix[slotPosition.getI()][slotPosition.getJ()] = 1);
            }
            totalWin += winningAmount;
        }

        return totalWin;
    }

    private SlotType idToSlotType(int value) {
        return switch (value) {
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

    private void generateMatrix() {

        // Check if there was some winning combination to replace only these values
        if (matrixContainsWinningCombination()) {
            updateExistingMatrix();
        } else {
            this.currentState = generateNewMatrix();
        }
    }


    // TODO: replace by stream API
    private boolean matrixContainsWinningCombination() {
        for (int i = 0; i < SLOTS_MATRIX_ROW_SIZE; i++) {
            for (int j = 0; j < SLOTS_MATRIX_COLUMN_SIZE; j++) {
                if (winningMatrix[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private void updateExistingMatrix() {
        for (int i = 0; i < SLOTS_MATRIX_ROW_SIZE; i++) {
            for (int j = 0; j < SLOTS_MATRIX_COLUMN_SIZE; j++) {
                if (winningMatrix[i][j] == 1) {
                    this.currentState[i][j] = generateRandom();
                    winningMatrix[i][j] = 0;
                }
            }
        }
    }

    private int[][] generateNewMatrix() {
        int[][] matrix = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_ROW_SIZE];
        for (int i = 0; i < SLOTS_MATRIX_ROW_SIZE; i++) {
            for (int j = 0; j < SLOTS_MATRIX_COLUMN_SIZE; j++) {
                matrix[i][j] = generateRandom();
            }
        }

        return matrix;
    }

    private int generateRandom() {
        return new Random().nextInt(SlotTypeConfiguration.SLOT_TYPE_AMOUNT);
    }
}
