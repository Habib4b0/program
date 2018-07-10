package com.stpl.gtn.gtn2o.ui.framework.component.grid.pagedgrid;

import com.vaadin.ui.TextField;

public class GtnFrameworkReadOnlyField extends TextField {

	public GtnFrameworkReadOnlyField() {
		super();
		this.setReadOnly(true);
	}
	
	@Override
    public void setEnabled(boolean enabled)
    {
        super.setEnabled(false);
    }
}
