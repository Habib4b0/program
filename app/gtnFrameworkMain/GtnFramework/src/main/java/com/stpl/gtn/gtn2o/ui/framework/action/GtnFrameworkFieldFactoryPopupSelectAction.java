package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;

public class GtnFrameworkFieldFactoryPopupSelectAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> selectParam = gtnUIFrameWorkActionConfig.getActionParameterList();

		String resultTableId = (String) selectParam.get(0);
		String inputColumId = (String) selectParam.get(1);

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(resultTableId, componentId);
		GtnUIFrameworkComponentData componenetData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		ExtFilterTable resultTable = (ExtFilterTable) componenetData.getCustomData();
		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getSourceComponentId(), componentId)
				.getComponentData();
		idComponentData.setCustomData(resultTable.getValue());

		GtnWsRecordBean dto = (GtnWsRecordBean) resultTable.getValue();

		AbstractField<Object> fieldFactoryVaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(
						GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getSourceComponentId(),
						componentId)
				.getComponent();
		Object newValue;
		if (dto.getPropertyValue(inputColumId) != null) {
			newValue = dto.getPropertyValue(inputColumId);
		} else {
			newValue = dto.getPropertyValueByIndex(Integer.parseInt(inputColumId));
		}
		if (fieldFactoryVaadinField != null) {
			boolean isReadOnly = fieldFactoryVaadinField.isReadOnly();
			fieldFactoryVaadinField.setReadOnly(false);
			fieldFactoryVaadinField.setValue(String.valueOf(newValue));
			fieldFactoryVaadinField.setReadOnly(isReadOnly);

		}

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);

		GtnUIFrameworkComponentConfig fieldFactoryComponentConfig = componentData.getCurrentComponentConfig();

		if (fieldFactoryComponentConfig.getDependentComponentList() != null) {

			for (String reloadComponentId : fieldFactoryComponentConfig.getDependentComponentList()) {
				GtnUIFrameworkComponentData reloadComponentData = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(reloadComponentId, componentId).getComponentData();
				GtnUIFrameworkComponentConfig reloadComponentConfig = reloadComponentData.getCurrentComponentConfig();
				GtnUIFrameworkComponent gtnUIFrameworkComponent = reloadComponentConfig.getComponentType()
						.getGtnComponent();
				List<Object> comboboxWhereClauseParamList = new ArrayList<>();
				comboboxWhereClauseParamList.add(dto.getPropertyValueByIndex(dto.getProperties().size() - 1));

				gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION, reloadComponentId,
						getParentViewId(componentData, componentId), comboboxWhereClauseParamList);
			}
		}
	}

	private String getParentViewId(GtnUIFrameworkComponentData fieldFactoryComponentData,
			String fieldFactoryComponentId) {
		if (fieldFactoryComponentData.getParentViewId() != null) {
			return fieldFactoryComponentData.getParentViewId();
		} else {
			return fieldFactoryComponentId;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
