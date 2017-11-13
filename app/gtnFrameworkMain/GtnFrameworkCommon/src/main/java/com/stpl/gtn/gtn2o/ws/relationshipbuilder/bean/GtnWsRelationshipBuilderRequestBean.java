/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRelationshipBuilderRequestBean {

	public GtnWsRelationshipBuilderRequestBean() {
		super();
	}

	private String relationshipName;
	private String relationshipDescription;
	private String relationshipType;
	private String buildType;
	private Date startDate;

	private int versionNo = 1;
	private int selectedHierarchySysId;
	private int levelNo;
	private List<String> levelValues;
	private List<String> primaykeyColumns;
	private List<String> primarykeySID;
	private GtnWsRecordBean rsTreeNode;

	public int getSelectedHierarchySysId() {
		return selectedHierarchySysId;
	}

	public void setSelectedHierarchySysId(int selectedHierarchySysId) {
		this.selectedHierarchySysId = selectedHierarchySysId;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public List<String> getLevelValues() {
		return levelValues == null ? null : Collections.unmodifiableList(levelValues);
	}

	public void setLevelValues(List<String> levelValues) {
		this.levelValues = levelValues == null ? null : Collections.unmodifiableList(levelValues);
	}

	public List<String> getPrimaykeyColumns() {
		return primaykeyColumns == null ? null : Collections.unmodifiableList(primaykeyColumns);
	}

	public void setPrimaykeyColumns(List<String> primaykeyColumns) {
		this.primaykeyColumns = primaykeyColumns == null ? null : Collections.unmodifiableList(primaykeyColumns);
	}

	public List<String> getPrimarykeySID() {
		return primarykeySID == null ? null : Collections.unmodifiableList(primarykeySID);
	}

	public void setPrimarykeySID(List<String> primarykeySID) {
		this.primarykeySID = primarykeySID == null ? null : Collections.unmodifiableList(primarykeySID);
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public GtnWsRecordBean getRsTreeNode() {
		return rsTreeNode;
	}

	public void setRsTreeNode(GtnWsRecordBean rsTreeNode) {
		this.rsTreeNode = rsTreeNode;
	}

	public String getRelationshipDescription() {
		return relationshipDescription;
	}

	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Date getStartDate() {
		return startDate == null ? null : (Date) startDate.clone();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate == null ? null : (Date) startDate.clone();
	}

}
