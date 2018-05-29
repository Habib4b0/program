/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GtnReportDataAssumptionsTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataAssumptionsTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
        final List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

                    final TabSheet tabSheet = (TabSheet) getAbstractComponent(String.valueOf(actionParameterList.get(1)),
					componentId);
                    
                    addTab(tabSheet, actionParameterList, componentId);
                   
                    
		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}
        
        private void addTab(final TabSheet tabSheet, List<Object> actionParameterList,String sourceComponentId) {
            
            List<Integer> projection = new ArrayList<>();
            projection.add(1688);
            projection.add(1689);
            projection.add(1699);
            
            for(int i=1;i<6;i++){
              if(i>projection.size()){
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkReportStringConstants.TAB_SHEET+"dataAssump", sourceComponentId)
					.setTabComponentVisible(i, false);
              }
            }
            List<String> dsAssumptions = new ArrayList<>();
            dsAssumptions.add("dataAssumptionsPagedTableComponentdataAssumptionsTab0dataAssumptionsTab");
            dsAssumptions.add("dataAssumptionsPagedTableComponentdataAssumptionsTab1dataAssumptionsTab");
            dsAssumptions.add("dataAssumptionsPagedTableComponentdataAssumptionsTab2dataAssumptionsTab");
            dsAssumptions.add("dataAssumptionsPagedTableComponentdataAssumptionsTab3dataAssumptionsTab");
            dsAssumptions.add("dataAssumptionsPagedTableComponentdataAssumptionsTab4dataAssumptionsTab");
            
            
            
            
            for(int i=0;i<projection.size();i++){
             Grid<GtnWsRecordBean> dataAssumptionsCurrentTabComponent = getDataAssumptionsGridComponent(dsAssumptions.get(i),sourceComponentId);         
             List<GtnWsRecordBean> dsLoadResults = getDataAssumptionGridLoadValues(projection.get(i));
             dataAssumptionsCurrentTabComponent.setItems(dsLoadResults);
            }                          
	}

    private List<GtnWsRecordBean> getDataAssumptionGridLoadValues(int projectionmasterSid) {
        List<GtnWsRecordBean> records=new ArrayList<>();
        GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
        GtnWsReportRequest reportRequest = new GtnWsReportRequest();
        reportRequest.setProjectionMasterSid(projectionmasterSid);
        request.setGtnWsReportRequest(reportRequest);
        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl("/gtnReport/gtnWsReportLoadDataAssumptionsMultipleTabs", "report", request,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        
        for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
            GtnWsRecordBean dto = new GtnWsRecordBean();
            dto.setProperties(record.getColList());
            dto.setRecordHeader(Arrays.asList(new Object[]{"file", "company", "businessUnit",
                "type", "version", "activeFrom", "fromPeriod", "toPeriod"}));
            records.add(dto);
        }
        return records;
    }

        private Grid<GtnWsRecordBean> getDataAssumptionsGridComponent(String gridId, String sourceComponentId) {
        GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(gridId,sourceComponentId);
        PagedGrid pagedGrid = (PagedGrid) componentData.getCustomData();
        Grid<GtnWsRecordBean> dataAssumptionsCurrentTabComponent = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
        return dataAssumptionsCurrentTabComponent;
    }

        private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}
        
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}

