package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

public class GtnUIFrameworkLayoutComponent extends AbstractComponent implements GtnUIFrameworkComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		AbstractLayout vaadinLayout = getLayout(componentConfig);
		vaadinLayout.setWidth(componentConfig.getComponentWidth());
		vaadinLayout.setHeight(componentConfig.getComponentHight());
		vaadinLayout.setVisible(componentConfig.isVisible());
		loadStyles(vaadinLayout, componentConfig.getComponentStyle());
		return vaadinLayout;
	}

	private AbstractLayout getLayout(GtnUIFrameworkComponentConfig conf) {
		GtnUIFrameworkLayoutConfig componentConfig = conf.getGtnLayoutConfig();
		return componentConfig.returnVadinComponent();
	}

	private void loadStyles(final Component component, final List<String> layoutStyles) {
		if (layoutStyles != null) {
			for (String style : layoutStyles) {
				component.addStyleName(style);
			}
		}
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String layoutComponentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		return;

	}

}
