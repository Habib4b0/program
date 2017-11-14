package com.stpl.gtn.gtn2o.ui.action.tabs;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkComponentValueSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkForecastReturnDataSelectionTabAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkForecastReturnDataSelectionTabAction.class);

	private GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter = new GtnUIFrameworkComponentValueSetter();

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {

			gtnLogger.info(" inside GtnFrameworkForecastReturnDataSelectionTabAction ");
			List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String toNamespace = actionParameterList.get(1).toString();
			String fromNamespace = actionParameterList.get(2).toString();

			if (GtnFrameworkForecastConstantCommon.GENERATE.equals(actionParameterList.get(3))) {
				AbstractComponent abstractComponentSave = GtnUIFrameworkGlobalUI
						.getVaadinComponent(actionParameterList.get(5).toString(), componentId);

				Button componentSaveButton = (Button) abstractComponentSave;
				componentSaveButton.setVisible(true);

				AbstractComponent abstractComponentUpdate = GtnUIFrameworkGlobalUI
						.getVaadinComponent(actionParameterList.get(6).toString(), componentId);
				Button componentUpdateButton = (Button) abstractComponentUpdate;
				componentUpdateButton.setVisible(false);

			}
			loadDataByDefalutValue(toNamespace + GtnFrameworkForecastConstantCommon.UNDERSCORE,
					fromNamespace + GtnFrameworkForecastConstantCommon.UNDERSCORE, componentId,
					gtnUIFrameWorkActionConfig);

		} catch (GtnFrameworkValidationFailedException exception) {
			gtnLogger.error("Error in doAction Method", exception);
			throw exception;
		} catch (Exception ex) {
			gtnLogger.error("Error in doAction Method", ex);
			throw new GtnFrameworkValidationFailedException("Error in doAction  Method", ex);
		}
	}

	private void loadDataByDefalutValue(String toNamespace, String fromNamespace, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkValidationFailedException {

		try {
			List<String> fieldValuesParameterList = gtnUIFrameWorkActionConfig.getFieldValues();

			String projectionName = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(0), componentId)
					.getStringFromField();
			getGtnUIFrameworkSetter()
					.loadFieldValue(componentId,
							toNamespace
									+ fieldValuesParameterList.get(1),
							(String) GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
									fromNamespace + fieldValuesParameterList.get(2), componentId).getStringFromField());
			getGtnUIFrameworkSetter()
					.loadFieldValue(componentId,
							toNamespace
									+ fieldValuesParameterList.get(3),
							(String) GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
									fromNamespace + fieldValuesParameterList.get(4), componentId).getStringFromField());
			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(5),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(6),
											componentId)
									.getIntegerFromField());
			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(7),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(8),
											componentId)
									.getIntegerFromField());
			getGtnUIFrameworkSetter().loadFieldValue(componentId, toNamespace + fieldValuesParameterList.get(9),
					projectionName);
			getGtnUIFrameworkSetter()
					.loadFieldValue(componentId,
							toNamespace
									+ fieldValuesParameterList.get(10),
							(String) GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(11),
											componentId)
									.getStringFromField());
			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(12),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(13),
											componentId)
									.getIntegerFromField());
			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(14),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(15),
											componentId)
									.getIntegerFromField());
			getGtnUIFrameworkSetter()
					.loadFieldValue(componentId,
							toNamespace
									+ fieldValuesParameterList.get(16),
							(String) GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(16),
											componentId)
									.getStringFromField());

			GtnUIFrameworkComponentData toComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(toNamespace + fieldValuesParameterList.get(16), componentId);
			toComponentData.setCustomData(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(16), componentId)
					.getIdFromField());

			List<Object> reloadInput = new ArrayList<>();
			reloadInput.add(toComponentData.getCustomData());

			GtnUIFrameworkComboboxComponent gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					toNamespace + fieldValuesParameterList.get(17), componentId, reloadInput);

			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(17),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(17),
											componentId)
									.getIntegerFromField());

			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					toNamespace + fieldValuesParameterList.get(18), componentId, reloadInput);

			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(18),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(18),
											componentId)
									.getIntegerFromField());

			List<Object> emptyInputList = new ArrayList<>();
			emptyInputList.add("");

			gtnUIFrameworkComponent = new GtnUIFrameworkComboboxComponent();
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(19), componentId, emptyInputList);

			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(20), componentId, emptyInputList);
			gtnUIFrameworkComponent.reloadComponent(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION,
					fieldValuesParameterList.get(21), componentId, emptyInputList);
			int forecastLevel = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(18), componentId)
					.getIntegerFromField();
			getGtnUIFrameworkSetter().loadComboBoxComponentValue(componentId,
					toNamespace + fieldValuesParameterList.get(18), forecastLevel);
			getGtnUIFrameworkSetter()
					.loadFieldValue(componentId,
							toNamespace
									+ fieldValuesParameterList.get(22),
							(String) GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(22),
											componentId)
									.getStringFromField());
			getGtnUIFrameworkSetter()
					.loadComboBoxComponentValue(componentId, toNamespace + fieldValuesParameterList.get(23),
							GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent(fromNamespace + fieldValuesParameterList.get(23),
											componentId)
									.getIntegerFromField());

		} catch (Exception ex) {
			gtnLogger.error("Error in load Data By Defalut Value Method", ex);
			throw new GtnFrameworkValidationFailedException("Error in Fetch Data By Default Value Method", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;

	}

	public GtnUIFrameworkComponentValueSetter getGtnUIFrameworkSetter() {
		return gtnUIFrameworkSetter;
	}

	public void setGtnUIFrameworkSetter(GtnUIFrameworkComponentValueSetter gtnUIFrameworkSetter) {
		this.gtnUIFrameworkSetter = gtnUIFrameworkSetter;
	}

}
