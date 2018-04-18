package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsCommonCalculationService {

	private static final Evaluator EVALUATOR = new Evaluator();
	private final GtnWsCalculationValidationService gtnWsCalculationValidation = GtnWsCalculationValidationService
			.getInstance();
	private static GtnWsCommonCalculationService gtnWsCommonCalculation;

	private GtnWsCommonCalculationService() {
		super();
	}

	public static GtnWsCommonCalculationService getInstance() {
		if (gtnWsCommonCalculation == null) {
			gtnWsCommonCalculation = new GtnWsCommonCalculationService();
		}
		return gtnWsCommonCalculation;
	}

	public double getDividedValue(Object numerator, Object denominator) throws EvaluationException {
		String numeratorStr = gtnWsCalculationValidation.getDoubleValue(numerator);
		String denominatorStr = gtnWsCalculationValidation.getDoubleValue(denominator);
		return gtnWsCalculationValidation
				.getDoubleValue(EVALUATOR.getNumberResult(numeratorStr + "/" + denominatorStr));
	}

	public double getSubtractedValue(Object first, Object second) throws EvaluationException {
		String numeratorStr = gtnWsCalculationValidation.getDoubleValue(first);
		String denominatorStr = gtnWsCalculationValidation.getDoubleValue(second);
		return gtnWsCalculationValidation
				.getDoubleValue(EVALUATOR.getNumberResult(numeratorStr + "-" + denominatorStr));
	}

	public double getMultipiedValue(Object first, Object second, boolean convertToPercent) throws EvaluationException {
		String numeratorStr = gtnWsCalculationValidation.getDoubleValue(first);
		String denominatorStr = gtnWsCalculationValidation.getDoubleValue(second);
		String expression = convertToPercent ? numeratorStr + "*" + denominatorStr + "*100"
				: numeratorStr + "*" + denominatorStr;
		return gtnWsCalculationValidation
				.getDoubleValue(EVALUATOR.getNumberResult(expression));
	}

}
