package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.SLOTS_MATRIX_COLUMN_SIZE;
import static by.slots.config.SlotsConfiguration.SLOTS_MATRIX_ROW_SIZE;

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

    private int [][] currentState = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];

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


    // TODO: replace by stream API
    private boolean matrixContainsWinningCombination(int[][] previousWinningMatrix) {
        for (int[] winningMatrix : previousWinningMatrix) {
            for (int matrix : winningMatrix) {
                if (matrix == 1) {
                    return true;
                }
            }
        }
        return false;
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
        int[][] matrix = new int[SLOTS_MATRIX_ROW_SIZE][SLOTS_MATRIX_COLUMN_SIZE];
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
