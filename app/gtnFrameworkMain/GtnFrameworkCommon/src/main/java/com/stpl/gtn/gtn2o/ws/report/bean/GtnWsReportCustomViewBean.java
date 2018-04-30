package com.stpl.gtn.gtn2o.ws.report.bean;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;

public class GtnWsReportCustomViewBean {
	private GtnWsHierarchyType hierarchyType;
	private GtnUIFrameworkDataTable gridData;
	private GtnWsCustomTreeData customTreeData;
	private String customViewName;

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

	public GtnWsCustomTreeData getCustomTreeData() {
		return customTreeData;
	}

	public void setCustomTreeData(GtnWsCustomTreeData customTreeData) {
		this.customTreeData = customTreeData;
	}

	public String getCustomViewName() {
		return customViewName;
	}

	public void setCustomViewName(String customViewName) {
		this.customViewName = customViewName;
	}

}
