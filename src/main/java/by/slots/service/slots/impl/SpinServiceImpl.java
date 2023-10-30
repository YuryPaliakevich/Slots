package by.slots.service.slots.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.domain.math.SlotAnalyzerResult;
import by.slots.domain.math.SlotMatrixGenerationResult;
import by.slots.domain.spin.SpinResult;
import by.slots.service.math.SlotMatrixAnalyzerService;
import by.slots.service.math.SlotMatrixService;
import by.slots.service.slots.SpinService;

@Service
public class SpinServiceImpl implements SpinService {

    @Autowired
    private SlotMatrixAnalyzerService slotMatrixAnalyzerService;

    @Autowired
    private SlotMatrixService slotMatrixService;

    @Override
    public SpinResult spin(int stake) {
        final SlotMatrixGenerationResult matrix = slotMatrixService.generate();
        final SlotAnalyzerResult analyzerResult = slotMatrixAnalyzerService.analyzeWinningCombinations(matrix.getMatrix());
        return new SpinResult(matrix.getMatrix(), analyzerResult.getWinningCombination(), analyzerResult.getWiningAmount());
    }
}
