/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.relationshipbuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsRelationshipBuilderResponse implements GtnWSResponseData {

	public GtnWsRelationshipBuilderResponse() {
		super();
	}

	private GtnWsRecordBean mainNode;
	private String relationshipName;
	private String relationshipDescription;
	private String relationshipType;
	private String hierarchyName;
	private int versionNo = 1;
	private String createdBy;
	private int noOfLevels;
	private int rbSysId;
	private int levelNo;
	private boolean success;
	private Date startDate;
	private Date creationDate;
	private Date modifiedDate;
	private String messageType;
	private String message;
	private int relationshipTypeId;
	private int hierarchyDefSId;
	private int createdById;
	private String buildType;
	private List<Integer> hierarchyVersionNo;
	private List<GtnWsRecordBean> rbTreeNodeList;
	private List<GtnWsRelationshipLevelDefinitionBean> relationshipBuilderBeanList;
	private HierarchyDefinitionBean hierarchyDefinitionBean;
	private List<String> hiddenIdList;
	private int selectedVersionNo;
	private List<String> hierarchyLevelNameList;

	public GtnWsRecordBean getMainNode() {
		return mainNode;
	}

	public void setMainNode(GtnWsRecordBean mainNode) {
		this.mainNode = mainNode;
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

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
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

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public Date getCreationDate() {
		return creationDate == null ? null : (Date) creationDate.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate == null ? null : (Date) creationDate.clone();
	}

	public int getRelationshipTypeId() {
		return relationshipTypeId;
	}

	public void setRelationshipTypeId(int relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getCreatedById() {
		return createdById;
	}

	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}

	public int getHierarchyDefSId() {
		return hierarchyDefSId;
	}

	public void setHierarchyDefSId(int hierarchyDefSId) {
		this.hierarchyDefSId = hierarchyDefSId;
	}

	public List<Integer> getHierarchyVersionNo() {
		return hierarchyVersionNo == null ? null : Collections.unmodifiableList(hierarchyVersionNo);
	}

	public void setHierarchyVersionNo(List<Integer> hierarchyVersionNo) {
		this.hierarchyVersionNo = hierarchyVersionNo == null ? null : Collections.unmodifiableList(hierarchyVersionNo);
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public int getRbSysId() {
		return rbSysId;
	}

	public void setRbSysId(int rbSysId) {
		this.rbSysId = rbSysId;
	}

	public int getNoOfLevels() {
		return noOfLevels;
	}

	public void setNoOfLevels(int noOfLevels) {
		this.noOfLevels = noOfLevels;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public List<GtnWsRelationshipLevelDefinitionBean> getRelationshipBuilderBeanList() {
		return relationshipBuilderBeanList == null ? null : Collections.unmodifiableList(relationshipBuilderBeanList);
	}

	public void setRelationshipBuilderBeanList(List<GtnWsRelationshipLevelDefinitionBean> relationshipBuilderBeanList) {
		this.relationshipBuilderBeanList = relationshipBuilderBeanList == null ? null
				: Collections.unmodifiableList(relationshipBuilderBeanList);
	}

	public List<GtnWsRecordBean> getRbTreeNodeList() {
		return rbTreeNodeList == null ? null : new ArrayList<>(rbTreeNodeList);
	}

	public void setRbTreeNodeList(List<GtnWsRecordBean> rbTreeNodeList) {
		this.rbTreeNodeList = rbTreeNodeList == null ? null : new ArrayList<>(rbTreeNodeList);
	}

	public HierarchyDefinitionBean getHierarchyDefinitionBean() {
		return hierarchyDefinitionBean;
	}

	public void setHierarchyDefinitionBean(HierarchyDefinitionBean hierarchyDefinitionBean) {
		this.hierarchyDefinitionBean = hierarchyDefinitionBean;
	}

	public void addToRbTreeNodeList(GtnWsRecordBean gtnWsRecordBean) {
		if (rbTreeNodeList == null) {
			rbTreeNodeList = new ArrayList<>();
		}
		rbTreeNodeList.add(gtnWsRecordBean);
	}

	public void clearRbTreeNodeList() {
		rbTreeNodeList = new ArrayList<>();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void addToHiddenIdList(String hiddenId) {
		if (hiddenIdList == null) {
			hiddenIdList = new ArrayList<>();
		}
		hiddenIdList.add(hiddenId);
	}

	public void clearHiddenIdList() {
		hiddenIdList = new ArrayList<>();
	}

	public List<String> getHiddenIdList() {
		return hiddenIdList == null ? null : Collections.unmodifiableList(hiddenIdList);
	}

	public int getSelectedVersionNo() {
		return selectedVersionNo;
	}

	public void setSelectedVersionNo(int selectedVersionNo) {
		this.selectedVersionNo = selectedVersionNo;
	}

	public List<String> getHierarchyLevelNameList() {
		return hierarchyLevelNameList == null ? null : Collections.unmodifiableList(hierarchyLevelNameList);
	}

	public void setHierarchyLevelNameList(List<String> hierarchyLevelNameList) {
		this.hierarchyLevelNameList = hierarchyLevelNameList == null ? null : new ArrayList<>(hierarchyLevelNameList);
	}

}
