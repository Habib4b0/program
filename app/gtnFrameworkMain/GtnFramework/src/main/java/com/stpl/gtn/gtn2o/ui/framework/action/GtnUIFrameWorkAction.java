
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public interface GtnUIFrameWorkAction {

	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException;

	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException;

	public GtnUIFrameWorkAction createInstance();

}
