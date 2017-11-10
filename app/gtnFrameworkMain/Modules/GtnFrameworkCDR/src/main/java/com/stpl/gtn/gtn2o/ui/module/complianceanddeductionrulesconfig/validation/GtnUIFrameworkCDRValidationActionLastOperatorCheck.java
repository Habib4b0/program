
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkCDRValidationActionLastOperatorCheck implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		if (!validate()) {
			List<Object> actionParams = new ArrayList<>();
			actionParams.add(" Cannot Add Rule");
			actionParams.add("Last rule must have an Operator in order to add Rule.");
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConf = new GtnUIFrameWorkActionConfig();

			gtnUIFrameWorkActionConf.setActionParameterList(actionParams);

			GtnUIFrameWorkAlertAction alert = new GtnUIFrameWorkAlertAction();
			alert.configureParams(gtnUIFrameWorkActionConf);
			alert.doAction(componentId, gtnUIFrameWorkActionConf);

			throw new GtnFrameworkGeneralException("Custom validation failed");

		}

	}

	private boolean validate() throws GtnFrameworkValidationFailedException {
		boolean hasOperator = true;

		List<GtnWsRecordBean> ruleDetailsList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ruleDetailsattachResultTable").getItemsFromDataTable();

		if (ruleDetailsList != null && !ruleDetailsList.isEmpty()) {
			GtnWsRecordBean dto = ruleDetailsList.get(ruleDetailsList.size() - 1);
			if ("null".equals(String.valueOf(dto.getAdditionalProperties().get(6)))) {
				hasOperator = false;
			}

		}

		return hasOperator;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCDRValidationActionLastOperatorCheck();
	}

}
