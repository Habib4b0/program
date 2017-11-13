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
public class GtnUIFrameworkMoveLeftButtonClickListener implements Button.ClickListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = -4810044420028625884L;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkMoveLeftButtonClickListener.class);

	private static volatile GtnUIFrameworkMoveLeftButtonClickListener moveLeftButtonClickListener = null;

	private GtnUIFrameworkMoveLeftButtonClickListener() {
	}

	public static GtnUIFrameworkMoveLeftButtonClickListener getInstance() {
		GtnUIFrameworkMoveLeftButtonClickListener buttonClickListener = GtnUIFrameworkMoveLeftButtonClickListener.moveLeftButtonClickListener;
		if (buttonClickListener == null) {
			synchronized (GtnUIFrameworkMoveLeftButtonClickListener.class) {
				buttonClickListener = GtnUIFrameworkMoveLeftButtonClickListener.moveLeftButtonClickListener;
				if (buttonClickListener == null) {
					GtnUIFrameworkMoveLeftButtonClickListener.moveLeftButtonClickListener = buttonClickListener = new GtnUIFrameworkMoveLeftButtonClickListener();
				}
			}
		}
		return buttonClickListener;
	}

	@Override
	public void buttonClick(Button.ClickEvent event) {
		LOGGER.info("Inside MoveLeftButtonClickListener:");
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) event.getButton().getData();
		GtnUIFrameWorkActionConfig loadRightToLeftTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadRightToLeftTableActionConfig
				.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_RIGHT_TO_LEFT_TABLE_LOADACTION);
		List<Object> actionParametersList = new ArrayList<>(1);
		actionParametersList.add(dualListBoxData);
		loadRightToLeftTableActionConfig.setActionParameterList(actionParametersList);
		try {
			GtnUIFrameworkActionExecutor.executeSingleAction(event.getButton().getId(),
					loadRightToLeftTableActionConfig);
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error("Exception in MoveRightButtonClickListener", e);
		}
	}
}
