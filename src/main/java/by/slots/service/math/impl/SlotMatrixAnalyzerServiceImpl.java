package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.SLOTS_MATRIX_COLUMN_SIZE;
import static by.slots.config.SlotsConfiguration.SLOTS_MATRIX_ROW_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.domain.math.SlotMatrixAnalyzerResult;
import by.slots.domain.slot.SlotPosition;
import by.slots.domain.slot.SlotType;
import by.slots.service.math.SlotMatrixAnalyzerService;
import by.slots.service.slots.WinningCombinationService;

@Service
public class SlotMatrixAnalyzerServiceImpl implements SlotMatrixAnalyzerService {

    @Autowired
    private WinningCombinationService winningCombinationService;

    private int[][] winningMatrix = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];

    @Override
    public SlotMatrixAnalyzerResult analyzeWinningCombinations(int [][] matrix) {
        final Map<SlotType, List<SlotPosition>> slotPositioning = new HashMap<>();
        winningMatrix = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                final SlotType type = idToSlotType(matrix[i][j]);
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

        return new SlotMatrixAnalyzerResult(winningMatrix, totalWin);
    }

    @Override
    public int[][] getCurrentWinningState() {
        return winningMatrix;
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

}
