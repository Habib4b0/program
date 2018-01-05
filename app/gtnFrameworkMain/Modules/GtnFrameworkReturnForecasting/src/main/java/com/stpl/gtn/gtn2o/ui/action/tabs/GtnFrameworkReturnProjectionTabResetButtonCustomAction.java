package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.OptionGroup;

public class GtnFrameworkReturnProjectionTabResetButtonCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig tempActionConfig = (GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);

		List<Object> actionParameterList = tempActionConfig.getActionParameterList();

		ComboBox forecastReturnsFrequency = (ComboBox) getAbstractComponentData(
				String.valueOf(actionParameterList.get(1)), componentId);
		ComboBox forecastReturnsHistory = (ComboBox) getAbstractComponentData(
				String.valueOf(actionParameterList.get(2)), componentId);

		OptionGroup actualsProjectionOptionGroup = (OptionGroup) getAbstractComponentData(
				String.valueOf(actionParameterList.get(3)), componentId);
		OptionGroup projectionPeriodOrderOptionGroup = (OptionGroup) getAbstractComponentData(
				String.valueOf(actionParameterList.get(4)), componentId);

		setDataOnResetButtonClick(forecastReturnsFrequency, String.valueOf(actionParameterList.get(5)));

		setDataOnResetButtonClick(forecastReturnsHistory, String.valueOf(actionParameterList.get(6)));

		actualsProjectionOptionGroup.setValue(String.valueOf(actionParameterList.get(7)));

		projectionPeriodOrderOptionGroup.setValue(String.valueOf(actionParameterList.get(8)));

	}

	@SuppressWarnings("unchecked")
	private void setDataOnResetButtonClick(ComboBox componenetData, String currentComponentData) {

		List<Object> itemIdList = (List<Object>) componenetData.getItemIds();
		int getId = 0;

		for (int i = 0; i < itemIdList.size(); i++) {
			if (componenetData.getItemCaption(itemIdList.get(i)).equals(currentComponentData)) {
				getId = Integer.parseInt(String.valueOf(itemIdList.get(i)));
				componenetData.setValue(getId);
			}
		}
	}

	private AbstractComponent getAbstractComponentData(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
