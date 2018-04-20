package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnWsReportCustomViewBean {
	private GtnWsHierarchyType hierarchyType;
	private List<GtnWsRecordBean> bean;

	public GtnWsHierarchyType getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(GtnWsHierarchyType hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public List<GtnWsRecordBean> getBean() {
		return bean;
	}

	public void setBean(List<GtnWsRecordBean> bean) {
		this.bean = bean;
	}

}
