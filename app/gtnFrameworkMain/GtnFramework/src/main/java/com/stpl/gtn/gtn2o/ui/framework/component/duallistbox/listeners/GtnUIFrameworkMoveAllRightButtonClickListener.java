/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.listeners;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.Button;

/**
 *
 * @author STPL
 */
public class GtnUIFrameworkMoveAllRightButtonClickListener implements Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1629786330821313525L;

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkMoveAllRightButtonClickListener.class);

	private static volatile GtnUIFrameworkMoveAllRightButtonClickListener moveAllRightButtonClickListener = null;

	private GtnUIFrameworkMoveAllRightButtonClickListener() {
	}

	public static GtnUIFrameworkMoveAllRightButtonClickListener getInstance() {
		GtnUIFrameworkMoveAllRightButtonClickListener buttonClickListener = GtnUIFrameworkMoveAllRightButtonClickListener.moveAllRightButtonClickListener;
		if (buttonClickListener == null) {
			synchronized (GtnUIFrameworkMoveAllRightButtonClickListener.class) {
				buttonClickListener = GtnUIFrameworkMoveAllRightButtonClickListener.moveAllRightButtonClickListener;
				if (buttonClickListener == null) {
					GtnUIFrameworkMoveAllRightButtonClickListener.moveAllRightButtonClickListener = buttonClickListener = new GtnUIFrameworkMoveAllRightButtonClickListener();
				}
			}
		}
		return buttonClickListener;
	}

	@Override
	public void buttonClick(Button.ClickEvent event) {
		LOGGER.info("Inside MoveAllRightButtonClickListener:");
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) event.getButton().getData();
		GtnUIFrameWorkActionConfig loadRightTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadRightTableActionConfig.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_RIGHT_TABLE_LOADACTION);
		List<Object> actionParametersList = new ArrayList<>(2);
		actionParametersList.add(dualListBoxData);
		actionParametersList.add(Boolean.TRUE);
		loadRightTableActionConfig.setActionParameterList(actionParametersList);
		try {
			GtnUIFrameworkActionExecutor.executeSingleAction(event.getButton().getId(), loadRightTableActionConfig);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in MoveRightButtonClickListener", e);
		}
	}
}