package com.stpl.gtn.gtn2o.ws.report.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPList;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPListDetails;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnReportJsonService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportRightTableLoadDataService;
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

	@Autowired
	GtnWsReportRightTableLoadDataService rightTableService;

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

	private void callInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		createSessionTableForReporting(dataSelectionBean.getSessionId());
		variableHierarchyNoInsertProcedure(dataSelectionBean);
		dataPopulationInsertProcedure(dataSelectionBean);
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
		if (resultList != null && !resultList.isEmpty()) {
			GtnWsReportCustomCCPList gtnWsReportCustomCCPList = applicationContext
					.getBean(GtnWsReportCustomCCPList.class);
			gtnWsReportCustomCCPList.setGtnWsReportCustomCCPListDetails(customizeCustomCCP(resultList));
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
			data.setChildCount(Integer.parseInt(result[8].toString()));
			data.setRowIndex(Integer.parseInt(result[9].toString()));
			data.setData(result);
			ccpList.add(data);
		}
		return ccpList;
	}

	private String getCustomCCPQuery(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		List<Object[]> customViewDetails = getCustomViewDetailsList(dataSelectionBean);
		if (customViewDetails == null || customViewDetails.isEmpty()) {
			return "";
		}
		GtnCustomRelationshipLevelValueService customRelationshipLevelValues = applicationContext
				.getBean(GtnCustomRelationshipLevelValueService.class);
		customRelationshipLevelValues.setInputForQueryGeneration(customViewDetails, dataSelectionBean);
		return customRelationshipLevelValues.getFinalQuery();
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getCustomViewDetailsList(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		List<Integer> input = new ArrayList<>();
		input.add(dataSelectionBean.getCustomViewMasterSid());
		return (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery(input, "getCustomViewHierarchyTableDetails"));
	}

	private void createSessionTableForReporting(String sessionId) throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			input.add(sessionId);
		}
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlService.getQuery(input, "createSessionTableForReport"));

	}

	private void variableHierarchyNoInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling variable Insert Procedure");
		Object[] input = { dataSelectionBean.getCustomViewMasterSid(), dataSelectionBean.getSessionId() };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeProcedure(GtnWsQueryConstants.PRC_CUSTOM_CCPDV_POPULATION, input, type);
	}

	private void dataPopulationInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling Data Population Insert Procedure");
		Object[] input = { dataSelectionBean.getSessionId(), dataSelectionBean.getFrequencyName(),
				dataSelectionBean.getFromPeriodReport(), dataSelectionBean.getToPeriodReport(),
				dataSelectionBean.getCustomViewMasterSid(), dataSelectionBean.getCompanyReport(),
				dataSelectionBean.getBusinessUnitReport(), (dataSelectionBean.getReportDataSource() - 1) };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		// gtnSqlQueryEngine.executeProcedure(GtnWsQueryConstants.PRC_REPORT_DATA_POPULATION,
		// input, type);
	}

	public static GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}

	public List<GtnWsRecordBean> getDashboardLeftData(GtnWsReportDashboardBean reportDashboardBean,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		try {
			// Object inputs[] = gtnWsSearchRequest.getQueryInput().toArray();
			GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest()
					.getDataSelectionBean();
			List<Object> values = gtnWsRequest.getGtnWsSearchRequest().getQueryInputList();
			int start = gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart();
			int limit = gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset();
			int levelNo = Integer.parseInt(values.get(0).toString());
			String hierarchyNo = values.get(1).toString();
			String fileName = gtnReportJsonService.getFileName("CustomViewCCP", dataSelectionBean.getSessionId());
			GtnWsReportCustomCCPList ccpList = (GtnWsReportCustomCCPList) gtnReportJsonService
					.convertJsonToObject(fileName, GtnWsReportCustomCCPList.class);
			List<GtnWsReportCustomCCPListDetails> gtnWsReportCustomCCPListDetails = ccpList
					.getGtnWsReportCustomCCPListDetails();
			Map<String, Map<String, Double>> rightDataMap = rightTableService.getDataFromBackend();
			return gtnWsReportCustomCCPListDetails.stream()
					.filter(row -> row.getLevelNo() == levelNo && row.getHierarchyNo().startsWith(hierarchyNo) &&
                                                row.getRowIndex()>=start ).limit(limit)
					.map(row -> convertToRecordbean(row, gtnWsRequest.getGtnWsSearchRequest().getRecordHeader(),
							rightDataMap, gtnWsReportCustomCCPListDetails.indexOf(row)))
					.collect(Collectors.toList());

		} catch (Exception ex) {
			GTNLOGGER.error(ex.getMessage(), ex);
		}
		return new ArrayList<>();
	}

	private GtnWsRecordBean convertToRecordbean(GtnWsReportCustomCCPListDetails bean, List<Object> recordHeader,
			Map<String, Map<String, Double>> rightDataMap, int index) {
		Map.Entry<String, Map<String, Double>> dataEntry = rightDataMap.entrySet().iterator().next();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		Optional<List> optionalRecordHeader = Optional.of(recordHeader);
		recordHeader = optionalRecordHeader.orElseGet(ArrayList::new);
		recordBean.setRecordHeader(recordHeader);
		recordBean.addAdditionalProperty(bean.getChildCount());// for Child Count
		recordBean.addAdditionalProperty(bean.getLevelNo());// level No
		recordBean.addAdditionalProperty(bean.getHierarchyNo());
		recordBean.addAdditionalProperty(index);
		recordBean.addAdditionalProperty(bean.getRowIndex());
		recordBean.addAdditionalProperty(0);
		recordBean.addProperties("levelValue", bean.getData()[1]);
		dataEntry.getValue().entrySet().stream()
				.forEach(entry -> recordBean.addProperties(entry.getKey(), entry.getValue()));
		return recordBean;
	}

}
