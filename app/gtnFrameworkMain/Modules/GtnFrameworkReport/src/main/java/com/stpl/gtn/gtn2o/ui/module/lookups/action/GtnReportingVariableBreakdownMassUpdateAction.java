/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownMassUpdateAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

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
        PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
        Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
        
        ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId()));
        List<String> startAndEndPeriodItemCaptionList = new ArrayList(pagedGrid.getTableConfig().getColumnHeaders());
        
        int startDate = 0;
        int endDate = 0;
        int masterSid = 0;
        int periodSid = 0;
        int year = 0;
        int selectedVariable;
        for(int j=0;j<startAndEndPeriodItemIdList.size();j++){
            if(startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownStartPeriod)){
                startDate=j;
            }
             if(startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownEndPeriod)){
                endDate=j;
            }
        }
        
        ArrayList<String> gridColumnIdSubList = new ArrayList<String>(startAndEndPeriodItemIdList.subList(startDate, endDate+1));

        String variableBreakdownStartPeriodCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(3).toString())
                .getStringCaptionFromV8ComboBox();
        String variableBreakdownEndPeriodCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(4).toString())
                .getStringCaptionFromV8ComboBox();

        List<String> inputList = new ArrayList<>();
        inputList.add(variableBreakdownStartPeriodCaption);
        inputList.add(variableBreakdownEndPeriodCaption);

        GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
        gtnWsReportDataSelectionBean.setVariableBreakdownHeaderLoadList(inputList);
        GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
        gtnWsReportRequest.setDataSelectionBean(gtnWsReportDataSelectionBean);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(gtnWsReportRequest);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
                GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_PERIODS_SERVICE,GtnFrameworkReportStringConstants.REPORT,
                gtnUIFrameworkWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        
        List<Object[]> resultList = (List<Object[]>) response.getGtnReportResponse().getVariableBreakdownLookupBean().getResultList();
        List<GtnReportVariableBreakdownLookupBean> gtnReportVariableBreakdownLookupBeanList = new ArrayList<>();
        for (int i = 1; i < grid.getHeaderRowCount(); i++) {
            Label projectionNames = (Label) grid.getHeaderRow(i).getCell("projectionNames").getComponent();
            if (projectionNames.getValue().equalsIgnoreCase(variableBreakdownFileorProjections)) {
                for (int k=0;k<gridColumnIdSubList.size();k++) {
                    ComboBox variableBreakdownGridCombo = (ComboBox) grid.getHeaderRow(i).getCell(gridColumnIdSubList.get(k)).getComponent();
                    variableBreakdownGridCombo.setSelectedItem(Integer.valueOf(variableBreakdownValues));
                    masterSid = getMasterSid(projectionNames, comparisonLookupBeanList,masterSid);
                    selectedVariable = Integer.valueOf(variableBreakdownValues);
                    periodSid = Integer.valueOf(resultList.get(k)[1].toString());
                    year = Integer.valueOf(resultList.get(k)[2].toString());
                    GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean = new GtnReportVariableBreakdownLookupBean();
                    variableBreakdownLookupBean.setMasterSid(masterSid);
                    variableBreakdownLookupBean.setPeriod(periodSid);
                    variableBreakdownLookupBean.setYear(year);
                    variableBreakdownLookupBean.setSelectedVariable(selectedVariable);
                    gtnReportVariableBreakdownLookupBeanList.add(k, variableBreakdownLookupBean);
                }
            }
        }
        gridComponent.setCustomData(gtnReportVariableBreakdownLookupBeanList);
    }

    private int getMasterSid(Label projectionNames, List<GtnReportComparisonProjectionBean> comparisonLookupBeanList,int masterSid) {
       
        if(projectionNames.getValue().equalsIgnoreCase("Ex-Factory Sales")){
            masterSid = -1;
        }
        if(projectionNames.getValue().equalsIgnoreCase("Latest Approved")){
            masterSid = 0;
        }
        for(int start=0;start<comparisonLookupBeanList.size();start++){
            if (projectionNames.getValue().equalsIgnoreCase(comparisonLookupBeanList.get(start).getProjectionName())) {
                masterSid=comparisonLookupBeanList.get(start).getProjectionMasterSid();
            }
        }
        return masterSid;
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
