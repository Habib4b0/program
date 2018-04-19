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
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		configureParams(gtnUIFrameWorkActionConfig);

		List<String> vaadinVieldValueList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkValidationType validationType = (GtnUIFrameworkValidationType) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = null;
		if (gtnUIFrameWorkActionConfig.getActionParameterList().size() > 2) {
			onSucessActionConfigList = (List<GtnUIFrameWorkActionConfig>) gtnUIFrameWorkActionConfig
					.getActionParameterList().get(2);
		}

		for (int i = 0; i < vaadinVieldValueList.size(); i++) {
			String fieldId = vaadinVieldValueList.get(i);
			try {

				for (GtnUIFrameworkSupportedValidationType currentvalidationType : supportedValidationList) {

					GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = getValidationConfig(fieldId,
							componentId);

					GtnUIFrameworkValidator validator = currentvalidationType.getGtnUIFrameWorkAction();
					validator.validate(componentId, fieldId, gtnUIFrameworkValidationConfig);

				}

				if (GtnUIFrameworkValidationType.OR == validationType) {
					break;
				}
			} catch (GtnFrameworkGeneralException exception) {
				List<GtnUIFrameWorkActionConfig> onFailureActionConfigList = (List<GtnUIFrameWorkActionConfig>) gtnUIFrameWorkActionConfig
						.getActionParameterList().get(1);
				if (GtnUIFrameworkValidationType.AND == validationType || (i == vaadinVieldValueList.size() - 1)) {
					onFailure(fieldId, onFailureActionConfigList, exception);
				}

			}

		}
		onSucess(componentId, onSucessActionConfigList);

	}

	public void onSucess(final String componetId, List<GtnUIFrameWorkActionConfig> onSucessActionConfigList)
			throws GtnFrameworkGeneralException {
		if (onSucessActionConfigList != null) {
			for (GtnUIFrameWorkActionConfig actionConfig : onSucessActionConfigList) {
				final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
				action.doAction(componetId, actionConfig);
			}
		}
	}

	public void onFailure(final String componetId, List<GtnUIFrameWorkActionConfig> onFailureActionConfigList,
			GtnFrameworkGeneralException exception) throws GtnFrameworkGeneralException {

		if (onFailureActionConfigList != null) {
			for (GtnUIFrameWorkActionConfig actionConfig : onFailureActionConfigList) {
				final GtnUIFrameWorkAction action = actionConfig.getActionType().getGtnUIFrameWorkAction();
				action.doAction(componetId, actionConfig);
			}
		}
		throw exception;
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
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