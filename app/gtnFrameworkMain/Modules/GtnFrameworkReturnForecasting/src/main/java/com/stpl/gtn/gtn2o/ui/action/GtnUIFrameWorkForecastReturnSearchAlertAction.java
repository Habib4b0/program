package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;

/**
 *
 * @author Stpl
 */
public class GtnUIFrameWorkForecastReturnSearchAlertAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkForecastReturnSearchAlertAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GtnUIFrameWorkForecastReturnSearchAlertAction");

		GtnUIFrameworkPagedTableLogic pagedTablelogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(),
						componentId)
				.getLogicFromPagedDataTable();
		if (pagedTablelogic.getCount() == 0) {
			if (gtnUIFrameWorkActionConfig.getActionParameterList().size() > 4) {
				AbstractComponent abstractComponentSave = GtnUIFrameworkGlobalUI.getVaadinComponent(
						(String) gtnUIFrameWorkActionConfig.getActionParameterList().get(4), componentId);
				Button componentSaveButton = (Button) abstractComponentSave;
				componentSaveButton.setEnabled(false);
			}
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

			alertActionConfig.addActionParameter(gtnUIFrameWorkActionConfig.getActionParameterList().get(3).toString());
			alertActionConfig.addActionParameter(gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString());
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkGeneralException(
					"Validation Error :" + gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString());

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
