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

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkForecastReturnModeChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnModeChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {

			gtnLogger.info("inside GtnFrameworkForecastReturnValidationAction");
			List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();

			String namespace = actionParameter.get(1) + GtnFrameworkForecastConstantCommon.UNDERSCORE;
			String optionGroupComponentId = namespace + actionParameter.get(2);
			String optionGroupData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(optionGroupComponentId, componentId).getStringFromField();

			if (GtnFrameworkForecastConstantCommon.SEARCH_MODE.equals(optionGroupData)) {
				getSearchModeValidation(namespace, actionParameter, componentId);
			}

			if (GtnFrameworkForecastConstantCommon.ADD_MODE.equals(optionGroupData)) {
				getAddModeValidation(namespace, actionParameter, componentId);
			}

		} catch (GtnFrameworkGeneralException exception) {
			gtnLogger.error("Error in doAction Method", exception);
			throw exception;
		} catch (Exception ex) {
			gtnLogger.error("Error in doAction Method", ex);
			throw new GtnFrameworkGeneralException("Error in doAction  Method", ex);
		}

	}

	/**
	 * @param namespace
	 * @param actionParameter
	 * @throws GtnFrameworkGeneralException
	 */

	private void getAddModeValidation(String namespace, List<Object> actionParameter, String sourceComponentId)
			throws GtnFrameworkGeneralException {

		try {

			AbstractComponent abstractComponentFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(3), sourceComponentId)
					.getComponent();
			ComboBox componentComboboxFrom = (ComboBox) abstractComponentFrom;

			@SuppressWarnings("unchecked")
			List<Object> itemIdList = (List<Object>) componentComboboxFrom.getItemIds();

			List<Integer> componentComboboxFromId = new ArrayList<>();
			List<String> componentComboboxFromCaption = new ArrayList<>();

			for (int i = 0; i < itemIdList.size(); i++) {
				if (!GtnFrameworkForecastConstantCommon.SELECT_ONE
						.equals(componentComboboxFrom.getItemCaption(itemIdList.get(i)))) {
					componentComboboxFromId.add((Integer) itemIdList.get(i));
					componentComboboxFromCaption.add(componentComboboxFrom.getItemCaption(itemIdList.get(i)));
				}
			}

			componentComboboxFrom.removeAllItems();
			componentComboboxFrom.setNullSelectionAllowed(Boolean.FALSE);

			for (int i = 0; i < componentComboboxFromId.size(); i++) {
				componentComboboxFrom.addItem(componentComboboxFromId.get(i));
				componentComboboxFrom.setItemCaption(componentComboboxFromId.get(i),
						componentComboboxFromCaption.get(i));
			}
			if (!itemIdList.isEmpty()) {
				componentComboboxFrom.setNullSelectionItemId(itemIdList.get(0));
				componentComboboxFrom.setValue(Integer.parseInt(String.valueOf(itemIdList.get(0))));
			}

			AbstractComponent abstractComponentTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(4), sourceComponentId)
					.getComponent();
			ComboBox componentComboboxTo = (ComboBox) abstractComponentTo;
			componentComboboxTo.removeAllItems();
			componentComboboxTo.addItem(componentComboboxFromId.get(componentComboboxFromId.size() - 1));
			componentComboboxTo.setItemCaption(componentComboboxFromId.get(componentComboboxFromId.size() - 1),
					componentComboboxFromCaption.get(componentComboboxFromCaption.size() - 1));
			componentComboboxTo.setValue(componentComboboxFromId.get(componentComboboxFromId.size() - 1));
			componentComboboxTo.setEnabled(false);

			/**
			 * BUTTON ENABLE/DISABLE LOGIC
			 */
			AbstractComponent abstractComponentGenerateButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(5), sourceComponentId)
					.getComponent();
			abstractComponentGenerateButton.setEnabled(true);

			AbstractComponent abstractComponentSeacrhButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(6), sourceComponentId)
					.getComponent();
			abstractComponentSeacrhButton.setEnabled(false);

			AbstractComponent abstractComponentResetButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(7), sourceComponentId)
					.getComponent();
			abstractComponentResetButton.setEnabled(true);

			AbstractComponent abstractComponentSaveView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(8), sourceComponentId)
					.getComponent();
			abstractComponentSaveView.setEnabled(true);

			AbstractComponent abstractComponentDeleteView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(9), sourceComponentId)
					.getComponent();
			abstractComponentDeleteView.setEnabled(false);

			AbstractComponent abstractComponentCrudReset = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(10), sourceComponentId)
					.getComponent();
			abstractComponentCrudReset.setEnabled(false);

			AbstractComponent abstractComponentCrudEdit = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(11), sourceComponentId)
					.getComponent();
			abstractComponentCrudEdit.setEnabled(false);

			AbstractComponent abstractComponentCrudView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(12), sourceComponentId)
					.getComponent();
			abstractComponentCrudView.setEnabled(false);

			AbstractComponent abstractComponentCrudDelete = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(13), sourceComponentId)
					.getComponent();
			abstractComponentCrudDelete.setEnabled(false);

		} catch (Exception ex) {
			gtnLogger.error("Error in getAddModeValidation Method", ex);
			throw new GtnFrameworkGeneralException("Error in getAddModeValidation  Method", ex);
		}
	}

	private void getSearchModeValidation(String namespace, List<Object> actionParameter, String sourceComponentId)
			throws GtnFrameworkGeneralException {

		try {

			AbstractComponent abstractComponentFrom = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(3), sourceComponentId)
					.getComponent();
			ComboBox componentComboboxFrom = (ComboBox) abstractComponentFrom;

			@SuppressWarnings("unchecked")
			List<Object> itemIdList = (List<Object>) componentComboboxFrom.getItemIds();

			List<Integer> componentComboboxFromId = new ArrayList<>();
			List<String> componentComboboxFromCaption = new ArrayList<>();

			for (int i = 0; i < itemIdList.size(); i++) {
				if (!GtnFrameworkForecastConstantCommon.SELECT_ONE
						.equals(componentComboboxFrom.getItemCaption(itemIdList.get(i)))) {
					componentComboboxFromId.add((Integer) itemIdList.get(i));
					componentComboboxFromCaption.add(componentComboboxFrom.getItemCaption(itemIdList.get(i)));
				}
			}

			componentComboboxFrom.removeAllItems();
			componentComboboxFrom.setNullSelectionAllowed(Boolean.TRUE);
			componentComboboxFrom.setNullSelectionItemId(0);
			componentComboboxFrom.addItem(0);
			componentComboboxFrom.setItemCaption(0, GtnFrameworkForecastConstantCommon.SELECT_ONE);
			componentComboboxFrom.select(0);

			for (int i = 0; i < componentComboboxFromId.size(); i++) {
				componentComboboxFrom.addItem(componentComboboxFromId.get(i));
				componentComboboxFrom.setItemCaption(componentComboboxFromId.get(i),
						componentComboboxFromCaption.get(i));
			}

			AbstractComponent abstractComponentTo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(4), sourceComponentId)
					.getComponent();
			ComboBox componentComboboxTo = (ComboBox) abstractComponentTo;
			componentComboboxTo.removeAllItems();
			componentComboboxTo.setNullSelectionAllowed(Boolean.TRUE);
			componentComboboxTo.setNullSelectionItemId(0);
			componentComboboxTo.addItem(0);
			componentComboboxTo.setItemCaption(0, GtnFrameworkForecastConstantCommon.SELECT_ONE);
			componentComboboxTo.setEnabled(true);

			/**
			 * BUTTON ENABLE/DISABLE LOGIC
			 */
			AbstractComponent abstractComponentGenerateButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(5), sourceComponentId)
					.getComponent();
			abstractComponentGenerateButton.setEnabled(false);

			AbstractComponent abstractComponentSeacrhButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(6), sourceComponentId)
					.getComponent();
			abstractComponentSeacrhButton.setEnabled(true);

			AbstractComponent abstractComponentResetButton = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(7), sourceComponentId)
					.getComponent();
			abstractComponentResetButton.setEnabled(true);

			AbstractComponent abstractComponentSaveView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(8), sourceComponentId)
					.getComponent();
			abstractComponentSaveView.setEnabled(false);

			AbstractComponent abstractComponentDeleteView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(9), sourceComponentId)
					.getComponent();
			abstractComponentDeleteView.setEnabled(false);

			AbstractComponent abstractComponentCrudReset = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(10), sourceComponentId)
					.getComponent();
			abstractComponentCrudReset.setEnabled(true);

			AbstractComponent abstractComponentCrudEdit = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(11), sourceComponentId)
					.getComponent();
			abstractComponentCrudEdit.setEnabled(true);

			AbstractComponent abstractComponentCrudView = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(12), sourceComponentId)
					.getComponent();
			abstractComponentCrudView.setEnabled(true);

			AbstractComponent abstractComponentCrudDelete = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(namespace + actionParameter.get(13), sourceComponentId)
					.getComponent();
			abstractComponentCrudDelete.setEnabled(true);

		} catch (Exception ex) {
			gtnLogger.error("Error in getSearchModeValidation Method", ex);
			throw new GtnFrameworkGeneralException("Error in getSearchModeValidation  Method", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
