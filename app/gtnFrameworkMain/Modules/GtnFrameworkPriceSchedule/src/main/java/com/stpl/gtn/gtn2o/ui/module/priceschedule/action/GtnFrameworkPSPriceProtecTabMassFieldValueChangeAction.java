package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameworkPopUpBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;

public class GtnFrameworkPSPriceProtecTabMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		final Map<String, GtnUIFrameworkPopUpBean> popupIdViewMap = new HashMap<>();

		final Map<String, String> psPriceProtectioncomboBoxFieldMap = new HashMap<>();
		configurePopUp(popupIdViewMap, psPriceProtectioncomboBoxFieldMap);

		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDropDownLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDropDownlayout");
		GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassCustomTextFieldLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassCustomTextFieldlayout");
		GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassTextFieldLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("PSPriceProtectionTabMassTextFieldlayout");
		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeildLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDateFeildlayout");
		GtnUIFrameworkBaseComponent psPriceProtectionTabBasePriceDdlbLayout = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabPsBasePriceDdlbLayout");

		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDropDown");
		GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassCustomTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_TABMASS_CUSTOM_TEXT_FIELD);
		GtnUIFrameworkBaseComponent psPriceProtectionTabTabmassTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabTabmassTextField");
		GtnUIFrameworkBaseComponent psPriceProtectionTabMassDateFeild = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabMassDateFeild");
		GtnUIFrameworkBaseComponent psPriceProtectionTabBasePriceDdlb = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPriceProtectionTabPsBasePriceDdlb");

		psPriceProtectionTabMassDateFeildLayout.setVisible(false);
		psPriceProtectionTabMassDropDownLayout.setVisible(false);
		String massDdlbValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				"psPriceProtectionTabMassField").getCaptionFromComboBox();

		if (GtnFrameworkCommonConstants.BASE_PRICE_TYPE.equals(massDdlbValue)) {
			psPriceProtectionTabBasePriceDdlbLayout.setVisible(true);
			psPriceProtectionTabBasePriceDdlb.setVisible(true);
			massDdlbValue = psPriceProtectionTabBasePriceDdlb.getCaptionFromComboBox();
			psPriceProtectionTabBasePriceDdlb.setVisible(massDdlbValue != null && componentId.equals("psPriceProtectionTabMassField"));
		} else {
			psPriceProtectionTabBasePriceDdlbLayout.setVisible(false);
			psPriceProtectionTabBasePriceDdlb.setVisible(false);
			psPriceProtectionTabBasePriceDdlb.setPropertyValue(null);
		}
		psPriceProtectionTabTabmassCustomTextFieldLayout.setVisible(false);
		psPriceProtectionTabTabmassTextFieldLayout.setVisible(false);
		psPriceProtectionTabTabmassCustomTextField.setPropertyValue("");
		psPriceProtectionTabMassDateFeild.setPropertyValue(null);
		psPriceProtectionTabMassDropDown.loadComboBoxComponentValue(null);
		psPriceProtectionTabTabmassTextField.setPropertyValue("");

		GtnUIFrameworkComponentData componenetData = (GtnUIFrameworkComponentData) psPriceProtectionTabTabmassCustomTextField
				.getData();
		componenetData.setCustomData(null);
		if (massDdlbValue != null) {
			if (GtnFrameworkCommonConstants.BASE_PRICE_TYPE.equals(massDdlbValue)) {
				psPriceProtectionTabBasePriceDdlb.loadComboBoxComponentValue(null);
			} else if (massDdlbValue.endsWith("Date")) {

				psPriceProtectionTabMassDateFeildLayout.setVisible(true);

			} else if (psPriceProtectioncomboBoxFieldMap.containsKey(massDdlbValue)) {

				psPriceProtectionTabMassDropDownLayout.setVisible(true);
				fillPriceProtectionComboBox("psPriceProtectionTabMassDropDown",
						psPriceProtectioncomboBoxFieldMap.get(massDdlbValue));
			} else if (massDdlbValue.equals("Max Incremental Change") || massDdlbValue.equals("NEP")
					|| massDdlbValue.equals("Price Tolerance") || massDdlbValue.isEmpty()
					|| massDdlbValue.equals("Manual")) {
				psPriceProtectionTabTabmassTextFieldLayout.setVisible(!massDdlbValue.isEmpty());

			} else {
				psPriceProtectionTabTabmassCustomTextFieldLayout.setVisible(true);

				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_TABMASS_CUSTOM_TEXT_FIELD)
						.getComponentConfig();

				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
				actionConfigList.add(popupActionConfig);
				popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
				List<Object> popupActionParam = new ArrayList<>();
				popupActionParam.add(popupIdViewMap.get(massDdlbValue).getViewName());
				popupActionParam.add(massDdlbValue);
				popupActionParam.add("70%");
				popupActionParam.add("70%");

				popupActionConfig.setActionParameterList(popupActionParam);
				gtnUIFrameworkComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

				GtnUIFrameworkView view = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
						.getVaadinComponent(popupIdViewMap.get(massDdlbValue).getViewName());
				view.buildScreen();
				GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
				GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(popupIdViewMap.get(massDdlbValue).getSelectButtonId())
						.getComponentConfig();

				List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

				GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
				selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
				List<Object> actionParameter = new ArrayList<>();
				actionParameter.add(popupIdViewMap.get(massDdlbValue).getSourceTableName());
				actionParameter.add(popupIdViewMap.get(massDdlbValue).getDestinationComponentId());
				actionParameter.add(popupIdViewMap.get(massDdlbValue).getSourcePropertyIdList());
				actionParameter.add(popupIdViewMap.get(massDdlbValue).getDestinaComponentIdList());

				selectAction.setActionParameterList(actionParameter);
				selectActionConfigList.add(selectAction);

				GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
				closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
				closeAction.addActionParameter(popupIdViewMap.get(massDdlbValue).getViewName());
				selectActionConfigList.add(closeAction);

				selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

			}
		}
	}

	private void configurePopUp(Map<String, GtnUIFrameworkPopUpBean> popupIdViewMap,
			Map<String, String> psPriceProtectioncomboBoxFieldMap) {
		GtnUIFrameworkPopUpBean netSalesFormulaPopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
				"netSalesFormulaPopUpView");
		netSalesFormulaPopUpSearchViewBean.setSourceTableName("netSalesSearchResultTable");
		netSalesFormulaPopUpSearchViewBean.setDestinationComponentId(
				GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_TABMASS_CUSTOM_TEXT_FIELD);
		netSalesFormulaPopUpSearchViewBean.setSourcePropertyIdList(Arrays.asList("formulaName"));
		netSalesFormulaPopUpSearchViewBean.setDestinaComponentIdList(
				Arrays.asList(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_TABMASS_CUSTOM_TEXT_FIELD));
		netSalesFormulaPopUpSearchViewBean.setSelectButtonId("netSalesFormulaPopUpViewAddButton");

		popupIdViewMap.put("NEP Formula", netSalesFormulaPopUpSearchViewBean);
		popupIdViewMap.put("Net Price Type Formula", netSalesFormulaPopUpSearchViewBean);
		popupIdViewMap.put("Net Baseline WAC Formula", netSalesFormulaPopUpSearchViewBean);
		popupIdViewMap.put("Net Subsequent Period Price Formula", netSalesFormulaPopUpSearchViewBean);
		popupIdViewMap.put("Net Reset Price Formula", netSalesFormulaPopUpSearchViewBean);
       
		GtnFrameworkPSConstants.loadPsPriceProtectioncomboBoxFieldMap(psPriceProtectioncomboBoxFieldMap);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void fillPriceProtectionComboBox(String componentId, String type) {
		GtnUIFrameworkWebserviceComboBoxResponse priceProtectionComboBoxResponse = GtnUIFrameworkGlobalUI
				.getComboBoxItemListFromService(type);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).loadMassUpdateComboBox(
				priceProtectionComboBoxResponse.getItemCodeList(), priceProtectionComboBoxResponse.getItemValueList());

	}

}
