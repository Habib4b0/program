package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CssLayout;

public class GtnUIFrameworkCSSLayout implements GtnUIFrameworkLayout {

	@Override
	public AbstractLayout buildVaadinLayout(GtnUIFrameworkLayoutConfig layoutConfig) {
		CssLayout layout = new CssLayout();
		if (layoutConfig.getComponentColumnSize() > 0 && layoutConfig.getComponentColumnSize() <= 12) {
			layout.addStyleName("gtnFrameworkCol-" + layoutConfig.getComponentColumnSize());
		}
		return layout;
	}

}