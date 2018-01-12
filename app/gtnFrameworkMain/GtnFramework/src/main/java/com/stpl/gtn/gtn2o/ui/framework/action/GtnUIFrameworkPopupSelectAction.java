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

public class GtnUIFrameworkPopupSelectAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	/*
	 * Param 0 - Result table Id , Param 1 - popup source compoent id, Param2 -
	 * input column idList(property id of table), Param3 - component id list of
	 * parant view (param 2 and 3 sholud be same size), Param4 - Close Action
	 * class, Param5 - PopUpSharedData *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> selectParam = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) selectParam.get(0);
		String idComponent = (String) selectParam.get(1);
		List<String> inputColumIds = (List<String>) selectParam.get(2);
		List<String> outputFieldIds = (List<String>) selectParam.get(3);

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(resultTableId, componentId);
		GtnUIFrameworkComponentData componenetData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		ExtFilterTable resultTable = (ExtFilterTable) componenetData.getCustomData();
		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(idComponent, componentId).getComponentData();
		idComponentData.setCustomData(resultTable.getValue());

		GtnWsRecordBean dto = (GtnWsRecordBean) resultTable.getValue();

		for (int i = 0; i < inputColumIds.size(); i++) {
			AbstractField<Object> vaadinField = (AbstractField<Object>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(outputFieldIds.get(i), componentId).getComponent();
			Object newValue;
			if (dto.getPropertyValue(inputColumIds.get(i)) != null) {
				newValue = dto.getPropertyValue(inputColumIds.get(i));
			} else {
				newValue = dto.getPropertyValueByIndex(Integer.parseInt(inputColumIds.get(i)));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(outputFieldIds.get(i), componentId)
					.getComponentData().setCustomData(dto);
			if (vaadinField != null) {
				boolean isReadOnly = vaadinField.isReadOnly();
				vaadinField.setReadOnly(false);
				vaadinField.setValue(String.valueOf(newValue));
				vaadinField.setReadOnly(isReadOnly);

			}
		}

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);

		GtnUIFrameworkComponentConfig currentComponentConfig = componentData.getCurrentComponentConfig();

		if (currentComponentConfig.getDependentComponentList() != null) {

			for (String reloadComponentId : currentComponentConfig.getDependentComponentList()) {
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