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
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
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

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownSubmitAction.class);
	
    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    	logger.debug("Inside Configure Parameters");
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    	try {         
		Map<String, Integer> periodAndYearMap;
		
        String viewIdCheck = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("variableBreakdown", componentId).getComponentData().getSharedPopupData().toString();

        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent("variableBreakdownResultsLayout_comparisonLookupResultsPagedTableComponent",
                componentId);
        GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
        
        List<Object[]> reportVariableBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();
             
         Set<Object[]> inputSet = new LinkedHashSet<>(reportVariableBreakdownLookupBeanList);
        
        List<Object[]> gtnReportVariableBreakdownLookupBeanList = new ArrayList<>(inputSet);
        
        List<GtnReportVariableBreakdownLookupBean> variableBreakdownLookupBeanSaveList = new ArrayList<>(gtnReportVariableBreakdownLookupBeanList.size());
         for(int i=0;i<gtnReportVariableBreakdownLookupBeanList.size();i++){
        	periodAndYearMap = GtnReportingComparisonBreakdownSubmitAction.getPeriodAndYear(gtnReportVariableBreakdownLookupBeanList.get(i)[1].toString());
            GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean = new GtnReportVariableBreakdownLookupBean();
            variableBreakdownLookupBean.setMasterSid((int) gtnReportVariableBreakdownLookupBeanList.get(i)[2]);
            variableBreakdownLookupBean.setPeriod(periodAndYearMap.get("period"));
            variableBreakdownLookupBean.setYear(periodAndYearMap.get("year"));
            variableBreakdownLookupBean.setSelectedVariable((int) gtnReportVariableBreakdownLookupBeanList.get(i)[0]);
            variableBreakdownLookupBean.setRowCount((int)gtnReportVariableBreakdownLookupBeanList.get(i)[5]);
            variableBreakdownLookupBean.setProperty(gtnReportVariableBreakdownLookupBeanList.get(i)[3].toString());
            variableBreakdownLookupBean.setComponentId(gtnReportVariableBreakdownLookupBeanList.get(i)[6].toString());
            variableBreakdownLookupBean.setColumnId(gtnReportVariableBreakdownLookupBeanList.get(i)[1].toString());
            variableBreakdownLookupBeanSaveList.add(variableBreakdownLookupBean);
        }
         
         gridComponent.setCustomData(variableBreakdownLookupBeanSaveList);

         if("reportingDashboardScreen".equals(viewIdCheck)){
             	String sourceParentComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
                String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(sourceParentComponentId).getViewId();
		GtnWsReportDataSelectionBean gtnWsReportDataSelectionBeanSave = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
             gtnWsReportDataSelectionBeanSave.setVariableBreakdownSaveList(variableBreakdownLookupBeanSaveList);
             GtnWsReportRequest gtnWsReportRequestSave = new GtnWsReportRequest();
             gtnWsReportRequestSave.setDataSelectionBean(gtnWsReportDataSelectionBeanSave);
             GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequestSave = new GtnUIFrameworkWebserviceRequest();
             gtnUIFrameworkWebserviceRequestSave.setGtnWsReportRequest(gtnWsReportRequestSave);
             GtnUIFrameworkWebServiceClient clientSave = new GtnUIFrameworkWebServiceClient();
            clientSave.callGtnWebServiceUrl(
                     GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_SAVE_SERVICE, GtnFrameworkReportStringConstants.REPORT,
                     gtnUIFrameworkWebserviceRequestSave, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
         }
    	} catch (Exception e) {
			logger.error(e.getMessage());
		}
       
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
