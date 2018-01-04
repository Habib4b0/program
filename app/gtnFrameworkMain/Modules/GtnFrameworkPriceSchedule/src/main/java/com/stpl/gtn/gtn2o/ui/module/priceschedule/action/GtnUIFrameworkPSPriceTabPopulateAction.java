/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPSPriceTabPopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final Map<String, String> propertyColumMap = new HashMap<>();
		configurePopUp(propertyColumMap);
		GtnWsCheckAllUpdateBean checkAllUpdateBean = new GtnWsCheckAllUpdateBean();

		if ("psPricingTabTabPopulateAllButton".equals(componentId)) {
			checkAllUpdateBean.setCheckAll(true);
		}

		ComboBox psPricingTabTabMassDropDown = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinComponent("psPricingTabTabMassDropDown");
		TextField psPricingTabTabmassTextField = (TextField) GtnUIFrameworkGlobalUI
				.getVaadinComponent("psPricingTabTabmassTextField");
		PopupDateField psPricingTabTabMassDateFeild = (PopupDateField) GtnUIFrameworkGlobalUI
				.getVaadinComponent("psPricingTabTabMassDateFeild");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent("psPricingTabTabMassField");

		if (component.getValue() != null) {

			checkAllUpdateBean.setPropertyId(propertyColumMap.get(component.getValue().toString()));

			if (component.getValue().toString().endsWith("Date")) {

				checkAllUpdateBean.setValue(formatter.format(psPricingTabTabMassDateFeild.getValue()));

			} else if (component.getValue().toString().endsWith("Price")) {
				checkAllUpdateBean.setValue(psPricingTabTabmassTextField.getValue());
			} else {
				checkAllUpdateBean.setValue(psPricingTabTabMassDropDown.getValue());

			}

		}
		GtnUIFrameworkGlobalUI.updateFieldByMassUpdate(checkAllUpdateBean,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PRICETAB_POPULATE_SERVICE);

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psPricingTabResultDataTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePopUp(Map<String, String> propertyColumMap) {

		propertyColumMap.put("Status", "psStatus");
		propertyColumMap.put("Price", "psPrice");
		propertyColumMap.put("CP Start Date", "cpStartDate");
		propertyColumMap.put("CP End Date", "cpEndDate");
		propertyColumMap.put("Price Type", "priceType");
		propertyColumMap.put("Suggested Price", "suggestedPrice");

	}

}
