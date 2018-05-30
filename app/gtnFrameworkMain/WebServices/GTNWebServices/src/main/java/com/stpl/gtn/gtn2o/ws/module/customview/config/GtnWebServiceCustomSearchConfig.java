package com.stpl.gtn.gtn2o.ws.module.customview.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.module.customview.constants.GtnWsCustomViewConstants;

public class GtnWebServiceCustomSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadCustomViewSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadCustomViewSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceCustSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceCustSearchQueryConfig.setCountQuery(GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SEARCH_QUERY);
		gtnWebServiceCustSearchQueryConfig.setSearchQuery(GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SEARCH_QUERY);
                gtnWebServiceCustSearchQueryConfig.setCountQuerySelectClause("Select  count(distinct cvm.CUST_VIEW_MASTER_SID) ");
		GtnWsSearchQueryConfigProvider searchConfigProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
                
		fieldToColumnDetailsMap.put("customViewMasterSId",
				searchConfigProvider.getColumnIntegerConfig("CUST_VIEW_MASTER_SID", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.TREE_VIEW_NAME, searchConfigProvider.getColumnStringConfig("CUST_VIEW_NAME", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION, searchConfigProvider.getColumnStringConfig("CUST_VIEW_DESCRIPTION", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE, searchConfigProvider.getColumnStringConfig("CUST_VIEW_TYPE", GtnWsCustomViewConstants.CVM));
                fieldToColumnDetailsMap.put( GtnFrameworkCommonConstants.CUTOMER_RELATION, searchConfigProvider.getColumnStringConfig(GtnWsCustomViewConstants.RELATIONSHIP_NAME, "RBC","CUSTOMER_RELATIONSHIP_SID",GtnWsCustomViewConstants.RELATIONSHIP_BUILDER_SID));
                fieldToColumnDetailsMap.put( GtnFrameworkCommonConstants.PRODUCT_RELATION, searchConfigProvider.getColumnStringConfig(GtnWsCustomViewConstants.RELATIONSHIP_NAME, "RBP","PRODUCT_RELATIONSHIP_SID",GtnWsCustomViewConstants.RELATIONSHIP_BUILDER_SID));
		
                fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CUSTOM_VIEW_SCREEN_NAME, searchConfigProvider.getColumnStringConfig("SCREEN_NAME", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put("createdDate", searchConfigProvider.getColumnDateConfig("CREATED_DATE", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put("createdBy", searchConfigProvider.getColumnUserConfig("CREATED_BY", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put("modifiedDate", searchConfigProvider.getColumnDateConfig("MODIFIED_DATE", GtnWsCustomViewConstants.CVM));
		fieldToColumnDetailsMap.put("modifiedBy", searchConfigProvider.getColumnUserConfig("MODIFIED_BY", GtnWsCustomViewConstants.CVM));
                fieldToColumnDetailsMap.put( GtnFrameworkCommonConstants.CUTOMER_RELATION_SID, searchConfigProvider.getColumnStringConfig("CUSTOMER_RELATIONSHIP_SID", GtnWsCustomViewConstants.CVM));
                fieldToColumnDetailsMap.put( GtnFrameworkCommonConstants.PRODUCT_RELATION_SID, searchConfigProvider.getColumnStringConfig("PRODUCT_RELATIONSHIP_SID", GtnWsCustomViewConstants.CVM));

		gtnWebServiceCustSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByList = new ArrayList<>();
		orderByList.add(new GtnWebServiceOrderByCriteria("CVM.CUST_VIEW_MASTER_SID", "ASC"));
		gtnWebServiceCustSearchQueryConfig.setOrderByClause(orderByList);


		searchQueryConfigMap.put("customViewSearchQuery", gtnWebServiceCustSearchQueryConfig);

		return searchQueryConfigMap;
	}

}
