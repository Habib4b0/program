/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.type;

import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkCSSLayout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkCol1Layout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkCol2Layout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkCol3Layout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkCol4Layout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkHorizontalLayout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayout;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkVerticalLayout;

/**
 *
 * @author Harlin.Mani
 */
public enum GtnUIFrameworkLayoutType {
	HORIZONTAL_LAYOUT(new GtnUIFrameworkHorizontalLayout()), VERTICAL_LAYOUT(
			new GtnUIFrameworkVerticalLayout()), COL1_LAYOUT(new GtnUIFrameworkCol1Layout()), COL2_LAYOUT(
					new GtnUIFrameworkCol2Layout()), COL3_LAYOUT(new GtnUIFrameworkCol3Layout()), COL4_LAYOUT(
							new GtnUIFrameworkCol4Layout()), CSS_LAYOUT(new GtnUIFrameworkCSSLayout());

	private final GtnUIFrameworkLayout gtnUIFrameworkLayout;

	GtnUIFrameworkLayoutType(GtnUIFrameworkLayout gtnUIFrameworkLayout) {
		this.gtnUIFrameworkLayout = gtnUIFrameworkLayout;
	}

	public GtnUIFrameworkLayout getGtnUIFrameworkLayout() {
		return gtnUIFrameworkLayout;
	}

}
