package com.stpl.gtn.gtn2o.ui.framework.component.excelbutton;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentActionable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIGeneralButtonClickListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

public class GtnUIFrameworkExcelButtonComponent implements GtnUIFrameworkComponent, GtnUIFrameworkComponentActionable {

	@Override
	public AbstractComponent buildVaadinComponent(final GtnUIFrameworkComponentConfig componentConfig) {
		Button excelExport = new Button();
		excelExport.setWidth(GtnFrameworkCssConstants.PIXEL_65);
		excelExport.setStyleName(GtnFrameworkCssConstants.LINK);
		GtnUIFrameworkExcelButtonConfig excelButtonConfig = componentConfig.getGtnUIFrameworkExcelButtonConfig() == null
				? new GtnUIFrameworkExcelButtonConfig()
				: componentConfig.getGtnUIFrameworkExcelButtonConfig();
		excelExport.setIcon(new ThemeResource(excelButtonConfig.getIconUrl()));
		excelExport.setDescription(excelButtonConfig.getDescription());
		excelExport.setIconAlternateText(excelButtonConfig.getIconAlternateText());
		excelExport.setHtmlContentAllowed(excelButtonConfig.isHtmlContentAllowed());
		loadStyles(excelExport, componentConfig.getComponentStyle());
		excelExport.setEnabled(componentConfig.isEnable());
		excelExport.setVisible(componentConfig.isVisible());
		return excelExport;
	}

	@Override
	public void postCreateComponent(AbstractComponent component, final GtnUIFrameworkComponentConfig componentConfig) {
		Button vaadinButton = (Button) component;
		vaadinButton.addClickListener(GtnUIGeneralButtonClickListener.getListener());

	}

	private void loadStyles(final Component excelBtnComponent, final List<String> styles) {

		if (styles != null) {

			for (String style : styles) {

				excelBtnComponent.addStyleName(style);

			}
		}

	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;
	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig excelBtnComponentConfig) {
		AbstractComponent component = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId);
		component.setVisible(excelBtnComponentConfig.isVisible());
		component.setEnabled(excelBtnComponentConfig.isEnable());
	}
}
