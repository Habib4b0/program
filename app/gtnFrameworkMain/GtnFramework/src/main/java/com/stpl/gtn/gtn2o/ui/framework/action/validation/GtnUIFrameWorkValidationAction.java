package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkSupportedValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkValidationAction implements GtnUIFrameWorkAction {

	private static List<GtnUIFrameworkSupportedValidationType> supportedValidationList = new ArrayList<>();

	static {
		supportedValidationList.add(GtnUIFrameworkSupportedValidationType.LENGTH);
		supportedValidationList.add(GtnUIFrameworkSupportedValidationType.SIZE);
		supportedValidationList.add(GtnUIFrameworkSupportedValidationType.FORMAT);
		supportedValidationList.add(GtnUIFrameworkSupportedValidationType.CONDITION);
		supportedValidationList.add(GtnUIFrameworkSupportedValidationType.CUSTOM);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(final String validationComponentId, GtnUIFrameWorkActionConfig validationGtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		configureParams(validationGtnUIFrameWorkActionConfig);

		List<String> validationVaadinVieldValueList = validationGtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkValidationType validationActionType = (GtnUIFrameworkValidationType) validationGtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		List<GtnUIFrameWorkActionConfig> validationOnSucessActionConfigList = null;
		if (validationGtnUIFrameWorkActionConfig.getActionParameterList().size() > 2) {
			validationOnSucessActionConfigList = (List<GtnUIFrameWorkActionConfig>) validationGtnUIFrameWorkActionConfig
					.getActionParameterList().get(2);
		}

		for (int i = 0; i < validationVaadinVieldValueList.size(); i++) {
			String validationFieldId = validationVaadinVieldValueList.get(i);
			try {

				for (GtnUIFrameworkSupportedValidationType validationCurrentvalidationType : supportedValidationList) {

					GtnUIFrameworkValidationConfig validationGtnUIFrameworkValidationConfig = getValidationConfig(validationFieldId,
							validationComponentId);

					GtnUIFrameworkValidator validator = validationCurrentvalidationType.getGtnUIFrameWorkAction();
					validator.validate(validationComponentId, validationFieldId, validationGtnUIFrameworkValidationConfig);

				}

				if (GtnUIFrameworkValidationType.OR == validationActionType) {
					break;
				}
			} catch (GtnFrameworkGeneralException exception) {
				List<GtnUIFrameWorkActionConfig> onFailureActionConfigList = (List<GtnUIFrameWorkActionConfig>) validationGtnUIFrameWorkActionConfig
						.getActionParameterList().get(1);
				if (GtnUIFrameworkValidationType.AND == validationActionType || (i == validationVaadinVieldValueList.size() - 1)) {
					onFailure(validationFieldId, onFailureActionConfigList, exception);
				}

			}

		}
		onSucess(validationComponentId, validationOnSucessActionConfigList);

	}

	public void onSucess(final String onSuccessComponetId, List<GtnUIFrameWorkActionConfig> onSucessActionConfigList)
			throws GtnFrameworkGeneralException {
		if (onSucessActionConfigList != null) {
			for (GtnUIFrameWorkActionConfig validationActionConfig : onSucessActionConfigList) {
				final GtnUIFrameWorkAction action = validationActionConfig.getActionType().getGtnUIFrameWorkAction();
				action.doAction(onSuccessComponetId, validationActionConfig);
			}
		}
	}

	public void onFailure(final String componetId, List<GtnUIFrameWorkActionConfig> validationOnFailureActionConfigList,
			GtnFrameworkGeneralException exception) throws GtnFrameworkGeneralException {

		if (validationOnFailureActionConfigList != null) {
			for (GtnUIFrameWorkActionConfig actionConfig : validationOnFailureActionConfigList) {
				final GtnUIFrameWorkAction validationAction = actionConfig.getActionType().getGtnUIFrameWorkAction();
				validationAction.doAction(componetId, actionConfig);
			}
		}
		throw exception;
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnValidationUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return GtnUIFrameworkActionType.VALIDATION_ACTION.getSingletonAction();
	}

	private GtnUIFrameworkValidationConfig getValidationConfig(String fieldId, String componentId) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(fieldId, componentId);
		GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();

		return componentConfig.getGtnUIFrameworkValidationConfig();

	}

}