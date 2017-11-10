package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

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

public class GtnFrameworkForecastReturnFromToPeriodAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnFromToPeriodAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("inside GtnFrameworkForecastReturnFromToPeriodAction");
		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		String namespace = actionParameter.get(1) + GtnFrameworkForecastConstantCommon.UNDERSCORE;
		String optionGroupComponentId = namespace + actionParameter.get(2);
		String optionGroupData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(optionGroupComponentId, componentId)
				.getStringFromField();

		if (GtnFrameworkForecastConstantCommon.SEARCH_MODE.equals(optionGroupData)) {
			loadToPeriodComboBox(namespace, actionParameter, componentId);
		}
	}

	private void loadToPeriodComboBox(String namespace, List<Object> actionParameter, String sourceCompomponentId)
			throws GtnFrameworkGeneralException {

		try {
			int startIndex = 0;
			int endIndex = 0;
			AbstractComponent abstractComponentFrom = GtnUIFrameworkGlobalUI
					.getVaadinComponent(namespace + actionParameter.get(3), sourceCompomponentId);

			ComboBox componentComboboxFrom = (ComboBox) abstractComponentFrom;

			@SuppressWarnings("unchecked")
			List<Object> itemIdList = (List<Object>) componentComboboxFrom.getItemIds();

			List<Integer> componentComboboxFromId = new ArrayList<>();
			List<String> componentComboboxFromCaption = new ArrayList<>();

			List<Integer> componentComboboxToId = new ArrayList<>();
			List<String> componentComboboxToCaption = new ArrayList<>();

			for (int i = 0; i < itemIdList.size(); i++) {
				if (!GtnFrameworkForecastConstantCommon.SELECT_ONE
						.equals(componentComboboxFrom.getItemCaption(itemIdList.get(i)))) {
					componentComboboxFromId.add((Integer) itemIdList.get(i));
					componentComboboxFromCaption.add(componentComboboxFrom.getItemCaption(itemIdList.get(i)));
					if (componentComboboxFrom.getValue().equals(itemIdList.get(i))) {
						startIndex = i + 1;
						endIndex = itemIdList.size();
					}
				}
			}

			for (int j = startIndex; j < endIndex; j++) {
				componentComboboxToId.add((Integer) itemIdList.get(j));
				componentComboboxToCaption.add(componentComboboxFrom.getItemCaption(itemIdList.get(j)));
			}

			AbstractComponent abstractComponentTo = GtnUIFrameworkGlobalUI
					.getVaadinComponent(namespace + actionParameter.get(4), sourceCompomponentId);
			ComboBox componentComboboxTo = (ComboBox) abstractComponentTo;

			componentComboboxTo.removeAllItems();
			componentComboboxTo.setNullSelectionAllowed(Boolean.TRUE);
			componentComboboxTo.setNullSelectionItemId(0);
			componentComboboxTo.addItem(0);
			componentComboboxTo.setItemCaption(0, GtnFrameworkForecastConstantCommon.SELECT_ONE);
			componentComboboxTo.select(0);

			for (int i = 0; i < componentComboboxToId.size(); i++) {
				componentComboboxTo.addItem(componentComboboxToId.get(i));
				componentComboboxTo.setItemCaption(componentComboboxToId.get(i), componentComboboxToCaption.get(i));
			}

		} catch (Exception ex) {
			gtnLogger.error("Error in loadToPeriodComboBox Method", ex);
			throw new GtnFrameworkGeneralException("Error in loadToPeriodComboBox  Method", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
