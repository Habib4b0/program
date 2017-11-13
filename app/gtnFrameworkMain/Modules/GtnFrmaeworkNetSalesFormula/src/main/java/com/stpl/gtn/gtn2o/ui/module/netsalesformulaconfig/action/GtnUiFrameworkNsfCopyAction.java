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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFMessageConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkSkipActionException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfCopyAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
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
		GtnWsRecordBean editRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "netSalesSearchResultTable").getValueFromPagedDataTable();
		if (editRecordBean == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
			List<Object> alertParamsList = new ArrayList<>();
			alertParamsList.add(viewId + "netSalesSearchResultTable");
			alertParamsList.add("Copy" + GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_HEADER);
			alertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_BODY + "Copy.");
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			throw new GtnFrameworkSkipActionException("Copy Error ");
		}
		Object systemId = editRecordBean.getPropertyValue(GtnFrameworkNSFConstants.getSystemid());
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.getSystemid(), systemId);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUiFrameworkNsfLoadDataAction.class.getName());
		customAction.addActionParameter(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, customAction);
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkNSFConstants.getSystemid(), null);
		setValuesToComponents(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE);

		GtnUIFrameWorkActionConfig enableDisableAction = new GtnUIFrameWorkActionConfig();
		enableDisableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableDisableAction.addActionParameter(GtnUiFrameworkEnableDisableAction.class.getName());
		enableDisableAction.addActionParameter(addViewId + GtnFrameworkCommonStringConstants.UNDERSCORE);
		enableDisableAction.addActionParameter(Boolean.TRUE);
		enableDisableAction.addActionParameter(Boolean.TRUE);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, enableDisableAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void setValuesToComponents(String viewId) {
		GtnUIFrameworkBaseComponent formulaId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaId");
		GtnUIFrameworkBaseComponent formulaNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaNo");
		GtnUIFrameworkBaseComponent formulaName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName");
		GtnUIFrameworkBaseComponent formulaCategory = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "formulaCategory");

		formulaId.loadFieldValue(GtnFrameworkNSFConstants.getEmpty());
		formulaNo.loadFieldValue(GtnFrameworkNSFConstants.getEmpty());
		formulaName.loadFieldValue(GtnFrameworkNSFConstants.getEmpty());
		formulaCategory.loadComboBoxComponentValue(null);

	}

}
