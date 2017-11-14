package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkItemMasterRefreshPrcingEditListIdentifierAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Object msg = "Are you sure you want to reset the page to default/previous values? ";
		List<String> fieldValueList = gtnUIFrameWorkActionConfig.getFieldValues();
		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = yesButtonClickEvent(fieldValueList);
		GtnUIFrameWorkActionConfig confirmation = new GtnUIFrameWorkActionConfig();
		confirmation.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmation.addActionParameter("Confirmation");
		confirmation.addActionParameter(msg);
		confirmation.addActionParameter(finalYesActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmation);
	}

	private GtnUIFrameWorkActionConfig refreshComponenent(List<String> fieldValueList) {
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Object emptyString = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		List<String> resetIds = Arrays.asList(fieldValueList.get(GtnWsNumericConstants.ZERO),
				fieldValueList.get(GtnWsNumericConstants.ONE), fieldValueList.get(GtnWsNumericConstants.FOUR));
		List<Object> resetValues = Arrays.asList(emptyString, emptyString, emptyString);
		List<Object> selectResetParams = new ArrayList<>();
		Object selectOne = GtnFrameworkCommonConstants.SELECT_ONE;
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValueList.get(GtnWsNumericConstants.TWO))
				.loadDefaultComboboxAll(Arrays.asList(selectOne, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValueList.get(GtnWsNumericConstants.THREE))
				.loadDefaultComboboxAll(Arrays.asList(selectOne, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
		selectResetParams.add(resetIds);
		selectResetParams.add(resetValues);
		resetTableConfig.setActionParameterList(selectResetParams);

		return resetTableConfig;
	}

	private List<GtnUIFrameWorkActionConfig> yesButtonClickEvent(List<String> fieldValueList) {
		List<GtnUIFrameWorkActionConfig> finalYesActionConfigList = new ArrayList<>();
		finalYesActionConfigList.add(refreshComponenent(fieldValueList));
		return finalYesActionConfigList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
