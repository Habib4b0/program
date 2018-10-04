/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUIFrameworkCVDeleteConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkCVDeleteConfirmationAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParam = gtnUIFrameWorkActionConfig.getActionParameterList();
			Object value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.get(1).toString())
					.getValueFromComponent();
			if (value == null) {
				GtnUIFrameWorkActionConfig cvDeleteAlertAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.INFO_ACTION);
				cvDeleteAlertAction.addActionParameter(actionParam.get(2).toString());
				cvDeleteAlertAction.addActionParameter(actionParam.get(3).toString());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cvDeleteAlertAction);
				return;
			}
			GtnWsRecordBean customViewBean = (GtnWsRecordBean) value;
			String custViewName = String
					.valueOf(customViewBean.getPropertyValue(GtnFrameworkCommonConstants.TREE_VIEW_NAME));
			GtnUIFrameWorkAction confirmAction = GtnUIFrameworkActionType.CONFIRMATION_ACTION.getGtnUIFrameWorkAction();
			GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
			confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
			confirmActionConfig.addActionParameter(actionParam.get(4).toString() + custViewName + "?");
			List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
			deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			deleteActionConfig.addActionParameter(GtnFrameworkCVDeleteAction.class.getName());
			deleteActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
			deleteActionConfig.addActionParameter(customViewBean);
			deleteActionConfig.addActionParameter(actionParam.get(1));
			successActionConfigList.add(deleteActionConfig);
			confirmActionConfig.addActionParameter(successActionConfigList);
			confirmAction.configureParams(confirmActionConfig);
			confirmAction.doAction(componentId, confirmActionConfig);

		} catch (Exception ex) {
			LOGGER.error("message", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
