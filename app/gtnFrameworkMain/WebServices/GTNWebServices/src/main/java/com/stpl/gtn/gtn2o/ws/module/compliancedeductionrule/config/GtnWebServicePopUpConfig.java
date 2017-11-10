/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServicePopUpConfig implements GtnWsSearchQueryConfigLoader {
	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadRuleQueryConfig();
	}

	public static void loadRuleQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("systemId", configProvider.getColumnStringConfig("CDR_MODEL_SID", "CDR_D"));
		fieldToColumnDetailsMap.put("lineType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "LINE_TYPE",
						"LINE_TYPE", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("iGMSAssociation",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "IGMS", "IGMS",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("keyword",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "KEYWORD", "KEYWORD",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("operator",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "OPERATOR", "OPERATOR",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("comparison",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "COMPARISON",
						"COMPARISON", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("operatorOne",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "LOGICAL_OPERATOR",
						"LOGICAL_OPERATOR", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("value", configProvider.getColumnStringConfig("VALUE", "CDR_D"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("CDR_D.VALUE", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryContext.setCountQuery(" from CDR_DETAILS CDR_D\n"
				+ " JOIN HELPER_TABLE LINE_TYPE ON CDR_D.LINE_TYPE=LINE_TYPE.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE IGMS ON CDR_D.ITEM_GROUP_MS_ASSOCIATION=IGMS.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE KEYWORD ON CDR_D.KEYWORD=KEYWORD.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE OPERATOR ON CDR_D.OPERATOR=OPERATOR.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE COMPARISON ON CDR_D.COMPARISON=COMPARISON.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE LOGICAL_OPERATOR ON CDR_D.LOGICAL_OPERATOR=LOGICAL_OPERATOR.HELPER_TABLE_SID  ");

		gtnWebServiceSearchQueryContext.setSearchQuery("   from CDR_DETAILS CDR_D\n"
				+ " JOIN HELPER_TABLE LINE_TYPE ON CDR_D.LINE_TYPE=LINE_TYPE.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE IGMS ON CDR_D.ITEM_GROUP_MS_ASSOCIATION=IGMS.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE KEYWORD ON CDR_D.KEYWORD=KEYWORD.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE OPERATOR ON CDR_D.OPERATOR=OPERATOR.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE COMPARISON ON CDR_D.COMPARISON=COMPARISON.HELPER_TABLE_SID\n"
				+ " JOIN HELPER_TABLE LOGICAL_OPERATOR ON CDR_D.LOGICAL_OPERATOR=LOGICAL_OPERATOR.HELPER_TABLE_SID ");

		searchQueryConfigMap.put("cDRPopUpConfig", gtnWebServiceSearchQueryContext);

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}

}
