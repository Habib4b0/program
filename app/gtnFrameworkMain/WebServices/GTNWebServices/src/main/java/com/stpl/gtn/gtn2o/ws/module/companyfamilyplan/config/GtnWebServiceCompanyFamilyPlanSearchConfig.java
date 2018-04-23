    package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.constants.GtnWsCfpQueryContants;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

public class GtnWebServiceCompanyFamilyPlanSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceCompanyFamilyPlanSearchConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadSearchQueryConfig();
			loadCompanyAdditionSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {

			gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsCfpQueryContants.GTN_CFP_COUNT);
			gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_COUNT_QUERY);
			gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_SEARCH_QUERY);

			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>(18);
			fieldToColumnDetailsMap.put("companyFamilyPlanSystemId",
					configProvider.getColumnIntegerConfig("CFP_MODEL_SID", "CFP"));
			fieldToColumnDetailsMap.put("companyFamilyPlanId", configProvider.getColumnStringConfig("CFP_ID", "CFP"));
			fieldToColumnDetailsMap.put("companyFamilyPlanNo",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFP"));
			fieldToColumnDetailsMap.put("companyFamilyPlanName",
					configProvider.getColumnStringConfig("CFP_NAME", "CFP"));

			GtnWsColumnDetailsConfig cftTypeColumnConfig = configProvider.getColumnHelperConfig("CFP_TYPE", "CFP");
			cftTypeColumnConfig.setHelperTableAliasName("companyFamilyPlanTypeHelper");
			cftTypeColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
			fieldToColumnDetailsMap.put("companyFamilyPlanType", cftTypeColumnConfig);

			GtnWsColumnDetailsConfig cftStatusColumnConfig = configProvider.getColumnHelperConfig("CFP_STATUS", "CFP");
			cftStatusColumnConfig.setHelperTableAliasName("cfpStatusHelper");
			cftStatusColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
			fieldToColumnDetailsMap.put("companyFamilyPlanStatus", cftStatusColumnConfig);

			GtnWsColumnDetailsConfig cfpCategoryColumnConfig = configProvider.getColumnHelperConfig("CFP_CATEGORY",
					"CFP");
			cfpCategoryColumnConfig.setHelperTableAliasName("cfpCategoryHelper");
			cfpCategoryColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
			fieldToColumnDetailsMap.put("companyFamilyPlanCategory", cfpCategoryColumnConfig);

			fieldToColumnDetailsMap.put("companyFamilyPlanStartDate",
					configProvider.getColumnDateConfig("CFP_START_DATE", "CFP"));
			fieldToColumnDetailsMap.put("companyFamilyPlanEndDate",
					configProvider.getColumnDateConfig("CFP_END_DATE", "CFP"));

			GtnWsColumnDetailsConfig cfpDesignationColumnConfig = configProvider
					.getColumnHelperConfig("CFP_DESIGNATION", "CFP");
			cfpDesignationColumnConfig.setHelperTableAliasName("cfpDesignationHelper");
			cfpDesignationColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
			fieldToColumnDetailsMap.put("companyFamilyPlanDesignation", cfpDesignationColumnConfig);

			fieldToColumnDetailsMap.put("parentCompanyFamilyPlanId", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "parenCfp", "parenCfpId"));
			fieldToColumnDetailsMap.put("parentCompanyFamilyPlanName", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "parenCfp", "parenCfpNo"));
			fieldToColumnDetailsMap.put("cfpmodifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "CFP"));
			fieldToColumnDetailsMap.put("cfpmodifiedBy", configProvider.getColumnUserConfig("MODIFIED_BY", "CFP"));
			fieldToColumnDetailsMap.put("cfpcreatedBy", configProvider.getColumnUserConfig("CREATED_BY", "CFP"));
			fieldToColumnDetailsMap.put("cfpcreatedDate", configProvider.getColumnDateConfig("CREATED_DATE", "CFP"));
			fieldToColumnDetailsMap.put("companyId",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_ID, "CM"));
			fieldToColumnDetailsMap.put("companyNo",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
			fieldToColumnDetailsMap.put("companyName",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CM"));
			gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("CFP.CFP_ID", "ASC"));
			gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

			gtnWebServiceSearchQueryConfig.setWhereClauseList(
					Arrays.asList(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_SEARCH_QUERY_WHERE_CLAUSE));

			searchQueryConfigMap.put("cfpSearchQuery", gtnWebServiceSearchQueryConfig);

			GtnWsSearchQueryConfig parentGtnWebServiceSearchQueryConfig = gtnWebServiceSearchQueryConfig;

			Map<String, GtnWsColumnDetailsConfig> parentfieldToColumnDetailsMap = parentGtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			parentfieldToColumnDetailsMap.put("parentCfpPopupCFPId",
					configProvider.getColumnStringConfig("CFP_ID", "CFP"));
			parentfieldToColumnDetailsMap.put("parentCfpPopupCFPNo",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFP"));
			parentfieldToColumnDetailsMap.put("parentCfpPopupCFPName",
					configProvider.getColumnStringConfig("CFP_NAME", "CFP"));
			parentfieldToColumnDetailsMap.put("parentCfpPopupCFPType",
					configProvider.getColumnHelperConfig("CFP_TYPE", "CFP"));
			parentfieldToColumnDetailsMap.put("parentCfpPopupCFPStatus",
					configProvider.getColumnHelperConfig("CFP_STATUS", "CFP"));
			parentfieldToColumnDetailsMap.put("parentCfpSearchcompanyId",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_ID, "CM"));
			parentfieldToColumnDetailsMap.put("parentCfpSearchcompanyNo",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
			parentfieldToColumnDetailsMap.put("parentCfpSearchcompanyName",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CM"));
			searchQueryConfigMap.put("parentCfpSearchQuery", parentGtnWebServiceSearchQueryConfig);

		} catch (Exception ex) {
			logger.error("error while cloning the GtnWsSearchQueryConfig");

		}
		return searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadCompanyAdditionSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_ADDITION_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_ADDITION_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("companyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "cm"));
		fieldToColumnDetailsMap.put("companyId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_ID, "cm"));
		fieldToColumnDetailsMap.put("companyNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "cm"));
		fieldToColumnDetailsMap.put("companyName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "cm"));
		fieldToColumnDetailsMap.put("companyType", configProvider.getColumnHelperConfig("COMPANY_TYPE", "cm"));
		fieldToColumnDetailsMap.put("companyStatus", configProvider.getColumnHelperConfig("COMPANY_STATUS", "cm"));
		fieldToColumnDetailsMap.put("companyCategory", configProvider.getColumnHelperConfig("COMPANY_CATEGORY", "CM"));
		fieldToColumnDetailsMap.put("companyGroup", configProvider.getColumnHelperConfig("COMPANY_GROUP", "CM"));
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CM.COMPANY_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWebServiceSearchQueryConfig.setWhereClauseList(
				Arrays.asList(GtnWsCfpQueryContants.GTN_CFP_COMPANIES_ADDITION_SEARCH_QUERY_WHERE_CLAUSE));

		searchQueryConfigMap.put("cfpCompanyAdditionSearchQuery", gtnWebServiceSearchQueryConfig);

		return searchQueryConfigMap;
	}

}
