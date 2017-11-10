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
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkTreeSaveAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();

		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		if (cdTreeBaseComponent.getItemsFromDataTable().isEmpty()) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION, parameters.get(2).toString(),
					parameters.get(3).toString());
			return;
		}

		List<GtnWsRecordBean> treeNodes = cdTreeBaseComponent.getTreeNodes();
		StringBuilder confirmationMessageBuilder = new StringBuilder(parameters.get(5).toString());
		String comma = "";
		for (GtnWsRecordBean treeNode : treeNodes) {
			confirmationMessageBuilder.append(comma);
			confirmationMessageBuilder.append(treeNode.getStringPropertyByIndex(2));
			comma = ", ";
		}
		confirmationMessageBuilder.append("?");
		confirmSaveTree(componentId, parameters, confirmationMessageBuilder.toString(), treeNodes);
	}

	private void confirmSaveTree(String componentId, List<Object> parameters, String confirmMessage,
			List<GtnWsRecordBean> treeNodes) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter(parameters.get(4));
		confirmActionConfig.addActionParameter(confirmMessage);
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkTreeConfirmSaveAction.class.getName());
		saveActionConfig.addActionParameter(parameters.get(1));
		saveActionConfig.addActionParameter(treeNodes);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_TREE_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter("RIGHTCDMainView_resultTable");
		successActionConfigList.add(saveActionConfig);
		successActionConfigList.add(loadDataTableActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
