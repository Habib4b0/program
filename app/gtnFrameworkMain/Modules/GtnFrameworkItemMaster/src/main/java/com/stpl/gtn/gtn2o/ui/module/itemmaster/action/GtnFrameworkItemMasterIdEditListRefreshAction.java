package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

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

public class GtnFrameworkItemMasterIdEditListRefreshAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty method

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object emptyString = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		List<String> resetIds = Arrays.asList(fieldValues.get(GtnWsNumericConstants.ZERO),
				fieldValues.get(GtnWsNumericConstants.ONE), fieldValues.get(GtnWsNumericConstants.FOUR));
		List<Object> resetValues = Arrays.asList(emptyString, emptyString, emptyString);
		Object selectOne = GtnFrameworkCommonConstants.SELECT_ONE;
		List<Object> selectResetParams = new ArrayList<>();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.TWO))
				.loadDefaultComboboxAll(Arrays.asList(selectOne, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.THREE))
				.loadDefaultComboboxAll(Arrays.asList(selectOne, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.FIVE)).setCaption("SAVE");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.ZERO)).loadFieldValue(emptyString);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.ONE)).loadFieldValue(emptyString);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.FOUR)).loadFieldValue(emptyString);
		selectResetParams.add(resetIds);
		selectResetParams.add(resetValues);
		resetTableConfig.setActionParameterList(selectResetParams);

		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetTableConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
