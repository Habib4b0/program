package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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

public class GtnFrameworkIfpItemAdditionSearchValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> textFields = Arrays.asList("Brand", "Item ID", "Item No", "Item Name");
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchField").getStringFromField();
		boolean isTextField = textFields.contains(value);

		boolean isSelectOne = (value == null) || value.isEmpty();

		GtnUIFrameworkBaseComponent textField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ifpItemAdditionSearchValue");
		GtnUIFrameworkBaseComponent dropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_TAB_VALUE_DROP_DOWN);

		if (isSelectOne) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchValueLayout").setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionTabValueDropDownLayout").setVisible(false);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionSearchValueLayout").setVisible(isTextField);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemAdditionTabValueDropDownLayout")
					.setVisible(!isTextField);
		}

		if (dropDown.isVisible() && !isSelectOne) {
			dropDown.removeAllItemsFromComboBox();
			String helperListName;
			switch (value) {
			case "Form":
				helperListName = "FORM";
				break;
			case "Item Status":
				helperListName = "STATUS";
				break;
			case "Strength":
				helperListName = "STRENGTH";
				break;
			case "Therapeutic Class":
				helperListName = "THERAPEUTIC_CLASS";
				break;
			case "UOM":
				helperListName = "UOM";
				break;
			default:
				helperListName = "STATUS";
				break;
			}

			GtnUIFrameworkComboBoxConfig companyTypeConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_TAB_VALUE_DROP_DOWN).getComponentConfig()
					.getGtnComboboxConfig();
			companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			companyTypeConfig.setComboBoxType(helperListName);

			GtnUIFrameworkComboboxComponent combobox = new GtnUIFrameworkComboboxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_TAB_VALUE_DROP_DOWN,
					componentId, Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
		}
		textField.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
