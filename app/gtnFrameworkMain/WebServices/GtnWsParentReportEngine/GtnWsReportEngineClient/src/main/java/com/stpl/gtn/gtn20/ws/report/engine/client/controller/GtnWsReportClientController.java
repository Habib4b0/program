package com.stpl.gtn.gtn20.ws.report.engine.client.controller;

import com.stpl.gtn.gtn20.ws.report.engine.client.service.GtnWsRawDataCustomiseService;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.engine.GtnGenerateReportEngine;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.controller.GtnWsReportInputGenerator;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

public class GtnWsReportClientController {

	private static final GtnWsMongoService MONGO_SERVICE = GtnWsMongoService.getInstance();

	public static void main(String[] args) {
		GtnWsReportClientController controller = new GtnWsReportClientController();
		controller.getWsReportClientController();
	}

	public void getWsReportClientController() {
		GtnWsReportEngineTreeNode input = new GtnWsReportInputGenerator().callBuildTree(9);
		new GtnWsRawDataCustomiseService().generateDataToMongo();
		GtnWsReportEngineTreeNode outputTree = getGtnGeneratedReportOutput(input);
		System.out.println("outputTree = " + outputTree);

		 MONGO_SERVICE.createCollection("computedResults");
		 MONGO_SERVICE.updateFinalResultsToMongo("computedResults", outputTree);
	}

	private GtnWsReportEngineTreeNode getGtnGeneratedReportOutput(GtnWsReportEngineTreeNode input) {
		GtnGenerateReportEngine engine = new GtnGenerateReportEngine();
		GtnWsReportEngineBean engineBean = getGtnWsReportEngineBean(input);
		GtnWsReportEngineTreeNode output = engine.generateReportOutput(engineBean);
		GtnWsReportInputGenerator.shutdown();
		return output;
	}

	private GtnWsReportEngineBean getGtnWsReportEngineBean(GtnWsReportEngineTreeNode input) {
		GtnWsReportEngineBean engineBean = new GtnWsReportEngineBean();
		engineBean.setSelectedProjectionId(0);
		engineBean.setComparisonBasis("Actuals");
		engineBean.setInput(input);
		engineBean.addComparisonTableName("projection");
		engineBean.addComparisonTableName("projection1");
		engineBean.addComparisonTableName("projection2");
		return engineBean;
	}
}
