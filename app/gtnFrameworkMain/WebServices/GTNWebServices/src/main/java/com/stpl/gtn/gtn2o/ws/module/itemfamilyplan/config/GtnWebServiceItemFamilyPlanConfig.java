package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.constants.GtnWsIfpQueryContants;

public class GtnWebServiceItemFamilyPlanConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> ifpSearchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (ifpSearchQueryConfigMap == null) {
			ifpSearchQueryConfigMap = new HashMap<>();
			loadSearchQueryConfig();
		}
		return ifpSearchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.ifpSearchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {

			gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsIfpQueryContants.GTN_IFP_SEARCH_QUERY_SELECT);
			gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsIfpQueryContants.GTN_IFP_SEARCH_QUERY_COUNT);
			gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsIfpQueryContants.GTN_IFP_SEARCH_QUERY);

			Map<String, GtnWsColumnDetailsConfig> ifpColumnDetailsMap = new HashMap<>();
			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			ifpColumnDetailsMap.put("itemFamilyplanSystemId",
					configProvider.getColumnIntegerConfig("IFP_MODEL_SID", "IFP"));
			ifpColumnDetailsMap.put("ifpId", configProvider.getColumnStringConfig("IFP_ID", "IFP"));
			ifpColumnDetailsMap.put("ifpNo", configProvider.getColumnStringConfig("IFP_NO", "IFP"));
			ifpColumnDetailsMap.put("ifpName", configProvider.getColumnStringConfig("IFP_NAME", "IFP"));
			ifpColumnDetailsMap.put("ifpType", configProvider.getColumnHelperConfig("IFP_TYPE", "IFP"));
			ifpColumnDetailsMap.put("ifpStatus", configProvider.getColumnHelperConfig("IFP_STATUS", "IFP"));
			ifpColumnDetailsMap.put("ifpCategory", configProvider.getColumnHelperConfig("IFP_CATEGORY", "IFP"));
			ifpColumnDetailsMap.put("itemFamilyplanStartDate",
					configProvider.getColumnDateConfig("IFP_START_DATE", "IFP"));
			ifpColumnDetailsMap.put("itemFamilyplanEndDate",
					configProvider.getColumnDateConfig("IFP_END_DATE", "IFP"));
			ifpColumnDetailsMap.put("ifpDesignation",
					configProvider.getColumnHelperConfig("IFP_DESIGNATION", "IFP"));
			ifpColumnDetailsMap.put("totalDollarCommitment",
					configProvider.getColumnStringConfig("TOTAL_DOLLAR_COMMITMENT", "IFP"));
			ifpColumnDetailsMap.put("commitmentPeriod",
					configProvider.getColumnStringConfig("COMMITMENT_PERIOD", "IFP"));
			ifpColumnDetailsMap.put("totalVolumeCommitment",
					configProvider.getColumnStringConfig("TOTAL_VOLUME_COMMITMENT", "IFP"));
			ifpColumnDetailsMap.put("totalMarketshareCommitment",
					configProvider.getColumnStringConfig("TOTAL_MARKETSHARE_COMMITMENT", "IFP"));
			ifpColumnDetailsMap.put("ifpcreatedBy", configProvider.getColumnStringConfig("CREATED_BY", "IFP"));
			ifpColumnDetailsMap.put("ifpcreatedDate", configProvider.getColumnDateConfig("CREATED_DATE", "IFP"));
			ifpColumnDetailsMap.put("parentItemFamilyplanId",
					configProvider.getColumnStringConfig("PARENT_IFP_ID", "IFP", "parenCfpId"));
			ifpColumnDetailsMap.put("parentIetmFamilyplanName",
					configProvider.getColumnStringConfig("PARENT_IFP_NAME", "IFP", "parenCfpName"));
			ifpColumnDetailsMap.put("itemId", configProvider.getColumnStringConfig("ITEM_ID", "IM"));
			ifpColumnDetailsMap.put("itemNo", configProvider.getColumnStringConfig("ITEM_NO", "IM"));
			ifpColumnDetailsMap.put("itemName", configProvider.getColumnStringConfig("ITEM_NAME", "IM"));

			gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(ifpColumnDetailsMap);

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("IFP.IFP_ID", "ASC"));
			gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

			gtnWebServiceSearchQueryConfig
					.setWhereClauseList(Arrays.asList(GtnWsIfpQueryContants.GTN_IFP_SEARCH_QUERY_WHERE));

			ifpSearchQueryConfigMap.put("ifpSearchQuery", gtnWebServiceSearchQueryConfig);
			GtnWsSearchQueryConfig parentgtnWebServiceSearchQueryConfig = gtnWebServiceSearchQueryConfig;

			Map<String, GtnWsColumnDetailsConfig> parentFieldToColumnDetailsMap = parentgtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			parentFieldToColumnDetailsMap.put("parentifpId", configProvider.getColumnStringConfig("IFP_ID", "IFP"));
			parentFieldToColumnDetailsMap.put("parentIfpNo", configProvider.getColumnStringConfig("IFP_NO", "IFP"));
			parentFieldToColumnDetailsMap.put("parentIfpName", configProvider.getColumnStringConfig("IFP_NAME", "IFP"));
			parentFieldToColumnDetailsMap.put("parentIfpType", configProvider.getColumnHelperConfig("IFP_TYPE", "IFP"));
			parentFieldToColumnDetailsMap.put("parentIfpStatus",
					configProvider.getColumnHelperConfig("IFP_STATUS", "IFP"));

			ifpSearchQueryConfigMap.put("parentIfpSearchQuery", parentgtnWebServiceSearchQueryConfig);

		} catch (Exception ex) {
			Logger.getLogger(GtnWebServiceItemFamilyPlanConfig.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ifpSearchQueryConfigMap;
	}

}