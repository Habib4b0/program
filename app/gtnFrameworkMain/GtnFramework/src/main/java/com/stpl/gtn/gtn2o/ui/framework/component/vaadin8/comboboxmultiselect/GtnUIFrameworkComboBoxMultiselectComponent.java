
/* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.*/

package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.comboboxmultiselect;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

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
		if (checkedComboBoxConfig.getBlurListenerClassName() != null) {
			GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
			try {
				comboBoxMultiSelect.addBlurListener(
						(BlurListener) classLoader.loadDynamicClass(checkedComboBoxConfig.getBlurListenerClassName()));
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.info(e.getMessage());
			}
		}

		GtnUIFrameworkWebserviceComboBoxResponse response = wsClient
				.callGtnWebServiceUrl(checkedComboBoxConfig.getLoadingUrl(), wsRequest,
						GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
				.getGtnUIFrameworkWebserviceComboBoxResponse();
		if (response.getItemValueList() != null) {
			comboBoxMultiSelect.setItems((List<String>) response.getItemValueList());
			comboBoxMultiSelect.setValue(new HashSet<>(Arrays.asList(checkedComboBoxConfig.getDefaultValue())));
		}
		return comboBoxMultiSelect;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType actionType, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;
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

}
