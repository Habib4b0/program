package com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;

public class GtnWebServiceCDRConfig implements GtnWsSearchQueryConfigLoader {
	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
	static {
		loadSearchQueryConfig();
	}

	public static void loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("systemId", configProvider.getColumnStringConfig("CDR_MODEL_SID", "CDRM"));
		fieldToColumnDetailsMap.put("ruleType",
				configProvider.getColumnStringConfig("DESCRIPTION", "RULE_TYPE", "RULE_TYPE", "HELPER_TABLE_SID"));
		fieldToColumnDetailsMap.put("ruleNo", configProvider.getColumnStringConfig("RULE_NO", "CDRM"));
		fieldToColumnDetailsMap.put("ruleName", configProvider.getColumnStringConfig("RULE_NAME", "CDRM"));
		fieldToColumnDetailsMap.put("ruleCategory", configProvider.getColumnStringConfig("DESCRIPTION", "RULE_CATEGORY",
				"RULE_CATEGORY", "HELPER_TABLE_SID"));
		fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnStringConfig("CREATED_DATE", "CDRM"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("CREATED_BY", "CDRM"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnStringConfig("MODIFIED_DATE", "CDRM"));
		fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnStringConfig("MODIFIED_BY", "CDRM"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("CDRM.RULE_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryContext.setCountQuery("  FROM CDR_MODEL CDRM \n"
				+ "JOIN HELPER_TABLE RULE_TYPE ON RULE_TYPE.HELPER_TABLE_SID=CDRM.RULE_TYPE\n"
				+ "JOIN HELPER_TABLE RULE_CATEGORY ON RULE_CATEGORY.HELPER_TABLE_SID=CDRM.RULE_CATEGORY ");

		gtnWebServiceSearchQueryContext.setSearchQuery("  FROM CDR_MODEL CDRM \n"
				+ "JOIN HELPER_TABLE RULE_TYPE ON RULE_TYPE.HELPER_TABLE_SID=CDRM.RULE_TYPE\n"
				+ "JOIN HELPER_TABLE RULE_CATEGORY ON RULE_CATEGORY.HELPER_TABLE_SID=CDRM.RULE_CATEGORY ");

		searchQueryConfigMap.put("complianceDeductionAndRulesCloseSearch", gtnWebServiceSearchQueryContext);

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}

}
