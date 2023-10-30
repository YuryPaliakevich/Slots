package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.REELS_AMOUNT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.domain.math.SlotAnalyzerResult;
import by.slots.domain.slot.SlotPosition;
import by.slots.domain.slot.SlotType;
import by.slots.service.math.SlotMatrixAnalyzerService;
import by.slots.service.slots.WinningCombinationService;

@Service
public class SlotMatrixAnalyzerServiceImpl implements SlotMatrixAnalyzerService {

    @Autowired
    private WinningCombinationService winningCombinationService;

    private int[] winningMatrix = new int[REELS_AMOUNT];

    @Override
    public SlotAnalyzerResult analyzeWinningCombinations(int [] matrix) {
        final Map<SlotType, List<SlotPosition>> slotPositioning = new HashMap<>();
        winningMatrix = new int[REELS_AMOUNT];

        IntStream.range(0, matrix.length).forEach(i -> {
            final SlotType type = SlotType.fromNumber(matrix[i]);
            if (!slotPositioning.containsKey(type)) {
                slotPositioning.put(type, new ArrayList<>());
            }
            slotPositioning.get(type).add(new SlotPosition(i));
        });

        double totalWin = 0;

        for (Map.Entry<SlotType, List<SlotPosition>> entry : slotPositioning.entrySet()) {
            final Double winningAmount = winningCombinationService.getWinningAmount(entry.getKey(), entry.getValue().size());
            if (winningAmount > 0) {
                entry.getValue().forEach(slotPosition -> winningMatrix[slotPosition.getI()] = 1);
            }
            totalWin += winningAmount;
        }

        return new SlotAnalyzerResult(winningMatrix, totalWin);
    }

    @Override
    public int[] getCurrentWinningState() {
        return winningMatrix;
    }

}
