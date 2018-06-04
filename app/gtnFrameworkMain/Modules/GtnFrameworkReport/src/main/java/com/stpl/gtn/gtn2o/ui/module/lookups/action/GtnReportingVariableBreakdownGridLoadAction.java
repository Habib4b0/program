package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;
import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.LABEL;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.v7.ui.Label;

public class GtnReportingVariableBreakdownGridLoadAction
        implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownGridLoadAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            logger.info("------------GtnReportingVariablBreakdownGridLoadAction----------------");
            int i = 0;
           
            List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
            String variableBreakdownTableId = actionParameterList.get(1).toString();

            GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponentFromParent(
                            "reportLandingScreen_reportingDashboardComparisonConfig", componentId)
                    .getComponentData();
            List comparisonLookupBeanList = (List) idComponentData.getCustomData();
            int comparisonLookupBeanSize = comparisonLookupBeanList.size();
            AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(variableBreakdownTableId,
                    componentId);
            GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
            PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
            Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();

            clearGrid(grid);

            
             GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
                    "variableBreakdownLabelConfig", componentId);

            GtnUIFrameworkComponent component = LABEL.getGtnComponent();
            Component labelVaadinComponent = null;
            labelVaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
            Label vaadinLabel = (Label) labelVaadinComponent;
           vaadinLabel.setValue("2017");
            
            
            Component vaadinComponent = null;

            Object[] filterColumnIdList = pagedGrid.getTableConfig().getTableColumnMappingId();
            while (comparisonLookupBeanSize > 0) {
                 HeaderRow filterRow = grid.appendHeaderRow();
                for (Object column : filterColumnIdList) {
                    vaadinComponent = getCustomFilterComponent(String.valueOf(column), componentId, i);
                    filterRow.getCell(String.valueOf(column)).setComponent(vaadinComponent);

                }
                comparisonLookupBeanSize--;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearGrid(Grid<GtnWsRecordBean> grid) {
        int headerCount = grid.getHeaderRowCount();
        if (headerCount > 1) {
            for (int start = headerCount; start > 1; start--) {
                grid.removeHeaderRow(start - 1);
            }
        }
    }

    private Component getCustomFilterComponent(String property, String componentId, int i) {
        try {
            GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
                    "reportOptionsTab_variableBreakdownGridComboConfig", componentId);

            GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
            Component vaadinComponent = null;
            vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
            ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
            vaadinCombobox.setId(property + String.valueOf(i));
            return vaadinCombobox;
        } catch (Exception e) {
            logger.error("Error message" + e);
        }
        return null;
    }


    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
