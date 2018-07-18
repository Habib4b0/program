/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.action;

/**
 *
 * @author Karthik.Raja
 */

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.HorizontalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GtnUIFrameworkV8PopupSelectAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	/*
	 * Param 0 - Result table Id , Param 1 - popup source component id, Param2 -
	 * input column idList(property id of table), Param3 - component id list of
	 * parant view (param 2 and 3 sholud be same size), Param4 - Close Action
	 * class, Param5 - PopUpSharedData *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultGridId = (String) paramsList.get(0);
		String sourceComponentId = (String) paramsList.get(1);
		List<String> inputColumnIds = (List<String>) paramsList.get(2);
		List<String> outputFieldId = (List<String>) paramsList.get(3);

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(resultGridId, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		Set<GtnWsRecordBean> rows = componentData.getPagedGrid().getValue();
		GtnWsRecordBean selectedRow = rows.isEmpty() ? null : rows.iterator().next();
		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(sourceComponentId, componentId).getComponentData();
		idComponentData.setCustomData(selectedRow);

		for (int i = 0; i < inputColumnIds.size(); i++) {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(outputFieldId.get(i), componentId);
			HasValue<Object> vaadinField;
			if (baseComponent.getComponentConfig()
					.getComponentType() == GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8) {
				HorizontalLayout layout = (HorizontalLayout) baseComponent.getComponent();
				vaadinField = (HasValue<Object>) layout.getComponent(0);
			} else {
				vaadinField = (HasValue<Object>) baseComponent.getComponent();
			}
			Object newValue = null;
			if (selectedRow != null && selectedRow.getPropertyValue(inputColumnIds.get(i)) != null) {
				newValue = selectedRow.getPropertyValue(inputColumnIds.get(i));
			} else if (selectedRow == null) {
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
				List<Object> alertMsgParamsList = new ArrayList<>();
				alertMsgParamsList.add("No View Selected");
				alertMsgParamsList.add("There is no view selected. Please select a saved view and try again");
				alertActionConfig.setActionParameterList(alertMsgParamsList);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);

				throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
			} else {
				newValue = selectedRow.getPropertyValueByIndex(Integer.parseInt(inputColumnIds.get(i)));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(outputFieldId.get(i), componentId)
					.getComponentData().setCustomData(selectedRow);
			if (vaadinField != null && newValue != null && !"null".equals(String.valueOf(newValue))) {
				boolean isReadOnly = vaadinField.isReadOnly();
				vaadinField.setReadOnly(false);
				vaadinField.setValue(String.valueOf(newValue));
				vaadinField.setReadOnly(isReadOnly);

			}
		}

		GtnUIFrameworkComponentData customData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);

		GtnUIFrameworkComponentConfig currentComponentConfig = customData.getCurrentComponentConfig();

		if (currentComponentConfig.getDependentComponentList() != null) {

			for (String reloadComponentId : currentComponentConfig.getDependentComponentList()) {
				GtnUIFrameworkComponentData reloadComponentData = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(reloadComponentId, componentId).getComponentData();
				GtnUIFrameworkComponentConfig reloadComponentConfig = reloadComponentData.getCurrentComponentConfig();
				GtnUIFrameworkComponent gtnUIFrameworkComponent = reloadComponentConfig.getComponentType()
						.getGtnComponent();
				List<Object> comboboxWhereClauseParamList = new ArrayList<>();
				if (selectedRow != null) {
					comboboxWhereClauseParamList
							.add(selectedRow.getPropertyValueByIndex(selectedRow.getProperties().size() - 1));
				}
				gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, reloadComponentId,
						getParentViewId(customData, componentId), comboboxWhereClauseParamList);
			}
		}
	}

	private String getParentViewId(GtnUIFrameworkComponentData componentData, String componentId) {
		if (componentData.getParentViewId() != null) {
			return componentData.getParentViewId();
		} else {
			return componentId;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}