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
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent baseComp = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameterList.get(1).toString());
		Object ObjectValue = baseComp.getValueFromComponent();
		if (ObjectValue != null) {
			GtnWsRecordBean recordBeanDto = (GtnWsRecordBean) ObjectValue;

			GtnUIFrameworkComponentData viewDataComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(parameterList.get(2).toString());
			Object popupSharedData = viewDataComponent.getSharedPopupData();
			if (popupSharedData != null) {
				List<Object> cdSharedPopupDataList = (List<Object>) popupSharedData;

				GtnUIFrameworkComponentData popupSharedIdComponent = GtnUIFrameworkGlobalUI
						.getVaadinComponentData(cdSharedPopupDataList.get(0).toString());
				Object propObjIdValue = recordBeanDto.getPropertyValueByIndex(Integer.parseInt(String.valueOf(parameterList.get(3))));
				popupSharedIdComponent.setCustomDataList(Arrays.asList(propObjIdValue, recordBeanDto, parameterList.get(3)));
				List<String> columnIds = (List<String>) cdSharedPopupDataList.get(1);
				List<String> fieldIds = (List<String>) cdSharedPopupDataList.get(2);
				for (int i = 0; i < columnIds.size(); i++) {
					Object newValueObject = recordBeanDto.getPropertyValue(columnIds.get(i));
					if (newValueObject == null) {
						newValueObject = recordBeanDto.getPropertyValueByIndex(Integer.parseInt(columnIds.get(i)));
					}
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldIds.get(i)).setPropertyValue(newValueObject);
				}
				if (cdSharedPopupDataList.size() > 3 && cdSharedPopupDataList.get(3) != null) {
					List<Object> actionConfigLists = (List<Object>) cdSharedPopupDataList.get(3);
					GtnUIFrameWorkActionConfig cdPopupSharedActionConfig = (GtnUIFrameWorkActionConfig) actionConfigLists.get(0);
					Object newPropValue = recordBeanDto
							.getPropertyValueByIndex(Integer.parseInt(String.valueOf(actionConfigLists.get(1))));
					cdPopupSharedActionConfig.addActionParameter(newPropValue);
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cdPopupSharedActionConfig);
				}
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
