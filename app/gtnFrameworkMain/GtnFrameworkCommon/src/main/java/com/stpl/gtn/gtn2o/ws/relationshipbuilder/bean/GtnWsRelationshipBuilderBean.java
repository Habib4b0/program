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
public class GtnWsRelationshipBuilderBean {

	public GtnWsRelationshipBuilderBean() {
		super();
	}

	private String rekationshipLevelValue = "";

	private int hierarchyLevelSystemId;

	private int relationshipLevelSystemId;

	private String parentNode = "";

	private String hierarchyNo;

	private String levelNo;

	private String levelName;

	public int getRelationshipLevelSystemId() {
		return relationshipLevelSystemId;
	}

	public void setRelationshipLevelSystemId(int relationshipLevelSystemId) {
		this.relationshipLevelSystemId = relationshipLevelSystemId;
	}

	public int getHierarchyLevelSystemId() {
		return hierarchyLevelSystemId;
	}

	public void setHierarchyLevelSystemId(int hierarchyLevelSystemId) {
		this.hierarchyLevelSystemId = hierarchyLevelSystemId;
	}

	public String getRekationshipLevelValue() {
		return rekationshipLevelValue;
	}

	public void setRekationshipLevelValue(String rekationshipLevelValue) {
		this.rekationshipLevelValue = rekationshipLevelValue;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
