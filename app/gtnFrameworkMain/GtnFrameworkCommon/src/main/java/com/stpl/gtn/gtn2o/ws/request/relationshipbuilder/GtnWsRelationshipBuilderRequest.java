/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.relationshipbuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelValuesBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelsBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRelationshipBuilderRequest implements GtnWSRequestData {

	public GtnWsRelationshipBuilderRequest() {
		super();
	}

	private GtnWsRecordBean mainNode;
	private String relationshipName;
	private String relationshipDescription;
	private String relationshipType;
	private String hierarchyName;
	private int versionNo = 1;
	private Date startDate;
	private Date creationDate;
	private Date modifiedDate;
	private String createdBy;
	private int relationshipTypeId;
	private int hierarchyDefSId;
	private int createdById;
	private String buildType;
	private int hierarchyVersionNo;
	private int noOfLevels;
	private int rbSysId;
	private int levelNo;
	private int userId;

	private Map<String, List<GtnWsRecordBean>> hierarchyLevelValueList;
	private Map<String, List<GtnWsRecordBean>> savedHierarchyLevelValueList;
	private List<GtnWsRecordBean> rsTreeNodeList;
	private List<HierarchyLevelsBean> hierarchyLevelsBeanList;
	private List<HierarchyLevelValuesBean> hierarchyLevelValuesBeanList;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionBeanList;
	private List<HierarchyDefinitionBean> hierarchyDefinitionBeanList;
	private List<GtnWsRelationshipLevelDefinitionBean> relationshipBuilderBeanList;
	private HierarchyDefinitionBean hierarchyDefinitionBean;
	private List<String> hiddenIdList;
	private List<String> levelValueList;
	private List<String> primarykeyColumnList;
	private List<String> primarySIDList;
	private GtnWsRecordBean selectedTreeBean;

	public GtnWsRecordBean getMainNode() {
		return mainNode;
	}

	public void setMainNode(GtnWsRecordBean mainNode) {
		this.mainNode = mainNode;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public String getRelationshipDescription() {
		return relationshipDescription;
	}

	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
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

	public Date getStartDate() {
		return startDate == null ? null : (Date) startDate.clone();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate == null ? null : (Date) startDate.clone();
	}

	public Date getCreationDate() {
		return creationDate == null ? null : (Date) creationDate.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate == null ? null : (Date) creationDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getRelationshipTypeId() {
		return relationshipTypeId;
	}

	public void setRelationshipTypeId(int relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}

	public int getHierarchyDefSId() {
		return hierarchyDefSId;
	}

	public void setHierarchyDefSId(int hierarchyDefSId) {
		this.hierarchyDefSId = hierarchyDefSId;
	}

	public int getCreatedById() {
		return createdById;
	}

	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public int getHierarchyVersionNo() {
		return hierarchyVersionNo;
	}

	public void setHierarchyVersionNo(int hierarchyVersionNo) {
		this.hierarchyVersionNo = hierarchyVersionNo;
	}

	public int getNoOfLevels() {
		return noOfLevels;
	}

	public void setNoOfLevels(int noOfLevels) {
		this.noOfLevels = noOfLevels;
	}

	public int getRbSysId() {
		return rbSysId;
	}

	public void setRbSysId(int rbSysId) {
		this.rbSysId = rbSysId;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<String, List<GtnWsRecordBean>> getHierarchyLevelValueList() {
		return hierarchyLevelValueList == null ? null : Collections.unmodifiableMap(hierarchyLevelValueList);
	}

	public void setHierarchyLevelValueList(Map<String, List<GtnWsRecordBean>> hierarchyLevelValueList) {
		this.hierarchyLevelValueList = hierarchyLevelValueList == null ? null
				: Collections.unmodifiableMap(hierarchyLevelValueList);
	}

	public Map<String, List<GtnWsRecordBean>> getSavedHierarchyLevelValueList() {
		return savedHierarchyLevelValueList == null ? null : Collections.unmodifiableMap(savedHierarchyLevelValueList);
	}

	public void setSavedHierarchyLevelValueList(Map<String, List<GtnWsRecordBean>> savedHierarchyLevelValueList) {
		this.savedHierarchyLevelValueList = savedHierarchyLevelValueList == null ? null
				: Collections.unmodifiableMap(savedHierarchyLevelValueList);
	}

	public List<GtnWsRecordBean> getRsTreeNodeList() {
		return rsTreeNodeList == null ? null : Collections.unmodifiableList(rsTreeNodeList);
	}

	public void setRsTreeNodeList(List<GtnWsRecordBean> rsTreeNodeList) {
		this.rsTreeNodeList = rsTreeNodeList == null ? null : Collections.unmodifiableList(rsTreeNodeList);
	}

	public List<HierarchyLevelsBean> getHierarchyLevelsBeanList() {
		return hierarchyLevelsBeanList == null ? null : Collections.unmodifiableList(hierarchyLevelsBeanList);
	}

	public void setHierarchyLevelsBeanList(List<HierarchyLevelsBean> hierarchyLevelsBeanList) {
		this.hierarchyLevelsBeanList = hierarchyLevelsBeanList == null ? null
				: Collections.unmodifiableList(hierarchyLevelsBeanList);
	}

	public List<HierarchyLevelValuesBean> getHierarchyLevelValuesBeanList() {
		return hierarchyLevelValuesBeanList == null ? null : Collections.unmodifiableList(hierarchyLevelValuesBeanList);
	}

	public void setHierarchyLevelValuesBeanList(List<HierarchyLevelValuesBean> hierarchyLevelValuesBeanList) {
		this.hierarchyLevelValuesBeanList = hierarchyLevelValuesBeanList == null ? null
				: Collections.unmodifiableList(hierarchyLevelValuesBeanList);
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionBeanList() {
		return hierarchyLevelDefinitionBeanList == null ? null
				: Collections.unmodifiableList(hierarchyLevelDefinitionBeanList);
	}

	public void setHierarchyLevelDefinitionBeanList(
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionBeanList) {
		this.hierarchyLevelDefinitionBeanList = hierarchyLevelDefinitionBeanList == null ? null
				: Collections.unmodifiableList(hierarchyLevelDefinitionBeanList);
	}

	public List<HierarchyDefinitionBean> getHierarchyDefinitionBeanList() {
		return hierarchyDefinitionBeanList == null ? null : Collections.unmodifiableList(hierarchyDefinitionBeanList);
	}

	public void setHierarchyDefinitionBeanList(List<HierarchyDefinitionBean> hierarchyDefinitionBeanList) {
		this.hierarchyDefinitionBeanList = hierarchyDefinitionBeanList == null ? null
				: Collections.unmodifiableList(hierarchyDefinitionBeanList);
	}

	public List<GtnWsRelationshipLevelDefinitionBean> getRelationshipBuilderBeanList() {
		return relationshipBuilderBeanList == null ? null : Collections.unmodifiableList(relationshipBuilderBeanList);
	}

	public void setRelationshipBuilderBeanList(List<GtnWsRelationshipLevelDefinitionBean> relationshipBuilderBeanList) {
		this.relationshipBuilderBeanList = relationshipBuilderBeanList == null ? null
				: Collections.unmodifiableList(relationshipBuilderBeanList);
	}

	public HierarchyDefinitionBean getHierarchyDefinitionBean() {
		return hierarchyDefinitionBean;
	}

	public void setHierarchyDefinitionBean(HierarchyDefinitionBean hierarchyDefinitionBean) {
		this.hierarchyDefinitionBean = hierarchyDefinitionBean;
	}

	public List<String> getHiddenIdList() {
		return hiddenIdList == null ? null : Collections.unmodifiableList(hiddenIdList);
	}

	public void setHiddenIdList(List<String> hiddenIdList) {
		this.hiddenIdList = hiddenIdList == null ? null : Collections.unmodifiableList(hiddenIdList);
	}

	public List<String> getLevelValueList() {
		return levelValueList == null ? null : Collections.unmodifiableList(levelValueList);
	}

	public void setLevelValueList(List<String> levelValueList) {
		this.levelValueList = levelValueList == null ? null : Collections.unmodifiableList(levelValueList);
	}

	public List<String> getPrimarykeyColumnList() {
		return primarykeyColumnList == null ? null : Collections.unmodifiableList(primarykeyColumnList);
	}

	public void setPrimarykeyColumnList(List<String> primarykeyColumnList) {
		this.primarykeyColumnList = primarykeyColumnList == null ? null
				: Collections.unmodifiableList(primarykeyColumnList);
	}

	public List<String> getPrimarySIDList() {
		return primarySIDList == null ? null : Collections.unmodifiableList(primarySIDList);
	}

	public void setPrimarySIDList(List<String> primarySIDList) {
		this.primarySIDList = primarySIDList == null ? null : Collections.unmodifiableList(primarySIDList);
	}
        private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

	public GtnWsRecordBean getSelectedTreeBean() {
		return selectedTreeBean;
	}

	public void setSelectedTreeBean(GtnWsRecordBean selectedTreeBean) {
		this.selectedTreeBean = selectedTreeBean;
	}
}
