package by.slots.service.slots.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.slots.domain.math.SlotMatrixAnalyzerResult;
import by.slots.domain.math.SlotGenerationResult;
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
        final SlotGenerationResult matrix = slotMatrixService.generate();
        final SlotMatrixAnalyzerResult analyzerResult = slotMatrixAnalyzerService.analyzeWinningCombinations(matrix.getResult());
        return new SpinResult(matrix.getResult(), analyzerResult.getWinningCombination(), analyzerResult.getWiningAmount());
    }
}
