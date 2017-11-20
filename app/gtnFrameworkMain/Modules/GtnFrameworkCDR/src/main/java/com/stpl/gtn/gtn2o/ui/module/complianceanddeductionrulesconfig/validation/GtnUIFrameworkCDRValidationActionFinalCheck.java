
package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig.validation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkCDRValidationActionFinalCheck implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> ruleDetailsList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ruleDetailsattachResultTable").getItemsFromDataTable();
		if (ruleDetailsList != null && !ruleDetailsList.isEmpty()) {
			String value = (String) ruleDetailsList.get(ruleDetailsList.size() - 1).getPropertyValueByIndex(6);

			if (value != null && !"".equals(value)) {
				List<Object> actionParams = new ArrayList<>();
				actionParams.add("Cannot Save Rule");
				actionParams.add("Last rule cannot have an Operator.");
				GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();
				gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

				GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
				alert.configureParams(gtnUIFrameWorkActionConf);
				alert.doAction("", gtnUIFrameWorkActionConf);

				throw new GtnFrameworkGeneralException("Custom validation failed");

			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRValidationActionFinalCheck();
	}
}
