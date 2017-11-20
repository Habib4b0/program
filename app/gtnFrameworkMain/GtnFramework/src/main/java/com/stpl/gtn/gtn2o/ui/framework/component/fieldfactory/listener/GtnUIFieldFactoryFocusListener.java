package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractField;

public class GtnUIFieldFactoryFocusListener implements FieldEvents.FocusListener {

	private static GtnUIFieldFactoryFocusListener gtnUIFieldFactoryFocusListener = new GtnUIFieldFactoryFocusListener();
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void focus(FieldEvents.FocusEvent event) {
		try {
			AbstractField<?> current = (AbstractField<?>) event.getSource();
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) current.getData();
			GtnUIFrameworkActionParameter actionparameter = componentData.getActionParameter();
			actionparameter.setCurrentValue(current.getValue());
			List<GtnUIFrameWorkActionConfig> actionConfigList = componentData.getCurrentComponentConfig()
					.getGtnUIFrameWorkFocusActionConfigList();
			String componentId = actionparameter.getVaadinComponentId();
			GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);

		} catch (GtnFrameworkGeneralException e) {
			GtnWSLogger.getGTNLogger(GtnUIFieldFactoryValueChangeListener.class).error(e.getMessage(), e);
		}
	}

	public static GtnUIFieldFactoryFocusListener getListener() {
		return gtnUIFieldFactoryFocusListener;
	}

}
