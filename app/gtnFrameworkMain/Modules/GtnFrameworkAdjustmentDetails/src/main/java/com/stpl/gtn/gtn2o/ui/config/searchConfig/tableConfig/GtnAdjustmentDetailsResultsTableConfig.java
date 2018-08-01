/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.searchConfig.tableConfig;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionTableCheckAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionTableCheckAllAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnAdjustmentDetailsResultsTableConfig {
    
    public void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE, true, GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT,
                GtnUIFrameworkComponentType.PAGEDTABLE);
        searchResultConfig.setAuthorizationIncluded(true);
        searchResultConfig.setComponentName("Results");
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(searchResultConfig);

        GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE,
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE,
                "Adjustment Details", "ReserveQuery");
        searchResults.setSinkItemPerPageWithPageLength(false);
        int length = GtnFrameworkAdjustmentDetailsStringConstants.getReservePropertyIds().length;
        Class[] classes = new Class[length];
        Arrays.fill(classes, GtnFrameworkCommonConstants.JAVALANG_STRING);
        classes[0] = Boolean.class;
        searchResults.setTableColumnDataType(classes);

        searchResults.setTableVisibleHeader(GtnFrameworkAdjustmentDetailsStringConstants.getReserveColumnHeaders());
        searchResults.setTableColumnMappingId(GtnFrameworkAdjustmentDetailsStringConstants.getReservePropertyIds());

        searchResults.setDoubleClickEnable(true);
        searchResults.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);

        searchResults.setEditable(true);
        searchResults.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
        searchResults.setEditableField(createTableFieldFactoryComponents(searchResults.getEditableColumnList()));

        GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
        checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        checkAllAction.addActionParameter(GtnUIFrameworkTransactionTableCheckAllAction.class.getName());
        checkAllAction.addActionParameter(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT);
        searchResults.addColumnCheckActionConfig(checkAllAction);
        searchResultConfig.setGtnPagedTableConfig(searchResults);
    }

    List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
        List<GtnUIFrameworkComponentConfig> psPricingEditableFields = new ArrayList<>();
        GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
        GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
        Object checkAll = GtnFrameworkCommonConstants.CHECK_RECORD_ID;
        fieldConfig.setComponentType(gtnUIFrameworkComponentType);
        fieldConfig.setResetToDefaultAllowed(false);
        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig(
                GtnUIFrameworkActionType.CUSTOM_ACTION);
        customAction.addActionParameter(GtnUIFrameworkTransactionTableCheckAction.class.getName());
        customAction.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        customAction.addActionParameter(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT);
        customAction.addActionParameter(checkAll);
        actionConfigList.add(customAction);
        fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);

        psPricingEditableFields.add(fieldConfig);
        return psPricingEditableFields;
    }
}
