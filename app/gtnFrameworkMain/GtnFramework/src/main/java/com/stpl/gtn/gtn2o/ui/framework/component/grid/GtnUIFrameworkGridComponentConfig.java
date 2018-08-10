package com.stpl.gtn.gtn2o.ui.framework.component.grid;

public class GtnUIFrameworkGridComponentConfig {

    private String[] gridColumnHeadersId;
    private String[] gridColumnHeadersName;

    public GtnUIFrameworkGridComponentConfig() {
        super();
    }

    public String[] getColumnHeadersId() {
        return gridColumnHeadersId;
    }

    public void setColumnHeadersId(String[] columnHeadersId) {
        this.gridColumnHeadersId = columnHeadersId;
	}

	public String[] getColumnHeadersName() {
		return gridColumnHeadersName;
	}

	public void setColumnHeadersName(String[] columnHeadersName) {
		this.gridColumnHeadersName = columnHeadersName;
	}

}
