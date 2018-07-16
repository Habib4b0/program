/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.config.searchConfig.tableConfig.GtnAdjustmentDetailsResultsTableConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkTransactionDetailsValueChange implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeductionLevelValueChange.class);

    private final GtnAdjustmentDetailsResultsTableConfig tableConfig = new GtnAdjustmentDetailsResultsTableConfig();

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        String transactionDetails = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getValueFromComponent().toString();
        String[] columnHeader;
        Object[] visiColumn;
        if ("GTN Details".equals(transactionDetails)) {
            enableDisableComponents(parameters, true);
            columnHeader = GtnFrameworkAdjustmentDetailsStringConstants.getGtnColumnHeaders();
            visiColumn = GtnFrameworkAdjustmentDetailsStringConstants.getGtnPropertyIds();
        } else {
            enableDisableComponents(parameters, false);
            columnHeader = GtnFrameworkAdjustmentDetailsStringConstants.getReserveColumnHeaders();
            visiColumn = GtnFrameworkAdjustmentDetailsStringConstants.getReservePropertyIds();
        }
        GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(parameters.size() - 1).toString());
        GtnUIFrameworkComponentConfig componentConfig = baseComponent.getComponentConfig();
        GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getGtnPagedTableConfig();
        searchResults.setCountQuery(transactionDetails);
        searchResults.setDataQuery(transactionDetails);
        int length = visiColumn.length;
        Class[] classes = new Class[length];
        Arrays.fill(classes, GtnFrameworkCommonConstants.JAVALANG_STRING);
        searchResults.setTableColumnDataType(classes);
        searchResults.setTableVisibleHeader(columnHeader);
        searchResults.setTableColumnMappingId(visiColumn);
        baseComponent.reloadPagedTable(searchResults, baseComponent.getExtPagedTable(), classes, visiColumn, columnHeader);
    }

    private void enableDisableComponents(List<Object> parameters, boolean value) {
        int length = parameters.size() - 1;
        for (int i = 2; i < length; i++) {
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(i).toString()).setEnable(value);

        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
