/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.GtnFrameworkRelationshipBuilderResultLayoutConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkVersionChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		if (!GtnFrameworkValueChangeManager.isValueChangeAllowed()) {
			return;
		}
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		int hierarchySid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getIntegerFromField();
		int versionNo = component.getIntegerFromField();
		if (versionNo == 0) {
			return;
		}
		Object value = GtnUIFrameworkGlobalUI.getSessionProperty(parameters.get(4).toString());
		if (value != null) {
			GtnWsRecordBean bean = (GtnWsRecordBean) value;
			if (bean.getIntegerPropertyByIndex(10) == hierarchySid && bean.getIntegerPropertyByIndex(12) == versionNo) {
				return;
			}
		}
		loadResultLayout(gtnUIFrameWorkActionConfig, componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	private void loadResultLayout(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(6).toString(), null);
		GtnFrameworkRelationshipBuilderResultLayoutConfig resultLayoutConfig = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		resultLayoutConfig.addResultLayout(componentList, parameters.get(1).toString(),
				parameters.get(1).toString().contains("View"));
		GtnUIFrameworkGlobalUI.addChildComponent(parameters.get(3).toString(), componentList);
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(4).toString());
		tableBaseComponent.getLogicFromPagedDataTable()
				.startSearchProcess(Arrays.asList(parameters.get(2).toString(), componentId), true);
	}

}
