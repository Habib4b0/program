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
public class GtnUIFrameworkMoveRightButtonClickListener implements Button.ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5332814666307472151L;

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkMoveRightButtonClickListener.class);

	private static volatile GtnUIFrameworkMoveRightButtonClickListener moveRightButtonClickListener = null;

	private GtnUIFrameworkMoveRightButtonClickListener() {
	}

	public static GtnUIFrameworkMoveRightButtonClickListener getInstance() {
		GtnUIFrameworkMoveRightButtonClickListener buttonClickListener = GtnUIFrameworkMoveRightButtonClickListener.moveRightButtonClickListener;
		if (buttonClickListener == null) {
			synchronized (GtnUIFrameworkMoveRightButtonClickListener.class) {
				buttonClickListener = GtnUIFrameworkMoveRightButtonClickListener.moveRightButtonClickListener;
				if (buttonClickListener == null) {
					GtnUIFrameworkMoveRightButtonClickListener.moveRightButtonClickListener = buttonClickListener = new GtnUIFrameworkMoveRightButtonClickListener();
				}
			}
		}
		return buttonClickListener;
	}

	@Override
	public void buttonClick(Button.ClickEvent event) {
		LOGGER.info("Inside MoveRightButtonClickListener:");
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) event.getButton().getData();
		GtnUIFrameWorkActionConfig loadRightTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadRightTableActionConfig.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_RIGHT_TABLE_LOADACTION);
		List<Object> actionParametersList = new ArrayList<>(2);
		actionParametersList.add(dualListBoxData);
		actionParametersList.add(Boolean.FALSE);
		loadRightTableActionConfig.setActionParameterList(actionParametersList);
		try {
			GtnUIFrameworkActionExecutor.executeSingleAction(event.getButton().getId(), loadRightTableActionConfig);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in MoveRightButtonClickListener", e);
		}
	}

}