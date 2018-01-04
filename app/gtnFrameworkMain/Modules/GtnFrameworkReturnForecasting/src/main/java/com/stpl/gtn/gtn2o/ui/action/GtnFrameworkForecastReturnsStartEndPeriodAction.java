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
import com.vaadin.v7.ui.ComboBox;

public class GtnFrameworkForecastReturnsStartEndPeriodAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnsStartEndPeriodAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkForecastReturnsStartEndPeriodAction");

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		loadEndPeriodComboBox(actionParameterList, componentId);

	}

	private void loadEndPeriodComboBox(List<Object> actionParameterList, String sourceComponentId)
			throws GtnFrameworkGeneralException {

		try {

			int startIndex = 0;
			int endIndex = 0;

			AbstractComponent abstractComponentFrom = GtnUIFrameworkGlobalUI
					.getVaadinComponent(String.valueOf(actionParameterList.get(1)), sourceComponentId);

			ComboBox componentComboboxStartPeriod = (ComboBox) abstractComponentFrom;

			@SuppressWarnings("unchecked")
			List<Object> itemIdList = (List<Object>) componentComboboxStartPeriod.getItemIds();

			List<Integer> componentComboboxStartPeriodId = new ArrayList<>();
			List<String> componentComboboxStartPeriodCaption = new ArrayList<>();

			List<Integer> componentComboboxEndPeriodId = new ArrayList<>();
			List<String> componentComboboxEndPeriodCaption = new ArrayList<>();

			for (int i = 0; i < itemIdList.size(); i++) {
				if (!GtnFrameworkForecastConstantCommon.SELECT_ONE
						.equals(componentComboboxStartPeriod.getItemCaption(itemIdList.get(i)))) {
					componentComboboxStartPeriodId.add((Integer) itemIdList.get(i));
					componentComboboxStartPeriodCaption
							.add(componentComboboxStartPeriod.getItemCaption(itemIdList.get(i)));

					if (componentComboboxStartPeriod.getValue().equals(itemIdList.get(i))) {

						if ((itemIdList.get(i)) == (itemIdList.get(itemIdList.size() - 1))) {
							startIndex = i;
							endIndex = itemIdList.size();
						} else {
							startIndex = i + 1;
							endIndex = itemIdList.size();
						}
					}

				}
			}

			for (int j = startIndex; j < endIndex; j++) {
				componentComboboxEndPeriodId.add((Integer) itemIdList.get(j));
				componentComboboxEndPeriodCaption.add(componentComboboxStartPeriod.getItemCaption(itemIdList.get(j)));
			}

			AbstractComponent abstractComponentTo = GtnUIFrameworkGlobalUI
					.getVaadinComponent(String.valueOf(actionParameterList.get(2)), sourceComponentId);
			ComboBox componentComboboxEndPeriod = (ComboBox) abstractComponentTo;

			componentComboboxEndPeriod.removeAllItems();
			componentComboboxEndPeriod.setNullSelectionAllowed(Boolean.TRUE);
			componentComboboxEndPeriod.setNullSelectionItemId(0);
			componentComboboxEndPeriod.addItem(0);
			componentComboboxEndPeriod.setItemCaption(0, GtnFrameworkForecastConstantCommon.SELECT_ONE);

			for (int i = 0; i < componentComboboxEndPeriodId.size(); i++) {
				componentComboboxEndPeriod.addItem(componentComboboxEndPeriodId.get(i));
				componentComboboxEndPeriod.setItemCaption(componentComboboxEndPeriodId.get(i),
						componentComboboxEndPeriodCaption.get(i));
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
