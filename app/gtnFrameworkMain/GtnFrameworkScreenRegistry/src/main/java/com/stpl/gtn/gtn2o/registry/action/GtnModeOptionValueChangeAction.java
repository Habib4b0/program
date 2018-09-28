/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkFromAndToLoadBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnModeOptionValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnModeOptionValueChangeAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParamList.get(1)));
		String modeValue = baseComponent.getV8StringFromField();
		boolean add = "Add".equals(modeValue);
		valueChangeForFrom("Commercial Forecasting_from", add);
		valueChangeForTo("Commercial Forecasting_to", add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(2))).setEnable(add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(3))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(4))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(5))).setEnable(add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(6))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(7))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(8))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(9))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(10))).setEnable(!add);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamList.get(11))).setEnable(!add);
	}

	private void valueChangeForTo(String string, boolean add) throws GtnFrameworkValidationFailedException {
		try {
			GtnFrameworkFromAndToLoadBean bean = GtnFrameworkFromAndToLoadBean.getInstance();
			List<Integer> itemId = new ArrayList<>();
			List<String> itemCaption = new ArrayList<>();
			itemId.addAll(bean.getToPeriodItemCodeList());
			itemCaption.addAll(bean.getToPeriodItemValueList());
			if (!add) {
				itemId.add(0, 0);
				itemCaption.add(0, "-Select one-");

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).addAllItemsToComboBox(itemCaption, itemId);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).loadV8ComboBoxComponentValue(itemId.get(0));

			} else {
				if (itemCaption.contains("-Select one-")) {
					itemCaption.remove(0);
					itemId.remove(0);

				}
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).addAllItemsToComboBox(itemCaption, itemId);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).loadV8ComboBoxComponentValue(itemId.get(0));

			}
		} catch (GtnFrameworkValidationFailedException e) {
			logger.info("Exception in mode value change action" + e);
		}
	}

	@SuppressWarnings("unchecked")
	private void valueChangeForFrom(String string, boolean add) {
		try {
			GtnFrameworkFromAndToLoadBean bean = GtnFrameworkFromAndToLoadBean.getInstance();
			List<Integer> itemIdFrom = new ArrayList<>();
			List<String> itemCaption = new ArrayList<>();
			itemIdFrom.addAll(bean.getFromPeriodItemCodeList());
			itemCaption.addAll(bean.getFromPeriodItemValueList());
			if (!add) {
				itemIdFrom.add(0, 0);
				itemCaption.add(0, "-Select one-");

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).addAllItemsToComboBox(itemCaption, itemIdFrom);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).loadV8ComboBoxComponentValue(itemIdFrom.get(0));

			} else {
				if (itemCaption.contains("-Select one-")) {
					itemCaption.remove(0);
					itemIdFrom.remove(0);

				}
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).addAllItemsToComboBox(itemCaption, itemIdFrom);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).loadV8ComboBoxComponentValue(itemIdFrom.get(0));

			}

		} catch (GtnFrameworkValidationFailedException e) {
			logger.info("Exception in mode value change action" + e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
