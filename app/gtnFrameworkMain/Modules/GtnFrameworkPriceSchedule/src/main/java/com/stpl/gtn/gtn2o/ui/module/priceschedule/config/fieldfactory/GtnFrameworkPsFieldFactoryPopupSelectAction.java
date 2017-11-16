/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPsFieldFactoryPopupSelectAction implements
		GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentConfig psSelectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("netSalesFormulaPopUpViewAddButton").getComponentConfig();
		List<GtnUIFrameWorkActionConfig> psActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig psSelectAction = new GtnUIFrameWorkActionConfig();
		psSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> psActionParameter = new ArrayList<>();
		psActionParameter.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		psActionParameter.add(GtnFrameworkCommonConstants.FORMULA_NAME);
		psActionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.FORMULA_NAME));
		psActionParameter.add(Arrays.asList(componentId));
		psSelectAction.setActionParameterList(psActionParameter);
		psActionConfigList.add(psSelectAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(Arrays
						.asList(GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW));
		psActionConfigList.add(closeAction);
		psSelectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(psActionConfigList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
