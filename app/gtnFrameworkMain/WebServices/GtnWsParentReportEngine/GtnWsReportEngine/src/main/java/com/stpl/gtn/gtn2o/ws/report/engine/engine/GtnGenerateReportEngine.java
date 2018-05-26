package com.stpl.gtn.gtn2o.ws.report.engine.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsMongoCalculation;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

@Service
public class GtnGenerateReportEngine {

	public GtnGenerateReportEngine() {
		super();
	}

	@Autowired
	private GtnWsMongoCalculation gtnWsMongoCalculation;

	public GtnWsReportEngineTreeNode generateReportOutput(GtnWsReportEngineBean engineBean) {
		gtnWsMongoCalculation.nodeData(engineBean);
		return gtnWsMongoCalculation.variableCategoryCalculation(engineBean);
	}
}
