package com.stpl.gtn.gtn2o.ws.report.engine.engine;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsMongoCalculation;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

public class GtnGenerateReportEngine {

	public GtnWsReportEngineTreeNode generateReportOutput(GtnWsReportEngineBean engineBean) {

		GtnWsMongoCalculation calculation = new GtnWsMongoCalculation(engineBean);
		calculation.nodeData();
		GtnWsReportEngineTreeNode calculatedOutputTree = calculation.variableCategoryCalculation();
		return calculatedOutputTree;
	}
}
