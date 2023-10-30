package by.slots.service.math.impl;

import static by.slots.config.SlotsConfiguration.REELS_AMOUNT;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

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

    private volatile int[] currentState = new int[REELS_AMOUNT];

    @Override
    public SlotMatrixGenerationResult generate() {
        final int[] previousWinningMatrix = analyzerService.getCurrentWinningState();
        generateMatrix(previousWinningMatrix);
        return new SlotMatrixGenerationResult(currentState);
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
        return Arrays.stream(previousWinningMatrix).anyMatch(value -> value == 1);
    }

    private void updateExistingMatrix(int[] previousWinningMatrix) {
        IntStream.range(0, previousWinningMatrix.length)
                .filter(i -> previousWinningMatrix[i] == 1)
                .forEach(i -> this.currentState[i] = generateRandomIndex());
    }

    private int[] generateNewMatrix() {
        int[] matrix = new int[REELS_AMOUNT];
        IntStream.range(0, matrix.length).forEach(i -> matrix[i] = generateRandomIndex());
        return matrix;
    }

    private int generateRandomIndex() {
        return new Random().nextInt(SlotsConfiguration.SLOT_TYPE_AMOUNT);
    }
}
