package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkNsfNetSalesRulePopupSelectAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CDRPopUpSearchSearchViewSelectButton").getComponentConfig();
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add("cDRPopUpsearchResultTable");
		actionParameter.add("ruleNo");
		actionParameter.add(Arrays.asList("ruleNo","ruleName"));
		actionParameter.add(Arrays.asList(componentId,componentId.replace("No","Name")));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter("CDRPopUpSearchSearchView");
		actionConfigList.add(closeAction);
		selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
