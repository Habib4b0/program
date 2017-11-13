package com.stpl.gtn.gtn2o.ws.module.companymaster.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;

public class GtnWsCMasterFinancialCloseConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
	static {
		loadSearchQueryConfig();
	}

	public static void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		GtnWsCMasterFinancialCloseConfig.searchQueryConfigMap = searchQueryConfigMap;
	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}

	public static Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("companyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "cfc"));
		fieldToColumnDetailsMap.put("modeDescription", configProvider.getColumnStringConfig("DESCRIPTION", "ht"));
		fieldToColumnDetailsMap.put("calendarDesc", configProvider.getColumnStringConfig("CALENDAR", "CFC"));
		fieldToColumnDetailsMap.put("statusDesc", configProvider.getColumnStringConfig("DESCRIPTION", "ht1"));
		fieldToColumnDetailsMap.put("year", configProvider.getColumnStringConfig("YEAR", "PER"));
		fieldToColumnDetailsMap.put("month",
				configProvider.getColumnStringConfig("DateName( month , DateAdd( month , PER.MONTH , -1 ) )", null));
		fieldToColumnDetailsMap.put("statusPeriodDate",
				configProvider.getColumnDateConfig("STATUS_PERIOD_DATE", "CFC"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "cfc"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("CREATED_BY", "cfc"));
		fieldToColumnDetailsMap.put("userId", configProvider.getColumnIntegerConfig("USERS_ID", "cfc"));
		fieldToColumnDetailsMap.put("SessionId", configProvider.getColumnIntegerConfig("SESSION_ID", "cfc"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CFC.STATUS_PERIOD_DATE", "DESC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(orderByClauseList);

		// Financial Close Table Load Data Starts

		gtnWebServiceSearchQueryContext.setCountQuery(" FROM IMTD_COMPANY_FINANCIAL_CLOSE as CFC \n"
				+ "        join Helper_table as ht on CFC.MODE=ht.HELPER_TABLE_SID\n"
				+ "        JOIN PERIOD as PER on PER.PERIOD_SID=CFC.PERIOD_SID\n"
				+ "        JOIN HELPER_TABLE as ht1 ON ht1.HELPER_TABLE_SID=cfc.STATUS ");

		gtnWebServiceSearchQueryContext.setSearchQuery(" FROM IMTD_COMPANY_FINANCIAL_CLOSE as CFC \n"
				+ "        join Helper_table as ht on CFC.MODE=ht.HELPER_TABLE_SID\n"
				+ "        JOIN PERIOD as PER on PER.PERIOD_SID=CFC.PERIOD_SID\n"
				+ "        JOIN HELPER_TABLE as ht1 ON ht1.HELPER_TABLE_SID=cfc.STATUS");
		searchQueryConfigMap.put("FinancialCloseTableLoadData", gtnWebServiceSearchQueryContext);

		return searchQueryConfigMap;

		// Financial Close Load Data Ends

	}

}
