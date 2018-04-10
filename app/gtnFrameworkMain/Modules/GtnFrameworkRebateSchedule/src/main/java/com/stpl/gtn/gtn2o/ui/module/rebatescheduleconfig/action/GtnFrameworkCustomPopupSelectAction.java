package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkCustomPopupSelectAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - Source Table Id to fetch
	 * value, Param 2 - View Id of Popup, Param 3 - Index of the id property for
	 * the Selected value of source table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = (List<Object>) gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		Object value = tableBaseComponent.getValueFromComponent();
		if (value != null) {
			GtnWsRecordBean dto = (GtnWsRecordBean) value;

			GtnUIFrameworkComponentData viewComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(parameters.get(2).toString());
			Object sharedPopupData = viewComponentData.getSharedPopupData();
			if (sharedPopupData != null) {
				List<Object> sharedPopupDataList = (List<Object>) sharedPopupData;

				GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
						.getVaadinComponentData(sharedPopupDataList.get(0).toString());

				Object idValue = dto.getPropertyValueByIndex(Integer.parseInt(String.valueOf(parameters.get(3))));
				idComponentData.setCustomDataList(Arrays.asList(idValue, dto, parameters.get(3)));
				idComponentData.setCustomData(dto);
				List<String> inputColumIds = (List<String>) sharedPopupDataList.get(1);
				List<String> outputFieldIds = (List<String>) sharedPopupDataList.get(2);
				GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();
				for (int i = 0; i < inputColumIds.size(); i++) {
					Object newValue = dto.getPropertyValue(inputColumIds.get(i));
					if (newValue == null) {
						newValue = dto.getPropertyValueByIndex(Integer.parseInt(inputColumIds.get(i)));
					}
					setter.setPropertyValue(outputFieldIds.get(i), newValue);
				}
				if (sharedPopupDataList.size() > 3 && sharedPopupDataList.get(3) != null) {
					List<Object> actionConfigs = (List<Object>) sharedPopupDataList.get(3);
					GtnUIFrameWorkActionConfig actionConfig = (GtnUIFrameWorkActionConfig) actionConfigs.get(0);
					Object newValue = dto
							.getPropertyValueByIndex(Integer.parseInt(String.valueOf(actionConfigs.get(1))));
					actionConfig.addActionParameter(newValue);
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
				}
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
