package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

@Service
@Scope(value = "singleton")
public class GtnWsCommonCalculationService {

	@Autowired
	GtnWsCalculationValidationService gtnWsCalculationValidation;

	private static final Evaluator EVALUATOR = new Evaluator();

	public void disp() {
		System.out.println("Success");
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
		return gtnWsCalculationValidation.getDoubleValue(EVALUATOR.getNumberResult(expression));
	}

}
