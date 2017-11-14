/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.textbox.listner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractField;
import java.util.List;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnUIFrameworkBlurListner implements FieldEvents.BlurListener {

	private static final GtnUIFrameworkBlurListner TEXT_FIELD_BLUR_LISTENER = new GtnUIFrameworkBlurListner();

	private static final long serialVersionUID = 1L;

	@Override
	public void blur(FieldEvents.BlurEvent event) {

		try {
			AbstractField<?> current = (AbstractField<?>) event.getSource();
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) current.getData();
			GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
			List<GtnUIFrameWorkActionConfig> actionConfigList = componentConfig.getGtnTextBoxConfig()
					.getBlurActionConfigList();
			GtnUIFrameworkActionExecutor.executeActionList(componentConfig.getComponentId(), actionConfigList);

		} catch (GtnFrameworkGeneralException e) {
			GtnWSLogger.getGTNLogger(GtnUIFrameworkBlurListner.class).error(e.getMessage(), e);
		}
	}

	public static GtnUIFrameworkBlurListner getListener() {
		return TEXT_FIELD_BLUR_LISTENER;
	}

}
