/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedgrid;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkPagedGridComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPagedGridComponent.class);
    @Override
    public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) throws GtnFrameworkGeneralException {
 
        VerticalLayout resultLayout = new VerticalLayout();
        GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();


        PagedGrid pagedGrid = new PagedGrid(tableConfig,componentConfig);
        pagedGrid.getGrid().setId(componentConfig.getComponentId());
        resultLayout.setSizeFull();
        resultLayout.addComponent(pagedGrid.getGrid());
        pagedGrid.getGrid().setWidth(componentConfig.getComponentWidth());
        pagedGrid.getGrid().setHeight(componentConfig.getComponentHight());
        resultLayout.setComponentAlignment(pagedGrid.getGrid(), Alignment.MIDDLE_CENTER);
        GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
        componentData.setTableConfig(tableConfig);
        componentData.setPagedGrid(pagedGrid);
        componentData.setCurrentPageGridLogic(pagedGrid.getPagedTableLogic());
        componentData.setCustomData(pagedGrid);

        VerticalLayout controls = new VerticalLayout();
        controls.addComponents(pagedGrid.getControlLayout());
        controls.setMargin(false);
        controls.setSpacing(false);
        controls.setWidth("100%");
        controls.setHeightUndefined();
        controls.setId(componentConfig.getComponentId()+"itemsPerPageLayout");
        if(tableConfig.isItemsPerPageAlignCentre()){
        controls.setComponentAlignment(pagedGrid.getControlLayout(), Alignment.MIDDLE_CENTER);
        }
        resultLayout.addComponent(controls);

        pagedGrid.getGrid().getEditor().setEnabled(false);

            resultLayout.setData(componentData);
    	
        return resultLayout;
    }

   private void configureLeftTablHeader(GtnUIFrameworkPagedTableConfig tableConfig, String sourceViewId)
            throws GtnFrameworkGeneralException {


       String classPath = tableConfig.getGridHeaderCustomClassLoadURL();
        classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, sourceViewId);
        GtnUIFrameworkWebserviceRequest headerRequest = getCustomPagedTableRequest(
                tableConfig.getGtnUIFrameWorkActionConfig(), sourceViewId);
        
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getGridColumnHeader(),
                tableConfig.getModuleName(), headerRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        GtnWsPagedTableResponse tableHeadersResponse = response.getGtnWsPagedTableResponse();

        tableConfig.setTableColumnMappingId(tableHeadersResponse.getSingleColumns().toArray());
        tableConfig.setColumnHeaders(tableHeadersResponse.getSingleHeaders());
    }

   private void classLoader(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String classPath,
            String sourceViewId) throws GtnFrameworkGeneralException {
        GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
        GtnUIFrameWorkAction loader = (GtnUIFrameWorkAction) classLoader.loadDynamicClass(classPath);
        loader.configureParams(gtnUIFrameWorkActionConfig);
        loader.doAction(sourceViewId, gtnUIFrameWorkActionConfig);
    }

    private GtnUIFrameworkWebserviceRequest getCustomPagedTableRequest(
            GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId) {
        GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponentFromParent(
                            gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), sourceViewId)
                    .getComponentData();
        return resultTableComponentData.getCustomPagedTreeTableRequest();
    }
    
    @Override
    public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId, Object reloadInput) {
        return;
    }

    @Override
    public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
        return;
    }

    @Override
    public void postCreateComponent(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
        return;
    }

}
