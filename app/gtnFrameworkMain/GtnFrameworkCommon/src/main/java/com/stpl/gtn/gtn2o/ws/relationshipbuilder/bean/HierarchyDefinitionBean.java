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
public class HierarchyDefinitionBean {

	public HierarchyDefinitionBean() {
		super();
	}

	private int noOfLevels;
	private int hierarchyDefinitionSid;
	private String hierarchyType;
	private String hierarchyCategory;
	private String hierarchyName;
	private int versionNo;

	public int getNoOfLevels() {
		return noOfLevels;
	}

	public void setNoOfLevels(int noOfLevels) {
		this.noOfLevels = noOfLevels;
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		this.hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public String getHierarchyCategory() {
		return hierarchyCategory;
	}

	public void setHierarchyCategory(String hierarchyCategory) {
		this.hierarchyCategory = hierarchyCategory;
	}

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

}
