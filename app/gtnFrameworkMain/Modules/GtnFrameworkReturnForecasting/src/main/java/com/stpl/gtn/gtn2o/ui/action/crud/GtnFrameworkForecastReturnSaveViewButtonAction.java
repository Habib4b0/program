package com.stpl.gtn.gtn2o.ui.action.crud;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;

public class GtnFrameworkForecastReturnSaveViewButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();
		String publicViewName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId)
				.getStringFromField();
		String privateViewName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(1), componentId)
				.getStringFromField();
		if ((publicViewName != null && !publicViewName.isEmpty())
				|| (privateViewName != null && !privateViewName.isEmpty())) {
			AbstractComponent abstractComponentUpdate = GtnUIFrameworkGlobalUI
					.getVaadinComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId);
			Button componentSaveButton = (Button) abstractComponentUpdate;
			componentSaveButton.setEnabled(true);

			AbstractComponent abstractComponentAdd = GtnUIFrameworkGlobalUI
					.getVaadinComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId);
			Button componentUpdateButton = (Button) abstractComponentAdd;
			componentUpdateButton.setEnabled(false);

			if (publicViewName != null && !publicViewName.isEmpty()) {
				gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(4),
						publicViewName);
				gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(5),
						gtnUIFrameWorkActionConfig.getFieldValues().get(6));
			}
			if (privateViewName != null && !privateViewName.isEmpty()) {
				gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(4),
						privateViewName);
				gtnUIFrameworkSetter.loadFieldValue(componentId, gtnUIFrameWorkActionConfig.getFieldValues().get(5),
						gtnUIFrameWorkActionConfig.getFieldValues().get(7));
			}
		} else {
			AbstractComponent abstractComponentUpdate = GtnUIFrameworkGlobalUI
					.getVaadinComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(2), componentId);
			Button componentSaveButton = (Button) abstractComponentUpdate;
			componentSaveButton.setEnabled(false);

			AbstractComponent abstractComponentAdd = GtnUIFrameworkGlobalUI
					.getVaadinComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(3), componentId);
			Button componentUpdateButton = (Button) abstractComponentAdd;
			componentUpdateButton.setEnabled(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;

	}
}
