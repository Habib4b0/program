package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSCommonLogic;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;

public class GtnFrameworkRsCustomResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
		Integer rsSystemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
		Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
		if (mode == null || mode == GtnUIFrameworkModeType.ADD) {
			rsSystemId = 0;
		}
		GtnWsRebateScheduleInfoBean rebateScheduleInfoBean = new GtnWsRebateScheduleInfoBean();
		if (rsSystemId == null || rsSystemId == 0) {
			switch (position) {
			case 0:
				resetRebateInfoTab(componentId, rebateScheduleInfoBean);
				break;
			case 2:
				GtnUIFrameworkBaseComponent rSleftResultTable = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("RSleftResultTable");
				rSleftResultTable.resetTable();

				GtnUIFrameworkBaseComponent rSrightResultTable = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("RSrightResultTable");
				rSrightResultTable.resetTable();

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("RSItemAdditionSearchValueTypeDropDown", componentId)
						.loadComboBoxComponentValue(null);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("RSItemAdditionSearchValueText", componentId)
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				break;
			case 3:
				resetRebateSetupTab(componentId);
				break;
			case 4:
				resetNotesTab();
				break;
			default:
				break;
			}

		} else {
			GtnUIFrameWorkActionConfig loadActionConfig = configProvider
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			loadActionConfig.setActionParameterList(Arrays.asList((Object) GtnUIFrameWorkRSLoadAction.class.getName()));
			GtnUIFrameworkActionExecutor.executeCustomAction(componentId, loadActionConfig);

		}

	}

	private void resetNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.refreshNotesTab();
	}

	private void resetRebateSetupTab(String componentId) {
		GtnUIFrameworkBaseComponent itemsTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psRebateSetupTabResultDataTable");
		itemsTable.resetTable();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("RSRebateSetupMassCheck")
				.setPropertyValue(GtnFrameworkCommonStringConstants.DISABLE);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateSetupTabMassDateFeild", componentId).loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("massTextField", componentId).loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebateSetupTabMassDropDown", componentId).loadFieldValue(null);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void resetRebateInfoTab(String componentId, GtnWsRebateScheduleInfoBean rebateScheduleInfoBean)
			throws GtnFrameworkGeneralException {
		GtnFrameworkRSCommonLogic.setValuesToFields(componentId, rebateScheduleInfoBean);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC1", componentId).loadComboBoxComponentValueWithDes("UDC1");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC2", componentId).loadComboBoxComponentValueWithDes("UDC2");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC3", componentId).loadComboBoxComponentValueWithDes("UDC3");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC4", componentId).loadComboBoxComponentValueWithDes("UDC4");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC5", componentId).loadComboBoxComponentValueWithDes("UDC5");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsUDC6", componentId).loadComboBoxComponentValueWithDes("UDC6");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("calculationRule", componentId)
				.loadFieldValue(rebateScheduleInfoBean.getCalculationRule());
		GtnUIFrameWorkCalculationTypeChangeAction calculationChangeAction = new GtnUIFrameWorkCalculationTypeChangeAction();
		calculationChangeAction.doAction(componentId, null);
	}

}
