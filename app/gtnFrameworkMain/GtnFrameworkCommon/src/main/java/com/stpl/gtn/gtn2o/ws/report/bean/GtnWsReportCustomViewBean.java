package com.stpl.gtn.gtn2o.ws.report.bean;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;

public class GtnWsReportCustomViewBean {
	private GtnWsHierarchyType hierarchyType;
	private GtnUIFrameworkDataTable gridData;

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

}
