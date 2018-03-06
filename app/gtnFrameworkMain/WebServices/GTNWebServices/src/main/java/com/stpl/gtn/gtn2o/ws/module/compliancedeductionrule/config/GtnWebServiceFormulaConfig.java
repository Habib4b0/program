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
public class GtnWebServiceFormulaConfig implements GtnWsSearchQueryConfigLoader {
	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
	static {
		loadRuleQueryConfig();
		loadFormulaPopupQueryConfig();
	}
       

	public static void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		GtnWebServiceFormulaConfig.searchQueryConfigMap = searchQueryConfigMap;
	}

	public static Map<String, GtnWsSearchQueryConfig> loadRuleQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContextRule = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapRule = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMapRule.put("systemId", configProvider.getColumnStringConfig("FORECASTING_FORMULA_SID", "FF"));
		fieldToColumnDetailsMapRule.put("formulaType", configProvider.getColumnStringConfig("DESCRIPTION", "FORMULA_TYPE",
				"FORMULA_TYPE", "HELPER_TABLE_SID"));
		fieldToColumnDetailsMapRule.put("formulaNo", configProvider.getColumnStringConfig("FORMULA_NO", "FF"));
		fieldToColumnDetailsMapRule.put("formulaName", configProvider.getColumnStringConfig("FORMULA_NAME", "FF"));
		fieldToColumnDetailsMapRule.put("formula", configProvider.getColumnStringConfig("FORMULA", "FF"));

		gtnWebServiceSearchQueryContextRule.setFieldToColumnDetailsMap(fieldToColumnDetailsMapRule);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("FF.FORMULA", "ASC"));
		gtnWebServiceSearchQueryContextRule.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryContextRule.setCountQuery(" FROM  dbo.FORECASTING_FORMULA FF\n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_HELPER_TABLE_FORMULA_TYP
				+ GtnFrameworkWebserviceConstant.ON_FORMULA_TYPEHELPER_TABLE_SID);

		gtnWebServiceSearchQueryContextRule.setSearchQuery("  FROM  dbo.FORECASTING_FORMULA FF\n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_HELPER_TABLE_FORMULA_TYP
				+ GtnFrameworkWebserviceConstant.ON_FORMULA_TYPEHELPER_TABLE_SID);

		searchQueryConfigMap.put("formulaPopUpConfig", gtnWebServiceSearchQueryContextRule);
                return searchQueryConfigMap;

	}

	public static Map<String, GtnWsSearchQueryConfig> loadFormulaPopupQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("systemId", configProvider.getColumnStringConfig("FORECASTING_FORMULA_SID", "FF"));
		fieldToColumnDetailsMap.put("formula", configProvider.getColumnStringConfig("FORMULA", "FF"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("FF.FORMULA", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryContext.setCountQuery(" FROM  dbo.FORECASTING_FORMULA FF\n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_HELPER_TABLE_FORMULA_TYP
				+ GtnFrameworkWebserviceConstant.ON_FORMULA_TYPEHELPER_TABLE_SID);

		gtnWebServiceSearchQueryContext.setSearchQuery("  FROM  dbo.FORECASTING_FORMULA FF\n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_HELPER_TABLE_FORMULA_TYP
				+ GtnFrameworkWebserviceConstant.ON_FORMULA_TYPEHELPER_TABLE_SID);
		searchQueryConfigMap.put("formulaPopUpDetailsConfig", gtnWebServiceSearchQueryContext);
                return searchQueryConfigMap;

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}
}
