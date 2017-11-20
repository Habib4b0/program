package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCfpCompanyAdditionSearchValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> textFields = Arrays.asList("Company No", "Company Name");
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchField")
				.getStringFromField();
		boolean isTextField = textFields.contains(value);

		boolean isSelectOne = (value == null) || value.isEmpty();

		GtnUIFrameworkBaseComponent textField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CFPCompanyAdditionSearchValue");
		GtnUIFrameworkBaseComponent dropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_TAB_VALUE_DROP_DOWN);

		if (isSelectOne) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchValueLayout").setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompanyAdditionTabValueDropDownLayout").setVisible(false);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPCompanyAdditionSearchValueLayout")
					.setVisible(isTextField);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompanyAdditionTabValueDropDownLayout")
					.setVisible(!isTextField);
		}

		if (dropDown.isVisible() && value!=null) {
			dropDown.removeAllItemsFromComboBox();
			String helperListName = null;
			switch (value) {
			case "Company Type":
				helperListName = "COMPANY_TYPE";
				break;
			case "Company Status":
				helperListName = "STATUS";
				break;
			default:
				helperListName = "STATUS";
				break;
			}

			GtnUIFrameworkComboBoxConfig companyTypeConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_TAB_VALUE_DROP_DOWN).getComponentConfig()
					.getGtnComboboxConfig();
			companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			companyTypeConfig.setComboBoxType(helperListName);

			GtnUIFrameworkComboboxComponent combobox = new GtnUIFrameworkComboboxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_TAB_VALUE_DROP_DOWN,
					componentId, Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
		}
		textField.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
