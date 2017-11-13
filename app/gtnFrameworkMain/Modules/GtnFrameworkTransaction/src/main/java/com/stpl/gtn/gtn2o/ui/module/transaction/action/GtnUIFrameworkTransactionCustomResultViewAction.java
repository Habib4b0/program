/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

/**
 *
 * @author Asha.Ravi
 */
public class GtnUIFrameworkTransactionCustomResultViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String>[] visibleColumn = (List<String>[]) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		List<String>[] visibleHeader = (List<String>[]) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		String tableComponentId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
		String tableName = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(4);
		Object[] visibleColumns = null;
		String[] visibleHeaders = null;
		String wsViewName = tableName;
                List<Object> visibleColumnAndHeaderList=new ArrayList<>();
		if (tableName.contains("InventoryWdActualProjMas")) {
			visibleColumnAndHeaderList=getVisibleColumnAndHeaderForInventory(visibleColumn, visibleHeader);
                       
		} else if (tableName.contains(GtnTransactionUIConstants.DEMAND)) {
			visibleColumnAndHeaderList = getVisibleColumnForDemand(visibleColumn, visibleHeader,
					tableName);
                        wsViewName=(String)visibleColumnAndHeaderList.get(2);
		}
                visibleColumns=(Object[])visibleColumnAndHeaderList.get(0);
                visibleHeaders=(String[])visibleColumnAndHeaderList.get(1);
		GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId).getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig config = componentConfig.getGtnPagedTableConfig();
		config.setModuleName(wsViewName);
		config.setTableColumnMappingId(visibleColumns);
		config.setTableVisibleHeader(visibleHeaders);
		List<GtnUIFrameworkComponentConfig> componentConfigList = new ArrayList<>();
		componentConfigList.add(componentConfig);
		GtnUIFrameworkGlobalUI.addChildComponent("resultlayout", componentConfigList);
	}

	private String getTableName(String tableName) {
		String wsViewName = "";
		if (tableName.contains("Ivld")) {
			wsViewName = "VwIvldDemandForecastActual";
		} else {
			wsViewName = "VwDemandForecastActual";
		}
		return wsViewName;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private List<Object> getVisibleColumnAndHeaderForInventory(
			List<String>[] visibleColumn, List<String>[] visibleHeader) throws GtnFrameworkValidationFailedException {
            Object[] visibleColumns=null;
            String[] visibleHeaders=null;
            List<Object> visbleColumnAndHeaderList=new ArrayList<>();
		String inventoryTypeCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("inventoryType", "inventoryType")
				.getCaptionFromComboBox();
		String inventoryLevelCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("inventoryLevel", "inventoryLevel")
				.getCaptionFromComboBox();
		if (GtnTransactionUIConstants.ACTUALS.equals(inventoryTypeCaption)
				&& GtnTransactionUIConstants.DETAILS.equals(inventoryLevelCaption)) {
			visibleColumns = visibleColumn[0].toArray(new Object[visibleColumn[0].size()]);
			visibleHeaders = visibleHeader[0].toArray(new String[visibleHeader[0].size()]);
		} else if (GtnTransactionUIConstants.ACTUALS.equals(inventoryTypeCaption)
				&& GtnTransactionUIConstants.SUMMARY.equals(inventoryLevelCaption)) {
			visibleColumns = visibleColumn[1].toArray(new Object[visibleColumn[1].size()]);
			visibleHeaders = visibleHeader[1].toArray(new String[visibleHeader[1].size()]);
		} else if (GtnTransactionUIConstants.PROJECTIONS.equals(inventoryTypeCaption)
				&& GtnTransactionUIConstants.DETAILS.equals(inventoryLevelCaption)) {
			visibleColumns = visibleColumn[2].toArray(new Object[visibleColumn[2].size()]);
			visibleHeaders = visibleHeader[2].toArray(new String[visibleHeader[2].size()]);
		} else if (GtnTransactionUIConstants.PROJECTIONS.equals(inventoryTypeCaption)
				&& GtnTransactionUIConstants.SUMMARY.equals(inventoryLevelCaption)) {
			visibleColumns = visibleColumn[3].toArray(new Object[visibleColumn[3].size()]);
			visibleHeaders = visibleHeader[3].toArray(new String[visibleHeader[3].size()]);
		}
                visbleColumnAndHeaderList.add(visibleColumns);
                visbleColumnAndHeaderList.add(visibleHeaders);
              
                return visbleColumnAndHeaderList;
	}

	private  List<Object> getVisibleColumnForDemand(
			List<String>[] visibleColumn, List<String>[] visibleHeader, String tableName)
			throws GtnFrameworkValidationFailedException {
            Object[] visibleColumns=null;
            String[] visibleHeaders=null;
             List<Object> visbleColumnAndHeaderList=new ArrayList<>();
		String wsViewName = tableName;
		String demandTypeCaption = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("adjustedDemandForecastId", "adjustedDemandForecastId")
				.getCaptionFromComboBox();
		String forecastTypeCaption = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("forecastTypeSid", "forecastTypeSid")
				.getCaptionFromComboBox();
		if (GtnTransactionUIConstants.ADJUSTED_DEMAND.equals(demandTypeCaption)
				&& GtnTransactionUIConstants.ACTUALS.equals(forecastTypeCaption)) {
			visibleColumns = visibleColumn[0].toArray(new Object[visibleColumn[0].size()]);
			visibleHeaders = visibleHeader[0].toArray(new String[visibleHeader[0].size()]);
		} else if (GtnTransactionUIConstants.ADJUSTED_DEMAND.equals(demandTypeCaption)
				&& GtnTransactionUIConstants.PROJECTIONS.equals(forecastTypeCaption)) {
			visibleColumns = visibleColumn[1].toArray(new Object[visibleColumn[1].size()]);
			visibleHeaders = visibleHeader[1].toArray(new String[visibleHeader[1].size()]);
		} else if (GtnTransactionUIConstants.DEMAND.equals(demandTypeCaption)
				&& GtnTransactionUIConstants.ACTUALS.equals(forecastTypeCaption)) {
			visibleColumns = visibleColumn[2].toArray(new Object[visibleColumn[2].size()]);
			visibleHeaders = visibleHeader[2].toArray(new String[visibleHeader[2].size()]);
			wsViewName = getTableName(tableName);
		} else if (GtnTransactionUIConstants.DEMAND.equals(demandTypeCaption)
				&& GtnTransactionUIConstants.PROJECTIONS.equals(forecastTypeCaption)) {
			visibleColumns = visibleColumn[3].toArray(new Object[visibleColumn[3].size()]);
			visibleHeaders = visibleHeader[3].toArray(new String[visibleHeader[3].size()]);
			wsViewName = getTableName(tableName);
		}
                visbleColumnAndHeaderList.add(visibleColumns);
                visbleColumnAndHeaderList.add(visibleHeaders);
                visbleColumnAndHeaderList.add(wsViewName);
		return visbleColumnAndHeaderList;
	}

}
