/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnUIFrameworkIdentifierEditListAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkIdentifierEditListAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		String popupId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		String popupName = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(GtnWsNumericConstants.TWO);
		String resultTable = (String) gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(GtnWsNumericConstants.THREE);
		if ("Edit List".equals(value)) {
			GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
			popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
			popupActionConfig.addActionParameter(popupId);
			popupActionConfig.addActionParameter(popupName);
			/* Width */popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PIXEL_1250);
			/* Height */popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PIXEL_650);
			popupActionConfig.addActionParameter(GtnFrameworkCompanyClassContants.IDENTIFIER_EDIT_LIST_POPUP_CLOSE);
			popupActionConfig.addActionParameter(null);
			popupActionConfig.addActionParameter(componentId);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, popupActionConfig);
			GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTable)
					.getLogicFromPagedDataTable();
			logic.startSearchProcess(null, true);

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
