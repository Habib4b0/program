package com.stpl.gtn.gtn2o.ui.framework.component.combo;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkValueChangeListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;

public class GtnUIFrameworkComboboxComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboboxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {

		ComboBox vaadinComboBox = new ComboBox();
		if (componentConfig.getComponentName() != null && !componentConfig.getComponentName().isEmpty()) {
			vaadinComboBox.setCaption(componentConfig.getComponentName());
		}
		loadStyles(vaadinComboBox, componentConfig.getComponentStyle());

		vaadinComboBox.setEnabled(componentConfig.isEnable());
		
		try {

			final GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();
			vaadinComboBox.setReadOnly(comboboxConfig.isReadOnly());
			vaadinComboBox.setRequired(comboboxConfig.isRequired());
			vaadinComboBox.setRequiredError(comboboxConfig.getRequiredMessage());
			fillComboBox(comboboxConfig, vaadinComboBox, null, componentConfig.getSourceViewId());

			vaadinComboBox.setVisible(componentConfig.isVisible());

			if (comboboxConfig.isHasDefaultValue()) {
				for (Object item : vaadinComboBox.getItemIds()) {
					if (vaadinComboBox.getItemCaption(item).equals(comboboxConfig.getDefaultDesc())) {
						vaadinComboBox.select(item);
						break;
					}
				}
			}
			if (comboboxConfig.getValueChangeListenerClassName() != null) {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				vaadinComboBox.addValueChangeListener((ValueChangeListener) (classLoader
						.loadDynamicClass(comboboxConfig.getValueChangeListenerClassName())));
			}

			if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
					&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
				vaadinComboBox.addValueChangeListener(GtnUIFrameworkValueChangeListener.getListener());
			}

