package com.stpl.gtn.gtn2o.ui.framework.action;

import org.asi.ui.customwindow.CustomWindow;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import org.asi.ui.customwindow.MinimizeTray;

public class GtnUIFrameworkPopupCloseAction implements GtnUIFrameWorkAction {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPopupCloseAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId);
		CustomWindow customWindow = componentData.getCustomWindow();
		if (customWindow != null) {
			try{
                            MinimizeTray tray = customWindow.getMinimizeTray();
                            if (tray.getWindowItems().size() == 1) {
                                tray.close();
                            }
			customWindow.close();
			}
			catch(Exception e)
			{
				logger.error("Exception:"+e.getMessage());
			}
		}

		GtnUIFrameworkGlobalUI.removeViewFromNavigator(componentId);
		logger.info("Remove ::: View Generated Id:" + componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}