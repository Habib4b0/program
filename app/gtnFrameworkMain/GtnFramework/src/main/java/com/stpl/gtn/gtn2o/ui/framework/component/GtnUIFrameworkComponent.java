package com.stpl.gtn.gtn2o.ui.framework.component;

import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

public interface GtnUIFrameworkComponent {

	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException;

	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput);

	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig);
}