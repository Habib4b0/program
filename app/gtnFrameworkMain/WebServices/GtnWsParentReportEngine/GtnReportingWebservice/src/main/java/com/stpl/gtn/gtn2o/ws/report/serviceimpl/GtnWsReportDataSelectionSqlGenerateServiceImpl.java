package com.stpl.gtn.gtn2o.ws.report.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPList;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPListDetails;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.service.GtnReportJsonService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportSqlService;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.service.GtnCustomRelationshipLevelValueService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service("reportDataSelectionSql")
@Scope(value = "singleton")
public class GtnWsReportDataSelectionSqlGenerateServiceImpl implements GtnWsReportDataSelectionGenerate {

	@Autowired
	GtnReportJsonService gtnReportJsonService;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnWsReportDataSelectionSqlGenerateServiceImpl.class);

	@Override
	public void dataSelectionGenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getReportBean()
				.getDataSelectionBean();
		try {
			callCCPInsertService(gtnWsRequest);
			callInsertProcedure(dataSelectionBean);
			saveCustomCCPMap(dataSelectionBean);
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error(e.getErrorMessage());
		}
	}

	private void callCCPInsertService(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GTNLOGGER.info("Calling Custom Map");
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE + GtnWebServiceUrlConstants.GTN_REPORT_CCP_INSERT_SQL,
				gtnWsRequest, getGsnWsSecurityToken(gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));
	}

	private void callInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean) {
		variableInsertProcedure();
		dataPopulationInsertProcedure();
	}

	private void saveCustomCCPMap(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling Save Custom Map");
		try {
			gtnReportJsonService.writeObjectAsJson(buildCustomCCP(dataSelectionBean),
					gtnReportJsonService.createJsonFilePath("CustomViewCCP", dataSelectionBean.getSessionId()));
		} catch (IOException e) {
			GTNLOGGER.error(e.getMessage());
		}
	}

	private GtnWsReportCustomCCPList buildCustomCCP(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling building Custom Map");
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(getCustomCCPQuery(dataSelectionBean));
		if (resultList != null && resultList.isEmpty()) {
			GtnWsReportCustomCCPList gtnWsReportCustomCCPList = applicationContext
					.getBean(GtnWsReportCustomCCPList.class);
			gtnWsReportCustomCCPList
					.setGtnWsReportCustomCCPListDetails(customizeCustomCCP(getCustomHierarchyResults()));
			return gtnWsReportCustomCCPList;
		}
		GTNLOGGER.info("File for CCP doesn't generate");
		return null;

	}

	private List<GtnWsReportCustomCCPListDetails> customizeCustomCCP(List<Object[]> resultList) {
		List<GtnWsReportCustomCCPListDetails> ccpList = new ArrayList<>();
		for (Object[] result : resultList) {
			GtnWsReportCustomCCPListDetails data = applicationContext.getBean(GtnWsReportCustomCCPListDetails.class);
			data.setLevelNo(Integer.parseInt(result[3].toString()));
			data.setHierarchyNo(result[0].toString());
			data.setData(result);
			ccpList.add(data);
		}
		return ccpList;
	}

	private String getCustomCCPQuery(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		List<Object[]> customViewDetails = getCustomViewDetailsList();
		if (customViewDetails == null || customViewDetails.isEmpty()) {
			return "";
		}
		GtnCustomRelationshipLevelValueService customRelationshipLevelValues = applicationContext
				.getBean(GtnCustomRelationshipLevelValueService.class);
		customRelationshipLevelValues.setInputForQueryGeneration(customViewDetails, dataSelectionBean);
		GTNLOGGER.info(customRelationshipLevelValues.getFinalQuery());
		return customRelationshipLevelValues.getFinalQuery();
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getCustomViewDetailsList() throws GtnFrameworkGeneralException {
		List<Integer> input = new ArrayList<>();
		input.add(24);
		return (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery(input, "getCustomViewHierarchyTableDetails"));
	}

	private void variableInsertProcedure() {
		GTNLOGGER.info("Calling variable Insert Procedure");
	}

	private void dataPopulationInsertProcedure() {
		GTNLOGGER.info("Calling Data Population Insert Procedure");
	}

	public static GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}

	private List<Object[]> getCustomHierarchyResults() {
		List<Object[]> result = new ArrayList<>();
		result.add(new Object[] { "390-116.", "Closed Plan", "Segment", "1", "116", null, null, 0 });
		result.add(new Object[] { "390-116.222.", "Mc Commercial", "Market Type", "2", "222", null, null, 1 });
		result.add(new Object[] { "390-116.222.44.", "TEST-R-MODIFIEDTEST", "Contract", "3", "44",
				"TEST-R-MODIFIEDTEST", "TEST-R-MODIFIEDTEST", 2 });
		result.add(new Object[] { "390-116.222.60.", "Contract762017", "Contract", "3", "60", "Contract762017",
				"Contract762017", 2 });
		result.add(
				new Object[] { "390-116.222.25.", "CONTRACT_4", "Contract", "3", "25", "CONTRACT_4", "CONTRACT_4", 2 });
		result.add(
				new Object[] { "390-116.222.24.", "CONTRACT_3", "Contract", "3", "24", "CONTRACT_3", "CONTRACT_3", 2 });
		result.add(
				new Object[] { "390-116.222.23.", "CONTRACT_2", "Contract", "3", "23", "CONTRACT_2", "CONTRACT_2", 2 });
		result.add(
				new Object[] { "390-116.222.22.", "CONTRACT_1", "Contract", "3", "22", "CONTRACT_1", "CONTRACT_1", 2 });
		result.add(new Object[] { "390-116.222.61.", "CH772017", "Contract", "3", "61", "CH772017", "CH772017", 2 });
		result.add(new Object[] { "390-116.222.1083.", "CD2607", "Contract", "3", "1083", "CD2607", "CD2607", 2 });
		result.add(new Object[] { "390-116.222.61.58318.", "MNGD & CLSD 1 OF MANY", "Trading Partner", "4", "58318",
				"MNGD & CLSD 1 OF MANY", "10000-Customer", 3 });
		result.add(new Object[] { "390-116.222.61.58319.", "MNGD & CLSD 1 OF MANY", "Trading Partner", "4", "58319",
				"MNGD & CLSD 1 OF MANY", "10001-Customer", 3 });
		result.add(new Object[] { "390-116.222.61.58320.", "LILETTA UPM - $350", "Trading Partner", "4", "58320",
				"LILETTA UPM - $350", "10002-Customer", 3 });
		result.add(new Object[] { "390-116.222.25.92.", "COMPANY_8", "Trading Partner", "4", "92", "COMPANY_8",
				"COMPANY_8", 3 });
		result.add(new Object[] { "390-116.222.25.91.", "COMPANY_7", "Trading Partner", "4", "91", "COMPANY_7",
				"COMPANY_7", 3 });
		result.add(new Object[] { "390-116.222.24.90.", "COMPANY_6", "Trading Partner", "4", "90", "COMPANY_6",
				"COMPANY_6", 3 });
		result.add(new Object[] { "390-116.222.24.89.", "COMPANY_5", "Trading Partner", "4", "89", "COMPANY_5",
				"COMPANY_5", 3 });
		result.add(new Object[] { "390-116.222.24.88.", "COMPANY_4", "Trading Partner", "4", "88", "COMPANY_4",
				"COMPANY_4", 3 });
		result.add(new Object[] { "390-116.222.44.87.", "COMPANY_3", "Trading Partner", "4", "87", "COMPANY_3",
				"COMPANY_3", 3 });
		result.add(new Object[] { "390-116.222.23.86.", "COMPANY_2", "Trading Partner", "4", "86", "COMPANY_2",
				"COMPANY_2", 3 });
		result.add(new Object[] { "390-116.222.44.86.", "COMPANY_2", "Trading Partner", "4", "86", "COMPANY_2",
				"COMPANY_2", 3 });
		result.add(new Object[] { "390-116.222.1083.84.", "COMPANY_1", "Trading Partner", "4", "84", "COMPANY_1",
				"COMPANY_1", 3 });
		result.add(new Object[] { "390-116.222.22.84.", "COMPANY_1", "Trading Partner", "4", "84", "COMPANY_1",
				"COMPANY_1", 3 });
		result.add(new Object[] { "390-116.222.44.84.", "COMPANY_1", "Trading Partner", "4", "84", "COMPANY_1",
				"COMPANY_1", 3 });
		result.add(new Object[] { "390-116.222.60.84.", "COMPANY_1", "Trading Partner", "4", "84", "COMPANY_1",
				"COMPANY_1", 3 });
		result.add(new Object[] { "390-116.222.61.84.", "COMPANY_1", "Trading Partner", "4", "84", "COMPANY_1",
				"COMPANY_1", 3 });
		result.add(new Object[] { "390-116.222.61.58322.", "1 of 3 Preferred", "Trading Partner", "4", "58322",
				"1 of 3 Preferred", "10003-PP-Customer", 3 });
		result.add(new Object[] { "390-116.222.61.58325.", "1 of 3 Preferred", "Trading Partner", "4", "58325",
				"1 of 3 Preferred", "10005-Customer", 3 });
		result.add(new Object[] { "390-116.222.61.58323.", "1 of 2 Preferred", "Trading Partner", "4", "58323",
				"1 of 2 Preferred", "10004-Customer", 3 });
		return result;
	}

}
