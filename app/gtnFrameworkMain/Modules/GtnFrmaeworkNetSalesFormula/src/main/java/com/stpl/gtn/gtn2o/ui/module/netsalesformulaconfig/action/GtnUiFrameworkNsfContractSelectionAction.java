/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfContractSelectionAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		String contractSelectionValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		List<String> componentIds = new ArrayList<>(50);
		componentIds.add(viewId + "searchCriteriaPanel");
		componentIds.add(viewId + "searchButtonlayout");
		componentIds.add(viewId + "displayButtonlayout");
		componentIds.add(viewId + "addButtonlayout");
		componentIds.add(viewId + "massUpdatePanel");
		componentIds.add(viewId + "resetRemoveExcelButtonLayout");
		componentIds.add("netSalesFormulaAddView_contractNo");
		componentIds.add("netSalesFormulaAddView_contractName");
		componentIds.add("netSalesFormulaAddView_contractHolder");
		componentIds.add("netSalesFormulaAddView_marketType");
		componentIds.add("netSalesFormulaAddView_cfpNo");
		componentIds.add("netSalesFormulaAddView_cfpName");
		componentIds.add("netSalesFormulaAddView_ifpNo");
		componentIds.add("netSalesFormulaAddView_ifpName");
		componentIds.add("netSalesFormulaAddView_companyNo");
		componentIds.add("netSalesFormulaAddView_companyName");
		componentIds.add("netSalesFormulaAddView_itemNo");
		componentIds.add("netSalesFormulaAddView_itemName");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "searchCriteriaPanel").setEnable(false);
		if (contractSelectionValue.equals(GtnFrameworkNSFConstants.getExistingContract())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "netSalesRule").setEnable(true);
			enableDisableComponents(componentIds, false);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "netSalesRule").setEnable(false);
			enableDisableComponents(componentIds, true);
		}
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(Arrays.asList(viewId + "massUpdateDdlb", viewId + "massUpdateNetSalesRuleNo",
				viewId + "massUpdatePopulateButton"));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, customAction);
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
