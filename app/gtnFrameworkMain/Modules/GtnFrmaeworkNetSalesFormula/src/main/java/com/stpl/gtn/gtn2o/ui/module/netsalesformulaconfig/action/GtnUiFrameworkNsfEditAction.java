/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfEditAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String addViewId = (String) actionParemeterList.get(2);
		boolean isEditMode = "edit".equalsIgnoreCase((String) actionParemeterList.get(3));
		boolean isEnable = Boolean.FALSE;
		GtnWsRecordBean editRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "netSalesSearchResultTable").getValueFromPagedDataTable();

		Object systemId = editRecordBean.getPropertyValue(GtnFrameworkNSFConstants.getSystemid());
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.getSystemid(), systemId);
		GtnUIFrameworkGlobalUI.addSessionProperty("mode", (String) actionParemeterList.get(3));
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfLoadDataAction.class.getName());
		customAction.addActionParameter(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, customAction);
		if (isEditMode) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE + "saveButton")
					.setCaption("UPDATE");
			isEnable = Boolean.TRUE;
		}
		GtnUIFrameWorkActionConfig enableDisableAction = new GtnUIFrameWorkActionConfig();
		enableDisableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableDisableAction.addActionParameter(GtnUiFrameworkEnableDisableAction.class.getName());
		enableDisableAction.addActionParameter(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE);
		enableDisableAction.addActionParameter(isEnable);
		enableDisableAction.addActionParameter(Boolean.FALSE);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, enableDisableAction);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
