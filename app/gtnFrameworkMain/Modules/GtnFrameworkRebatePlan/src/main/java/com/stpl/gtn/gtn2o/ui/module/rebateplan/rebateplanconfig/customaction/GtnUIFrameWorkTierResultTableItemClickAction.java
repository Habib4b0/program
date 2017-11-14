/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Kalpana.Ramanana
 */
public class GtnUIFrameWorkTierResultTableItemClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

String resultValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1))
				.getCaptionFromComboBox();
		String resultTableId = getTableIdResult(resultValue);
		GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(resultTableId);
		int getIndex = table.getItemsFromDataTable()
				.indexOf(gtnUIFrameWorkActionConfig.getActionParameter().getItemId());
		int size = table.getItemsFromDataTable().size();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_RP_REMOVE_BUTTON).getComponent()
				.setEnabled(size == 1 || getIndex == size - 1);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String getTableIdResult(String resultValueFormulaType) {
		String tableId;
		if (resultValueFormulaType.equals("Complex")) {
			tableId = "ruleDetailsattachResultTableComplex";
		} else {
			tableId = "ruleDetailsattachResultTable";
}
		return tableId;
	}

}
