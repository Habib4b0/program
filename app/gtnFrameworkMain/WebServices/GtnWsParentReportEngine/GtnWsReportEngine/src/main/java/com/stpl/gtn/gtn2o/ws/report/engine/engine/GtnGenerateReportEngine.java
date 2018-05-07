package com.stpl.gtn.gtn2o.ws.report.engine.engine;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsMongoCalculation;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

@Service
public class GtnGenerateReportEngine {

	public GtnWsReportEngineTreeNode generateReportOutput(GtnWsReportEngineBean engineBean) {
		GtnWsMongoCalculation calculation = new GtnWsMongoCalculation(engineBean);
		calculation.nodeData();
		return calculation.variableCategoryCalculation();
	}
}
