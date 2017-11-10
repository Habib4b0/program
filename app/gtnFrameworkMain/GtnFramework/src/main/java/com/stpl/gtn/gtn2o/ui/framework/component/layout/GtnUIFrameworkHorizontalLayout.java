
package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.HorizontalLayout;

public class GtnUIFrameworkHorizontalLayout implements GtnUIFrameworkLayout {

	@Override
	public AbstractLayout buildVaadinLayout(GtnUIFrameworkLayoutConfig layoutConfig) {
		return new HorizontalLayout();
	}

}
