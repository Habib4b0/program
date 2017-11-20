package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPopupSelectAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - Source Table Id to fetch
	 * value, Param 2 - View Id of Popup, Param 3 - Index of the id property for the
	 * Selected value of source table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		Object value = tableBaseComponent.getValueFromComponent();
		if (value != null) {
			GtnWsRecordBean dto = (GtnWsRecordBean) value;

			GtnUIFrameworkComponentData viewComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(parameters.get(2).toString());
			Object sharedPopupData = viewComponentData.getSharedPopupData();
			if (sharedPopupData != null) {
				List<Object> cdSharedPopupDataList = (List<Object>) sharedPopupData;

				GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
						.getVaadinComponentData(cdSharedPopupDataList.get(0).toString());
				Object idValue = dto.getPropertyValueByIndex(Integer.parseInt(String.valueOf(parameters.get(3))));
				idComponentData.setCustomDataList(Arrays.asList(idValue, dto, parameters.get(3)));
				List<String> inputColumIds = (List<String>) cdSharedPopupDataList.get(1);
				List<String> outputFieldIds = (List<String>) cdSharedPopupDataList.get(2);
				for (int i = 0; i < inputColumIds.size(); i++) {
					Object newValue = dto.getPropertyValue(inputColumIds.get(i));
					if (newValue == null) {
						newValue = dto.getPropertyValueByIndex(Integer.parseInt(inputColumIds.get(i)));
					}
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(outputFieldIds.get(i)).setPropertyValue(newValue);
				}
				if (cdSharedPopupDataList.size() > 3 && cdSharedPopupDataList.get(3) != null) {
					List<Object> actionConfigs = (List<Object>) cdSharedPopupDataList.get(3);
					GtnUIFrameWorkActionConfig cdPopupActionConfig = (GtnUIFrameWorkActionConfig) actionConfigs.get(0);
					Object newValue = dto
							.getPropertyValueByIndex(Integer.parseInt(String.valueOf(actionConfigs.get(1))));
					cdPopupActionConfig.addActionParameter(newValue);
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cdPopupActionConfig);
				}
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
