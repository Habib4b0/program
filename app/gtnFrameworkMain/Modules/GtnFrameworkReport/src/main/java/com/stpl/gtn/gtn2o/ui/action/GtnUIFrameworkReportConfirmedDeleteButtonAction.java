package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
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

public class GtnUIFrameworkReportConfirmedDeleteButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkReportConfirmedDeleteButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
	 	AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString(), componentId).getComponent();
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		Set value = grid.getSelectedItems();
		if (value.isEmpty()) {
			GtnUIFrameWorkActionConfig reportProfileDeleteAlertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.INFO_ACTION);
			reportProfileDeleteAlertAction.addActionParameter(parameters.get(2).toString());
			reportProfileDeleteAlertAction.addActionParameter(parameters.get(3).toString());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportProfileDeleteAlertAction);
			return;
		}
		GtnUIFrameWorkActionConfig reportProfileConfirmDeleteAction = new GtnUIFrameWorkActionConfig();
		reportProfileConfirmDeleteAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		reportProfileConfirmDeleteAction.addActionParameter("Delete Confirmation");
		reportProfileConfirmDeleteAction.addActionParameter("Are you sure you want to delete the selected view?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportProfileDeleteViewAction = new GtnUIFrameWorkActionConfig();
		reportProfileDeleteViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportProfileDeleteViewAction.addActionParameter(GtnReportReportProfileDeleteAction.class.getName());
		reportProfileDeleteViewAction.addActionParameter(parameters.get(1).toString());
		successActionConfigList.add(reportProfileDeleteViewAction);
		reportProfileConfirmDeleteAction.addActionParameter(successActionConfigList);
		
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportProfileConfirmDeleteAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
