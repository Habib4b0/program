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

    private Map<String, GtnWsSearchQueryConfig> adjustmentDetailsSearchQueryConfigMap = null;

    @Override
    public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
        if (adjustmentDetailsSearchQueryConfigMap == null) {
            adjustmentDetailsSearchQueryConfigMap = new HashMap<>();
            loadAdjustmentDetailsSearchQueryConfig();
        }
        return adjustmentDetailsSearchQueryConfigMap;
    }

    public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
        this.adjustmentDetailsSearchQueryConfigMap = searchQueryConfigMap;
    }

    private Map<String, GtnWsSearchQueryConfig> loadAdjustmentDetailsSearchQueryConfig() {

        GtnWsSearchQueryConfig gtnWebServiceCustSearchQueryConfig = new GtnWsSearchQueryConfig();
        gtnWebServiceCustSearchQueryConfig.setCountQuery(GtnWsAdjusmtmentDetailsQueryConstants.SELECT_PRIVATE_PUBLIC_VIEW);
        gtnWebServiceCustSearchQueryConfig.setSearchQuery(GtnWsAdjusmtmentDetailsQueryConstants.SELECT_PRIVATE_PUBLIC_VIEW);
        gtnWebServiceCustSearchQueryConfig.setCountQuerySelectClause("Select  count(distinct AVM.ARM_VIEW_MASTER_SID) ");
        GtnWsSearchQueryConfigProvider searchConfigProvider = GtnWsSearchQueryConfigProvider.getInstance();
        Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ARM_VIEW_MASTER_SID, searchConfigProvider.getColumnIntegerConfig("ARM_VIEW_MASTER_SID", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_NAME, searchConfigProvider.getColumnStringConfig("VIEW_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_TYPE, searchConfigProvider.getColumnStringConfig("VIEW_TYPE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_CREATED_DATE, searchConfigProvider.getColumnDateConfig("CREATED_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_CREATED_BY, searchConfigProvider.getColumnUserConfig("CREATED_BY", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_MODIFIED_DATE, searchConfigProvider.getColumnDateConfig("MODIFIED_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENTDETAILS_LIST_VIEW_VIEW_MODIFIED_BY, searchConfigProvider.getColumnUserConfig("MODIFIED_BY", GtnWsAdjusmtmentDetailsQueryConstants.AVM));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.WORKFLOW_ID, searchConfigProvider.getColumnStringConfig("WORKFLOW_ID", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.WORKFLOW_NAME, searchConfigProvider.getColumnStringConfig("WORKFLOW_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE, searchConfigProvider.getColumnIntegerConfig("ADJUSTMENT_TYPE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CREATED_DATE, searchConfigProvider.getColumnDateConfig("CREATION_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO, searchConfigProvider.getColumnStringConfig("ITEM_NO", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME, searchConfigProvider.getColumnStringConfig("ITEM_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.GL_DATE, searchConfigProvider.getColumnDateConfig("GL_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID, searchConfigProvider.getColumnStringConfig("BATCH_ID", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.BRAND_NAME, searchConfigProvider.getColumnStringConfig("BRAND_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.REDEMPTION_PERIOD, searchConfigProvider.getColumnDateConfig("REDEMPTION_PERIOD", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.REDEMPTION_PERIOD_END_DATE, searchConfigProvider.getColumnDateConfig("REDEMPTION_PERIOD_END_DATE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CUSTOMER_NO, searchConfigProvider.getColumnStringConfig("CUSTOMER_NO", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CUSTOMER_NAME, searchConfigProvider.getColumnStringConfig("CUSTOMER_NAME", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.TRANSACTION_LEVEL, searchConfigProvider.getColumnIntegerConfig("TRANSACTION_LEVEL", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.GL_COMPANY, searchConfigProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.BUSINESS_UNIT, searchConfigProvider.getColumnIntegerConfig("BU_COMPANY_MASTER_SID", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.DEDUCTION_LEVEL, searchConfigProvider.getColumnIntegerConfig("DEDUCTION_LEVEL", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.DEDUCTION_VALUE, searchConfigProvider.getColumnStringConfig("DEDUCTION_VALUE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.POSTING_INDICATOR, searchConfigProvider.getColumnIntegerConfig("POSTING_INDICATOR", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ACCOUNT_CATEGORY, searchConfigProvider.getColumnIntegerConfig("ACCOUNT_CATEGORY", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ACCOUNT_TYPE, searchConfigProvider.getColumnIntegerConfig("ACCOUNT_TYPE", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ACCOUNT, searchConfigProvider.getColumnStringConfig("ACCOUNT", GtnWsAdjusmtmentDetailsQueryConstants.AVD));
        fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL, searchConfigProvider.getColumnIntegerConfig("ADJUSTMENT_LEVEL", GtnWsAdjusmtmentDetailsQueryConstants.AVD));

        gtnWebServiceCustSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

        List<GtnWebServiceOrderByCriteria> orderByList = new ArrayList<>();
        orderByList.add(new GtnWebServiceOrderByCriteria("AVM.ARM_VIEW_MASTER_SID", "ASC"));
        gtnWebServiceCustSearchQueryConfig.setOrderByClause(orderByList);

        adjustmentDetailsSearchQueryConfigMap.put("privatePublicViewSearchQuery", gtnWebServiceCustSearchQueryConfig);

        return adjustmentDetailsSearchQueryConfigMap;
    }

}
