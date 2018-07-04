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
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomCCPListDetails;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.service.transform.GtnWsReportRightTableResultTransformer;
import com.stpl.gtn.gtn2o.ws.report.service.transform.GtnWsReportVaribleRowResultTransformer;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportRightTableLoadDataService {
	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	GtnWsReportRightTableResultTransformer columnTransFormer;

	@Autowired
	GtnWsReportVaribleRowResultTransformer rowTransformer;

	public Map<String, Map<String, Double>> getDataFromBackend(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsReportCustomCCPListDetails bean, String[] customViewTypeDataArray) {
		try {

			String hierarchyNo = bean.getHierarchyNo();
			int levelNo = bean.getLevelNo();
			boolean isVAriableRow = false;
			if (bean.getData()[5].equals("V")) {
				isVAriableRow = true;
			}
			// List<Object[]> customviewData = (List<Object[]>)
			// gtnSqlQueryEngine.executeSelectQuery(
			// GtnWsQueryConstants.CUSTOM_VIEW_TYPE,
			// new Object[] {
			// gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean().getCustomViewMasterSid()
			// },
			// new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
			//
			// String customViewTypeInBackend = String.valueOf(customviewData.get(0));
			// String[] customViewTypeDataArray = customViewTypeInBackend.split("~");
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
			e.printStackTrace();
		}

		return Collections.emptyMap();

	}

	public String getQueryFromProcedure(GtnUIFrameworkWebserviceRequest gtnWsRequest, String hierarchyNo, int levelNo,
			String customViewType) throws GtnFrameworkGeneralException {

		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean();

		String frequency = dataSelectionBean.getFrequencyName();
		GtnWsReportDashboardBean dashboardBean = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean();

		int salesInClusion = dashboardBean.getSalesInclusion();
		int deductionInclusion = dashboardBean.getDeductionInclusion();
		String annualTotals = dashboardBean.getAnnualTotals();

		String currencyConversion = dashboardBean.getCurrencyConversion().isEmpty()
				|| "0".equals(dashboardBean.getCurrencyConversion()) ? null : dashboardBean.getCurrencyConversion();

		String procedure = "PRC_REPORT_DASHBOARD_GENERATE ?,?,:comparisonBasis:,:ccpComp:,:salesInclusion:,:deductionIncl:,null,?,601,672,?,?,?,null,?,?,?";
		procedure = procedure.replaceAll(":salesInclusion:",
				salesInClusion == -1 ? "NULL" : String.valueOf(salesInClusion));
		procedure = procedure.replaceAll(":deductionIncl:",
				deductionInclusion == -1 ? "NULL" : String.valueOf(deductionInclusion));
		String ccpFilter = "NULL";
		if (dashboardBean.getCcpDetailsSidList() != null && !dashboardBean.getCcpDetailsSidList().isEmpty()) {
			ccpFilter = StringUtils.join(dashboardBean.getCcpDetailsSidList(), ",");
		}
		procedure = procedure.replaceAll(":ccpComp:", ccpFilter);
		String comparisonBasis = dashboardBean.getComparisonBasis().isEmpty() ? "NULL"
				: dashboardBean.getComparisonBasis();
		procedure = procedure.replaceAll(":comparisonBasis:", comparisonBasis);
		String hierarchy = hierarchyNo == null || hierarchyNo.isEmpty() ? null : hierarchyNo;
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
			queryBuilder.append(clobToString(dataClob)).append(" ");
			;
		}

		return queryBuilder.toString();

		// PRC_REPORT_DASHBOARD_GENERATE ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?
		// PRC_REPORT_DASHBOARD_GENERATE
		// 'QUARTERLY',null,null,null,0,0,null,null,601,672,47,1,'3e5ee4af_57f4_4a',null,189858,'','Static'
		// select SUBSTRING(CUST_VIEW_TYPE, 7, LEN(CUST_VIEW_TYPE)) AS typeS from
		// CUST_VIEW_MASTER where CUST_VIEW_MASTER_SID = 61
	}

	private String clobToString(Clob data) {
		StringBuilder sb = new StringBuilder();
		try {
			Reader reader = data.getCharacterStream();
			BufferedReader br = new BufferedReader(reader);

			String line;
			while (null != (line = br.readLine())) {
				sb.append(line).append(" ");
			}
			br.close();
		} catch (SQLException | IOException ex) {
		}
		return sb.toString();
	}

}
