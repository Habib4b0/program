/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.grid.HeaderRow;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownMassUpdateAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

        List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

        List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = new ArrayList<>();

        GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponentFromParent(
                        "reportLandingScreen_reportingDashboardComparisonConfig", componentId)
                .getComponentData();

        comparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) idComponentData.getCustomData();

        String variableBreakdownValues = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(1).toString())
                .getCaptionFromV8ComboBox();
        String variableBreakdownFileorProjections = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(2).toString())
                .getStringCaptionFromV8ComboBox();
        String variableBreakdownStartPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(3).toString())
                .getCaptionFromV8ComboBox();
        String variableBreakdownEndPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(4).toString())
                .getCaptionFromV8ComboBox();

        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(actionParameterList.get(5).toString(),
                componentId);
        GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
        PagedGrid pagedGrid = (PagedGrid) gridComponent.getPagedGrid();
        Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();

        ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId()));

        int startDate = 0;
        int endDate = 0;

        for (int j = 0; j < startAndEndPeriodItemIdList.size(); j++) {
            if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownStartPeriod)) {
                startDate = j;
            }
            if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownEndPeriod)) {
                endDate = j;
            }
        }

        ArrayList<String> gridColumnIdSubList = new ArrayList<String>(startAndEndPeriodItemIdList.subList(startDate, endDate + 1));

        String frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig")
                .getStringCaptionFromV8ComboBox();

        String localDate = String.valueOf(LocalDate.now());

        String[] localDateSplit = localDate.split("-");
        String currentYear = localDateSplit[0];
        String currentmonth = localDateSplit[1];
        
        GtnReportingVariableBreakdownGridLoadAction variableBreakdownGridLoadAction= new GtnReportingVariableBreakdownGridLoadAction();
        String currentDateToDisableField = variableBreakdownGridLoadAction.getCurrentDateToDisableField(frequency, currentYear, currentmonth);
        
        HeaderRow gridHeaderRow = grid.getHeaderRow(0);
        for (int i = 1; i < grid.getHeaderRowCount(); i++) {
            Label projectionNames = (Label) grid.getHeaderRow(i).getCell("projectionNames").getComponent();
            if (projectionNames.getValue().equalsIgnoreCase(variableBreakdownFileorProjections)) {
                for (int k = 0; k < gridColumnIdSubList.size(); k++) {
                    if (gridColumnIdSubList.get(k).toString().equals(currentDateToDisableField)) {
                        break;
                    }
                    CheckBoxGroup headerCheckboxGroup = (CheckBoxGroup) gridHeaderRow.getCell(gridColumnIdSubList.get(k)).getComponent();
                    Set<String> headerCheckboxSelectedSet = headerCheckboxGroup.getSelectedItems();
                    boolean isHeaderCheckboxSelected = headerCheckboxSelectedSet.toArray().length == 0 ? false :headerCheckboxGroup.isSelected(headerCheckboxSelectedSet.toArray()[0].toString());
                    if (isHeaderCheckboxSelected) {
                        ComboBox variableBreakdownGridCombo = (ComboBox) grid.getHeaderRow(i).getCell(gridColumnIdSubList.get(k)).getComponent();
                        variableBreakdownGridCombo.setSelectedItem(Integer.valueOf(variableBreakdownValues));
                    }

                }
            }
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
