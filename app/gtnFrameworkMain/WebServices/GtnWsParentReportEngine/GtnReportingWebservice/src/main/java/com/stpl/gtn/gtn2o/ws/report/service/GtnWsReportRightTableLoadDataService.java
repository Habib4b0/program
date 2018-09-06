package com.stpl.gtn.gtn2o.ws.report.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPListDetails;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.service.transform.GtnWsReportRightTableResultTransformer;
import com.stpl.gtn.gtn2o.ws.report.service.transform.GtnWsReportVaribleRowResultTransformer;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportRightTableLoadDataService {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportRightTableLoadDataService.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportRightTableResultTransformer columnTransFormer;

	@Autowired
	private GtnWsReportVaribleRowResultTransformer rowTransformer;

	public GtnWsReportRightTableLoadDataService() {
		super();
	}

	public Map<String, Map<String, Double>> getDataFromBackend(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsReportCustomCCPListDetails bean, String[] customViewTypeDataArray) {
		try {

			String hierarchyNo = bean.getHierarchyNo();
			int levelNo = bean.getLevelNo();
			ResultTransformer transformer = rowTransformer;

			String customViewType = "";
			if (customViewTypeDataArray.length == 3) {
				customViewType = customViewTypeDataArray[1];
				if (customViewTypeDataArray[2].equals("Columns")) {
					customViewType = "VARIABLE";
					transformer = columnTransFormer;

				}
			}

			String query = getQueryFromProcedure(gtnWsRequest, hierarchyNo, levelNo, customViewType);
			List<?> object = gtnSqlQueryEngine.executeSelectQuery(query, new Object[] {}, new GtnFrameworkDataType[] {},
					transformer);
			return (Map<String, Map<String, Double>>) object.get(0);
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(e.getErrorMessage(), e);
		}
		return Collections.emptyMap();
	}

	public String getQueryFromProcedure(GtnUIFrameworkWebserviceRequest gtnWsRequest, String hierarchyNo, int levelNo,
			String customViewType) throws GtnFrameworkGeneralException {

		GtnWsReportDashboardBean dashboardBean = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean();
		int salesInClusion = dashboardBean.getSalesInclusion();
		int deductionInclusion = dashboardBean.getDeductionInclusion();
		String annualTotals = dashboardBean.getAnnualTotals();
		String frequency = dashboardBean.getSelectFreqString();
		String currencyConversion = dashboardBean.getCurrencyConversion().isEmpty()
				|| "0".equals(dashboardBean.getCurrencyConversion()) ? null : dashboardBean.getCurrencyConversion();

		String procedure = "PRC_REPORT_DASHBOARD_GENERATE ?,?,:comparisonBasis:,:ccpComp:,:salesInclusion:,:deductionIncl:,null,?,:startPerioSid:,:endPeriodSid:,?,?,?,null,?,?,?";
		procedure = procedure.replaceAll(":salesInclusion:",
				salesInClusion == -1 ? "NULL" : String.valueOf(salesInClusion));
		procedure = procedure.replaceAll(":deductionIncl:",
				deductionInclusion == -1 ? "NULL" : String.valueOf(deductionInclusion));
		String ccpFilter = "NULL";
		if (dashboardBean.getCcpDetailsSidList() != null && !dashboardBean.getCcpDetailsSidList().isEmpty()) {
			ccpFilter = StringUtils.join(dashboardBean.getCcpDetailsSidList(), ",");
		}

		ccpFilter = !ccpFilter.equals("NULL") ? "'" + ccpFilter + "'" : "NULL";
		procedure = procedure.replaceAll(":ccpComp:", ccpFilter);
		procedure = procedure.replaceAll(":startPerioSid:", String.valueOf(dashboardBean.getPeriodRangeFromSid()));
		procedure = procedure.replaceAll(":endPeriodSid:", String.valueOf(dashboardBean.getPeriodRangeToSid()));
		String comparisonBasis = dashboardBean.getComparisonBasis().isEmpty()
				|| "-Select one-".equals(dashboardBean.getComparisonBasis()) ? "NULL"
						: dashboardBean.getComparisonBasis();
		procedure = procedure.replaceAll(":comparisonBasis:", comparisonBasis);
		String hierarchy = hierarchyNo == null || hierarchyNo.isEmpty() ? null : hierarchyNo;

		@SuppressWarnings("unchecked")
		List<Object[]> outputFromProcedure = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(procedure,
				new Object[] { frequency, annualTotals, currencyConversion,
						gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getCustomViewMasterSid(), levelNo,
						gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getSessionId(),
						Integer.valueOf(gtnWsRequest.getGtnWsGeneralRequest().getUserId()), hierarchy, customViewType },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
						GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.STRING });

		String declareStatement = "declare @COMPARISION_BASIS varchar(100) = '" + comparisonBasis + "',@level_no int = "
				+ levelNo + " , @HIERARCHY_NO varchar(100) = '" + hierarchyNo + "'";
		Object[] stringData = outputFromProcedure.get(0);
		StringBuilder queryBuilder = new StringBuilder(declareStatement);
		for (Object tempData : stringData) {
			Clob dataClob = (Clob) tempData;
			queryBuilder.append(clobToString(dataClob)).append(Character.MIN_VALUE);
		}

		return queryBuilder.toString();
	}

	private String clobToString(Clob data) {
		StringBuilder sb = new StringBuilder();
		try {
			Reader reader = data.getCharacterStream();
			BufferedReader br = new BufferedReader(reader);

			String line;
			while (null != (line = br.readLine())) {
				sb.append(line).append(Character.MIN_VALUE);
			}
			br.close();
		} catch (SQLException | IOException ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
		return sb.toString();
	}

}
