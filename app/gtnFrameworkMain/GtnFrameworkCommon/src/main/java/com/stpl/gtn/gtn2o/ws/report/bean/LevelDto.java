package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sooriya.lakshmanan
 */
public class LevelDto implements Comparable<LevelDto> {

	/**
	 * The level.
	 */
	private String level = "";

	/**
	 * The level no.
	 */
	private Integer levelNo = Integer.valueOf("0");

	/**
	 * The parent node.
	 */
	private String parentNode = "";

	/**
	 * The relationship level sid.
	 */
	private Integer relationshipLevelSid = Integer.valueOf("0");

	/**
	 * The relationship level value.
	 */
	private String relationshipLevelValue = "";

	/**
	 * The table name.
	 */
	private String tableName = "";

	/**
	 * The field name.
	 */
	private String fieldName = "";

	/**
	 * The level value reference.
	 */
	private String levelValueReference = "";

	private boolean fromCompany;

	private boolean fromItem;

	private boolean fromContract;

	private String hierarchyNo = "";

	private String parentHierarchyNo = "";

	/**
	 * The tree level no.
	 */
	private Integer treeLevelNo = Integer.valueOf("0");

	/**
	 * The hierarchy Id.
	 */
	private Integer hierarchyId = Integer.valueOf("0");

	/**
	 * The hierarchy Indicator.
	 */
	private String hierarchyIndicator = "";

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

	private String displayValue = "";

	private String relationshipLevelName = "";
	private int count = 0;

	private String deductionLevel = "";
	private String deductionValue = "";
	private String hierarchyType = "";
	private Integer hierarchyVersionNo = Integer.valueOf("0");
	private int relationShipVersionNo;

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

	public int getRelationShipVersionNo() {
		return relationShipVersionNo;
	}

	public void setRelationShipVersionNo(int relationShipVersionNo) {
		this.relationShipVersionNo = relationShipVersionNo;
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
	public int compareTo(LevelDto obj) {
		return this.levelNo.compareTo(obj.levelNo);
	}

	public static List<Object> getBeanByLevelNo(List<LevelDto> selectedCustomerContractList, int i) {
		List<Object> finalList = new ArrayList<>();
		List<LevelDto> levelBeanList = new ArrayList<>();
		Set<String> sidList = new HashSet<>();
		for (LevelDto LevelDto : selectedCustomerContractList) {
			if (i == LevelDto.getLevelNo() && !"User Defined".equals(LevelDto.getLevelValueReference())) {
				levelBeanList.add(LevelDto);
				sidList.add(LevelDto.relationshipLevelValue);
			}
		}
		finalList.add(sidList);
		finalList.add(levelBeanList);
		return finalList;
	}

	public static Set<String> getTableNameList(List<LevelDto> levelHierarchyLevelDefinitionList) {
		Set<String> tableNameSet = new HashSet<>();
		for (LevelDto LevelDto : levelHierarchyLevelDefinitionList) {
			if (LevelDto.getTableName().isEmpty())
				continue;
			tableNameSet.add(LevelDto.getTableName());
		}
		return tableNameSet;
	}

	public static List<String> getLastLevelHierarchyNo(List<LevelDto> selectedCustomerContractList, Integer levelNo) {
		List<String> hierarchyNosList = new ArrayList<>();
		for (LevelDto LevelDto : selectedCustomerContractList) {
			if (LevelDto.levelNo.intValue() == levelNo.intValue()) {
				hierarchyNosList.add(LevelDto.hierarchyNo);
			}
		}
		return hierarchyNosList;
	}

	public static LevelDto getLastLinkedLevel(List<LevelDto> customerHierarchyLevelDefinitionList) {
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

	public Integer getHierarchyVersionNo() {
		return hierarchyVersionNo;
	}

	public void setHierarchyVersionNo(Integer hierarchyVersionNo) {
		this.hierarchyVersionNo = hierarchyVersionNo;
	}

	public static LevelDto getPreviousLinkedLevel(List<LevelDto> hierarchyLevelDefinitionList,
			LevelDto currnetHierarchyLevelBean) {
		for (int i = currnetHierarchyLevelBean.getLevelNo() - 2; i >= 0; i--) {
			if (!hierarchyLevelDefinitionList.get(i).isUserDefined()) {
				return hierarchyLevelDefinitionList.get(i);
			}
		}
		return null;
	}

	public static LevelDto getBeanByLevelNo(int levelNo, List<LevelDto> hierarchyList) {
		if (levelNo > 1 && hierarchyList.get(levelNo - 1).levelNo == levelNo)
			return hierarchyList.get(levelNo - 1);
		for (LevelDto hierarchyLevelDefinitionBean : hierarchyList) {
			if (levelNo == hierarchyLevelDefinitionBean.levelNo) {
				return hierarchyLevelDefinitionBean;
			}
		}
		return null;
	}

}
