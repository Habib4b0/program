package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameworkPopUpBean;

public class GtnFrameworkRebateSetupMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final Map<String, GtnUIFrameworkPopUpBean> popupIdViewMap = new HashMap<>();

	static {
        GtnUIFrameworkPopUpBean deductionCalendarPopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                "deductionCalendarPopUpSearchView");
        deductionCalendarPopUpSearchViewBean.setSourceTableName("dcPopUpSearchResultTable");
        deductionCalendarPopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        deductionCalendarPopUpSearchViewBean
                .setSourcePropertyIdList(Arrays.asList(new String[]{"deductionCalendarName"}));
        deductionCalendarPopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        deductionCalendarPopUpSearchViewBean.setSelectButtonId("dcPopUpViewSelectButton");

        GtnUIFrameworkPopUpBean evaluationRulePopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
        evaluationRulePopUpSearchViewBean.setSourceTableName(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);
        evaluationRulePopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        evaluationRulePopUpSearchViewBean
                .setSourcePropertyIdList(Arrays.asList(new String[]{GtnFrameworkCommonConstants.RULE_NAME}));
        evaluationRulePopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        evaluationRulePopUpSearchViewBean.setSelectButtonId(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);
        GtnUIFrameworkPopUpBean calculationRulePopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
        calculationRulePopUpSearchViewBean.setSourceTableName(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);
        calculationRulePopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        calculationRulePopUpSearchViewBean
                .setSourcePropertyIdList(Arrays.asList(new String[]{GtnFrameworkCommonConstants.RULE_NAME}));
        calculationRulePopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        calculationRulePopUpSearchViewBean.setSelectButtonId(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);

        GtnUIFrameworkPopUpBean netSalesRulePopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
        netSalesRulePopUpSearchViewBean.setSourceTableName(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);
        netSalesRulePopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        netSalesRulePopUpSearchViewBean
                .setSourcePropertyIdList(Arrays.asList(new String[]{GtnFrameworkCommonConstants.RULE_NAME}));
        netSalesRulePopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        netSalesRulePopUpSearchViewBean.setSelectButtonId(GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);

        GtnUIFrameworkPopUpBean netSalesFormulaPopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                "netSalesFormulaPopUpView");
        netSalesFormulaPopUpSearchViewBean.setSourceTableName("netSalesSearchResultTable");
        netSalesFormulaPopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        netSalesFormulaPopUpSearchViewBean.setSourcePropertyIdList(Arrays.asList(new String[]{"formulaName"}));
        netSalesFormulaPopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        netSalesFormulaPopUpSearchViewBean.setSelectButtonId("netSalesFormulaPopUpViewAddButton");

        GtnUIFrameworkPopUpBean formulaPopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                "FormulaPopUpSearchSearchView");
        formulaPopUpSearchViewBean.setSourceTableName("FormulaPopUpsearchResultTable");
        formulaPopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        formulaPopUpSearchViewBean.setSourcePropertyIdList(Arrays.asList(new String[]{"formulaName"}));
        formulaPopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        formulaPopUpSearchViewBean.setSelectButtonId("FormulaPopUpSearchSearchViewAddButton");

        GtnUIFrameworkPopUpBean rebatePlanPopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
                "rebatePlanPopUpSearchView");
        rebatePlanPopUpSearchViewBean.setSourceTableName("rpPopUpSearchResultTable");
        rebatePlanPopUpSearchViewBean.setDestinationComponentId(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
        rebatePlanPopUpSearchViewBean.setSourcePropertyIdList(Arrays.asList(new String[]{"secondaryRebatePlanNo"}));
        rebatePlanPopUpSearchViewBean.setDestinaComponentIdList(
                Arrays.asList(new String[]{GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD}));
        rebatePlanPopUpSearchViewBean.setSelectButtonId("rebatePlanPopUpViewSelectButton");
        popupIdViewMap.put("Deduction Calendar No", deductionCalendarPopUpSearchViewBean);
        popupIdViewMap.put("Evaluation Rule", evaluationRulePopUpSearchViewBean);
        popupIdViewMap.put("Calculation Rule", calculationRulePopUpSearchViewBean);
        popupIdViewMap.put("Net Sales Rule", netSalesRulePopUpSearchViewBean);
        popupIdViewMap.put("Formula No", formulaPopUpSearchViewBean);
        popupIdViewMap.put("Net Sales Formula", netSalesFormulaPopUpSearchViewBean);
        popupIdViewMap.put("Rebate Plan No", rebatePlanPopUpSearchViewBean);
    }

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkBaseComponent rebateSetupTabMassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassDropDown");
		GtnUIFrameworkBaseComponent massCustomTextField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD);
		GtnUIFrameworkBaseComponent massTextField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("massTextField");
		GtnUIFrameworkBaseComponent rebateSetupTabMassDateFeild = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassDateFeild");

		rebateSetupTabMassDateFeild.setVisible(false);
		rebateSetupTabMassDropDown.setVisible(false);
		massCustomTextField.setVisible(false);
		massTextField.setVisible(false);
		massCustomTextField.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkComponentData componenetData = (GtnUIFrameworkComponentData) massCustomTextField.getData();
		componenetData.setCustomData(null);
		final GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassField");
		if (component.getValueFromComponent() != null) {
			if (component.getCaptionFromComboBox().equals("Start Date")
					|| component.getCaptionFromComboBox().equals("End Date")) {

				rebateSetupTabMassDateFeild.setVisible(true);

			} else if (component.getCaptionFromComboBox().equals("RS Status")) {

				rebateSetupTabMassDropDown.setVisible(true);
			} else if (component.getCaptionFromComboBox().contains("Bundle")) {
				massTextField.setVisible(true);

			} else {
				massCustomTextField.setVisible(true);

				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkRSConstants.MASS_CUSTOM_TEXT_FIELD).getComponentConfig();

				List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
				GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
				actionConfigList.add(popupActionConfig);
				popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
				List<Object> popupActionParam = new ArrayList<>();
				popupActionParam.add(popupIdViewMap.get(component.getCaptionFromComboBox()).getViewName());
				popupActionParam.add(component.getCaptionFromComboBox());
				popupActionParam.add("70%");
				popupActionParam.add("70%");

				popupActionConfig.setActionParameterList(popupActionParam);
				gtnUIFrameworkComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

				GtnUIFrameworkView view = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
						.getVaadinComponent(popupIdViewMap.get(component.getCaptionFromComboBox()).getViewName());
				view.buildScreen();
				GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
				GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(
								popupIdViewMap.get(component.getCaptionFromComboBox()).getSelectButtonId())
						.getComponentConfig();

				List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

				GtnUIFrameWorkActionConfig selectAction = configProvider
						.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
				List<Object> actionParameter = new ArrayList<>();
				actionParameter.add(popupIdViewMap.get(component.getCaptionFromComboBox()).getSourceTableName());
				actionParameter.add(popupIdViewMap.get(component.getCaptionFromComboBox()).getDestinationComponentId());
				actionParameter.add(popupIdViewMap.get(component.getCaptionFromComboBox()).getSourcePropertyIdList());
				actionParameter.add(popupIdViewMap.get(component.getCaptionFromComboBox()).getDestinaComponentIdList());

				selectAction.setActionParameterList(actionParameter);
				selectActionConfigList.add(selectAction);

				GtnUIFrameWorkActionConfig closeAction = configProvider
						.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
				closeAction.setActionParameterList(Arrays
						.asList(new Object[] { popupIdViewMap.get(component.getCaptionFromComboBox()).getViewName() }));
				selectActionConfigList.add(closeAction);

				selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkRebateSetupMassFieldValueChangeAction();
	}

	

}
