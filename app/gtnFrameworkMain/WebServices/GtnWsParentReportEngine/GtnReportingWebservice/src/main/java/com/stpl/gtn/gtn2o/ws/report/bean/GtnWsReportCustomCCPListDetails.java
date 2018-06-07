package com.stpl.gtn.gtn2o.ws.report.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GtnWsReportCustomCCPListDetails {

	public GtnWsReportCustomCCPListDetails() {
		super();
	}

	private int levelNo;
	private String hierarchyNo;
	private Object[] data;

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

}
