package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkItemMasterIdEditListDeleteValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameter.get(1).toString())
				.getValueFromDataTable();
		Boolean recordLockStatus = (selectedId.getPropertyValue(actionParameter.get(2).toString()) == null) ? false
				: ((Boolean) selectedId.getPropertyValue(actionParameter.get(2).toString()));
		getMsgAlertForDelete(recordLockStatus, componentId, actionParameter.get(1),
				gtnUIFrameWorkActionConfig.getFieldValues(), actionParameter.get(3).toString(),"Identifier".equals(actionParameter.get(4).toString()));
	}

	private void getMsgAlertForDelete(Boolean recordLockStatus, String componentId, Object tableComponent,
			List<String> fieldValues, String customClass, boolean isIdentiferTab) throws GtnFrameworkGeneralException {

		if (recordLockStatus) {
			Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_VALIDATION_MSG_DELETE_001;
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			alertActionConfig.setActionParameterList(Arrays
					.asList(GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_ACCESS_DENIED_MSG_HEADER, msg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error " + msg);

		} else {
			Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_VALIDATION_MSG_DELETE_002;
			List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkConfirmationAction confirmationAction = new GtnUIFrameWorkConfirmationAction();
			confirmationActionConfig.setActionParameterList(Arrays.asList(
					GtnFrameworkItemMasterStringContants.GTN_ITEM_QUALIFIER_DELETE_CONFIRMATION_MSG_HEADER, msg,
					onSucessActionConfigList));

			GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
			deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			deleteActionConfig.addActionParameter(customClass);
			deleteActionConfig.addActionParameter(tableComponent);
			deleteActionConfig.addActionParameter(isIdentiferTab);
			deleteActionConfig.setFieldValues(fieldValues);

			GtnUIFrameWorkActionConfig refreshTableActionConfig = new GtnUIFrameWorkActionConfig();
			refreshTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

			List<Object> actionParams = new ArrayList<>();
			actionParams.add(tableComponent);
			refreshTableActionConfig.setActionParameterList(actionParams);

			onSucessActionConfigList.add(deleteActionConfig);
			onSucessActionConfigList.add(refreshTableActionConfig);

			confirmationAction.doAction(componentId, confirmationActionConfig);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
