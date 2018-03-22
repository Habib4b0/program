package com.stpl.gtn.gtn2o.ws.forecast.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public class GtnFrameworkRelationshipLevelDefintionBean
		implements Comparable<GtnFrameworkRelationshipLevelDefintionBean>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HierarchyLevelDefinitionBean hierarchyLevelBean = new HierarchyLevelDefinitionBean();

	private int relationshipBuilderSid;
	private int relationShipLevelValue;
	private int relationshipLevelSid;
	private int relationshipVersionNo;
	private String hierarchyNo;

	public GtnFrameworkRelationshipLevelDefintionBean() {
		super();
	}

	public HierarchyLevelDefinitionBean getHierarchyLevelBean() {
		return hierarchyLevelBean;
	}

	public void setHierarchyLevelBean(HierarchyLevelDefinitionBean hierarchyLevelBean) {
		this.hierarchyLevelBean = hierarchyLevelBean;
	}

	public int getRelationshipBuilderSid() {
		return relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		this.relationshipBuilderSid = relationshipBuilderSid;
	}

	public int getRelationShipLevelValue() {
		return relationShipLevelValue;
	}

	public void setRelationShipLevelValue(int relationShipLevelValue) {
		this.relationShipLevelValue = relationShipLevelValue;
	}

	public String getTableName() {
		return hierarchyLevelBean.getTableName();
	}

	public void setTableName(String tableName) {
		this.hierarchyLevelBean.setTableName(tableName);
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public Date getActionDate() {
		return hierarchyLevelBean.getActionDate();
	}

	public void setActionDate(Date actionDate) {
		hierarchyLevelBean.setActionDate(actionDate);
	}

	public int getRelationshipVersionNo() {
		return relationshipVersionNo;
	}

	public void setRelationshipVersionNo(int relationshipVersionNo) {
		this.relationshipVersionNo = relationshipVersionNo;
	}

	public int getRelationshipLevelSid() {
		return relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	public String getFieldName() {
		return hierarchyLevelBean.getFieldName();
	}

	public void setFieldName(String fieldName) {
		hierarchyLevelBean.setFieldName(fieldName);
	}

	public int getHierarchyLevelDefinitionSid() {
		return hierarchyLevelBean.getHierarchyLevelDefinitionSid();
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		hierarchyLevelBean.setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
	}

	public int getHierarchyVersionNo() {
		return hierarchyLevelBean.getVersionNo();
	}

	public void setHierarchyVersionNo(int versionNo) {
		hierarchyLevelBean.setVersionNo(versionNo);
	}

	public Date getHierarchyModifiedDate() {
		return hierarchyLevelBean.getModifiedDate();
	}

	public void setModifiedDate(Date modifiedDate) {
		hierarchyLevelBean.setModifiedDate(modifiedDate);
	}

	public int getHierarchyCreatedBy() {
		return hierarchyLevelBean.getCreatedBy();
	}

	public void setHierarchyCreatedBy(int createdBy) {
		hierarchyLevelBean.setCreatedBy(createdBy);
	}

	public Date getCreatedDate() {
		return hierarchyLevelBean.getCreatedDate();
	}

	public void setCreatedDate(Date createdDate) {
		hierarchyLevelBean.setCreatedDate(createdDate);
	}

	public String getLevelValueReference() {
		return hierarchyLevelBean.getLevelValueReference();
	}

	public void setLevelValueReference(String levelValueReference) {
		hierarchyLevelBean.setLevelValueReference(levelValueReference);
	}

	public int getLevelNo() {
		return hierarchyLevelBean.getLevelNo();
	}

	public void setLevelNo(int levelNo) {
		hierarchyLevelBean.setLevelNo(levelNo);
	}

	public String getActionFlag() {
		return hierarchyLevelBean.getActionFlag();
	}

	public void setActionFlag(String actionFlag) {
		hierarchyLevelBean.setActionFlag(actionFlag);
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyLevelBean.getHierarchyDefinitionSid();
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		hierarchyLevelBean.setHierarchyDefinitionSid(hierarchyDefinitionSid);
	}

	public int getHierarchyModifiedBy() {
		return hierarchyLevelBean.getModifiedBy();
	}

	public void setHierarchyModifiedBy(int modifiedBy) {
		hierarchyLevelBean.setModifiedBy(modifiedBy);
	}

	public int getDefaultVlaue() {
		return hierarchyLevelBean.getDefaultVlaue();
	}

	public void setDefaultVlaue(int defaultVlaue) {
		hierarchyLevelBean.setDefaultVlaue(defaultVlaue);
	}

	public String getLevelName() {
		return hierarchyLevelBean.getLevelName();
	}

	public void setLevelName(String levelName) {
		hierarchyLevelBean.setLevelName(levelName);
	}

	public String getHierarchyCategory() {
		return hierarchyLevelBean.getHierarchyCategory();
	}

	public void setHierarchyCategory(String hierarchyCategory) {
		hierarchyLevelBean.setHierarchyCategory(hierarchyCategory);
	}

	public static List<Object> getLinkedLevelListByLevelNo(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList, int i) {
		List<Object> finalList = new ArrayList<>();
		List<GtnFrameworkRelationshipLevelDefintionBean> levelBeanList = new ArrayList<>();
		Set<String> sidList = new HashSet<>();
		for (GtnFrameworkRelationshipLevelDefintionBean leveldto : selectedCustomerContractList) {
			if (i == leveldto.getLevelNo() && !"User Defined".equals(leveldto.getLevelValueReference())) {
				levelBeanList.add(leveldto);
				sidList.add(String.valueOf(leveldto.relationShipLevelValue));
			}
		}
		finalList.add(sidList);
		finalList.add(levelBeanList);
		return finalList;
	}

	public static GtnFrameworkRelationshipLevelDefintionBean getLastLinkedLevel(
			List<GtnFrameworkRelationshipLevelDefintionBean> hierarchyList) {
		int levelNo = GtnFrameworkRelationshipLevelDefintionBean.getLastLinkedLevelNo(hierarchyList);
		return GtnFrameworkRelationshipLevelDefintionBean.getBeanByLevelNo(levelNo, hierarchyList);
	}

	public static int getLastLinkedLevelNo(List<GtnFrameworkRelationshipLevelDefintionBean> hierarchyList) {
		for (int i = hierarchyList.size() - 1; i > 0; i--) {
			GtnFrameworkRelationshipLevelDefintionBean hierarchyLevelDefinitionBean = hierarchyList.get(i);
			if (!hierarchyLevelDefinitionBean.hierarchyLevelBean.isUserDefined()) {
				return hierarchyLevelDefinitionBean.getLevelNo();
			}
		}
		return 0;

	}

	public static GtnFrameworkRelationshipLevelDefintionBean getBeanByLevelNo(int levelNo,
			List<GtnFrameworkRelationshipLevelDefintionBean> hierarchyList) {
		if (levelNo > 1 && hierarchyList.get(levelNo - 1).hierarchyLevelBean.getLevelNo() == levelNo)
			return hierarchyList.get(levelNo - 1);
		for (GtnFrameworkRelationshipLevelDefintionBean hierarchyLevelDefinitionBean : hierarchyList) {
			if (levelNo == hierarchyLevelDefinitionBean.getLevelNo()) {
				return hierarchyLevelDefinitionBean;
			}
		}
		return null;
	}

	@Override
	public int compareTo(GtnFrameworkRelationshipLevelDefintionBean obj) {
		return Integer.compare(hierarchyLevelBean.getLevelNo(), obj.getLevelNo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hierarchyLevelBean == null) ? 0 : hierarchyLevelBean.hashCode());
		result = prime * result + ((hierarchyNo == null) ? 0 : hierarchyNo.hashCode());
		result = prime * result + relationShipLevelValue;
		result = prime * result + relationshipBuilderSid;
		result = prime * result + relationshipLevelSid;
		result = prime * result + relationshipVersionNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GtnFrameworkRelationshipLevelDefintionBean other = (GtnFrameworkRelationshipLevelDefintionBean) obj;
		if (hierarchyLevelBean == null) {
			if (other.hierarchyLevelBean != null)
				return false;
		} else if (!hierarchyLevelBean.equals(other.hierarchyLevelBean))
			return false;
		if (hierarchyNo == null) {
			if (other.hierarchyNo != null)
				return false;
		} else if (!hierarchyNo.equals(other.hierarchyNo))
			return false;
		if (relationShipLevelValue != other.relationShipLevelValue)
			return false;
		if (relationshipBuilderSid != other.relationshipBuilderSid)
			return false;
		if (relationshipLevelSid != other.relationshipLevelSid)
			return false;
		if (relationshipVersionNo != other.relationshipVersionNo)
			return false;
		return true;
	}

}
