package com.stpl.gtn.gtn2o.ui.framework.action;

import org.asi.container.ExtContainer;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractSelect;

public class GtnUIFrameworkRemoveAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		AbstractSelect resultTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0), componentId).getGridComponent();

		if (resultTable.getValue() != null) {
			ExtContainer<?> container = (ExtContainer<?>) resultTable.getContainerDataSource();
			container.removeItem(resultTable.getValue());
			resultTable.setValue(null);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}