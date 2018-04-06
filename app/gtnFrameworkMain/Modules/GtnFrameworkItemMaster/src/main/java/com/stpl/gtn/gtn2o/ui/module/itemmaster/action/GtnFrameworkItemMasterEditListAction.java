package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterClassContants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkItemMasterEditListAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterEditListAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameworkItemMasterEditListAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		String popupId = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		String popupName = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		String resultTable = (String) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);
		if ("Edit List".equals(value)) {
			GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
			popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
			popupActionConfig.addActionParameter(popupId);
			popupActionConfig.addActionParameter(popupName);
			popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
			popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
			popupActionConfig
					.addActionParameter(GtnFrameworkItemMasterClassContants.ITEM_MASTER_EDIT_LIST_CLOSE_ACTION);
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
