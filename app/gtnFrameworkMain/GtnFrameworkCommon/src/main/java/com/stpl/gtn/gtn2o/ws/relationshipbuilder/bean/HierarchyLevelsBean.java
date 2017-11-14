/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Mahesh.James
 */
public class HierarchyLevelsBean {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(HierarchyLevelsBean.class);

	public HierarchyLevelsBean() {
		super();
	}

	private List<HierarchyLevelDefinitionBean> listOfLevels;

	private List<HierarchyLevelValuesBean> listOfLevelValues;

	private int levelCount;

	private String levelName = "";

	private String levelNo = "";

	private String levelValue = "";

	private int hierarchyLevelSystemId;

	private int relationshipLevelSystemId;

	private String parentNode = "";

	private String hiddenId = "";

	private boolean equality = false;

	private String primaryKeyColumn = "";

	private String hierarchyNo = "";

	public List<HierarchyLevelDefinitionBean> getListOfLevels() {
		return listOfLevels != null ? Collections.unmodifiableList(listOfLevels) : listOfLevels;
	}

	public void setListOfLevels(List<HierarchyLevelDefinitionBean> listOfLevels) {
		this.listOfLevels = listOfLevels != null ? Collections.unmodifiableList(listOfLevels) : listOfLevels;
	}

	public List<HierarchyLevelValuesBean> getListOfLevelValues() {
		return listOfLevelValues != null ? Collections.unmodifiableList(listOfLevelValues) : listOfLevelValues;
	}

	public void setListOfLevelValues(List<HierarchyLevelValuesBean> listOfLevelValues) {
		this.listOfLevelValues = listOfLevelValues != null ? Collections.unmodifiableList(listOfLevelValues)
				: listOfLevelValues;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public String getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public int getHierarchyLevelSystemId() {
		return hierarchyLevelSystemId;
	}

	public void setHierarchyLevelSystemId(int hierarchyLevelSystemId) {
		this.hierarchyLevelSystemId = hierarchyLevelSystemId;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public int getRelationshipLevelSystemId() {
		return relationshipLevelSystemId;
	}

	public void setRelationshipLevelSystemId(int relationshipLevelSystemId) {
		this.relationshipLevelSystemId = relationshipLevelSystemId;
	}

	public String getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
	}

	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public boolean isEquality() {
		return equality;
	}

	public void setEquality(boolean equality) {
		this.equality = equality;
	}

	public HierarchyLevelsBean getH(final HierarchyLevelsBean hierarchyLevelsDTO) {
		HierarchyLevelsBean hierarchyLevelsDTO2 = null;
		try {
			hierarchyLevelsDTO2 = (HierarchyLevelsBean) hierarchyLevelsDTO.clone();
		} catch (CloneNotSupportedException ex) {
			logger.debug(ex.getMessage());
		}
		return hierarchyLevelsDTO2;
	}

}
