package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import static com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType.COMBOBOX_VAADIN8;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.HeaderRow;

public class GtnReportingComparisonBreakdownGridLoadAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownGridLoadAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
		
	}
	private boolean isDisableColumns;
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
	          try {
	              logger.info("------------GtnReportingComparisionBreakdownGridLoadAction----------------");
	              int i = 0;
	              List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
	              String comparisonBreakdownTableId = actionParameterList.get(1).toString();
	              List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = new ArrayList<>();
	              String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId)
	      				.getViewId();
	              GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
	      				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
	              comparisonLookupBeanList = dataSelectionBean.getComparisonProjectionBeanList();
	              List<String> projectionNameListFromCustomData = new ArrayList<>();
	              projectionNameListFromCustomData.clear();
	              projectionNameListFromCustomData.add("Latest Approved");
	              for(int count=0;count<comparisonLookupBeanList.size();count++){
	                  projectionNameListFromCustomData.add(comparisonLookupBeanList.get(count).getProjectionName());
	              }
	              
	              int comparisonLookupBeanSize = projectionNameListFromCustomData.size();
	              
	              AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(comparisonBreakdownTableId, componentId).getComponent();
	              GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
	              PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
	              Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
	              clearGrid(grid);
	              GtnUIFrameworkPagedTableConfig tableConfig = setHeaderFromWs(pagedGrid, componentId, grid);
	              configureCheckboxHeaderComponents(tableConfig.getTableColumnMappingId(), tableConfig.getColumnHeaders(), grid, tableConfig);
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

	      private GtnUIFrameworkPagedTableConfig setHeaderFromWs(PagedGrid pagedGrid, String componentId, Grid<GtnWsRecordBean> grid) throws GtnFrameworkGeneralException {
	          GtnUIFrameworkPagedTableConfig tableConfig = pagedGrid.getTableConfig();
	          String classPath = tableConfig.getGridHeaderCustomClassLoadUrl();
	          classLoader(tableConfig.getGtnUIFrameWorkActionConfig(), classPath, componentId);
	          GtnUIFrameworkWebserviceRequest headerRequest = getCustomPagedTableRequest(
	                  tableConfig.getGtnUIFrameWorkActionConfig(), componentId);
	          GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
	          GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(tableConfig.getGridColumnHeader(),
	                  tableConfig.getModuleName(), headerRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	          GtnWsPagedTableResponse tableHeadersResponse = response.getGtnWsPagedTableResponse();
	          tableConfig.setTableColumnMappingId(tableHeadersResponse.getSingleColumns().toArray());
	          tableConfig.setColumnHeaders(tableHeadersResponse.getSingleHeaders());
	          int j = 0;
	          for (Object column : tableConfig.getTableColumnMappingId()) {
	              String property = column.toString();
	              grid.addColumn(row -> row.getPropertyValue(property)).setCaption(tableConfig.getColumnHeaders().get(j))
	                      .setId(property);

	              j++;
	          }
	          return tableConfig;
	      }

	      private void configureCheckboxHeaderComponents(Object[] tableColumnMappingId, List<String> columnHeaders,
	              Grid<GtnWsRecordBean> grid, GtnUIFrameworkPagedTableConfig tableConfig) {
	          if (tableConfig.isEnableCheckBoxInGridHeader()) {
	              HeaderRow mainHeader = grid.getHeaderRow(0);
	              for (int i = 0; i < tableColumnMappingId.length; i++) {
	                  CheckBoxGroup vaadinCheckBoxGroup = new CheckBoxGroup();
	                  vaadinCheckBoxGroup.setItems(columnHeaders.get(i));
	                  mainHeader.getCell(String.valueOf(tableColumnMappingId[i])).setComponent(vaadinCheckBoxGroup);
	              }
	          }
	      }

	      private void clearGrid(Grid<GtnWsRecordBean> grid) {
	          grid.removeAllColumns();
	          int headerCount = grid.getHeaderRowCount();
	          if (headerCount > 1) {
	              for (int start = headerCount; start > 1; start--) {
	                  grid.removeHeaderRow(start - 1);
	              }
	          }
	      }

	      private Component getCustomFilterComponent(String property, String componentId, int i) {
	          try {
	              GtnUIFrameworkBaseComponent base = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(
	                      "reportOptionsTabComparisonOptions_value", componentId);

	              GtnUIFrameworkComponent component = COMBOBOX_VAADIN8.getGtnComponent();
	              Component vaadinComponent = null;
	              vaadinComponent = component.buildVaadinComponent(base.getComponentConfig());
	              ComboBox vaadinCombobox = (ComboBox) vaadinComponent;
	              vaadinCombobox.setId(property + String.valueOf(i));
	              vaadinCombobox.setSelectedItem("Projections");
	              vaadinCombobox.setReadOnly(true);
	              return vaadinCombobox;
	          } catch (Exception e) {
	              logger.error("Error message" + e);
	          }
	          return null;
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
	          GtnUIFrameworkBaseComponent vaadinBaseComponentFromParent =  GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
                      gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(),sourceViewId);
	          GtnUIFrameworkComponentData resultTableComponentData =  vaadinBaseComponentFromParent.getComponentData();
	          return resultTableComponentData.getCustomPagedTreeTableRequest();
	      }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
