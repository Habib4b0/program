package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUiFrameworkNsfDeductionMassFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String massUpdateDdlbValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "selectedDeductionsmassUpdateDdlb").getCaptionFromComboBox();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedDeductionsmassUpdateNetSalesRuleNo")
				.setVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedDeductionsAddSubtractDdlb").setVisible(false);
		if (GtnFrameworkNSFConstants.getNetSalesRuleNo().equals(massUpdateDdlbValue)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedDeductionsmassUpdateNetSalesRuleNo")
					.setVisible(true);
		} else if (GtnFrameworkNSFConstants.getAddSubtractIndicator().equals(massUpdateDdlbValue)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedDeductionsAddSubtractDdlb")
					.setVisible(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
