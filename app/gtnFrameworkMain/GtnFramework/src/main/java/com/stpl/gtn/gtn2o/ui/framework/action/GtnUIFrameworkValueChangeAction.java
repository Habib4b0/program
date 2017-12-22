/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.ComboBox;

public class GtnUIFrameworkValueChangeAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
				.getVaadinComponent(componentId).getData();
		GtnUIFrameworkComponentConfig currentComponentConfig = componentData.getCurrentComponentConfig();
		for (String reloadComponentId : currentComponentConfig.getDependentComponentList()) {
			GtnUIFrameworkComponentData reloadComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(reloadComponentId, componentId);
			GtnUIFrameworkComponentConfig reloadComponentConfig = reloadComponentData.getCurrentComponentConfig();
			ComboBox currentVaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
			GtnUIFrameworkComponent gtnUIFrameworkComponent = reloadComponentConfig.getComponentType()
					.getGtnComponent();
			List<Object> comboboxWhereClauseParamList = new ArrayList<>();
			comboboxWhereClauseParamList.add(currentVaadinComboBox.getValue());
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, reloadComponentId,
					componentId, comboboxWhereClauseParamList);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkValueChangeAction();
	}

}