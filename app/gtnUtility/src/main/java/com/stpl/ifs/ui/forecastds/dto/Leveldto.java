/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class Leveldto implements Cloneable, Comparable<Leveldto> {

	/**
	 * The level.
	 */
	private String level = StringUtils.EMPTY;

	/**
	 * The level no.
	 */
	private Integer levelNo = new Integer("0");

	/**
	 * The parent node.
	 */
	private String parentNode = StringUtils.EMPTY;

	/**
	 * The relationship level sid.
	 */
	private Integer relationshipLevelSid = new Integer("0");

	/**
	 * The relationship level value.
	 */
	private String relationshipLevelValue = StringUtils.EMPTY;

	/**
	 * The table name.
	 */
	private String tableName = StringUtils.EMPTY;

	/**
	 * The field name.
	 */
	private String fieldName = StringUtils.EMPTY;

	/**
	 * The level value reference.
	 */
	private String levelValueReference = StringUtils.EMPTY;

	private boolean fromCompany;

	private boolean fromItem;

	private boolean fromContract;

	private String hierarchyNo = StringUtils.EMPTY;

	private String parentHierarchyNo = StringUtils.EMPTY;

	/**
	 * The tree level no.
	 */
	private Integer treeLevelNo = new Integer("0");

	/**
	 * The hierarchy Id.
	 */
	private Integer hierarchyId = new Integer("0");

	/**
	 * The hierarchy Indicator.
	 */
	private String hierarchyIndicator = StringUtils.EMPTY;

	/**
	 * The relationShipBuilderId.
	 */
	private String relationShipBuilderId;

	/**
	 * The relationShipBuilderId.
	 */
	private String hierarchyLevelDefnId;

	private String ndc;

	private String form;

	private String strength;

	private String relationShipSid;

	private String displayValue = StringUtils.EMPTY;

	private String relationshipLevelName = StringUtils.EMPTY;
	private int count = 0;

	private String deductionLevel = StringUtils.EMPTY;
	private String deductionValue = StringUtils.EMPTY;
	private String hierarchyType = StringUtils.EMPTY;

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRelationshipLevelName() {
		return relationshipLevelName;
	}

	public void setRelationshipLevelName(String relationshipLevelName) {
		this.relationshipLevelName = relationshipLevelName;
	}

	/**
	 * Gets the hierarchy Indicator
	 *
	 * @return the hierarchyIndicator
	 */
	public String getHierarchyIndicator() {
		return hierarchyIndicator;
	}

	/**
	 * Sets the hierarchy Indicator
	 *
	 * @param hierarchyIndicator
	 */
	public void setHierarchyIndicator(String hierarchyIndicator) {
		this.hierarchyIndicator = hierarchyIndicator;
	}

	/**
	 * Gets the level value reference.
	 *
	 * @return the level value reference
	 */
	public String getLevelValueReference() {
		return levelValueReference;
	}

	/**
	 * Sets the level value reference.
	 *
	 * @param levelValueReference
	 *            the new level value reference
	 */
	public void setLevelValueReference(String levelValueReference) {
		this.levelValueReference = levelValueReference;
	}

	/**
	 * Gets the relationship level value.
	 *
	 * @return the relationship level value
	 */
	public String getRelationshipLevelValue() {
		return relationshipLevelValue;
	}

	/**
	 * Sets the relationship level value.
	 *
	 * @param relationshipLevelValue
	 *            the new relationship level value
	 */
	public void setRelationshipLevelValue(String relationshipLevelValue) {
		this.relationshipLevelValue = relationshipLevelValue;
	}

	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the table name.
	 *
	 * @param tableName
	 *            the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName
	 *            the new field name
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the relationship level sid.
	 *
	 * @return the relationship level sid
	 */
	public Integer getRelationshipLevelSid() {
		return relationshipLevelSid;
	}

	/**
	 * Sets the relationship level sid.
	 *
	 * @param relationshipLevelSid
	 *            the new relationship level sid
	 */
	public void setRelationshipLevelSid(Integer relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	/**
	 * Gets the parent node.
	 *
	 * @return the parent node
	 */
	public String getParentNode() {
		return parentNode;
	}

	/**
	 * Sets the parent node.
	 *
	 * @param parentNode
	 *            the new parent node
	 */
	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * Gets the level no.
	 *
	 * @return the level no
	 */
	public Integer getLevelNo() {
		return levelNo;
	}

	/**
	 * Sets the level no.
	 *
	 * @param levelNo
	 *            the new level no
	 */
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level
	 *            the new level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(boolean fromCompany) {
		this.fromCompany = fromCompany;
	}

	public boolean isFromItem() {
		return fromItem;
	}

	public void setFromItem(boolean fromItem) {
		this.fromItem = fromItem;
	}

	public boolean isFromContract() {
		return fromContract;
	}

	public void setFromContract(boolean fromContract) {
		this.fromContract = fromContract;
	}

	public Integer getTreeLevelNo() {
		return treeLevelNo;
	}

	public void setTreeLevelNo(Integer treeLevelNo) {
		this.treeLevelNo = treeLevelNo;
	}

	public Integer getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(Integer hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getRelationShipBuilderId() {
		return relationShipBuilderId;
	}

	public void setRelationShipBuilderId(String relationShipBuilderId) {
		this.relationShipBuilderId = relationShipBuilderId;
	}

	public String getHierarchyLevelDefnId() {
		return hierarchyLevelDefnId;
	}

	public void setHierarchyLevelDefnId(String hierarchyLevelDefnId) {
		this.hierarchyLevelDefnId = hierarchyLevelDefnId;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getParentHierarchyNo() {
		return parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getRelationShipSid() {
		return relationShipSid;
	}

	public void setRelationShipSid(String relationShipSid) {
		this.relationShipSid = relationShipSid;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDeductionLevel() {
		return deductionLevel;
	}

	public void setDeductionLevel(String deductionLevel) {
		this.deductionLevel = deductionLevel;
	}

	public String getDeductionValue() {
		return deductionValue;
	}

	public void setDeductionValue(String deductionValue) {
		this.deductionValue = deductionValue;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int compareTo(Leveldto obj) {
		return this.levelNo.compareTo(obj.levelNo);
	}

	public static List<Object> getBeanByLevelNo(List<Leveldto> selectedCustomerContractList, int i) {
		List<Object> finalList = new ArrayList<>();
		List<Leveldto> levelBeanList = new ArrayList<>();
		List<String> sidList = new ArrayList<>();
		for (Leveldto leveldto : selectedCustomerContractList) {
			if (i == leveldto.getLevelNo() && !"User Defined".equals(leveldto.getLevelValueReference())) {
				levelBeanList.add(leveldto);
				sidList.add(leveldto.relationshipLevelValue);
			}
		}
		finalList.add(sidList);
		finalList.add(levelBeanList);
		return finalList;
	}

	public static Set<String> getTableNameList(List<Leveldto> levelHierarchyLevelDefinitionList) {
		Set<String> tableNameSet = new HashSet<>();
		for (Leveldto leveldto : levelHierarchyLevelDefinitionList) {
			if (leveldto.getTableName().isEmpty())
				continue;
			tableNameSet.add(leveldto.getTableName());
		}
		return tableNameSet;
	}

	public static List<String> getLastLevelHierarchyNo(List<Leveldto> selectedCustomerContractList, Integer levelNo) {
		List<String> hierarchyNosList = new ArrayList<>();
		for (Leveldto leveldto : selectedCustomerContractList) {
			if (leveldto.levelNo == levelNo) {
				hierarchyNosList.add(leveldto.hierarchyNo);
			}
		}
		return hierarchyNosList;
	}

	public static Leveldto getLastLinkedLevel(List<Leveldto> customerHierarchyLevelDefinitionList) {
		for (int i = customerHierarchyLevelDefinitionList.size() - 1; i >= 0; i--) {
			if (!customerHierarchyLevelDefinitionList.get(i).tableName.isEmpty()) {
				return customerHierarchyLevelDefinitionList.get(i);
			}
		}
		return null;
	}

	public boolean isUserDefined() {
		return "User Defined".equals(levelValueReference);

	}

}
