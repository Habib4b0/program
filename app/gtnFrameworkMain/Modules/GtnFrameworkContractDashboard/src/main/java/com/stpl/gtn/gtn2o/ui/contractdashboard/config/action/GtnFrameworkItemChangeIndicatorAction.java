/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkItemChangeIndicatorAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		processDataBean.setRebateDetailsTableLoad(true);
		processDataBean.setItemAddTableLoad(true);
		processDataBean.setItemsTableLoad(true);
		processDataBean.setPriceProtectionTableLoad(true);
		processDataBean.setPricingDetailsTableLoad(true);
		List<String> resetFieldList = new ArrayList<>();
		List<Object> resetValueList = new ArrayList<>();
		if (processDataBean.isItemsLoaded()) {
			resetFieldList.add(processDataBean.getItemsTableId());
			resetValueList.add(null);
		}
		if (processDataBean.isPricingLoaded()) {
			resetFieldList.add(processDataBean.getPriceProtectionTableId());
			resetFieldList.add(processDataBean.getPricingDetailsTableId());
			resetValueList.add(null);
			resetValueList.add(null);
		}
		if (processDataBean.isRebateLoaded()) {
			resetFieldList.add(processDataBean.getRebateDetailsTableId());
			resetValueList.add(null);
		}
		if (!resetFieldList.isEmpty()) {
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(resetFieldList);
			resetActionConfig.addActionParameter(resetValueList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
