package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnRelationshipVersionLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnRelationshipVersionLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String relationshipValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(1).toString(), componentId)
					.getStringCaptionFromV8ComboBox();
			Integer relationshipBuilderSid = Integer.valueOf(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(1).toString(), componentId)
							.getCaptionFromV8ComboBox());
			if (relationshipValue != "") {
				GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(actionParamsList.get(2).toString(), componentId).getComponentConfig()
						.getGtnComboboxConfig();
				relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

				GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
				combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
						(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2), componentId,
						Arrays.asList(relationshipBuilderSid));
			}
		} catch (Exception e) {
			logger.error("Error in", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
