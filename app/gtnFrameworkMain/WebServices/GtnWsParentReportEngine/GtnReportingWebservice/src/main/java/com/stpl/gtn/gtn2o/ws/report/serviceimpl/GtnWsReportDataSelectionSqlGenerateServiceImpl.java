package com.stpl.gtn.gtn2o.ws.report.serviceimpl;

import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.BYTE;
import static com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType.INTEGER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPList;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPListDetails;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportDecimalFormat;
import com.stpl.gtn.gtn2o.ws.report.service.GtnReportJsonService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnReportVariableDescriptionIndicatorService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportRightTableLoadDataService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportSqlService;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.service.GtnCustomRelationshipLevelValueService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service("reportDataSelectionSql")
public class GtnWsReportDataSelectionSqlGenerateServiceImpl implements GtnWsReportDataSelectionGenerate {

	@Autowired
	private GtnReportJsonService gtnReportJsonService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private GtnWsReportRightTableLoadDataService rightTableService;

	@Autowired
	private GtnReportVariableDescriptionIndicatorService variableDescriptionIndicatorService;
	
	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnWsReportDataSelectionSqlGenerateServiceImpl.class);

	public GtnWsReportDataSelectionSqlGenerateServiceImpl() {
		super();
	}
	
	@Override
	public void dataSelectionGenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getReportBean()
				.getDataSelectionBean();
		try {
			createSessionTableForReporting(dataSelectionBean);
			callCCPInsertService(gtnWsRequest);
			if (dataSelectionBean.getCustomViewMasterSid() != 0) {
				callInsertProcedure(dataSelectionBean);
				saveCustomCCPMap(dataSelectionBean);
				callVariableBreakdownInsertService(dataSelectionBean);
			}
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
		variableHierarchyNoInsertProcedure(dataSelectionBean);
		dataPopulationInsertProcedure(dataSelectionBean);
	}

	private void saveCustomCCPMap(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling Save Custom Map");
		try {
			gtnReportJsonService.writeObjectAsJson(buildCustomCCP(dataSelectionBean), gtnReportJsonService
					.createJsonFilePath(GtnWsQueryConstants.CUSTOM_CCP_FILE_NAME, dataSelectionBean.getSessionId()));
		} catch (IOException e) {
			GTNLOGGER.error(e.getMessage());
		}
	}

	private GtnWsReportCustomCCPList buildCustomCCP(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling building Custom Map");
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
				replaceTableNames(getCustomCCPQuery(dataSelectionBean), dataSelectionBean.getSessionTableMap()));
		Map<Integer, Integer> customViewDetails = getCustomViewDetailsVariableCount(
				dataSelectionBean.getCustomViewMasterSid());
		if (resultList != null && !resultList.isEmpty()) {
			GtnWsReportCustomCCPList gtnWsReportCustomCCPList = applicationContext
					.getBean(GtnWsReportCustomCCPList.class);
			gtnWsReportCustomCCPList
					.setGtnWsReportCustomCCPListDetails(customizeCustomCCP(resultList, customViewDetails));
			return gtnWsReportCustomCCPList;
		}
		GTNLOGGER.info("File for CCP doesn't generate");
		return null;

	}

	private List<GtnWsReportCustomCCPListDetails> customizeCustomCCP(List<Object[]> resultList,
			Map<Integer, Integer> customViewDetails) {
		List<GtnWsReportCustomCCPListDetails> ccpList = new ArrayList<>(resultList.size());
		for (Object[] result : resultList) {
			GtnWsReportCustomCCPListDetails data = applicationContext.getBean(GtnWsReportCustomCCPListDetails.class);
			data.setLevelNo(Integer.parseInt(result[3].toString()));
			data.setHierarchyNo(result[0].toString());
			data.setChildCount(Integer.parseInt(result[result.length - 2].toString()));
			data.setRowIndex(Integer.parseInt(result[result.length - 1].toString()));
			data.setData(result);
			Optional.ofNullable(customViewDetails.get(data.getLevelNo()))
					.ifPresent(e -> data.setVariableCount(customViewDetails.get(data.getLevelNo())));
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

	@SuppressWarnings("unchecked")
	private void createSessionTableForReporting(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		StringBuilder query = new StringBuilder(
				"DECLARE @TABLE_LIST TABLE_LIST, @USER_ID INT, @SESSION_ID VARCHAR(100) \r\n"
						+ " INSERT INTO @TABLE_LIST(ORG_TABLE_NAME) VALUES ('");
		String[] tableName = GtnWsQueryConstants.PRC_TEMP_TABLE_LIST.split(",");
		query.append(StringUtils.join(tableName, "'),('"));
		query.append("') \n").append(" EXEC ").append("PRC_TEMP_TABLE_CREATION ").append("@TABLE_LIST,")
				.append(Integer.parseInt(dataSelectionBean.getUserId())).append(",'")
				.append(dataSelectionBean.getSessionId()).append(GtnWsQueryConstants.SINGLE_QUOTES);

		List<Object[]> sessionTable = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query.toString());
		if (sessionTable != null && !sessionTable.isEmpty()) {
			for (Object[] obj : sessionTable) {
				dataSelectionBean.putSessionTableMap(obj[0].toString(), obj[1].toString());
			}
		}
	}

	private void variableHierarchyNoInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		GTNLOGGER.info("Calling variable Insert Procedure");
		Object[] input = { dataSelectionBean.getCustomViewMasterSid(), Integer.valueOf(dataSelectionBean.getUserId()),
				dataSelectionBean.getSessionId() };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeProcedure(GtnWsQueryConstants.PRC_CUSTOM_CCPDV_POPULATION, input, type);
	}

	private void dataPopulationInsertProcedure(GtnWsReportDataSelectionBean dataSelectionBean) {
		try {
			GTNLOGGER.info("Calling Data Population Insert Procedure");
			StringBuilder dataPopulation = new StringBuilder(" EXEC PRC_REPORTING_DASHBOARD ");
			dataPopulation.append(Integer.parseInt(dataSelectionBean.getUserId())).append(",'");
			dataPopulation.append(dataSelectionBean.getSessionId()).append("','");
			dataPopulation.append(dataSelectionBean.getFrequencyName()).append("',");
			dataPopulation.append(dataSelectionBean.getFromPeriodReport()).append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append(dataSelectionBean.getToPeriod()).append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append(dataSelectionBean.getCustomViewMasterSid()).append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append(dataSelectionBean.getCompanyReport()).append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append(dataSelectionBean.getBusinessUnitReport()).append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append((dataSelectionBean.getReportDataSource() - 1))
					.append(GtnWsQueryConstants.STRING_COMMA);
			dataPopulation.append(getComparisonProjection(dataSelectionBean.getComparisonProjectionBeanList()));
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(dataPopulation.toString());
		} catch (GtnFrameworkGeneralException ex) {
			GTNLOGGER.error(ex.getErrorMessage(), ex);
		}
	}

	private String getComparisonProjection(List<GtnReportComparisonProjectionBean> comparisonProjectionList) {
		StringBuilder comparisonProjections = new StringBuilder();
		if (comparisonProjectionList != null && !comparisonProjectionList.isEmpty()) {
			for (int i = 0; i < comparisonProjectionList.size(); i++) {
				if (i != 0) {
					comparisonProjections.append(GtnWsQueryConstants.STRING_COMMA);
				}
				comparisonProjections.append(comparisonProjectionList.get(i).getProjectionMasterSid())
						.append(GtnWsQueryConstants.UNDERSCORE)
						.append(comparisonProjectionList.get(i).getProjectionType());
			}
		}
		GTNLOGGER.info(comparisonProjections.toString());
		return comparisonProjections.length() == 0 ? null
				: GtnWsQueryConstants.SINGLE_QUOTES + comparisonProjections.toString()
						+ GtnWsQueryConstants.SINGLE_QUOTES;
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
			GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest()
					.getDataSelectionBean();
			List<Object> values = gtnWsRequest.getGtnWsSearchRequest().getQueryInputList();

			String fileName = gtnReportJsonService.getFileName(GtnWsQueryConstants.CUSTOM_CCP_FILE_NAME,
					dataSelectionBean.getSessionId());
			GtnWsReportCustomCCPList ccpList = (GtnWsReportCustomCCPList) gtnReportJsonService
					.convertJsonToObject(fileName, GtnWsReportCustomCCPList.class);
			List<GtnWsReportCustomCCPListDetails> gtnWsReportCustomCCPListDetails = ccpList
					.getGtnWsReportCustomCCPListDetails();
			Set<String> filteredHierarchy = Optional.ofNullable(reportDashboardBean.getFilteredHierarchy())
					.orElseGet(HashSet::new);
			
			List<Object[]> customviewData = getCustomViewType(
					gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean().getCustomViewMasterSid());
			if (values == null) {
				return gtnWsReportCustomCCPListDetails.stream()
						.filter(row -> (filteredHierarchy.isEmpty() || filteredHierarchy.contains(row.getHierarchyNo()))
								&& filterCustomViewVariable(customviewData,
										reportDashboardBean.getSelectedVariableType(), row))
						.map(row -> convertToRecordbean(gtnWsRequest, row,
								gtnWsRequest.getGtnWsSearchRequest().getRecordHeader(),
								gtnWsReportCustomCCPListDetails.indexOf(row), reportDashboardBean.getDisplayFormat(),
								customviewData))
						.collect(Collectors.toList());
			}
			
			int start = gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart();
			int limit = gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset();
			int levelNo = Integer.parseInt(values.get(0).toString());
			String hierarchyNo = values.get(1).toString();
			return gtnWsReportCustomCCPListDetails.stream().filter(row -> row.getLevelNo() == levelNo
					&& matchedFilteredHierarchyNo(filteredHierarchy, row.getHierarchyNo(), row.getData()[5].toString())
					&& filterCustomViewVariable(customviewData, reportDashboardBean.getSelectedVariableType(), row)
					&& row.getHierarchyNo().startsWith(hierarchyNo) && row.getRowIndex() >= start)
					.limit(limit)
					.map(row -> aggregate(
							convertToRecordbean(gtnWsRequest, row,
									gtnWsRequest.getGtnWsSearchRequest().getRecordHeader(),
									gtnWsReportCustomCCPListDetails.indexOf(row),
									reportDashboardBean.getDisplayFormat(), customviewData),
							row, customviewData, gtnWsRequest))
					.collect(Collectors.toList());

		} catch (IOException | NumberFormatException ex) {
			GTNLOGGER.error(ex.getMessage(), ex);
		}
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	GtnWsRecordBean aggregate(GtnWsRecordBean bean, GtnWsReportCustomCCPListDetails hierachyBean,
			List<Object[]> customviewData, GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		bean.getRecordHeader().stream().filter(e -> e != null && e.toString().contains("Total")).forEach(object -> {

			Double total;
			String levelName = "levelValue";
			String indicator = hierachyBean.getData()[5].toString();
			if (("V".equals(indicator)
					&& bean.getStringProperty(levelName).contains(GtnWsQueryConstants.PERCENTAGE_OPERATOR))
					|| object.toString().contains("PER") || object.toString().contains("RATE")) {
				total = bean.getRecordHeader().stream()
						.filter(e -> e != null && e.toString().contains(object.toString().replace("Total", "")))
						.mapToDouble(columns -> extractDouble(bean.getPropertyValue(columns.toString()))).average()
						.getAsDouble();
			} else {
				total = bean.getRecordHeader().stream()
						.filter(e -> e != null && e.toString().contains(object.toString().replace("Total", "")))
						.mapToDouble(columns -> extractDouble(bean.getPropertyValue(columns.toString()))).sum();
			}
			
			String customViewTypeInBackend = String.valueOf(customviewData.get(0));
			String[] customViewTypeDataArray = customViewTypeInBackend.split("~");
			boolean isTotalSpecialCondition = !"V".equals(indicator) && !customViewTypeDataArray[2].equals("Columns");
			
			String currencyConversionType = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean()
					.getCurrencyConversion();
			if (!"0".equals(currencyConversionType)) {
				dataConvertors(bean, object.toString(), total, indicator, bean.getStringProperty(levelName),
						isTotalSpecialCondition);
			} else {
				currencyTypeNoConversionDataConverters(bean, object.toString(), total, indicator,
						bean.getStringProperty(levelName), isTotalSpecialCondition);
			}

		});
		return bean;
	}

	private GtnWsRecordBean convertToRecordbean(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsReportCustomCCPListDetails bean, List<Object> recordHeader, int index, Object[] displayFormat,
			List<Object[]> customviewData) {
		
		String customViewTypeInBackend;
		String[] customViewTypeDataArray;
		Map<String, Double> dataForHierarchy = null;
		if (Optional.ofNullable(customviewData).isPresent()) {
			customViewTypeInBackend = String.valueOf(customviewData.get(0));
			customViewTypeDataArray = customViewTypeInBackend.split("~");
			Pattern containsOneChar = Pattern.compile("(?=.*[A-Z])");
			Matcher charMatcher = containsOneChar.matcher(bean.getHierarchyNo());
			if (bean.getVariableCount() == 1 || bean.getData()[5].equals("V")
					|| customViewTypeDataArray[2].equals("Columns") || charMatcher.find()) {
				Map<String, Map<String, Double>> rightDataMap = rightTableService.getDataFromBackend(gtnWsRequest, bean,
						customViewTypeDataArray);
				if (bean.getData()[5].equals("V")
						|| customViewTypeDataArray[1].equals("Expandable") && charMatcher.find()) {
					Pattern indexPattern = Pattern.compile("([A-Z])");
					Matcher charIndexmatch = indexPattern.matcher(bean.getHierarchyNo());
					charIndexmatch.find();
					char variableIndicator = bean.getHierarchyNo().charAt(charIndexmatch.start());
					String variable = variableDescriptionIndicatorService.getVariable(variableIndicator);
					dataForHierarchy = rightDataMap.get(bean.getHierarchyNo() + getVariableMap().get(variable));
					dataForHierarchy.putAll(rightDataMap.get(bean.getHierarchyNo()));
				} else {
					dataForHierarchy = rightDataMap.get(bean.getHierarchyNo());
				}
			}
		}

		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		Optional<List> optionalRecordHeader = Optional.of(recordHeader);
		List<Object> recordHeaderList = optionalRecordHeader.orElseGet(ArrayList::new);
		recordBean.setRecordHeader(recordHeaderList);
		recordBean.addAdditionalProperty(bean.getChildCount());// for Child
																// Count
		recordBean.addAdditionalProperty(bean.getLevelNo());// level No
		recordBean.addAdditionalProperty(bean.getHierarchyNo());
		recordBean.addAdditionalProperty(index + 1);
		recordBean.addAdditionalProperty(bean.getRowIndex());
		recordBean.addAdditionalProperty(0);
		String levelName = setDisplayFormat(bean.getData(), displayFormat);
		recordBean.addProperties("levelValue", levelName);

		String currencyConversionType = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean()
				.getCurrencyConversion();
		
		if (Optional.ofNullable(customviewData).isPresent()) {
			
			String indicator = bean.getData()[5].toString();
			boolean isTotalSpecialCondition = false;
			
			customViewTypeInBackend = String.valueOf(customviewData.get(0));
			customViewTypeDataArray = customViewTypeInBackend.split("~");
			
			GTNLOGGER.info("indicator: " + indicator);
			GTNLOGGER.info("customViewTypeDataArray[2]: " + customViewTypeDataArray[2]);
			isTotalSpecialCondition = !"V".equals(indicator) && !customViewTypeDataArray[2].equals("Columns");
			GTNLOGGER.info("isTotalSpecialCondition: " + isTotalSpecialCondition);
			
			if(isTotalSpecialCondition)
			{
				GTNLOGGER.info("if");
				if (dataForHierarchy != null && !"0".equals(currencyConversionType)) {
					dataForHierarchy.entrySet().stream()
							.forEach(entry -> Optional.ofNullable(entry.getValue()).ifPresent(data -> dataConvertors(recordBean,
									entry.getKey(), data, indicator, levelName, true)));
				}
				if (dataForHierarchy != null && "0".equals(currencyConversionType)) {
					dataForHierarchy.entrySet().stream()
							.forEach(entry -> Optional.ofNullable(entry.getValue())
									.ifPresent(data -> currencyTypeNoConversionDataConverters(recordBean, entry.getKey(), data,
											indicator, levelName, true)));
				}
			}
			else
			{
				GTNLOGGER.info("else");
				if (dataForHierarchy != null && !"0".equals(currencyConversionType)) {
					dataForHierarchy.entrySet().stream()
							.forEach(entry -> Optional.ofNullable(entry.getValue()).ifPresent(data -> dataConvertors(recordBean,
									entry.getKey(), data, indicator, levelName, false)));
				}
				if (dataForHierarchy != null && "0".equals(currencyConversionType)) {
					dataForHierarchy.entrySet().stream()
							.forEach(entry -> Optional.ofNullable(entry.getValue())
									.ifPresent(data -> currencyTypeNoConversionDataConverters(recordBean, entry.getKey(), data,
											indicator, levelName, false)));
				}
			}
		}
		

//		if (dataForHierarchy != null && !"0".equals(currencyConversionType)) {
//			dataForHierarchy.entrySet().stream()
//					.forEach(entry -> Optional.ofNullable(entry.getValue()).ifPresent(data -> dataConvertors(recordBean,
//							entry.getKey(), data, indicator, levelName, false)));
//		}

		// When currency display is set to no conversion in report options
//		if (dataForHierarchy != null && "0".equals(currencyConversionType)) {
//			dataForHierarchy.entrySet().stream()
//					.forEach(entry -> Optional.ofNullable(entry.getValue())
//							.ifPresent(data -> currencyTypeNoConversionDataConverters(recordBean, entry.getKey(), data,
//									indicator, levelName, isTotalSpecialCondition)));
//		}

		return recordBean;
	}

	private static Map<String, String> getVariableMap() {
		Map<String, String> variableMap = new HashMap<>();
		variableMap.put("Ex-Factory Sales", "EXFACTORY_SALES");
		variableMap.put("Gross Contract Sales % of Ex-Factory", "CON_SALES_PER_FO_EX");
		variableMap.put("Gross Contract Sales", "CONTRACT_SALES");
		variableMap.put("Contract Units", "CONTRACT_UNITS");
		variableMap.put("Contract Sales % of Total Contract Sales", "CONT_SALES_PER_OF_TOTAL_SALES");
		variableMap.put("Deduction $", "DISCOUNT_AMOUNT");
		variableMap.put("Deduction %", "DISCOUNT_RATE");
		variableMap.put("Deduction % of Ex-Factory", "DISC_PER_OF_EX");
		variableMap.put("Net Contract Sales", "NET_SALES");
		variableMap.put("Net Contract Sales % of Ex-Factory", "NET_CON_SALES_PER_OF_EX");
		variableMap.put("Net Ex-Factory Sales", "NET_EX_SALES");
		variableMap.put("Net Ex-Factory Sales % of Total Ex-Factory Sales", "NET_EXP_SALES_PER_OF_TOATL_EX");
		variableMap.put("Weighted GTN Contribution", "WEIGHTED_GTN_CONTRIBUTION");
		variableMap.put("RPU", "RPU");
		return variableMap;
	}

	private String setDisplayFormat(Object[] data, Object[] displayFormat) {
		StringBuilder levelName = new StringBuilder();
		if (displayFormat != null && displayFormat.length != 0) {
			if (displayFormat.length == 2) {
				levelName.append(setLevelName(data, displayFormat, true));
			} else {
				levelName.append(setLevelName(data, displayFormat, false));
			}
		} else {
			levelName.append(data[1]);
		}
		return levelName.toString();
	}

	private String setLevelName(Object[] data, Object[] displayFormat, boolean isBoth) {
		if (isBoth) {
			if (data[6] == null && data[7] == null) {
				return data[1].toString();
			}
			return data[6] + " - " + data[7];
		}
		if (String.valueOf(displayFormat[0]).equals("Name")) {
			return data[7] == null ? data[1].toString() : data[7].toString();
		} else {
			return data[6] == null ? data[1].toString() : data[6].toString();
		}
	}

	private boolean filterCustomViewVariable(List<Object[]> customviewData, String[] selectedVariables,
			GtnWsReportCustomCCPListDetails bean) {
		String[] customViewType = String.valueOf(customviewData).split("~");
		if (customViewType[1].equals("Expandable") && "V".equals(String.valueOf(bean.getData()[5]))) {
			for (String variable : selectedVariables) {
				if (variable.equals(String.valueOf(bean.getData()[1]))) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public static String replaceTableNames(String query, final Map<String, String> tableNameMap) {
		String tempQuery = query;
		for (Map.Entry<String, String> key : tableNameMap.entrySet()) {
			tempQuery = tempQuery.replaceAll("(?i:\\b" + key.getKey() + "\\b)", key.getValue());
		}
		return tempQuery;
	}

	public void callVariableBreakdownInsertService(GtnWsReportDataSelectionBean dataSelectionBean) {
		try {

			Map<String, String> tableMap = dataSelectionBean.getSessionTableMap();
			List<GtnReportVariableBreakdownLookupBean> variableBreakdown = dataSelectionBean
					.getVariableBreakdownSaveList();
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(
					replaceTableNames(GtnWsQueryConstants.VARIABLE_BREAKDOWN_TRUNCATE_QUERY, tableMap));
			if (variableBreakdown != null) {
				for (int i = 0; i < variableBreakdown.size(); i++) {
					Object[] obj = new Object[4];
					obj[0] = variableBreakdown.get(i).getMasterSid();
					obj[1] = variableBreakdown.get(i).getPeriod();
					obj[2] = variableBreakdown.get(i).getYear();
					obj[3] = Byte.valueOf((byte) ((Integer) variableBreakdown.get(i).getSelectedVariable()).intValue());
					gtnSqlQueryEngine.executeInsertOrUpdateQuery(

							replaceTableNames(GtnWsQueryConstants.VARIABLE_BREAKDOWN_SAVE_SERVICE_QUERY, tableMap), obj,
							new GtnFrameworkDataType[] { INTEGER, INTEGER, INTEGER, BYTE });
				}
			}
		} catch (GtnFrameworkGeneralException ex) {
			GTNLOGGER.error(ex.getMessage(), ex);
		}
	}

	@Override
	public void dataSelectionRegenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GTNLOGGER.info(" Regenerating Data Selection Logic ");
			GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getReportBean()
					.getDataSelectionBean();
			truncateTables(Arrays.asList(dataSelectionBean.getSessionTable(GtnWsQueryConstants.ST_CCP_HIERARCHY)));
			callCCPInsertService(gtnWsRequest);
			if (dataSelectionBean.getCustomViewMasterSid() != 0) {
				callInsertProcedure(dataSelectionBean);
				gtnReportJsonService.deleteFile(GtnWsQueryConstants.CUSTOM_CCP_FILE_NAME,
						dataSelectionBean.getSessionId());
				saveCustomCCPMap(dataSelectionBean);
			}
		} catch (GtnFrameworkGeneralException | IOException ex) {
			GTNLOGGER.error(ex.getMessage(), ex);
		}
		GTNLOGGER.info("Ending regeneration of Data Selection Logic ");
	}

	@Override
	public void regenerateTreeAndData(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GTNLOGGER.info(" Regenerating Custom view tables and Data ");
		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getReportBean()
				.getDataSelectionBean();

		resetToNewDatasetInReportDashboard(dataSelectionBean);
		GTNLOGGER.info(" Ending regeneration of Custom view tables and Data ");
	}

	public void regenerateChangedCustomViewTreeAndData(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GTNLOGGER.info(" Regenerating Custom view tables and Data after dataset is chabged in custom view");
		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest()
				.getDataSelectionBean();

		resetToNewDatasetInReportDashboard(dataSelectionBean);
		GTNLOGGER.info(" Ending regeneration of Custom view tables and Data after dataset is chabged in custom view");
	}

	private void resetToNewDatasetInReportDashboard(GtnWsReportDataSelectionBean dataSelectionBean) {
		try {
			if (dataSelectionBean.getCustomViewMasterSid() != 0) {
				truncateTables(Arrays
						.asList(dataSelectionBean.getSessionTable(GtnWsQueryConstants.CUSTOM_VARIABLE_HIERARCHY)));
				callInsertProcedure(dataSelectionBean);
				gtnReportJsonService.deleteFile(GtnWsQueryConstants.CUSTOM_CCP_FILE_NAME,
						dataSelectionBean.getSessionId());
				saveCustomCCPMap(dataSelectionBean);
			}
		} catch (GtnFrameworkGeneralException | IOException ex) {
			GTNLOGGER.error(ex.getMessage(), ex);
		}

	}

	private void truncateTables(List<String> tableNameList) {
		Optional.ofNullable(tableNameList).ifPresent(tableNames -> {
			try {
				for (String tableName : tableNames) {
					gtnSqlQueryEngine.executeInsertOrUpdateQuery(
							sqlService.getQuery(Arrays.asList(tableName), "getTruncateQuery"));
				}
			} catch (GtnFrameworkGeneralException e) {
				GTNLOGGER.error(e.getErrorMessage(), e);
			}
		});
	}

	private Map<Integer, Integer> getCustomViewDetailsVariableCount(int customViewMasterSid) {
		Object[] input = { customViewMasterSid };
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		Map<Integer, Integer> customViewDetails = new HashMap<>();
		try {
			List<Object[]> resultResult = (List<Object[]>) gtnSqlQueryEngine
					.executeSelectQuery(sqlService.getQuery("getQueryForCustomViewVariableCount"), input, type);
			getCustomViewDetailsVariableCount(resultResult, customViewDetails);
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error(e.getErrorMessage(), e);
		}
		return customViewDetails;
	}

	private void getCustomViewDetailsVariableCount(List<Object[]> resultResult,
			Map<Integer, Integer> customViewDetails) {
		if (Optional.ofNullable(resultResult).isPresent()) {
			setVariableCount(resultResult, customViewDetails);
		}
	}

	private void setVariableCount(List<Object[]> resultResult, Map<Integer, Integer> customViewDetails) {
		int lastOccurance = 0;
		for (int i = 0; i < resultResult.size(); i++) {
			Object[] obj = resultResult.get(i);
			if (!"V".equals(obj[1].toString()) && Integer.parseInt(obj[3].toString()) != 0) {
				for (int j = Integer.parseInt(obj[2].toString()); j > lastOccurance; j--) {
					customViewDetails.put(j, Integer.valueOf(obj[3].toString()));
				}
				lastOccurance = Integer.parseInt(obj[2].toString());
			}
		}
	}

	public List<Object[]> getCustomViewType(int customViewMasterSid) {
		try {
			return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(GtnWsQueryConstants.CUSTOM_VIEW_TYPE,
					new Object[] { customViewMasterSid }, new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error(e.getErrorMessage(), e);
			return Collections.emptyList();
		}
	}

	private void dataConvertors(GtnWsRecordBean recordBean, String key, Double data, String indicator, String levelName,
			boolean isTotalSpecialCondition) {
		if (("V".equals(indicator) && levelName.contains(GtnWsQueryConstants.PERCENTAGE_OPERATOR))
				|| key.contains("PER") || key.contains("RATE")) {
			recordBean.addProperties(key,
					GtnWsReportDecimalFormat.PERCENT.getFormattedValue(data) + GtnWsQueryConstants.PERCENTAGE_OPERATOR);
		} else if (("V".equals(indicator) && levelName.contains("Unit")) || isTotalSpecialCondition) {
			recordBean.addProperties(key, GtnWsReportDecimalFormat.UNITS.getFormattedValue(data));
		} else {
			recordBean.addProperties(key, GtnWsReportDecimalFormat.DOLLAR.getFormattedValue(data));
		}

	}
//To be changed ***	
	// Method to format values to non-decimal if user has selected Currency
	// Display
	// = No Conversion
	private void currencyTypeNoConversionDataConverters(GtnWsRecordBean gtnWsRecordBean, String mapKey,
			Double dataValue, String variableIndicator, String levelName, boolean isTotalSpecialCondition) {
		
		GTNLOGGER.info("mapkey: " + mapKey);
		GTNLOGGER.info("levelName: " + levelName);
		
		if (("V".equals(variableIndicator) && levelName.contains(GtnWsQueryConstants.PERCENTAGE_OPERATOR))
				|| mapKey.contains("PER") || mapKey.contains("RATE") || mapKey.contains("WEIGHTED")) {
			
			gtnWsRecordBean.addProperties(mapKey, GtnWsReportDecimalFormat.PERCENT.getFormattedValue(dataValue)
					+ GtnWsQueryConstants.PERCENTAGE_OPERATOR);
		} else if (("V".equals(variableIndicator) && levelName.contains("Unit")) || mapKey.contains("UNIT")
				|| isTotalSpecialCondition) {
			
				gtnWsRecordBean.addProperties(mapKey,
						GtnWsReportDecimalFormat.UNITS_NO_CONVERSION.getFormattedValue(dataValue));

		}
//		else if (isTotalSpecialCondition  && !mapKey.contains("UNIT") && !(mapKey.contains("PER") 
//				&& !mapKey.contains("WEIGHTED"))) {
//			
//			gtnWsRecordBean.addProperties(mapKey,
//					GtnWsReportDecimalFormat.DOLLAR_NO_CONVERSION.getFormattedValue(dataValue));
//			
//		}
		else {
			gtnWsRecordBean.addProperties(mapKey,
					GtnWsReportDecimalFormat.DOLLAR_NO_CONVERSION.getFormattedValue(dataValue));
		}
	}

	public static Double extractDouble(Object value) {
		return Optional.ofNullable(value).isPresent()
				? Double.parseDouble(String.valueOf(value).replaceAll("[^0-9,//.,-]|[,]", "")) : 0.0;
	}

	private boolean matchedFilteredHierarchyNo(Set<String> filteredHierarchyNo, String hierarchyNoFromFile,
			String indicator) {
		boolean result = false;
		if (filteredHierarchyNo.isEmpty() || filteredHierarchyNo.contains(hierarchyNoFromFile)) {
			result = true;
		} else if (indicator.equals("V")) {
			result = filteredHierarchyNo.parallelStream().filter(hierarchyNoFromFile::startsWith).count() > 0;
		}
		return result;
	}

}
