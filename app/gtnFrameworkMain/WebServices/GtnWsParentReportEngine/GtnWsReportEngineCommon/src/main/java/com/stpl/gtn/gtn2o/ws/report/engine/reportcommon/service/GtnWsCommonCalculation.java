package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsCommonCalculation {

    private static final Evaluator EVALUATOR = new Evaluator();
    private final GtnWsCalculationValidation gtnWsCalculationValidation = GtnWsCalculationValidation.getInstance();
    private static volatile GtnWsCommonCalculation gtnWsCommonCalculation;

    private GtnWsCommonCalculation() {
        super();
    }

    public static synchronized GtnWsCommonCalculation getInstance() {
        if (gtnWsCommonCalculation == null) {
            gtnWsCommonCalculation = new GtnWsCommonCalculation();
        }
        return gtnWsCommonCalculation;
    }

    public double getDividedValue(Object numerator, Object denominator) throws EvaluationException {
        String numeratorStr = gtnWsCalculationValidation.getDoubleValue(numerator);
        String denominatorStr = gtnWsCalculationValidation.getDoubleValue(denominator);
        return gtnWsCalculationValidation.getDoubleValue(EVALUATOR.getNumberResult(numeratorStr + "/" + denominatorStr));
    }

    public double getSubtractedValue(Object first, Object second) throws EvaluationException {
        String numeratorStr = gtnWsCalculationValidation.getDoubleValue(first);
        String denominatorStr = gtnWsCalculationValidation.getDoubleValue(second);
        return gtnWsCalculationValidation.getDoubleValue(EVALUATOR.getNumberResult(numeratorStr + "-" + denominatorStr));
    }

}
