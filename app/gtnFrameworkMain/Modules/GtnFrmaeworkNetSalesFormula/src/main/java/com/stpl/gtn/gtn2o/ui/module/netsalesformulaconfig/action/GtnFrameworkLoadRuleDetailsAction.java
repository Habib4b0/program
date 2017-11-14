package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkLoadRuleDetailsAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent formulaTypeSearchTableComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString(), componentId);
		GtnWsRecordBean recordBean = (GtnWsRecordBean) formulaTypeSearchTableComponent.getValueFromComponent();
		Object extraParam = recordBean.getPropertyValue("systemId");
		GtnUIFrameWorkActionConfig detailsTableActionConfig = new GtnUIFrameWorkActionConfig();
		detailsTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		detailsTableActionConfig.addActionParameter(parameters.get(2).toString());
		detailsTableActionConfig.addActionParameter(extraParam);
		detailsTableActionConfig.setFieldValues(gtnUIFrameWorkActionConfig.getFieldValues());
		detailsTableActionConfig.setFieldDescription(gtnUIFrameWorkActionConfig.getFieldDescription());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, detailsTableActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
