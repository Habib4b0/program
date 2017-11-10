package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public class GtnUIGeneralButtonClickListener implements Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static GtnUIGeneralButtonClickListener gtnUIGeneralButtonClickListener = new GtnUIGeneralButtonClickListener();

	@Override
	public void buttonClick(ClickEvent event) {
		try {

			Button button = (Button) event.getSource();
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) button.getData();
			GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
			GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
					componentConfig.getGtnUIFrameWorkActionConfigList());
		} catch (GtnFrameworkGeneralException e) {
			getGtnLogger().error(e.getMessage());
		}
	}

	public static GtnUIGeneralButtonClickListener getListener() {
		return gtnUIGeneralButtonClickListener;
	}

	public GtnWSLogger getGtnLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIGeneralButtonClickListener.class);

	}

}
