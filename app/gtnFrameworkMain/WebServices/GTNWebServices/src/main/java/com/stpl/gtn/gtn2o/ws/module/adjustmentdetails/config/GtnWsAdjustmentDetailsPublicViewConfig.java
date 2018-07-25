/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.config;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.constants.GtnWsAdjusmtmentDetailsQueryConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsAdjustmentDetailsPublicViewConfig implements GtnWsSearchQueryConfigLoader {

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
        gtnWebServiceCustSearchQueryConfig.setCountQuery(GtnWsAdjusmtmentDetailsQueryConstants.SELECT_PRIVATE_PUBLIC_VIEW);
        gtnWebServiceCustSearchQueryConfig.setSearchQuery(GtnWsAdjusmtmentDetailsQueryConstants.SELECT_PRIVATE_PUBLIC_VIEW);
        gtnWebServiceCustSearchQueryConfig.setCountQuerySelectClause("Select  count(distinct ARM_VIEW_MASTER_SID) ");
        GtnWsSearchQueryConfigProvider searchConfigProvider = GtnWsSearchQueryConfigProvider.getInstance();
        Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

        fieldToColumnDetailsMap.put("viewMasterSId",
                searchConfigProvider.getColumnIntegerConfig("ARM_VIEW_MASTER_SID", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_NAME, searchConfigProvider.getColumnStringConfig("VIEW_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_TYPE, searchConfigProvider.getColumnStringConfig("VIEW_TYPE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_CREATED_DATE, searchConfigProvider.getColumnDateConfig("CREATED_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_CREATED_BY, searchConfigProvider.getColumnUserConfig("CREATED_BY", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_MODIFIED_DATE, searchConfigProvider.getColumnDateConfig("MODIFIED_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_MODIFIED_BY, searchConfigProvider.getColumnUserConfig("MODIFIED_BY", GtnWsAdjusmtmentDetailsQueryConstants.AVM));

        gtnWebServiceCustSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

        List<GtnWebServiceOrderByCriteria> orderByList = new ArrayList<>();
        orderByList.add(new GtnWebServiceOrderByCriteria("AVM.ARM_VIEW_MASTER_SID", "ASC"));
        gtnWebServiceCustSearchQueryConfig.setOrderByClause(orderByList);

        searchQueryConfigMap.put("privatePublicViewSearchQuery", gtnWebServiceCustSearchQueryConfig);

        return searchQueryConfigMap;
    }

}
