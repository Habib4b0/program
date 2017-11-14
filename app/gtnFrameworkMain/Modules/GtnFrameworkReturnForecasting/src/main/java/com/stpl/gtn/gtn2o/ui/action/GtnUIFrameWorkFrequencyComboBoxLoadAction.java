/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.GtnFrameworkUIForecastingFrequencyType;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import java.util.Locale;

public class GtnUIFrameWorkFrequencyComboBoxLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkFrequencyComboBoxLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GtnUIFrameWorkFrequency");

		List<Object> list = gtnUIFrameWorkActionConfig.getActionParameterList();

		ComboBox frequency = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(list.get(2)),
				componentId);
		String selectedFrequency = frequency.getItemCaption(frequency.getValue());

		selectedFrequency = selectedFrequency.replace(GtnFrameworkForecastConstantCommon.HYPHEN,
				GtnFrameworkForecastConstantCommon.UNDERSCORE);

		String[] historyValue = loadHistoryMap(selectedFrequency.toUpperCase(Locale.ENGLISH));

		AbstractComponent component = GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(list.get(1)),
				componentId);
		ComboBox history = (ComboBox) component;

		history.removeAllItems();
		for (int i = 1; i <= Integer.valueOf(historyValue[0]); i++) {
			history.addItem(i);
			history.setItemCaption(i, i + " " + historyValue[1]);
		}

		selectedFrequency = selectedFrequency.replace("_", "-");

		if (GtnFrameworkForecastConstantCommon.SELECTFREQ_ANNUAL.equals(selectedFrequency)) {
			history.setValue(1);
		} else if (GtnFrameworkForecastConstantCommon.SELECTFREQ_SEMI.equals(selectedFrequency)) {
			history.setValue(2);
		} else if (GtnFrameworkForecastConstantCommon.SELECTFREQ_QUAD.equals(selectedFrequency)) {
			history.setValue(4);
		} else {
			history.setValue(12);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	/**
	 * Configuration for history load
	 * 
	 * @param returnValue
	 */
	private String[] loadHistoryMap(final String frequency) {
		return GtnFrameworkUIForecastingFrequencyType.valueOf(frequency).getFrequencyData();
	}
}
