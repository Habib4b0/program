package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkLoadValueComboBox implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String resultValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
				.getCaptionFromComboBox();

		if (resultValue != null) {
			String comboType = "";
			if (resultValue.equals("%")) {
				comboType = "TIER_PERCENT_VALUE";
			} else {
				comboType = "ItemPricingQualifier";
			}
			GtnUIFrameworkComboBoxConfig rebatePlanCalculationsValueConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2)).getComponentConfig().getGtnComboboxConfig();
			rebatePlanCalculationsValueConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			rebatePlanCalculationsValueConfig.setComboBoxType(comboType);
			rebatePlanCalculationsValueConfig.setNewItemsAllowed(true);

			GtnUIFrameworkComboboxComponent combobox = new GtnUIFrameworkComboboxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2),
					componentId, Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkLoadValueComboBox();
	}

}
