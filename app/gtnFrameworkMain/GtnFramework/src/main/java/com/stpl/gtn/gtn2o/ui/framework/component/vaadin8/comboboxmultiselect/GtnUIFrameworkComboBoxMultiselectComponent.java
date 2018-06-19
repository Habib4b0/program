
/* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.*/

package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.comboboxmultiselect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
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
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

public class GtnUIFrameworkComboBoxMultiselectComponent implements GtnUIFrameworkComponent {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkComboBoxMultiselectComponent.class);

	@SuppressWarnings("rawtypes")
	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Enter buildVaadinComponent method");
		ComboBoxMultiselect comboBoxMultiSelect = new ComboBoxMultiselect();
		comboBoxMultiSelect.setCaption(componentConfig.getComponentName());
		comboBoxMultiSelect.showSelectAllButton(true);
		comboBoxMultiSelect.showClearButton(true);
		loadStyles(comboBoxMultiSelect, componentConfig.getComponentStyle());

		final GtnUIFrameworkCheckedComboBoxConfig checkedComboBoxConfig = componentConfig.getGtnCheckedComboboxConfig();
		GtnUIFrameworkWebServiceClient wsClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWsRequest = new GtnWsGeneralRequest();
		generalWsRequest.setComboBoxType(checkedComboBoxConfig.getCheckedComboBoxType());
		wsRequest.setGtnWsGeneralRequest(generalWsRequest);
		addblurListener(componentConfig, comboBoxMultiSelect);

		if (checkedComboBoxConfig.getLoadingUrl() != null) {
			GtnUIFrameworkWebserviceComboBoxResponse response = wsClient
					.callGtnWebServiceUrl(checkedComboBoxConfig.getLoadingUrl(), wsRequest,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceComboBoxResponse();
			if (response.getItemValueList() != null) {
				List idList = new ArrayList<>(response.getItemCodeList());
				List<String> valueList = new ArrayList<>(response.getItemValueList());
				String defaultValue = checkedComboBoxConfig.getDefaultValue() != null
						? String.valueOf(checkedComboBoxConfig.getDefaultValue())
						: "-Select Values-";
				idList.add(0, "0");
				valueList.add(0, defaultValue);
				comboBoxMultiSelect.setValue(new HashSet<>(Arrays.asList(defaultValue)));
				comboBoxMultiSelect.setItems(idList);
				comboBoxMultiSelect.setItemCaptionGenerator(item -> valueList.get(idList.indexOf(item)));
			}
			return comboBoxMultiSelect;
		}

		if (checkedComboBoxConfig.getItemValueList() != null) {
			if (checkedComboBoxConfig.getItemCodeList() != null) {
				comboBoxMultiSelect.setItems(checkedComboBoxConfig.getItemCodeList());
				comboBoxMultiSelect.setItemCaptionGenerator(item -> checkedComboBoxConfig.getItemValueList()
						.get(checkedComboBoxConfig.getItemCodeList().indexOf(item)));
				return comboBoxMultiSelect;
			}
			comboBoxMultiSelect.setItems(checkedComboBoxConfig.getItemValueList());
		}

		return comboBoxMultiSelect;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(dependentComponentId,
				componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, comboComponentConfig.getSourceViewId(),
				comboComponentConfig);
	}

	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			String sourceViewId, Object reloadInput) {

		gtnLogger.info("Triggered reload with reload input " + reloadInput + "--Action--" + action);
		GtnUIFrameworkComponentConfig comboComponentConfig = getComboBoxComponentConfig(dependentComponentId,
				componentId);
		reloadComboBoxComponent(dependentComponentId, componentId, reloadInput, sourceViewId, comboComponentConfig);

	}

	private GtnUIFrameworkComponentConfig getComboBoxComponentConfig(String dependentComponentId, String componentId) {
		AbstractComponent abstractComboComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(dependentComponentId,
				componentId);
		GtnUIFrameworkComponentData comboComponentData = (GtnUIFrameworkComponentData) abstractComboComponent.getData();
		return comboComponentData.getCurrentComponentConfig();

	}

	@SuppressWarnings("unchecked")
	private void reloadComboBoxComponent(String dependentComponentId, String componentId, Object reloadInput,
			String sourceViewId, GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Triggered reload with reload input " + reloadInput);
		List<Object> comboBoxRequestInputList = (List<Object>) reloadInput;
		String simpleComboBoxReload = String.valueOf(comboBoxRequestInputList.get(0));
		if (!"simpleReload".equals(simpleComboBoxReload)) {
			if (componentConfig.getReloadLogicActionClassName() != null) {
				try {
					GtnUIFrameworkClassLoader comboBoxClassLoader = new GtnUIFrameworkClassLoader();
					GtnUIFrameWorkAction comboBoxCustomAction = (GtnUIFrameWorkAction) comboBoxClassLoader
							.loadDynamicClass(componentConfig.getReloadLogicActionClassName());
					GtnUIFrameWorkActionConfig comboBoxActionConfig = new GtnUIFrameWorkActionConfig();
					if (componentConfig.getReloadActionConfig() != null) {
						comboBoxActionConfig = componentConfig.getReloadActionConfig();
					}
					comboBoxActionConfig.addActionParameter(reloadInput);
					comboBoxCustomAction.doAction(sourceViewId + "_" + componentConfig.getComponentId(),
							comboBoxActionConfig);
				} catch (GtnFrameworkGeneralException ex) {
					gtnLogger.error(componentId, ex);
				}
				return;
			}

		} else {
			comboBoxRequestInputList.set(0, "");
		}
		GtnUIFrameworkComboBoxConfig comboboxComponentConfig = componentConfig.getGtnComboboxConfig();

		ComboBoxMultiselect vaadinMultiSelectComponent = (ComboBoxMultiselect) GtnUIFrameworkGlobalUI
				.getVaadinComponent(dependentComponentId, componentId);
		vaadinMultiSelectComponent.setItems(new ArrayList<>());

		vaadinMultiSelectComponent = fillComboBox(comboboxComponentConfig, comboBoxRequestInputList, sourceViewId,
				vaadinMultiSelectComponent);
		gtnLogger.info("Reloaded vaadin combobox" + vaadinMultiSelectComponent);

	}

	private ComboBoxMultiselect fillComboBox(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId,
			ComboBoxMultiselect vaadinMultiSelectComponent) {
		ComboBoxMultiselect vaadinMultiSelect = vaadinMultiSelectComponent;
		GtnUIFrameworkWebserviceComboBoxResponse response = getResponseFromSource(comboboxConfig,
				comboBoxWhereClauseParamList, sourceViewId);
		try {
			if (comboboxConfig.getItemValues() != null) {
				vaadinMultiSelect.setItems(comboboxConfig.getItemValues());
				if (comboboxConfig.getItemCaptionValues() != null) {

					vaadinMultiSelect = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemCaptionValues(), vaadinMultiSelect);
				} else {
					vaadinMultiSelect = fillDataAndCaption(comboboxConfig, comboboxConfig.getItemValues(),
							comboboxConfig.getItemValues(), vaadinMultiSelect);
				}
				return vaadinMultiSelect;
			}

			vaadinMultiSelect = fillComboboxFromResponse(response, comboboxConfig, vaadinMultiSelect);
		} catch (Exception e) {
			gtnLogger.error("Unable to fill combobox", e);
		}
		return vaadinMultiSelect;
	}

	private ComboBoxMultiselect fillComboboxFromResponse(GtnUIFrameworkWebserviceComboBoxResponse response,
			GtnUIFrameworkComboBoxConfig comboboxConfig, ComboBoxMultiselect vaadinMultiSelectComponent) {
		ComboBoxMultiselect vaadinMultiSelect = vaadinMultiSelectComponent;
		if (response != null && response.getItemCodeList() != null && !response.getItemCodeList().isEmpty()) {
			if (comboboxConfig.isIntegerItemCode()) {
				List<Integer> integerList = new ArrayList<>();
				for (String str : response.getItemCodeList()) {
					integerList.add(Integer.valueOf(str));
				}
				vaadinMultiSelect = fillDataAndCaption(comboboxConfig, integerList, response.getItemValueList(),
						vaadinMultiSelect);
			} else {
				vaadinMultiSelect = fillDataAndCaption(comboboxConfig, response.getItemCodeList(),
						response.getItemValueList(), vaadinMultiSelect);
			}
			return vaadinMultiSelect;
		}
		return vaadinMultiSelect;
	}

	@SuppressWarnings("unchecked")
	private ComboBoxMultiselect fillDataAndCaption(GtnUIFrameworkComboBoxConfig comboboxConfig, List itemCodeList,
			List<String> itemValueList, ComboBoxMultiselect vaadinMultiSelect) {

		try {
			List valueList = new ArrayList<>(itemCodeList);
			List<String> captionList = new ArrayList<>(itemValueList);
			vaadinMultiSelect.setItems(valueList);
			vaadinMultiSelect.setItemCaptionGenerator(item -> captionList.get(valueList.indexOf(item)));
			if (!comboboxConfig.isHasDefaultValue()) {
				String defaultValue = comboboxConfig.getDefaultValue() != null
						? String.valueOf(comboboxConfig.getDefaultValue())
						: GtnFrameworkCommonStringConstants.SELECT_ONE;
				valueList.add(0, 0);
				captionList.add(0, defaultValue);
				vaadinMultiSelect.setValue(new HashSet<>(Arrays.asList(defaultValue)));
			} else {
				for (int i = 0; i < captionList.size(); i++) {
					if (comboboxConfig.getDefaultDesc().equals(captionList.get(i))) {
						vaadinMultiSelect.setValue(new HashSet<>(Arrays.asList(valueList.get(i))));
						break;
					}
				}
			}
			gtnLogger.info("Fill Data And Caption to the Component" + vaadinMultiSelect);
			return vaadinMultiSelect;
		} catch (Exception e) {
			gtnLogger.error("Error message", e);
		}
		return null;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromSource(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList, String sourceViewId) {

		GtnUIFrameworkWebserviceComboBoxResponse comboboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();

		if ((GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SERVICE) == (comboboxConfig.getSourceType())) {
			return getResponseFromService(comboboxConfig, comboBoxWhereClauseParamList);
		}

		GtnUIFrameworkBaseComponent comboboxBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(comboboxConfig.getSourceComboboxId(), sourceViewId);

		if (comboboxBaseComponent == null) {
			return comboboxResponse;
		}
		return comboboxResponse;
	}

	private GtnUIFrameworkWebserviceComboBoxResponse getResponseFromService(GtnUIFrameworkComboBoxConfig comboboxConfig,
			List<Object> comboBoxWhereClauseParamList) {
		GtnUIFrameworkWebserviceComboBoxResponse comboboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		GtnUIFrameworkWebServiceClient comboboxWsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest comboboxRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest comboboxGeneralWSRequest = new GtnWsGeneralRequest();
		comboboxGeneralWSRequest.setComboBoxType(comboboxConfig.getComboBoxType());
		if (null != comboBoxWhereClauseParamList) {
			comboboxGeneralWSRequest.setComboBoxWhereclauseParamList(comboBoxWhereClauseParamList);
		}

		comboboxRequest.setGtnWsGeneralRequest(comboboxGeneralWSRequest);
		if (comboboxConfig.getLoadingUrl() != null) {
			try {
				comboboxResponse = comboboxWsclient
						.callGtnWebServiceUrl(comboboxConfig.getLoadingUrl(), comboboxConfig.getModuleName(),
								comboboxRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
						.getGtnUIFrameworkWebserviceComboBoxResponse();
			} catch (Exception e) {
				gtnLogger.error("Error message", e);
			}
		}
		return comboboxResponse;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		return;
	}

	private void loadStyles(final Component checkedComboboxComponent, final List<String> styles) {
		if (styles != null) {
			for (String style : styles) {
				checkedComboboxComponent.addStyleName(style);
			}
		}
	}

	private void addblurListener(GtnUIFrameworkComponentConfig componentConfig,
			ComboBoxMultiselect vaadinComboBoxMultiSelect) {
		if (componentConfig.getGtnUIFrameWorkActionConfigList() != null
				&& !componentConfig.getGtnUIFrameWorkActionConfigList().isEmpty()) {
			vaadinComboBoxMultiSelect.addBlurListener(event -> {
				try {
					AbstractComponent component = (AbstractComponent) event.getComponent();
					GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
					GtnUIFrameworkComponentConfig blurComponentConfig = componentData.getCurrentComponentConfig();
					GtnUIFrameworkActionExecutor.executeActionList(componentData.getComponentIdInMap(),
							blurComponentConfig.getGtnUIFrameWorkActionConfigList());

				} catch (GtnFrameworkGeneralException e) {
					gtnLogger.error(e.getMessage(), e);
				}
			});
		}
	}

}
