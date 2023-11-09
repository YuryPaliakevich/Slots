package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.SLOTS_REELS_AMOUNT;
import static by.slots.config.SlotsConfiguration.SLOTS_ROW_SIZE;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.config.SlotsConfiguration;
import by.slots.domain.math.SlotMatrixGenerationResult;
import by.slots.service.math.SlotMatrixAnalyzerService;
import by.slots.service.math.SlotMatrixService;

@Service
public class SlotMatrixServiceImpl implements SlotMatrixService {

    @Autowired
    private SlotMatrixAnalyzerService analyzerService;

    private volatile int[][] currentState = new int[SLOTS_ROW_SIZE][SLOTS_REELS_AMOUNT];

    @Override
    public SlotMatrixGenerationResult generate() {
        final int[][] previousWinningMatrix = analyzerService.getCurrentWinningState();
        generateMatrix(previousWinningMatrix);
        return new SlotMatrixGenerationResult(currentState);
    }

    private void generateMatrix(int[][] previousWinningMatrix) {

        // Check if there was some winning combination to replace only these values
        if (matrixContainsWinningCombination(previousWinningMatrix)) {
            updateExistingMatrix(previousWinningMatrix);
        } else {
            this.currentState = generateNewMatrix();
        }
    }


    private boolean matrixContainsWinningCombination(int[][] previousWinningMatrix) {
        return Arrays.stream(previousWinningMatrix)
                .flatMap(array -> Arrays.stream(array).boxed())
                .anyMatch(value -> value == 1);
    }

    private void updateExistingMatrix(int[][] previousWinningMatrix) {
        for (int i = 0; i < previousWinningMatrix.length; i++) {
            for (int j = 0; j < previousWinningMatrix[i].length; j++) {
                if (previousWinningMatrix[i][j] == 1) {
                    this.currentState[i][j] = generateRandom();
                }
            }
        }
    }

    private int[][] generateNewMatrix() {
        int[][] matrix = new int[SLOTS_ROW_SIZE][SLOTS_REELS_AMOUNT];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = generateRandom();
            }
        }

        return matrix;
    }

    private int generateRandom() {
        return new Random().nextInt(SlotsConfiguration.SLOT_TYPE_AMOUNT);
    }
}
