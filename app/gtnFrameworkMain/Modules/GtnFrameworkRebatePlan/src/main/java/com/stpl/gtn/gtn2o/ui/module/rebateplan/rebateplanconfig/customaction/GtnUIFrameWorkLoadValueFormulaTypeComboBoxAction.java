/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String resultValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rebatePlanInformationTabformulaType")
				.getCaptionFromComboBox();
		String mode = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode"));
		if (resultValue.equals("Complex")) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TAB)
					.setWidth(GtnFrameworkCssConstants.HUNDRED_AND_FIFTY_PERCENTAGE);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnRpRemoveComplexButton")
					.setVisible(!GtnUIFrameworkModeType.VIEW.equals(GtnUIFrameworkModeType.valueOf(mode)));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnRpRemoveButton").setVisible(false);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX)
					.setVisible(true);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX)
					.setVisible(true);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_COMPLEX_TABLE_CSSLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTable").getExtFilterTable()
					.setVisible(false);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TAB)
					.setWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnRpRemoveComplexButton").setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnRpRemoveButton")
					.setVisible(!GtnUIFrameworkModeType.VIEW.equals(GtnUIFrameworkModeType.valueOf(mode)));
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.REBATE_PLAN_COMPLEX_TABLE_CSSLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ruleDetailsattachResultTable").getExtFilterTable()
					.setVisible(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
