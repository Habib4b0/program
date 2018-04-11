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
    

import com.stpl.addons.grid.paged.bean.Row;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.data.HasValue;
import com.vaadin.ui.AbstractComponent;
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
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
                 Set<Row> rows= componentData.getPagedGrid().getValue();
                 Row selectedRow=rows.isEmpty()?null:rows.iterator().next();
//		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
//				.getVaadinBaseComponentFromParent(idComponent, componentId).getComponentData();
//		idComponentData.setCustomData(selectedRow);

//		GtnWsRecordBean dto = (GtnWsRecordBean) resultTable.getValue();

		for (int i = 0; i < inputColumIds.size(); i++) {
			HasValue<Object> vaadinField = (HasValue<Object>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(outputFieldIds.get(i), componentId).getComponent();
			Object newValue = null;
			if (selectedRow != null && selectedRow.getPropertyValue(inputColumIds.get(i)) != null) {
				newValue = selectedRow.getPropertyValue(inputColumIds.get(i));
			}
                        else if(selectedRow==null){
                            GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgParamsList = new ArrayList<>();
			alertMsgParamsList.add("Select Error");
			alertMsgParamsList.add("Please select a row from the Results list view to proceed");
			alertActionConfig.setActionParameterList(alertMsgParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
                        
                        throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
                        }
                        else {
				newValue = selectedRow.getPropertyValueByIndex(Integer.parseInt(inputColumIds.get(i)));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(outputFieldIds.get(i), componentId)
					.getComponentData().setCustomData(selectedRow);
			if (vaadinField != null && newValue!=null && !"null".equals(String.valueOf(newValue))) {
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
				comboboxWhereClauseParamList.add(selectedRow.getPropertyValueByIndex(selectedRow.getProperties().size() - 1));

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