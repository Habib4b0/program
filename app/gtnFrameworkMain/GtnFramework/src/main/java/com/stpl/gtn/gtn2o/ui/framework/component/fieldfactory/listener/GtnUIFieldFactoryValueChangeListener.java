package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;

public class GtnUIFieldFactoryValueChangeListener implements Property.ValueChangeListener {

	private static GtnUIFieldFactoryValueChangeListener gtnUIFieldFactoryValueChangeListener = new GtnUIFieldFactoryValueChangeListener();

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void valueChange(Property.ValueChangeEvent event) {
		try {

			if (event.getProperty() != null) {
				AbstractField<?> current = (AbstractField<?>) event.getProperty();
				GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) current.getData();
				GtnUIFrameworkActionParameter actionparameter = componentData.getActionParameter();
				actionparameter.setCurrentValue(current.getValue());
				String newStringValue = String.valueOf(actionparameter.getCurrentValue());
				if (current instanceof ComboBox && actionparameter.getOldValue() == null
						&& "0".equals(newStringValue)) {
					return;
				}
				if (String.class.isAssignableFrom(current.getType()) && actionparameter.getOldValue() == null
						&& newStringValue.isEmpty()) {
					return;
				}
				if (newStringValue.equals(actionparameter.getOldValue())) {
					return;
				}
				List<GtnUIFrameWorkActionConfig> actionConfigList = componentData.getCurrentComponentConfig()
						.getGtnUIFrameWorkValueChangeActionConfigList();
				String componentId = actionparameter.getVaadinComponentId();
				GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);
				actionparameter.setOldValue(String.valueOf(actionparameter.getCurrentValue()));
			}

		} catch (GtnFrameworkGeneralException e) {
			GtnWSLogger.getGTNLogger(GtnUIFieldFactoryValueChangeListener.class).error(e.getMessage(), e);
		}
	}

	public static GtnUIFieldFactoryValueChangeListener getListener() {
		return gtnUIFieldFactoryValueChangeListener;
	}

}
