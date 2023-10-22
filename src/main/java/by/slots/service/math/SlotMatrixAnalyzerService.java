package by.slots.service.math;

import by.slots.domain.math.SlotMatrixAnalyzerResult;

public interface SlotMatrixAnalyzerService {

    SlotMatrixAnalyzerResult analyzeWinningCombinations(int[][] matrix);

    int[][] getCurrentWinningState();

}
