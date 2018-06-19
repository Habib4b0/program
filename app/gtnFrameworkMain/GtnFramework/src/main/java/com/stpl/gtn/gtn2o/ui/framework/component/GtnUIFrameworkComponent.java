package com.stpl.gtn.gtn2o.ui.framework.component;

import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

public interface GtnUIFrameworkComponent {

	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig)
			throws GtnFrameworkGeneralException;

	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput);

	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig);

	public default void setComponentData(AbstractComponent component, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		if (componentData == null) {
			componentData = new GtnUIFrameworkComponentData();
		}
		componentData.setCurrentGtnComponent(this);
		componentData.setCurrentComponentConfig(componentConfig);
		component.setData(componentData);
	}
}