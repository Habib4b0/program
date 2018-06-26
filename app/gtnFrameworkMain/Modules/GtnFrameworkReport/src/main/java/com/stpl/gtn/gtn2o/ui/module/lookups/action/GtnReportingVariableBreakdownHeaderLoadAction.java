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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownHeaderLoadAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> variableBreakdownActionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
        String variableBreakdownTableId = variableBreakdownActionParameterList.get(0).toString();
 GtnUIFrameworkComponentData variableBreakdownGridComponentData ;
 String variableBreakdownFromPeriod ;
 String variableBreakdownToPeriod ;
 String variableBreakdownFrequency;
        if(variableBreakdownActionParameterList.get(3).toString().equals("reportingDashboardScreen")){
            AbstractComponent variableBreakdownGridAbstractComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(variableBreakdownTableId, componentId).getComponent();
        variableBreakdownGridComponentData = (GtnUIFrameworkComponentData) variableBreakdownGridAbstractComponent.getData();
        
     
                        
                  GtnUIFrameworkBaseComponent   reportingDashboardFromPeriodComponent = GtnUIFrameworkGlobalUI
                            .getVaadinBaseComponentFromParent("reportingDashboard_displaySelectionTabPeriodRangeFrom",componentId);
                  GtnUIFrameworkBaseComponent   reportingDashboardToPeriodComponent = GtnUIFrameworkGlobalUI
                            .getVaadinBaseComponentFromParent("reportingDashboard_displaySelectionTabPeriodRangeTo",componentId);
                  
                  GtnUIFrameworkBaseComponent   reportingDashboardFrequencyComponent = GtnUIFrameworkGlobalUI
                            .getVaadinBaseComponentFromParent("reportingDashboard_displaySelectionTabFrequency",componentId);
                  
                  
                  String reportingDashboardFromPeriod = reportingDashboardFromPeriodComponent.getStringCaptionFromV8ComboBox();
                  String reportingDashboardToPeriod = reportingDashboardToPeriodComponent.getStringCaptionFromV8ComboBox();
                  String reportingDashboardFrequency = reportingDashboardFrequencyComponent.getStringCaptionFromV8ComboBox();
                  if("-Select one-".equals(reportingDashboardFromPeriod)){
                       variableBreakdownFromPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(1).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
                  }
                  else{
                      if(!reportingDashboardFromPeriod.startsWith("Q")&&(!reportingDashboardFromPeriod.startsWith("S"))){
                      variableBreakdownFromPeriod=reportingDashboardFromPeriod;
                      }
                      else{
                           variableBreakdownFromPeriod=reportingDashboardFromPeriod.replace(" ", "-");
                      }
                  }
                  if("-Select one-".equals(reportingDashboardToPeriod)){
                        variableBreakdownToPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(2).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
                  }
                  else{
                      if(!reportingDashboardToPeriod.startsWith("Q")&&(!reportingDashboardToPeriod.startsWith("S"))){
                      variableBreakdownToPeriod=reportingDashboardToPeriod;
                      }
                      else{
                           variableBreakdownToPeriod=reportingDashboardToPeriod.replace(" ", "-");
                      }
                  }
                  
                
         variableBreakdownFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig")
                .getStringCaptionFromV8ComboBox();
        
        }
        else{
        AbstractComponent variableBreakdownGridAbstractComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(variableBreakdownTableId, componentId).getComponent();
        
        variableBreakdownGridComponentData = (GtnUIFrameworkComponentData) variableBreakdownGridAbstractComponent.getData();
        
          variableBreakdownFrequency="";
         variableBreakdownFromPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(1).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
         variableBreakdownToPeriod = GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(2).toString(), componentId)
                .getStringCaptionFromV8ComboBox();
//        if(GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT.equals(variableBreakdownTableId)){
//        variableBreakdownFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig")
//                .getStringCaptionFromV8ComboBox();
//        }
//        else{
          variableBreakdownFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableBreakdownActionParameterList.get(0).toString())
                .getStringCaptionFromV8ComboBox(); 
       // }
        
        }
//        String variableBreakdownFrequency="";
//        String variableBreakdownFromPeriod = GtnUIFrameworkGlobalUI
//                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(1).toString(), componentId)
//                .getStringCaptionFromV8ComboBox();
//        String variableBreakdownToPeriod = GtnUIFrameworkGlobalUI
//                .getVaadinBaseComponent(variableBreakdownActionParameterList.get(2).toString(), componentId)
//                .getStringCaptionFromV8ComboBox();
//        if(GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT.equals(variableBreakdownTableId)){
//        variableBreakdownFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig")
//                .getStringCaptionFromV8ComboBox();
//        }
//        else{
//          variableBreakdownFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableBreakdownActionParameterList.get(0).toString())
//                .getStringCaptionFromV8ComboBox(); 
//        }
        List<String> variableBreakdownInputList = new ArrayList<>();
        variableBreakdownInputList.add(variableBreakdownFromPeriod);
        variableBreakdownInputList.add(variableBreakdownToPeriod);
        variableBreakdownInputList.add(variableBreakdownFrequency);

        GtnWsReportDataSelectionBean variableBreakdownReportDataSelectionBean = new GtnWsReportDataSelectionBean();
        variableBreakdownReportDataSelectionBean.setVariableBreakdownHeaderLoadList(variableBreakdownInputList);
        GtnWsReportRequest variableBreakdownReportRequest = new GtnWsReportRequest();
        variableBreakdownReportRequest.setDataSelectionBean(variableBreakdownReportDataSelectionBean);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkVariableBreakdownWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        gtnUIFrameworkVariableBreakdownWebserviceRequest.setGtnWsReportRequest(variableBreakdownReportRequest);
        variableBreakdownGridComponentData.setCustomData(gtnUIFrameworkVariableBreakdownWebserviceRequest);
        variableBreakdownGridComponentData.setCustomPagedTreeTableRequest(gtnUIFrameworkVariableBreakdownWebserviceRequest);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
