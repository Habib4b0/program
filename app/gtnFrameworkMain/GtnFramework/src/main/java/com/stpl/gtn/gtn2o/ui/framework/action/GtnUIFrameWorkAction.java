
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public interface GtnUIFrameWorkAction {

	public default void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//No need to configure
	};

	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException;

	public default GtnUIFrameWorkAction createInstance() {
		return this;
	};

}
