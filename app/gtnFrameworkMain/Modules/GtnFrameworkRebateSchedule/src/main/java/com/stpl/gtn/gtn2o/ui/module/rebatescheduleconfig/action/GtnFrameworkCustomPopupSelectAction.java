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
		List<Object> paramaterListObj = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent baseTableComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(paramaterListObj.get(1).toString());
		Object objValue = baseTableComponent.getValueFromComponent();
		if (objValue != null) {
			GtnWsRecordBean recordDto = (GtnWsRecordBean) objValue;

			GtnUIFrameworkComponentData componentViewData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(paramaterListObj.get(2).toString());
			Object popupData = componentViewData.getSharedPopupData();
			if (popupData != null) {
				List<Object> popupDataList = (List<Object>) popupData;

				GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
						.getVaadinComponentData(popupDataList.get(0).toString());

				Object propIdValue = recordDto.getPropertyValueByIndex(Integer.parseInt(String.valueOf(paramaterListObj.get(3))));
				componentData.setCustomDataList(Arrays.asList(propIdValue, recordDto, paramaterListObj.get(3)));
				componentData.setCustomData(recordDto);
				List<String> inputIdColumns = (List<String>) popupDataList.get(1);
				List<String> outputIdFields = (List<String>) popupDataList.get(2);
				GtnUIFrameworkSetter setterObj = new GtnUIFrameworkSetter();
				for (int i = 0; i < inputIdColumns.size(); i++) {
					Object newObjValue = recordDto.getPropertyValue(inputIdColumns.get(i));
					if (newObjValue == null) {
						newObjValue = recordDto.getPropertyValueByIndex(Integer.parseInt(inputIdColumns.get(i)));
					}
					setterObj.setPropertyValue(outputIdFields.get(i), newObjValue);
				}
				if (popupDataList.size() > 3 && popupDataList.get(3) != null) {
					List<Object> configActions = (List<Object>) popupDataList.get(3);
					GtnUIFrameWorkActionConfig actionConfigVal = (GtnUIFrameWorkActionConfig) configActions.get(0);
					Object newObjValue = recordDto
							.getPropertyValueByIndex(Integer.parseInt(String.valueOf(configActions.get(1))));
					actionConfigVal.addActionParameter(newObjValue);
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfigVal);
				}
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
