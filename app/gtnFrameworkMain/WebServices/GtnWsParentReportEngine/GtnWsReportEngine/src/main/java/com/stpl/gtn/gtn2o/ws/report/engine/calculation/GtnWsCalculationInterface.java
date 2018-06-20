package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;

public interface GtnWsCalculationInterface {

	public void initiateCalculation(GtnWsVariableCategoryBean variableCategoryBean);

	public Double executeCalculation(String currentProperty, String priorProperty);
}
