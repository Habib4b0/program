package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class GtnUIPopUpCloseListener implements CloseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static GtnUIPopUpCloseListener gtnUIPopUpCloseListener = new GtnUIPopUpCloseListener();

	@Override
	public void windowClose(CloseEvent e) {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) e.getWindow().getData();
		GtnUIFrameWorkActionConfig actionConfig = (GtnUIFrameWorkActionConfig) componentData.getCustomData();
		List<Object> configList = actionConfig.getActionParameterList();
		if (configList.size() > 4) {
			Object customActionClassName = configList.get(4);
			if (customActionClassName != null && !configList.get(4).toString().isEmpty()) {
				try {
					GtnUIFrameworkActionExecutor.executeActionClass((String) configList.get(0),
							customActionClassName.toString(), actionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					getGtnLogger().error("Popup window Error", ex);
				}
			}
		}
	}

	public static GtnUIPopUpCloseListener getListener() {
		return gtnUIPopUpCloseListener;
	}

	public GtnWSLogger getGtnLogger() {
		return GtnWSLogger.getGTNLogger(GtnUIGeneralButtonClickListener.class);
	}

}
