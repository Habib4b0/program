/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mahi
 */
public class TreeNode {

	public TreeNode() {
		super();
	}

	private int childCount = 0;

	private TreeNode parent = null;

	private List<TreeNode> childList = null;

	private String columnValue;

	private Integer tableSysId;

	private Integer currentLevel;

	private String tableName;

	private String levelName = "";

	private String levelNo = "";

	private String levelValue = "";

	private String hierarchyLevelSystemId;

	private String relationshipLevelSystemId;

	private String parentNode = "";

	private String hiddenId = "";

	private boolean equality = false;

	private String primaryKeyColumn = "";

	private String hierarchyNo;

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
		this.parent.addChild(this);
	}

	public List<TreeNode> getChildList() {
		return childList == null ? childList : Collections.unmodifiableList(childList);
	}

	public void setChildList(List<TreeNode> childList) {
		this.childList = childList == null ? childList : Collections.unmodifiableList(childList);
	}

	public void addChild(TreeNode childNode) {
		if (getChildList() == null) {

			this.childList = new ArrayList<>();
		}
		this.childList.add(childNode);

	}

	public void removeParentChild() {
		if (this.parent != null) {

			this.parent.removeChild(this);
		}

	}

	public void removeChild(TreeNode childNode) {
		if (getChildList() != null) {

			this.childList.remove(childNode);
		}

	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public Integer getTableSysId() {
		return tableSysId;
	}

	public void setTableSysId(Integer tableSysId) {
		this.tableSysId = tableSysId;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public String getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getHierarchyLevelSystemId() {
		return hierarchyLevelSystemId;
	}

	public void setHierarchyLevelSystemId(String hierarchyLevelSystemId) {
		this.hierarchyLevelSystemId = hierarchyLevelSystemId;
	}

	public String getRelationshipLevelSystemId() {
		return relationshipLevelSystemId;
	}

	public void setRelationshipLevelSystemId(String relationshipLevelSystemId) {
		this.relationshipLevelSystemId = relationshipLevelSystemId;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public boolean isEquality() {
		return equality;
	}

	public void setEquality(boolean equality) {
		this.equality = equality;
	}

	public String getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

}
