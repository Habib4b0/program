/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import java.lang.ref.WeakReference;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GtnFrameworkDataAssumptionFilterAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkDataAssumptionFilterAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI.getGlobalComponentData();
		Map<String, WeakReference<AbstractComponent>> componentMap = gtnUIFrameworkComponentData.getFrameworkConfigMap()
				.getVaadinComponentMap();
		for (Map.Entry<String, WeakReference<AbstractComponent>> entryKey : componentMap.entrySet()) {
			if (entryKey.getKey().contains(componentId)) {
				componentId = entryKey.getKey();
				break;
			}
		}
		try {
			List<Object> filterValues = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent selectedGrids = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			PagedGrid pagedGrids = (PagedGrid) selectedGrids.getComponentData().getCustomData();
			Grid<GtnWsRecordBean> gridList = pagedGrids.getGrid();
			ListDataProvider<GtnWsRecordBean> dataProvider = (ListDataProvider<GtnWsRecordBean>) gridList.getDataProvider();
			if (filterValues.get(0) == null) {
				dataProvider.clearFilters();
				return;
			}
			dateFilter(dataProvider,filterValues);
		} 
		catch (Exception exception) {
			logger.error("Exception in filtering", exception);
		}
	}

	private void dateFilter(ListDataProvider<GtnWsRecordBean> dataProvider,List<Object> filterValues) {
		dataProvider.setFilter(s -> {
			String filterValue = "";
			String finalValue = "";
			if ((filterValues.get(1).toString().contains("activeFrom"))||(filterValues.get(1).toString().contains("toPeriod"))) {
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
				filterValue = dateTimeFormatter.format((LocalDate) filterValues.get(0));
				finalValue = String.valueOf(s.getPropertyValue(filterValues.get(1).toString()));
			}
			else {
				filterValue = (String) filterValues.get(0);
				finalValue = s.getPropertyValue(filterValues.get(1).toString()).toString().toLowerCase(Locale.ENGLISH);
			}			
			return finalValue.contains(filterValue.toLowerCase(Locale.ENGLISH));
		});
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}