/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownGridResetAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownGridResetAction.class);
	
    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
    	logger.debug("Inside Configure Parameters");
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        
        List<Object> actionParam = gtnUIFrameWorkActionConfig.getActionParameterList();
        AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(actionParam.get(1).toString(),componentId);
        GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();        
        List<Object[]> gtnReportVariableBreakdownLookupBeanList;
        if(gridComponent.getCustomData()== null){
            PagedGrid pagedGrid = gridComponent.getPagedGrid();
             Grid<GtnWsRecordBean> grid =  pagedGrid.getGrid();
            gtnReportVariableBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();
            int variableBreakdownLookupBeanCount = gtnReportVariableBreakdownLookupBeanList.size();
        int rowCount =grid.getHeaderRowCount();
        resetVariableBreakdownGridComponent(variableBreakdownLookupBeanCount, rowCount, grid, gtnReportVariableBreakdownLookupBeanList);
        }  else{    
        resetVariableBreakdownComboboxComponents(actionParam, componentId); 
        }   
    }
    private void resetVariableBreakdownGridComponent(int variableBreakdownLookupBeanCount, int rowCount, Grid<GtnWsRecordBean> grid, List<Object[]> gtnReportVariableBreakdownLookupBeanList) {
        for(int i=0;i<variableBreakdownLookupBeanCount;i++){
            for(int j=1;j<rowCount;j++){
                Label projectionNames = (Label)grid.getHeaderRow(j).getCell("projectionNames").getComponent();
                if (gtnReportVariableBreakdownLookupBeanList.get(i)[4].toString().equals(projectionNames.getValue())) {
                    ComboBox variableBreakdownResetCombo = (ComboBox) grid.getHeaderRow(j).getCell(gtnReportVariableBreakdownLookupBeanList.get(i)[3].toString()).getComponent();
                    variableBreakdownResetCombo.setSelectedItem(0);
                }
            }
        }
    }

    private void resetVariableBreakdownComboboxComponents(List<Object> actionParam, String componentId) throws GtnFrameworkValidationFailedException {
        for(int k=2;k<actionParam.size();k++){
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.get(k).toString(), componentId).loadV8ComboBoxComponentValue(0);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
