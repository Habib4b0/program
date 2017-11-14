/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkEnableDisableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		boolean isEnable = (Boolean) actionParemeterList.get(2);
		boolean isAdd = (Boolean) actionParemeterList.get(3);
		List<String> componentIds = new ArrayList<>();
		componentIds.add(viewId + "formulaInformationPanel");
		componentIds.add(viewId + "saveButtonLayout");
		componentIds.add(viewId + "salesBasisSearchButton");
		componentIds.add(viewId + "displayButtonlayout");
		componentIds.add(viewId + "addButtonlayout");
		componentIds.add(viewId + "salesBasisOptionMainLayout");
		componentIds.add(viewId + "searchCriteriaPanel");
		componentIds.add(viewId + "searchButtonlayout");
		componentIds.add(viewId + "massUpdatePanel");
		componentIds.add(viewId + "selectedCustomerTableResetButton");
		componentIds.add(viewId + "selectedCustomerTableRemoveButton");
		componentIds.add(viewId + "deductionsTabSearchCriteriaPanel");
		componentIds.add(viewId + "selectedDeductionsmassUpdatePanel");
		componentIds.add(viewId + "deductionTabsearchButtonlayout");
		componentIds.add(viewId + "availableDeductionButtonlayout");
		componentIds.add(viewId + "selectedDeductionsTableResetButton");
		componentIds.add(viewId + "selectedDeductionsTableRemoveButton");
		componentIds.add(viewId + "formulaType");
		enableDisableComponents(componentIds, isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableContractTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableCustomersTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedCustomersResultTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "selectedDeductionsResultTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "availableDeductionsTable").getExtFilterTable()
				.setReadOnly(!isEnable);
		if (isAdd) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "saveButton").setCaption("SAVE");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void enableDisableComponents(List<String> componentIds, boolean isEnable) {
		for (String componentId : componentIds) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setEnable(isEnable);
		}
	}
}
