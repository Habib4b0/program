package com.stpl.gtn.gtn2o.ui.framework.component.layout;

import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.vaadin.ui.AbstractLayout;

public class GtnUIFrameworkLayoutConfig {
	private GtnUIFrameworkLayoutType layoutType = GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT;
	private boolean spacing = true;
	private boolean margin = false;
	private int componentColumnSize;

	public GtnUIFrameworkLayoutConfig() {
		super();
	}

	public GtnUIFrameworkLayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(GtnUIFrameworkLayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public AbstractLayout returnVadinComponent() {
		return layoutType.getGtnUIFrameworkLayout().buildVaadinLayout(this);
	}

	public boolean isSpacing() {
		return spacing;
	}

	public void setSpacing(boolean spacing) {
		this.spacing = spacing;
	}

	public boolean isMargin() {
		return margin;
	}

	public void setMargin(boolean margin) {
		this.margin = margin;
	}

	public int getComponentColumnSize() {
		return componentColumnSize;
	}

	public void setComponentColumnSize(int componentColumnSize) {
		this.componentColumnSize = componentColumnSize;
	}

}
