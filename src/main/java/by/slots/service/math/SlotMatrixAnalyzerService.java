package by.slots.service.math;

import by.slots.domain.math.SlotAnalyzerResult;

public interface SlotMatrixAnalyzerService {

    SlotAnalyzerResult analyzeWinningCombinations(int[] matrix);

    int[] getCurrentWinningState();

}
