package com.stpl.gtn.gtn2o.ws.report.bean;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;

public class GtnWsReportCustomViewBean {
	private GtnWsHierarchyType hierarchyType;
	private GtnUIFrameworkDataTable gridData;
	private GtnWsReportCustomViewDataBean customViewDataBean;
	private boolean isEdit;

	public GtnWsHierarchyType getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(GtnWsHierarchyType hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public GtnUIFrameworkDataTable getGridData() {
		return gridData;
	}

	public void setGridData(GtnUIFrameworkDataTable gridData) {
		this.gridData = gridData;
	}

	public GtnWsReportCustomViewDataBean getCustomViewDataBean() {
		return customViewDataBean;
	}

	public void setCustomViewDataBean(GtnWsReportCustomViewDataBean customViewDataBean) {
		this.customViewDataBean = customViewDataBean;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

}
