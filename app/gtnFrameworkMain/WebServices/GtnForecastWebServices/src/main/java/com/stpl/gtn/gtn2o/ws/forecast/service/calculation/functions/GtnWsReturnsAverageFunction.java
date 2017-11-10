/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.functions;

import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;

/**
 *
 * @author STPL
 */
public class GtnWsReturnsAverageFunction implements Function {

	@Override
	public String getName() {
		return "Average";
	}

	@Override
	public FunctionResult execute(Evaluator evltr, String values) throws FunctionException {
		String[] arrArg = values.split(",");
		double average = 0;
		for (String string : arrArg) {
			average += Double.parseDouble(string);
		}
		average = average / arrArg.length;
		return new FunctionResult(Double.toString(average), FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
	}

}