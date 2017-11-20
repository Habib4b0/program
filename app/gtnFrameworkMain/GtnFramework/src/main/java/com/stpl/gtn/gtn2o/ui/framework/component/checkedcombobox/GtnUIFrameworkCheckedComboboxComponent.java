package com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox;

import java.util.List;

import org.asi.ui.custommenubar.CustomMenuBar;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkClassLoader;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;

public class GtnUIFrameworkCheckedComboboxComponent implements GtnUIFrameworkComponent {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCheckedComboboxComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		logger.info("Enter buildVaadinComponent Method ");
		CustomMenuBar customMenuBar = new CustomMenuBar();
		customMenuBar.setCaption(componentConfig.getComponentName());
		loadStyles(customMenuBar, componentConfig.getComponentStyle());

		try {
			GtnUIFrameworkCheckedComboBoxConfig checkedComboboxConfig = componentConfig.getGtnCheckedComboboxConfig();
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setComboBoxType(checkedComboboxConfig.getCheckedComboBoxType());
			request.setGtnWsGeneralRequest(generalWSRequest);
			if (checkedComboboxConfig.getBlurListenerClassName() != null) {
				GtnUIFrameworkClassLoader classLoader = new GtnUIFrameworkClassLoader();
				customMenuBar.addSubMenuCloseListener((CustomMenuBar.SubMenuCloseListener) (classLoader
						.loadDynamicClass(checkedComboboxConfig.getBlurListenerClassName())));

			}
			GtnUIFrameworkWebserviceComboBoxResponse response = wsclient
					.callGtnWebServiceUrl(checkedComboboxConfig.getLoadingUrl(), request,
							GtnUIFrameworkGlobalUI.getGtnWsSecurityToken())
					.getGtnUIFrameworkWebserviceComboBoxResponse();
			if (response.getItemValueList() != null) {
				CustomMenuBar.CustomMenuItem customMenuItem = customMenuBar
						.addItem(checkedComboboxConfig.getDefaultValue(), null);
				CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[response.getItemValueList()
						.size()];
				for (int valueIndex = 0; valueIndex < response.getItemValueList().size(); valueIndex++) {
					customItem[valueIndex] = customMenuItem.addItem(response.getItemValueList().get(valueIndex), null);
					customItem[valueIndex].setCheckable(true);
					customItem[valueIndex].setItemClickable(true);
					customItem[valueIndex].setItemClickNotClosable(true);
				}
				logger.info("End buildVaadinComponent Method ");

				return customMenuBar;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("End buildVaadinComponent Method ");
		return customMenuBar;
	}

	private void loadStyles(final Component checkedComboboxComponent, final List<String> styles) {
		if (styles != null) {
			for (String style : styles) {
				checkedComboboxComponent.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId,
			String checkedComboboxComponentId, Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig checkedComboboxComponentConfig) {
		return;
	}

}
