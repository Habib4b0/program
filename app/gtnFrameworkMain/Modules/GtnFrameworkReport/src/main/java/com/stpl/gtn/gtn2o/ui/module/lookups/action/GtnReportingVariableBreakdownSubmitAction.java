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
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownSubmitAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
//       
//        obj[0] = selectedValue;
//        obj[1] = columnId;
//        obj[2] = masterSid;
                    
        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent("variableBreakdownResultsLayout_comparisonLookupResultsPagedTableComponent",
                componentId);
        GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
        
        List<Object[]> gtnReportVariableBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();
        
        
        List<String> inputList = new ArrayList<>();
        for(int i=0;i<gtnReportVariableBreakdownLookupBeanList.size();i++){
            inputList.add(i, gtnReportVariableBreakdownLookupBeanList.get(i)[1].toString());
        }
     
        Set<String> inputSet = new LinkedHashSet<>(inputList);
        
        List<String> inputWsList = new ArrayList<>(inputSet);

        GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
        gtnWsReportDataSelectionBean.setVariableBreakdownHeaderLoadList(inputWsList);
        GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
        gtnWsReportRequest.setDataSelectionBean(gtnWsReportDataSelectionBean);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(gtnWsReportRequest);
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
                GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_PERIODS_SERVICE,GtnFrameworkReportStringConstants.REPORT,
                gtnUIFrameworkWebserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

       List<Object[]> reultList =  response.getGtnReportResponse().getVariableBreakdownLookupBean().getResultList();
        Map<String,Object[]> periodSidAndYearMap = new HashMap();
        for(int k=0;k<inputWsList.size();k++){
            periodSidAndYearMap.put(inputWsList.get(k),reultList.get(k) );
        }
        
        List<GtnReportVariableBreakdownLookupBean> variableBreakdownLookupBeanSaveList = new ArrayList<>();
         for(int i=0;i<gtnReportVariableBreakdownLookupBeanList.size();i++){
            GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean = new GtnReportVariableBreakdownLookupBean();
            variableBreakdownLookupBean.setMasterSid((int) gtnReportVariableBreakdownLookupBeanList.get(i)[2]);
            variableBreakdownLookupBean.setPeriod((int) periodSidAndYearMap.get(gtnReportVariableBreakdownLookupBeanList.get(i)[1])[1]);
            variableBreakdownLookupBean.setYear((int) periodSidAndYearMap.get(gtnReportVariableBreakdownLookupBeanList.get(i)[1])[2]);
            variableBreakdownLookupBean.setSelectedVariable((int) gtnReportVariableBreakdownLookupBeanList.get(i)[0]);
            variableBreakdownLookupBeanSaveList.add(variableBreakdownLookupBean);
        }
         
           GtnWsReportDataSelectionBean gtnWsReportDataSelectionBeanSave = new GtnWsReportDataSelectionBean();
        gtnWsReportDataSelectionBeanSave.setVariableBreakdownSaveList(variableBreakdownLookupBeanSaveList);
        GtnWsReportRequest gtnWsReportRequestSave = new GtnWsReportRequest();
        gtnWsReportRequestSave.setDataSelectionBean(gtnWsReportDataSelectionBeanSave);
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequestSave = new GtnUIFrameworkWebserviceRequest();
        gtnUIFrameworkWebserviceRequestSave.setGtnWsReportRequest(gtnWsReportRequestSave);
        GtnUIFrameworkWebServiceClient clientSave = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse saveResponse = clientSave.callGtnWebServiceUrl(
                GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_SAVE_SERVICE,GtnFrameworkReportStringConstants.REPORT,
                gtnUIFrameworkWebserviceRequestSave, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
         
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
