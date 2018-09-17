package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;
import java.util.Set;


import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;

public class GtnSelectButtonEnableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass 

{
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnSelectButtonEnableAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("loading configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getComponent();
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		Set<GtnWsRecordBean> selectedItem = grid.getSelectedItems();
		if(!selectedItem.isEmpty()) {
			
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
			actionConfig.addActionParameter(actionParameterList.get(1).toString());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
		}
		else {
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
			actionConfig.addActionParameter(actionParameterList.get(1).toString());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}
}
