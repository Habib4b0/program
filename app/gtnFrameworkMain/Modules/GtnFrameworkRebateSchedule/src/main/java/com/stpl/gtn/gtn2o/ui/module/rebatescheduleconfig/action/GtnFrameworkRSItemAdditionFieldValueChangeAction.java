/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class GtnFrameworkRSItemAdditionFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		ComboBox rsItemAdditionSearchValueTypeDropDown = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent("RSItemAdditionSearchValueTypeDropDown");
		ComboBox rsItemAdditionSearchValueCategoryDropDown = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent("RSItemAdditionSearchValueCategoryDropDown");
		ComboBox rsItemAdditionSearchValueStatusDropDown = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent("RSItemAdditionSearchValueStatusDropDown");
		TextField massTextField = (TextField) GtnUIFrameworkGlobalUI
				.getVaadinComponent("RSItemAdditionSearchValueText");
		PopupDateField rebateSetupTabMassDateFeild = (PopupDateField) GtnUIFrameworkGlobalUI
				.getVaadinComponent("RSItemAdditionSearchValueDate");

		GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("RSItemAdditiongtnSearch01").getComponentConfig();

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("RSleftResultTable");
		loadDataTableActionConfig.setActionParameterList(actionParams);

		actionConfigList.add(loadDataTableActionConfig);

		gtnUIFrameworkComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		rebateSetupTabMassDateFeild.setVisible(false);
		rsItemAdditionSearchValueTypeDropDown.setVisible(false);
		rsItemAdditionSearchValueCategoryDropDown.setVisible(false);
		rsItemAdditionSearchValueStatusDropDown.setVisible(false);

		massTextField.setVisible(false);
		massTextField.setValue("");

		final ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD);
		if (component.getValue() != null) {
			if (component.getValue().equals("IFP START DATE") || component.getValue().equals("IFP END DATE")) {

				rebateSetupTabMassDateFeild.setVisible(true);
				loadDataTableActionConfig.setFieldValues(Arrays.asList(
						GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD, "RSItemAdditionSearchValueDate"));

			} else if (component.getValue().equals("IFP CATEGORY")) {

				rsItemAdditionSearchValueCategoryDropDown.setVisible(true);
				loadDataTableActionConfig
						.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD,
								"RSItemAdditionSearchValueCategoryDropDown"));

			} else if (component.getValue().equals("IFP TYPE")) {

				rsItemAdditionSearchValueTypeDropDown.setVisible(true);
				loadDataTableActionConfig
						.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD,
								"RSItemAdditionSearchValueTypeDropDown"));

			} else if (component.getValue().equals("IFP STATUS")) {

				rsItemAdditionSearchValueStatusDropDown.setVisible(true);
				loadDataTableActionConfig
						.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD,
								"RSItemAdditionSearchValueStatusDropDown"));

			} else {
				loadDataTableActionConfig.setFieldValues(Arrays.asList(
						GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD, "RSItemAdditionSearchValueText"));
				massTextField.setVisible(true);

			}

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}