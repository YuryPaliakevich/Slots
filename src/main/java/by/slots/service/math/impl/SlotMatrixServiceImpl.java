package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.SLOTS_REELS_AMOUNT;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.SlotsConfiguration;
import by.slots.domain.math.SlotGenerationResult;
import by.slots.service.math.SlotMatrixAnalyzerService;
import by.slots.service.math.SlotMatrixService;

@Service
public class SlotMatrixServiceImpl implements SlotMatrixService {

    @Autowired
    private SlotMatrixAnalyzerService analyzerService;

    private volatile int[] currentState = new int[SLOTS_REELS_AMOUNT];

    @Override
    public SlotGenerationResult generate() {
        final int[] previousWinningMatrix = analyzerService.getCurrentWinningState();
        generateMatrix(previousWinningMatrix);
        return new SlotGenerationResult(currentState);
    }

    private void generateMatrix(int[] previousWinningMatrix) {

        // Check if there was some winning combination to replace only these values
        if (matrixContainsWinningCombination(previousWinningMatrix)) {
            updateExistingMatrix(previousWinningMatrix);
        } else {
            this.currentState = generateNewMatrix();
        }
    }

    private boolean matrixContainsWinningCombination(int[] previousWinningMatrix) {
        return Arrays.stream(previousWinningMatrix).boxed().anyMatch(value -> value == 1);
    }

    private void updateExistingMatrix(int[] previousWinningMatrix) {
        for (int i = 0; i < previousWinningMatrix.length; i++) {
            if (previousWinningMatrix[i] == 1) {
                this.currentState[i] = generateRandom();
            }
        }
    }

    private int[] generateNewMatrix() {
        int[] matrix = new int[SLOTS_REELS_AMOUNT];
        Arrays.setAll(matrix, i -> generateRandom());
        return matrix;
    }

    private int generateRandom() {
        return new Random().nextInt(SlotsConfiguration.SLOT_TYPE_AMOUNT);
    }
}
