package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkConfirmationAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCMIdEditListDeleteValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCMIdEditListDeleteValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean selectedId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameter.get(1).toString())
				.getValueFromDataTable();
		Boolean recordLockStatus = (selectedId
				.getPropertyValue(actionParameter.get(GtnWsNumericConstants.TWO).toString()) == null) ? Boolean.FALSE
						: ((Boolean) selectedId
								.getPropertyValue(actionParameter.get(GtnWsNumericConstants.TWO).toString()));
		getMsgAlertForDelete(recordLockStatus, componentId, actionParameter.get(1));
	}

	private void getMsgAlertForDelete(Boolean recordLockStatus, String componentId, Object tableComponent)
			throws GtnFrameworkGeneralException {
		if (recordLockStatus) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			Object deniedMsg = GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_ACCESS_DENIED_MSG_HEADER;
			alertActionConfig.setActionParameterList(Arrays.asList(deniedMsg,
					GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_001));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error "
					+ GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_001);

		} else {
			List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkConfirmationAction confirmationAction = new GtnUIFrameWorkConfirmationAction();
			confirmationActionConfig.setActionParameterList(Arrays.asList(
					GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_DELETE_CONFIRMATION_MSG_HEADER,
					GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_002,
					onSucessActionConfigList));

			GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
			deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			deleteActionConfig.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_DELETE_CUSTOM_ACTION);
			deleteActionConfig.addActionParameter(tableComponent);

			GtnUIFrameWorkActionConfig refreshTableActionConfig = new GtnUIFrameWorkActionConfig();
			refreshTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

			List<Object> actionParams = new ArrayList<>();
			actionParams.add(tableComponent);
			refreshTableActionConfig.setActionParameterList(actionParams);

			onSucessActionConfigList.add(deleteActionConfig);
			onSucessActionConfigList.add(refreshTableActionConfig);

			confirmationAction.doAction(componentId, confirmationActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error "
					+ GtnFrameworkCompanyStringContants.GTN_COMPANY_QUALIFIER_VALIDATION_MSG_DELETE_002);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
