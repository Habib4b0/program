package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

public class GtnWsCustomTreeData {
	private String levelName;
	private int levelNo;
	private int currentTreeLevelNo;
	private GtnWsHierarchyType hierarchyType;
	private int hierarchySid;
	private List<GtnWsReportVariablesType> variableList;
	private GtnWsCustomTreeData child;

    

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

	public GtnWsCustomTreeData getChild() {
		return child;
	}

	public void setChild(GtnWsCustomTreeData child) {
		this.child = child;
	}

	public List<GtnWsReportVariablesType> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<GtnWsReportVariablesType> variableList) {
		this.variableList = variableList;
	}

    public int getHierarchySid() {
        return hierarchySid;
    }

    public void setHierarchySid(int hierarchySid) {
        this.hierarchySid = hierarchySid;
    }

}
