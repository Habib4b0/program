/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

/**
 *
 * @author Mahesh.James
 */
public class HierarchyLevelValuesBean {

	public HierarchyLevelValuesBean() {
		super();
	}

	private String levelValues;
	private int hierarchyLevelValuesSid;
	private int hierarchyLevelDefinitionSid;
	private String levelName;
	private int levelNo;

	public String getLevelValues() {
		return levelValues;
	}

	public void setLevelValues(String levelValues) {
		this.levelValues = levelValues;
	}

	public int getHierarchyLevelValuesSid() {
		return hierarchyLevelValuesSid;
	}

	public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
		this.hierarchyLevelValuesSid = hierarchyLevelValuesSid;
	}

	public int getHierarchyLevelDefinitionSid() {
		return hierarchyLevelDefinitionSid;
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
	}

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

}
