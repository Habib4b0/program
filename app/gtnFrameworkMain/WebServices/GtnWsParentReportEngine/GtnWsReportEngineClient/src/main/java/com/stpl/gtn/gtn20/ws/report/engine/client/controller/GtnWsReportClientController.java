package com.stpl.gtn.gtn20.ws.report.engine.client.controller;

import java.util.List;

import com.stpl.gtn.gtn20.ws.report.engine.client.service.GtnWsRawDataCustomiseService;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.engine.GtnGenerateReportEngine;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.controller.GtnWsReportInputGenerator;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsJsonService;

public class GtnWsReportClientController {

	private static final GtnWsMongoService MONGO_SERVICE = GtnWsMongoService.getInstance();
	private static final GtnWsJsonService JSON_SERVICE_INSTANCE = GtnWsJsonService.getInstance();

	public static void main(String[] args) {
		GtnWsReportClientController controller = new GtnWsReportClientController();
		controller.getWsReportClientController();
	}

	public void getWsReportClientController() {
		GtnWsReportEngineTreeNode input = new GtnWsReportInputGenerator().callBuildTree(9);
		List<GtnWsProjectionBean> customizedRawList = new GtnWsRawDataCustomiseService().generateApprovedData();
		GtnWsReportEngineTreeNode outputTree = getGtnGeneratedReportOutput(input, customizedRawList);
		System.out.println("outputTree = " + outputTree);

		MONGO_SERVICE.createCollection("computedResults");
		MONGO_SERVICE.updateFinalResultsToMongo("computedResults", outputTree);
	}

	private GtnWsReportEngineTreeNode getGtnGeneratedReportOutput(GtnWsReportEngineTreeNode input,
			List<GtnWsProjectionBean> customizedRawList) {
		writeRawDataToMongo("D:\\Jayaram\\report0.json", customizedRawList);
		GtnGenerateReportEngine engine = new GtnGenerateReportEngine();
		GtnWsReportEngineBean engineBean = getGtnWsReportEngineBean(input);
		GtnWsReportEngineTreeNode output = engine.generateReportOutput(engineBean);
		GtnWsReportInputGenerator.shutdown();
		return output;
	}

	private void writeRawDataToMongo(String filename, List<GtnWsProjectionBean> rawData) {
		JSON_SERVICE_INSTANCE.writeToJsonFile(filename, rawData);
	}

	private GtnWsReportEngineBean getGtnWsReportEngineBean(GtnWsReportEngineTreeNode input) {
		GtnWsReportEngineBean engineBean = new GtnWsReportEngineBean();
		engineBean.setSelectedProjectionId(0);
		engineBean.setComparisonBasis("Actuals");
		engineBean.setInput(input);
		engineBean.addCollection("projection");
		engineBean.addCollection("projection1");
		engineBean.addCollection("projection2");
		return engineBean;
	}
}
