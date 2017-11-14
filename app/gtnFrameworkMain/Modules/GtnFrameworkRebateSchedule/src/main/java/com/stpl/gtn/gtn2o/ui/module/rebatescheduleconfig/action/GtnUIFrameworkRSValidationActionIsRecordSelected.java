package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkRSValidationActionIsRecordSelected
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean dto = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsSearchResultTable")
				.getValueFromPagedDataTable();
		if (dto == null) {
			List<Object> actionParams = new ArrayList<>();
			String mode = String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).toLowerCase();
			actionParams.add(" " + mode.replace(mode.charAt(0), Character.toUpperCase(mode.charAt(0))) + " Error");
			actionParams.add("Please select a record to " + mode + ".");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction(componentId, gtnUIFrameWorkActionConf);

			throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
