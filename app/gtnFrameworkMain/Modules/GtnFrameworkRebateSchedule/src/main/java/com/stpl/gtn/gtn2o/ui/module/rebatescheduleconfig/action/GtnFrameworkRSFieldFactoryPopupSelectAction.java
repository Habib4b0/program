package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Deepika.KrishnaKumar
 */
public class GtnFrameworkRSFieldFactoryPopupSelectAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		Map<String, String> popupIdViewMap = new HashMap<>();
		Map<String, String> popupTableIdMapValue = new HashMap<>();
		Map<String, String> sourcePropIdMapValue = new HashMap<>();
		Map<String, String> viewIdMapValue = new HashMap<>();

		String propertId = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));

		loadPropertyIdButtonValue(popupIdViewMap);
		loadTableIdValue(popupTableIdMapValue);
		loadSourcePropertyIdValue(sourcePropIdMapValue);
		loadCloseViewIdValue(viewIdMapValue);

		String tableId = String.valueOf(popupTableIdMapValue.get(propertId));
		String sourceId = String.valueOf(sourcePropIdMapValue.get(propertId));
		String viewId = String.valueOf(viewIdMapValue.get(propertId));

		GtnUIFrameworkComponentConfig rsSelectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(popupIdViewMap.get(propertId)).getComponentConfig();
		List<GtnUIFrameWorkActionConfig> rsActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig psSelectAction = new GtnUIFrameWorkActionConfig();
		List<Object> rsActionParameter = new ArrayList<>();

		if (propertId.equals(GtnFrameworkCommonConstants.FORMULA_NO)) {

			psSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
			rsActionParameter.add(tableId);
			rsActionParameter.add(GtnFrameworkCommonConstants.FORMULA_NO);
			rsActionParameter.add(
					Arrays.asList(GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NAME));
			rsActionParameter.add(Arrays.asList(componentId,
					componentId.replace(GtnFrameworkRSConstants.NO, GtnFrameworkRSConstants.NAME)));
			psSelectAction.setActionParameterList(rsActionParameter);
			rsActionConfigList.add(psSelectAction);

		}

		else {
			if (propertId.equals(GtnFrameworkRSConstants.REBATE_PLAN_NO1)) {
				psSelectAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
				rsActionParameter.add(GtnFrameworkRPSelectMsgAction.class.getName());

				rsActionParameter.add(tableId);
				rsActionParameter.add(componentId);
				rsActionParameter.add(Arrays.asList(sourceId));
				rsActionParameter.add(Arrays.asList(componentId));
				psSelectAction.setActionParameterList(rsActionParameter);
				rsActionConfigList.add(psSelectAction);

			} else {
				psSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);

				rsActionParameter.add(tableId);
				rsActionParameter.add(componentId);
				rsActionParameter.add(Arrays.asList(sourceId));
				rsActionParameter.add(Arrays.asList(componentId));
				psSelectAction.setActionParameterList(rsActionParameter);
				rsActionConfigList.add(psSelectAction);
			}

		}

		GtnUIFrameWorkActionConfig closeWindowAction = new GtnUIFrameWorkActionConfig();
		closeWindowAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeWindowAction.addActionParameter(Arrays.asList(viewId));
		rsActionConfigList.add(closeWindowAction);
		rsSelectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(rsActionConfigList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadPropertyIdButtonValue(Map<String, String> popupIdViewMap) {

		popupIdViewMap.put(GtnFrameworkRSConstants.REBATE_PLAN_NO1,
				GtnFrameworkRSConstants.RP_POP_UP_VIEW_SELECT_BUTTON);
		popupIdViewMap.put(GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
				GtnFrameworkRSConstants.NS_FORMULA_POP_UP_VIEW_ADD_SELECT_BUTTON);
		popupIdViewMap.put(GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);

		popupIdViewMap.put(GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);
		popupIdViewMap.put(GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_SELECT_BUTTON);
		popupIdViewMap.put(GtnFrameworkRSConstants.DEDUCTION_NAME,
				GtnFrameworkRSConstants.DC_POP_UP_VIEW_SELECT_BUTTON);
		popupIdViewMap.put(GtnFrameworkCommonConstants.FORMULA_NO,
				GtnFrameworkRSConstants.FORMULA_POP_UP_SEARCH_VIEW_ADD_BUTTON);
	}

	private void loadTableIdValue(Map<String, String> popupTableIdMapValue) {

		popupTableIdMapValue.put(GtnFrameworkRSConstants.EVALUATION_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);
		popupTableIdMapValue.put(GtnFrameworkRSConstants.CALCULATION_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);
		popupTableIdMapValue.put(GtnFrameworkRSConstants.NET_SALES_RULE_NAME,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW_RESULT_TABLE);

		popupTableIdMapValue.put(GtnFrameworkRSConstants.DEDUCTION_NAME,
				GtnFrameworkRSConstants.DC_POP_UP_SEARCH_RESULT_TABLE);
		popupTableIdMapValue.put(GtnFrameworkRSConstants.REBATE_PLAN_NO1,
				GtnFrameworkRSConstants.RP_POP_UP_SEARCH_RESULT_TABLE);
		popupTableIdMapValue.put(GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
				GtnFrameworkRSConstants.NS_SEARCH_RESULT_TABLE);
		popupTableIdMapValue.put(GtnFrameworkCommonConstants.FORMULA_NO,
				GtnFrameworkRSConstants.FORMULA_POP_UP_SEARCH_RESULT_TABLE);
	}

	private void loadSourcePropertyIdValue(Map<String, String> sourcePropIdMapValue) {

		sourcePropIdMapValue.put(GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkCommonConstants.RULE_NAME);
		sourcePropIdMapValue.put(GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkCommonConstants.RULE_NAME);
		sourcePropIdMapValue.put(GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkCommonConstants.RULE_NAME);

		sourcePropIdMapValue.put(GtnFrameworkRSConstants.DEDUCTION_NAME,
				GtnFrameworkRSConstants.DEDUCTION_CALENDAR_NAME);
		sourcePropIdMapValue.put(GtnFrameworkRSConstants.REBATE_PLAN_NO1,
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO);
		sourcePropIdMapValue.put(GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
				GtnFrameworkCommonConstants.FORMULA_NAME);
		sourcePropIdMapValue.put(GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NO);
	}

	private void loadCloseViewIdValue(Map<String, String> viewIdMapValue) {

		viewIdMapValue.put(GtnFrameworkRSConstants.EVALUATION_RULE_NAME, GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		viewIdMapValue.put(GtnFrameworkRSConstants.CALCULATION_RULE_NAME, GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		viewIdMapValue.put(GtnFrameworkRSConstants.NET_SALES_RULE_NAME, GtnFrameworkRSConstants.RS_NS_RULE_VIEW);

		viewIdMapValue.put(GtnFrameworkRSConstants.DEDUCTION_NAME,
				GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
		viewIdMapValue.put(GtnFrameworkRSConstants.REBATE_PLAN_NO1,
				GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW);
		viewIdMapValue.put(GtnFrameworkCommonConstants.NET_SALES_FORMULA_NAME,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW);
		viewIdMapValue.put(GtnFrameworkCommonConstants.FORMULA_NO,
				GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW);
	}

}
