package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnRelationshipVersionLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String relationshipValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString(), componentId).getCaptionFromComboBox();
		Integer relationshipBuilderSid = (Integer) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getValueFromComponent();
		if (relationshipValue != "") {
			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(2).toString(),componentId).getComponentConfig()
					.getGtnComboboxConfig();
			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION);

			GtnUIFrameworkComboboxComponent combobox = new GtnUIFrameworkComboboxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2), componentId,
					Arrays.asList(relationshipBuilderSid));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
