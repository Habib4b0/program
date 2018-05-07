package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

public class GtnWsCustomTreeData {
	private String levelName;
	private int levelNo;
	private int currentTreeLevelNo;
	private GtnWsHierarchyType hierarchyType;
	private List<GtnWsCustomTreeData> children;

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public int getCurrentTreeLevelNo() {
		return currentTreeLevelNo;
	}

	public void setCurrentTreeLevelNo(int currentTreeLevelNo) {
		this.currentTreeLevelNo = currentTreeLevelNo;
	}

	public GtnWsHierarchyType getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(GtnWsHierarchyType hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public List<GtnWsCustomTreeData> getChildren() {
		return children;
	}

	public void setChildren(List<GtnWsCustomTreeData> children) {
		this.children = children;
	}
	
	

}
