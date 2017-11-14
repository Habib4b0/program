package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CssLayout;

public class GtnUIFrameworkCol2Layout implements GtnUIFrameworkLayout {

	@Override
	public AbstractLayout buildVaadinLayout(GtnUIFrameworkLayoutConfig layoutConfig) {
		CssLayout layout = new CssLayout();
		layout.addStyleName("gtnGrid-single-ln-layout");
		layout.addStyleName("no-margin");
		return layout;
	}

}
