package com.stpl.gtn.gtn2o.registry.config.lookups.action;

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

public class GtnForecastLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private GtnWSLogger gtnForecastLevelLogger = GtnWSLogger.getGTNLogger(GtnRelationshipVersionLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> inputActionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String dataSelectionRelationshipValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(inputActionParamsList.get(1).toString(), componentId)
					.getStringCaptionFromV8ComboBox();
			Integer dataSelectionrelationshipBuilderSid = Integer.valueOf(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inputActionParamsList.get(1).toString(), componentId)
							.getCaptionFromV8ComboBox());
			if (dataSelectionRelationshipValue != "") {
				GtnUIFrameworkComboBoxConfig relationshipComboboxConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(inputActionParamsList.get(2).toString(), componentId).getComponentConfig()
						.getGtnComboboxConfig();
				relationshipComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				relationshipComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);

				GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
				combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
						(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2), componentId,
						Arrays.asList(dataSelectionrelationshipBuilderSid));
			}
		} catch (Exception e) {
			gtnForecastLevelLogger.error("Error in", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}