			if (comboboxConfig.isNewItemsAllowed()) {
				vaadinComboBox.setNewItemsAllowed(true);
				vaadinComboBox.setNewItemHandler(new GtnNewItemHandler(componentConfig, vaadinComboBox));
			}

		} catch (GtnFrameworkGeneralException ex) {

			gtnLogger.error(ex.getMessage(), ex);

		}

		return vaadinComboBox;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		GtnUIFrameworkComponentConfig componentConfig = getComboBoxComponentConfig(dependentComponentId, componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, componentConfig.getSourceViewId(),
				componentConfig);

	}

	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			String sourceViewId, Object reloadInput) {

		gtnLogger.info("Triggered reload with reload input " + reloadInput + "--Action--" + action);
		GtnUIFrameworkComponentConfig componentConfig = getComboBoxComponentConfig(dependentComponentId, componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, sourceViewId, componentConfig);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfig(String dependentComponentId, String componentId) {
		AbstractComponent component = GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		return componentData.getCurrentComponentConfig();

	}

	@SuppressWarnings("unchecked")
	private void reloadComboBoxComponent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Triggered reload with reload input " + reloadInput);
		List<Object> requestInputList = (List<Object>) reloadInput;
		String simpleReload = String.valueOf(requestInputList.get(0));
		if (!"simpleReload".equals(simpleReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction customAction = (GtnUIFrameWorkAction) classLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
					actionConfig.addActionParameter(reloadInput);
					customAction.doAction(componentId, actionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			requestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();

		ComboBox vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId,
				componentId);
		vaadinComboBox.removeAllItems();

		fillComboBox(comboboxConfig, vaadinComboBox, requestInputList, sourceViewId);
	}

	private void fillComboBox(GtnUIFrameworkComboBoxConfig comboboxConfig, ComboBox vaadinComboBox,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId) {

		GtnUIFrameworkWebserviceComboBoxResponse response = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		vaadinComboBox.removeAllItems();

		if (!comboboxConfig.isHasDefaultValue()) {
			String defaultValue = comboboxConfig.getDefaultValue() != null
					? String.valueOf(comboboxConfig.getDefaultValue()) : GtnFrameworkCommonStringConstants.SELECT_ONE;
			vaadinComboBox.setNullSelectionAllowed(Boolean.TRUE);
			vaadinComboBox.setNullSelectionItemId(0);
			vaadinComboBox.addItem(0);
			vaadinComboBox.setItemCaption(0, defaultValue);
			vaadinComboBox.select(0);
		}
		fillComboboxFromResponse(response, comboboxConfig, vaadinComboBox);

		if (comboboxConfig.getItemValues() != null) {
			vaadinComboBox.addItems(comboboxConfig.getItemValues());
			if (comboboxConfig.getItemCaptionValues() != null) {

				fillDataAndCaption(vaadinComboBox, comboboxConfig.getItemValues(),
						comboboxConfig.getItemCaptionValues());
			} else {
				fillDataAndCaption(vaadinComboBox, comboboxConfig.getItemValues(), comboboxConfig.getItemValues());
			}
		}

		if (comboboxConfig.isHasDefaultValue()) {
			vaadinComboBox.setNullSelectionAllowed(Boolean.FALSE);
			vaadinComboBox.setValue(vaadinComboBox.getItemIds().iterator().next());
		}

	}

	private void fillComboboxFromResponse(GtnUIFrameworkWebserviceComboBoxResponse response,
			GtnUIFrameworkComboBoxConfig comboboxConfig, ComboBox vaadinComboBox) {
		if (response != null && response.getItemCodeList() != null && !response.getItemCodeList().isEmpty()) {
			if (comboboxConfig.isIntegerItemCode()) {
				List<Integer> integerList = new ArrayList<>();
				for (String str : response.getItemCodeList()) {
					integerList.add(Integer.parseInt(str));
				}
				fillDataAndCaption(vaadinComboBox, integerList, response.getItemValueList());
			} else {
				fillDataAndCaption(vaadinComboBox, response.getItemCodeList(), response.getItemValueList());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void fillDataAndCaption(ComboBox vaadinComboBox, List valueList, List captionList) {
		for (int valueIndex = 0; valueIndex < valueList.size(); valueIndex++) {
			vaadinComboBox.addItem(valueList.get(valueIndex));
			vaadinComboBox.setItemCaption(valueList.get(valueIndex), String.valueOf(captionList.get(valueIndex)));
		}
	}

	private void loadStyles(final Component component, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				component.addStyleName(style);

			}
		}

	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromSource(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId) {

		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();

		if (comboboxConfig.getSourceType().equals(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE)) {
			return getResponseFromService(comboboxConfig, comboBoxWhereClauseParamList);
		}

		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId);

		if (baseComponent == null) {
			return response;
		}
		AbstractComponent sourceComponent = baseComponent.getComponent();
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) sourceComponent.getData();
		GtnUIFrameworkComponentConfig sourceComponentConfig = componentData.getCurrentComponentConfig();

		if (comboboxConfig.getSourceType().equals(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_COMBOBOX)) {

			return getResponseFromSourceCombobox(comboboxConfig, sourceComponentConfig.getGtnComboboxConfig(),
					sourceViewId);
		}

		if (comboboxConfig.getSourceType().equals(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX)) {
			return getResponseFromSubsetOfSourceCombobox(comboboxConfig, sourceComponentConfig.getGtnComboboxConfig(),
					sourceViewId);

		}
		return response;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromService(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList) {
		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(comboboxConfig.getComboBoxType());
		if (null != comboBoxWhereClauseParamList) {
			generalWSRequest.setComboBoxWhereclauseParamList(comboBoxWhereClauseParamList);
		}

		request.setGtnWsGeneralRequest(generalWSRequest);
		if (comboboxConfig.getLoadingUrl() != null) {
			response = wsclient
					.callGtnWebServiceUrl(comboboxConfig.getLoadingUrl(), request,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceComboBoxResponse();
		}
		return response;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromSourceCombobox(
			GtnUIFrameworkComboBoxConfig comboboxConfig, GtnUIFrameworkComboBoxConfig sourceComponentConfig,
			String sourceViewId) {
		ComboBox sourceCombobox = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId).getComponent();
		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
		List<?> itemIds = (List<?>) sourceCombobox.getItemIds();

		int startIndex;
		if (sourceComponentConfig.isHasDefaultValue()) {
			startIndex = 0;
		} else {
			startIndex = 1;
		}

		this.fillSubSetComboBox(startIndex, itemIds.size(), response, sourceCombobox);
		return response;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromSubsetOfSourceCombobox(
			GtnUIFrameworkComboBoxConfig comboboxConfig, GtnUIFrameworkComboBoxConfig sourceComponentConfig,
			String sourceViewId) {
		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
		ComboBox sourceCombobox = (ComboBox) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId).getComponent();
		List<?> itemIdList = (List<?>) sourceCombobox.getItemIds();
		Object currentValue = sourceCombobox.getValue();
		if (currentValue != null) {
			int startIndex = 0;
			int endIndex = 0;
			if (comboboxConfig.getSubsetType().equals(GtnUIFrameworkComboboxSourceSubsetType.BEFORE_SELECTED)) {
				if (sourceComponentConfig.isHasDefaultValue()) {
					startIndex = 0;
				} else {
					startIndex = 1;
				}
				endIndex = itemIdList.indexOf(sourceCombobox.getValue());
				if (!comboboxConfig.isInclusionOfSelected()) {
					endIndex--;
				}
			}
			if (comboboxConfig.getSubsetType().equals(GtnUIFrameworkComboboxSourceSubsetType.AFTER_SELECTED)) {
				startIndex = itemIdList.indexOf(sourceCombobox.getValue());
				endIndex = itemIdList.size() - 1;
				endIndex = getLastIndexForSubsetOfSourceCombobox(comboboxConfig, endIndex);
				startIndex = getStartIndexForSubsetOfSourceCombobox(comboboxConfig, startIndex);

			}

			this.fillSubSetComboBox(startIndex, endIndex + 1, response, sourceCombobox);

		}
		return response;
	}

	private void fillSubSetComboBox(int startIndex, int endIndex, GtnUIFrameworkWebserviceComboBoxResponse response,
			ComboBox sourceCombobox) {
		List<?> itemIdList = (List<?>) sourceCombobox.getItemIds();
		for (int i = startIndex; i < endIndex; i++) {
			response.addItemCodeList(String.valueOf(itemIdList.get(i)));
			response.addItemValueList(sourceCombobox.getItemCaption(itemIdList.get(i)));
		}

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		ComboBox vaadinComboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		vaadinComboBox.setEnabled(componentConfig.isEnable());
		GtnUIFrameworkComboBoxConfig comboboxConfig = componentConfig.getGtnComboboxConfig();
		vaadinComboBox.setRequired(comboboxConfig.isRequired());
		vaadinComboBox.setRequiredError(comboboxConfig.getRequiredMessage());
		vaadinComboBox.setVisible(componentConfig.isVisible());
		if (componentConfig.getGtnComboboxConfig().isHasDefaultValue()) {
			for (Object item : vaadinComboBox.getItemIds()) {
				if (vaadinComboBox.getItemCaption(item)
						.equals(componentConfig.getGtnComboboxConfig().getDefaultDesc())) {
					vaadinComboBox.select(item);
					break;
				}
			}
			return;
		}
		vaadinComboBox.setValue(0);
	}

	class GtnNewItemHandler implements NewItemHandler {

		private static final long serialVersionUID = 1L;

		private GtnUIFrameworkComponentConfig componentConfig;
		private AbstractSelect vaadinComboBox;

		public GtnNewItemHandler(GtnUIFrameworkComponentConfig componentConfig, AbstractSelect vaadinComboBox) {
			this.componentConfig = componentConfig;
			this.vaadinComboBox = vaadinComboBox;
		}

		@Override
		public void addNewItem(String newItem) {
			GtnUIFrameworkComboBoxConfig gtnComboboxConfig = componentConfig.getGtnComboboxConfig();
			if (gtnComboboxConfig.getNewItemHandlerActionConfig() == null) {
				/**
				 * New Items will have negative ID's
				 */

				try {
					Double value = Double.parseDouble(newItem);
					gtnLogger.debug("Value entered---" + value);
					int newItemId = -(vaadinComboBox.getItemIds().size() + 2);
					vaadinComboBox.addItem(newItemId);
					vaadinComboBox.setItemCaption(newItemId, newItem);
					vaadinComboBox.select(newItemId);
				} catch (NumberFormatException e) {
					vaadinComboBox.select(0);
				}
			} else {
				try {
					gtnComboboxConfig.getNewItemHandlerActionConfig().addActionParameter(newItem);
					GtnUIFrameworkActionExecutor.executeSingleAction(componentConfig.getComponentId(),
							gtnComboboxConfig.getNewItemHandlerActionConfig());
					gtnComboboxConfig.getNewItemHandlerActionConfig().getActionParameterList().remove(newItem);
				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getErrorMessage());
				}
			}
		}

	}

	public int getLastIndexForSubsetOfSourceCombobox(GtnUIFrameworkComboBoxConfig comboboxConfig, int endIndex) {
		int endIndexTemp = endIndex;
		if (comboboxConfig.isExclusionOfLast()) {
			endIndexTemp--;
		}
		return endIndexTemp;
	}

	public int getStartIndexForSubsetOfSourceCombobox(GtnUIFrameworkComboBoxConfig comboboxConfig, int startIndex) {
		int startIndexTemp = startIndex;
		if (!comboboxConfig.isInclusionOfSelected()) {
			startIndexTemp++;
		}
		return startIndexTemp;
	}

}
