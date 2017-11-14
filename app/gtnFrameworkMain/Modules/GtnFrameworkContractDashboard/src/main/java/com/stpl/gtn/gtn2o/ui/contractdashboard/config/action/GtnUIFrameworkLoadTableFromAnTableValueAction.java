package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

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

public class GtnUIFrameworkLoadTableFromAnTableValueAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - Source Table Id to fetch
	 * value, Param 2 - Index of the property to send as extra parameter, Param
	 * 3 - Destination Table Id to Load
	 */
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent sourceTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString(), componentId);
		GtnWsRecordBean bean = (GtnWsRecordBean) sourceTableBaseComponent.getValueFromComponent();
		Object extraParam = bean.getPropertyValueByIndex(Integer.parseInt(String.valueOf(parameters.get(2))));
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(parameters.get(3).toString());
		loadDataTableActionConfig.addActionParameter(extraParam);
		loadDataTableActionConfig.setFieldValues(gtnUIFrameWorkActionConfig.getFieldValues());
		loadDataTableActionConfig.setFieldDescription(gtnUIFrameWorkActionConfig.getFieldDescription());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
