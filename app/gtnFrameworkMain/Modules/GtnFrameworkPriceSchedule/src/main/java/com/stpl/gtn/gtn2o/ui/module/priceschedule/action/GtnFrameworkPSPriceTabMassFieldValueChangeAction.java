package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.HashMap;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;

public class GtnFrameworkPSPriceTabMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final Map<String, String> comboBoxFieldMap = new HashMap<>();
		configureMap(comboBoxFieldMap);
		GtnUIFrameworkBaseComponent psPricingTabTabMassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabTabMassDropDown");
		GtnUIFrameworkBaseComponent psPricingTabTabmassCustomTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabTabmassCustomTextField");
		GtnUIFrameworkBaseComponent psPricingTabTabmassTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabTabmassTextField");
		GtnUIFrameworkBaseComponent psPricingTabTabMassDateFeild = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabTabMassDateFeild");
		GtnUIFrameworkBaseComponent psPricingTabTabmassTextFieldLoading = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabTabmassTextFieldLoading");
		psPricingTabTabMassDateFeild.setVisible(false);
		psPricingTabTabMassDropDown.setVisible(false);
		psPricingTabTabmassCustomTextField.setVisible(false);
		psPricingTabTabmassTextField.setVisible(false);
		psPricingTabTabmassTextFieldLoading.setVisible(false);
		psPricingTabTabmassCustomTextField.setPropertyValue("");
		psPricingTabTabmassTextField.setPropertyValue("");
		psPricingTabTabMassDateFeild.setPropertyValue(null);
		psPricingTabTabMassDropDown.loadComboBoxComponentValue(null);
		GtnUIFrameworkComponentData componenetData = (GtnUIFrameworkComponentData) psPricingTabTabmassCustomTextField
				.getData();
		componenetData.setCustomData(null);
		String valueFromComboBox = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabTabMassField")
				.getCaptionFromComboBox();

		if (valueFromComboBox != null) {
			psPricingTabTabmassTextFieldLoading.setVisible(false);
			if (valueFromComboBox.endsWith("Date")) {
				psPricingTabTabMassDateFeild.setVisible(true);

			} else if (valueFromComboBox.endsWith("Price")) {
				psPricingTabTabmassTextField.setVisible(true);
			} else {
				psPricingTabTabMassDropDown.setVisible(true);
				fillPriceComboBox("psPricingTabTabMassDropDown", comboBoxFieldMap.get(valueFromComboBox));

			}

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkPSPriceTabMassFieldValueChangeAction();
	}

	private void configureMap(Map<String, String> comboBoxFieldMap) {

		comboBoxFieldMap.put("Status", "STATUS");
		comboBoxFieldMap.put("Price Type", "ItemPricingQualifier");

	}

	private void fillPriceComboBox(String componentId, String type) {

		GtnUIFrameworkWebserviceComboBoxResponse pricingResponse = GtnUIFrameworkGlobalUI
				.getComboBoxItemListFromService(type);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.loadMassUpdateComboBox(pricingResponse.getItemCodeList(), pricingResponse.getItemValueList());

	}

}
