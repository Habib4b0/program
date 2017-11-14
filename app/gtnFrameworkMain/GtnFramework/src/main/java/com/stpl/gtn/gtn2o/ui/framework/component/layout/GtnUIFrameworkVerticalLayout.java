/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkVerticalLayout implements GtnUIFrameworkLayout {

	@Override
	public AbstractLayout buildVaadinLayout(GtnUIFrameworkLayoutConfig layoutConfig) {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(layoutConfig.isMargin());
		verticalLayout.setSpacing(layoutConfig.isSpacing());
		return verticalLayout;
	}

}
