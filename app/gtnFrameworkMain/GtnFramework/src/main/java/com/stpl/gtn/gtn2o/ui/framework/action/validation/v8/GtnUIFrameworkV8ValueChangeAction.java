package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractListing;
import com.vaadin.ui.ComboBox;

public class GtnUIFrameworkV8ValueChangeAction implements GtnUIFrameWorkAction {

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
			AbstractListing currentVaadinComboBox = (AbstractListing) GtnUIFrameworkGlobalUI
					.getVaadinComponent(componentId);

			GtnUIFrameworkComponent gtnUIFrameworkComponent = reloadComponentConfig.getComponentType()
					.getGtnComponent();
			List<Object> comboboxWhereClauseParamList = new ArrayList<>();
			if (currentVaadinComboBox instanceof ComboBox) {
				comboboxWhereClauseParamList.add(((ComboBox) currentVaadinComboBox).getValue());
			}

			if (currentVaadinComboBox instanceof ComboBoxMultiselect) {
				comboboxWhereClauseParamList.add(((ComboBoxMultiselect) currentVaadinComboBox).getValue());
			}

			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION, reloadComponentId,
					componentId, comboboxWhereClauseParamList);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkV8ValueChangeAction();
	}

}