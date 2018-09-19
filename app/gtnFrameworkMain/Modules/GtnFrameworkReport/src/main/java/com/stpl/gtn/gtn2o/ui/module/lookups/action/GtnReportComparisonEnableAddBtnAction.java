package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonEnableAddBtnAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> addBtnActionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent addBtnAvailableGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(addBtnActionParamsList.get(1).toString());
		GtnUIFrameworkComponentData addBtnAvailableGridData = (GtnUIFrameworkComponentData) addBtnAvailableGrid.getData();
		Grid<GtnWsRecordBean> addBtnActiongrid = addBtnAvailableGridData.getPagedGrid().getGrid();
		if(addBtnActiongrid.getDataProvider() instanceof ListDataProvider){
			ListDataProvider addBtnActiondataProvider = (ListDataProvider) addBtnActiongrid.getDataProvider();
			if(addBtnActiondataProvider.getItems().size() > 0){
			GtnUIFrameWorkActionConfig addBtnEnableAction = new GtnUIFrameWorkActionConfig();
			addBtnEnableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
			addBtnEnableAction.addActionParameter(addBtnActionParamsList.get(2).toString()); 
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, addBtnEnableAction);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
