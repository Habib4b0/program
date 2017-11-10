/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractComponent;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkValueChangeListener implements Property.ValueChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final GtnUIFrameworkValueChangeListener gtnUIFrameworkValueChangeListener = new GtnUIFrameworkValueChangeListener();

	@Override
	public void valueChange(Property.ValueChangeEvent event) {
		try {
			AbstractComponent component = (AbstractComponent) event.getProperty();
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
			GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
			GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
					componentConfig.getGtnUIFrameWorkActionConfigList());

		} catch (GtnFrameworkGeneralException e) {
			getGtnLogger().error(e.getMessage(), e);
		}
	}

	public static GtnUIFrameworkValueChangeListener getListener() {
		return gtnUIFrameworkValueChangeListener;
	}

	public GtnWSLogger getGtnLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIFrameworkValueChangeListener.class);
	}
}
