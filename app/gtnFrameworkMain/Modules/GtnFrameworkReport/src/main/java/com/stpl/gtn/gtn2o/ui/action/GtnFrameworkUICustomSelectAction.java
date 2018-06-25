package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkUICustomSelectAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		boolean isEdit = (boolean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookupcustomViewSave")
				.getComponentData().getCustomData();
		String tabName = "reportCustomViewLookup";
		if (!isEdit) {
			saveView(componentId, tabName);

		}
		isEdit = (boolean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportCustomViewLookupcustomViewSave")
				.getComponentData().getCustomData();
		if (isEdit) {
			setSelectdedNameInComboBox(componentId, tabName);
			closeModal(componentId);
		}
	}

	private void saveView(String componentId, String tabName) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkUICustomTreeSaveAction.class.getName());
		saveActionConfig.addActionParameter(
				tabName + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		saveActionConfig.addActionParameter(tabName + "customTreeTable");
		saveActionConfig.addActionParameter(false);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, saveActionConfig);
	}

	private void closeModal(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig closeConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closeConfig);
	}

	private void setSelectdedNameInComboBox(String componentId, String tabName) {
		String selectedName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				tabName + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME)
				.getV8StringFromField();

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		String id = sourceComponentId + "_" + "reportingDashboardTab_displaySelectionTabCustomView";
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id).setHasValue(selectedName);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}