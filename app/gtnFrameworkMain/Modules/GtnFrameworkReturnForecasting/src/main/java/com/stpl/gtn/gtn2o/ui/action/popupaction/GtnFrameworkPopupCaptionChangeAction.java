package com.stpl.gtn.gtn2o.ui.action.popupaction;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPopupCaptionChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String nameSpace = actionParameterList.get(2).toString() + GtnFrameworkForecastConstantCommon.UNDERSCORE;
		@SuppressWarnings("unchecked")
		List<GtnUIFrameWorkActionConfig> popupChildActionList = (List<GtnUIFrameWorkActionConfig>) actionParameterList
				.get(3);
		String projectionName = "";
		if (GtnFrameworkForecastConstantCommon.ADD_MODE.equals(actionParameterList.get(1).toString())) {
			List<String> fieldValuesParameterList = gtnUIFrameWorkActionConfig.getFieldValues();
			projectionName = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(nameSpace + fieldValuesParameterList.get(0), componentId)
					.getStringFromField();
		} else {
			GtnWsRecordBean currentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(actionParameterList.get(1).toString(), componentId)
					.getValueFromDataTable();
			projectionName = currentData.getPropertyValueByIndex(0).toString();
		}
		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter("gtnForecastReturnProjectionDetailsPopup");
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(projectionName);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.EIGHTY_PERCENTAGE);
		gtnUIFrameWorkGeneratePopupAction.setChildActionList(popupChildActionList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameWorkGeneratePopupAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
