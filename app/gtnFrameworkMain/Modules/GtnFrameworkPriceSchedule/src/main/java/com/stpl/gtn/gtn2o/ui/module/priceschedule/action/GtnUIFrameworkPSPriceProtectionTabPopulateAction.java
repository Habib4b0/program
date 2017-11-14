/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceProtectionValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPSPriceProtectionTabPopulateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final Map<String, List<String>> popupIdViewMap = new HashMap<>();
		final Map<String, String> propertyColumMap = new HashMap<>();
		final Map<String, String> psPriceProtectioncomboBoxFieldMap = new HashMap<>();
		configurePopUp(popupIdViewMap, propertyColumMap, psPriceProtectioncomboBoxFieldMap);
		GtnWsCheckAllUpdateBean checkAllUpdateBean = new GtnWsCheckAllUpdateBean();
		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDropDown");
		GtnUIFrameworkBaseComponent psPriceProtectionTabmassTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabTabmassTextField");
		GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassCustomTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabTabmassCustomTextField");
                
		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeild = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDateFeild");
		GtnUIFrameworkBaseComponent psPriceProtectionTabBasePriceDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabPsBasePriceDdlb");
		if (!"psPriceProtectionTabPopulateButton".equals(componentId)) {
			checkAllUpdateBean.setCheckAll(true);
		}
		boolean valuePresent = validationCheck(psPriceProtectionTabMassDropDown, psPriceProtectionTabmassTextField,
				psPriceProtectionTabMassDateFeild, psPriceProtectionTabTabmassCustomTextField,componentId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionTabMassField")
				.getStringFromField();

		if (!componentValue.isEmpty() && valuePresent) {

			if (popupIdViewMap.get(componentValue) != null) {
				putPopupValuesinMap(checkAllUpdateBean, componentValue, popupIdViewMap,propertyColumMap.get(componentValue));
			} else {
				checkAllUpdateBean.setPropertyId(propertyColumMap.get(componentValue));
				checkAllUpdateBean.setValue(psPriceProtectionTabmassTextField.getStringFromField());
				if (componentValue.endsWith("Date")) {

					checkAllUpdateBean.setValue(getDateValue(formatter, psPriceProtectionTabMassDateFeild));

				} else if (psPriceProtectioncomboBoxFieldMap.containsKey(componentValue)) {

					checkAllUpdateBean.setValue(psPriceProtectionTabMassDropDown.getIntegerFromField());

				} else if (GtnFrameworkCommonConstants.BASE_PRICE_TYPE.equals(componentValue)) {
					checkAllUpdateBean.setValue(psPriceProtectionTabBasePriceDropDown.getIntegerFromField());
					getBasePriceValue(psPriceProtectionTabBasePriceDropDown.getCaptionFromComboBox(),
							checkAllUpdateBean, formatter, psPriceProtectionTabMassDateFeild,
							psPriceProtectionTabMassDropDown, psPriceProtectionTabmassTextField);
				}

			}
			GtnUIFrameworkGlobalUI.updateFieldByMassUpdate(checkAllUpdateBean, "/" + GtnWsCDRContants.PS_SERVICE + "/"
					+ GtnWsCDRContants.PS_PRICE_PROTECTION_TAB_POPULATE_SERVICE);
			GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData("psPriceProtectionTabResultDataTable");
			GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
			tableLogic.startSearchProcess(new ArrayList<String>(), true);
			GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private boolean validationCheck(GtnUIFrameworkBaseComponent psPriceProtectionTabMassDropDown,
			GtnUIFrameworkBaseComponent psPriceProtectionTabmassTextField,
			GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeild,GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassCustomTextField,
                        String componentId)
			throws GtnFrameworkGeneralException {
		boolean isValuePresent = true;
		if (psPriceProtectionTabMassDropDown.getIntegerFromField() == 0
				&& psPriceProtectionTabmassTextField.getStringFromField().isEmpty()
				&& psPriceProtectionTabMassDateFeild.getDateFromDateField() == null
                                && psPriceProtectionTabTabmassCustomTextField.getStringFromField().isEmpty()) {
			GtnUIFrameWorkActionConfig psProtectionPopulateFailActionConfig = new GtnUIFrameWorkActionConfig();
			psProtectionPopulateFailActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			psProtectionPopulateFailActionConfig.addActionParameter("Populate Error");
			psProtectionPopulateFailActionConfig.addActionParameter("Please Enter Value");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, psProtectionPopulateFailActionConfig);
			isValuePresent = false;
		}
		return isValuePresent;
	}

	private String getDateValue(SimpleDateFormat formatter,
			GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeild)
			throws GtnFrameworkValidationFailedException {
		return formatter.format(psPriceProtectionTabMassDateFeild.getDateFromDateField());

	}

	private void getBasePriceValue(String captionFromComboBox, GtnWsCheckAllUpdateBean checkAllUpdateBean,
			SimpleDateFormat formatter, GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeild,
			GtnUIFrameworkBaseComponent psPriceProtectionTabMassDropDown,
			GtnUIFrameworkBaseComponent psPriceProtectionTabmassTextField)
			throws GtnFrameworkValidationFailedException {
		String propertyId = "psBasePrice";
		if (captionFromComboBox.startsWith("P")) {
			checkAllUpdateBean.setPropertyId1(propertyId + "Ddlb");
			checkAllUpdateBean.setValue1(psPriceProtectionTabMassDropDown.getIntegerFromField());
		}
		if (captionFromComboBox.startsWith("D")) {
			checkAllUpdateBean.setPropertyId1(propertyId + "Date");
			checkAllUpdateBean.setValue1(getDateValue(formatter, psPriceProtectionTabMassDateFeild));
		}
		if (captionFromComboBox.startsWith("M")) {
			checkAllUpdateBean.setPropertyId1(propertyId + "Entry");
			checkAllUpdateBean.setValue1(psPriceProtectionTabmassTextField.getStringFromField());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkPSPriceProtectionTabPopulateAction();
	}

	void putPopupValuesinMap(GtnWsCheckAllUpdateBean rsPopulate, String componentValue,
			final Map<String, List<String>> popupIdViewMap,String populateColumn) {
		Map<String, String> valueMap = new HashMap<>();
		rsPopulate.setPropertyValueMap(valueMap);
		rsPopulate.setPropertyId(componentValue);
		GtnWsRecordBean dto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psPriceProtectionTabTabmassCustomTextField").getCustomData();
		for (int i = 0; i < popupIdViewMap.get(componentValue).size(); i++) {
			String newValue;
			if (dto.getPropertyValue(popupIdViewMap.get(componentValue).get(i)) != null) {
				newValue = String.valueOf(dto.getPropertyValue(popupIdViewMap.get(componentValue).get(i)));
			} else {
				newValue = String.valueOf(dto.getPropertyValueByIndex(dto.getRecordHeader().size()));
			}
			valueMap.put(populateColumn, newValue);
		}
	}

	private static void configurePopUp(Map<String, List<String>> popupIdViewMap, Map<String, String> propertyColumMap,
			Map<String, String> psPriceProtectioncomboBoxFieldMap) {

		popupIdViewMap.put("NEP Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
		popupIdViewMap.put("Net Price Type Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
                popupIdViewMap.put("Net Baseline WAC Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
                popupIdViewMap.put("Net Subsequent Period Price Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
                popupIdViewMap.put("Net Reset Price Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
           
                
		propertyColumMap.put("Price Protection Status", "psPPStatus");
		propertyColumMap.put("Price Protection Start Date", "psPPStartDate");
		propertyColumMap.put("Price Protection End Date", "psPPEndDate");
		propertyColumMap.put("Price Protection Price Type", "psPPPriceType");
		propertyColumMap.put("NEP", "psNEP");
		propertyColumMap.put("Base Price Type", "psBasePriceType");
		propertyColumMap.put("Baseline WAC", "psBasePriceType");
		propertyColumMap.put("Price Tolerance Interval", "psToleranceInterval");
		propertyColumMap.put("Price Tolerance Frequency", "psToleranceFreq");
		propertyColumMap.put("Price Tolerance Type", "psToleranceType");
		propertyColumMap.put("Max Incremental Change", "psMaxIncrementalChange");
		propertyColumMap.put("Price Tolerance", "psDetailsPriceTol");
		propertyColumMap.put("Reset Eligible", "psResetEligible");
		propertyColumMap.put("Reset Type", "psResetType");
		propertyColumMap.put("Reset Date", "psResetDate");
		propertyColumMap.put("Reset Interval", "psResetInterval");
		propertyColumMap.put("Reset Frequency", "psResetFrequency");
		propertyColumMap.put("Net Price Type", "psNetPriceType");
                
		propertyColumMap.put("Measurement Price", "psPPPriceType");
		propertyColumMap.put("Net Reset Price Formula", "psNetResetPriceFormulaId1");
		propertyColumMap.put("Net Reset Price Type", "psNetResetPriceType");
		propertyColumMap.put("NEP Formula", "psNEPFormula1");
		propertyColumMap.put("Net Price Type Formula", "psNetPriceTypeFormula1");
		propertyColumMap.put("Reset Price Type", "psResetPriceType");
		propertyColumMap.put("Net Subsequent Period Price Formula", "psNetBSubseqPriceFormulaId1");
		propertyColumMap.put("Net Subsequent Period Price", "psNetBSubseqPeriodPrice");
		propertyColumMap.put("Subsequent Period Price Type", "psSubseqPeriodPriceType");
		propertyColumMap.put("Net Baseline WAC Formula", "psNetBasePriceFormulaId1");
		propertyColumMap.put("Baseline Net WAC", "psNetBasePrice");
        
		GtnFrameworkPSConstants.loadPsPriceProtectioncomboBoxFieldMap(psPriceProtectioncomboBoxFieldMap);

	}

}
