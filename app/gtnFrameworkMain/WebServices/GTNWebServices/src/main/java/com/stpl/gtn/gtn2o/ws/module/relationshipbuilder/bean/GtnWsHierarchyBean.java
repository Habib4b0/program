/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author
 */
public class GtnWsHierarchyBean {

	public GtnWsHierarchyBean() {
		/**
		 * empty constructor
		 */
	}

	private List<String> hierarchyNo;
	private List<Object> relationshipLevelValues;
	private String parentHierarchyNo = "";
	private List<String> parentNode;
	private int levelNo = 0;

	public List<String> getHierarchyNo() {
		return hierarchyNo == null ? hierarchyNo : Collections.unmodifiableList(hierarchyNo);
	}

	public void setHierarchyNo(List<String> hierarchyNo) {
		this.hierarchyNo = Collections.unmodifiableList(hierarchyNo);
	}

	public List<Object> getRelationshipLevelValues() {
		return relationshipLevelValues == null ? relationshipLevelValues
				: Collections.unmodifiableList(relationshipLevelValues);
	}

	public void setRelationshipLevelValues(List<Object> levelValues) {
		this.relationshipLevelValues = Collections.unmodifiableList(levelValues);
	}

	public String getParentHierarchyNo() {
		return parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public List<String> getParentNode() {
		return parentNode == null ? parentNode : Collections.unmodifiableList(parentNode);
	}

	public void setParentNode(List<String> parentNode) {
		this.parentNode = Collections.unmodifiableList(parentNode);
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public void addParentNode(String value) {

		if (parentNode == null) {
			parentNode = new ArrayList<>();
		}
		parentNode.add(value);
	}

	public void addRelationshipLevelValues(Object value) {

		if (relationshipLevelValues == null) {
			relationshipLevelValues = new ArrayList<>();
		}
		relationshipLevelValues.add(value);
	}

	public void addHierarchyNo(String value) {

		if (hierarchyNo == null) {
			hierarchyNo = new ArrayList<>();
		}
		hierarchyNo.add(value);
	}

}
