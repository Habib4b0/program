/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Mahesh.James
 */

/**
 * The base model interface for the HistHierarchyLevelDefn service. Represents a
 * row in the &quot;HISTHIERARCHYLEVELDEFN&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation
 * {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl} exist only as
 * a container for the default property accessors generated by ServiceBuilder.
 * Helper methods and all application logic should be put in
 * {@link com.stpl.app.model.impl.HistHierarchyLevelDefnImpl}.
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefn
 * @see com.stpl.app.model.impl.HistHierarchyLevelDefnImpl
 * @see com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl
 * @generated
 */
public class HierarchyLevelDefinitionBean implements Comparable<HierarchyLevelDefinitionBean> {

	public HierarchyLevelDefinitionBean() {
		super();
	}

	private String tableName;
	private Date actionDate;
	private String fieldName;
	private int hierarchyLevelDefinitionSid;
	private int versionNo;
	private Date modifiedDate;
	private int createdBy;
	private Date createdDate;
	private String levelValueReference;
	private int levelNo;
	private String actionFlag;
	private int hierarchyDefinitionSid;
	private int modifiedBy;
	private String levelName;
	private int defaultVlaue;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getActionDate() {
		return actionDate == null ? null : (Date) actionDate.clone();
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate == null ? null : (Date) actionDate.clone();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getHierarchyLevelDefinitionSid() {
		return hierarchyLevelDefinitionSid;
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public String getLevelValueReference() {
		return levelValueReference;
	}

	public void setLevelValueReference(String levelValueReference) {
		this.levelValueReference = levelValueReference;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		this.hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getDefaultVlaue() {
		return defaultVlaue;
	}

	public void setDefaultVlaue(int defaultVlaue) {
		this.defaultVlaue = defaultVlaue;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public int compareTo(HierarchyLevelDefinitionBean obj) {
		return Integer.compare(levelNo, obj.getLevelNo());
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public static Set<String> getTableNameSet(List<HierarchyLevelDefinitionBean> hierarchyList) {
		Set<String> tableNameSet = new HashSet<>();
		for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyLevelDefinitionBean.levelValueReference)) {
				final String tableName = hierarchyLevelDefinitionBean.tableName;
				tableNameSet.add(tableName);
			}
		}
		return tableNameSet;
	}

	public static int getLastLinkedLevelNo(List<HierarchyLevelDefinitionBean> hierarchyList) {
		for (int i = hierarchyList.size() - 1; i > 0; i--) {
			HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyLevelDefinitionBean.levelValueReference)) {
				return hierarchyLevelDefinitionBean.getLevelNo();
			}
		}
		return 0;

	}

	public static HierarchyLevelDefinitionBean getLastLinkedLevel(List<HierarchyLevelDefinitionBean> hierarchyList) {
		int levelNo = HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList);
		return HierarchyLevelDefinitionBean.getBeanByLevelNo(levelNo, hierarchyList);
	}

	public static int getFirstLinkedLevel(List<HierarchyLevelDefinitionBean> hierarchyList) {
		for (int i = 0; i < hierarchyList.size(); i++) {
			HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = hierarchyList.get(i + 1);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyLevelDefinitionBean.levelValueReference)) {
				return hierarchyLevelDefinitionBean.getLevelNo();
			}
		}
		return 0;

	}

	public boolean isUserDefined() {
		return GtnFrameworkWebserviceConstant.USER_DEFINED.equals(levelValueReference);
	}

	public static HierarchyLevelDefinitionBean getBeanByLevelNo(int levelNo,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		if (levelNo > 1 && hierarchyList.get(levelNo - 1).levelNo == levelNo)
			return hierarchyList.get(levelNo - 1);
		for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
			if (levelNo == hierarchyLevelDefinitionBean.levelNo) {
				return hierarchyLevelDefinitionBean;
			}
		}
		return null;
	}

	public void customize(Object[] result) {
		setHierarchyLevelDefinitionSid((Integer) result[0]);
		setLevelNo((((BigDecimal) result[1]).intValue()));
		setLevelName((String) result[2]);
		setLevelValueReference((String) result[3]);
		setHierarchyDefinitionSid((Integer) result[4]);
		setTableName((String) result[5]);
		setFieldName((String) result[6]);
		setVersionNo((Integer) result[7]);
	}

	public static HierarchyLevelDefinitionBean getPreviousLinkedLevel(
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean) {
		for (int i = currnetHierarchyLevelBean.getLevelNo() - 2; i >= 0; i--) {
			if (!hierarchyLevelDefinitionList.get(i).isUserDefined()) {
				return hierarchyLevelDefinitionList.get(i);
			}
		}
		return null;
	}

	public static int countLinkedLevelsAboveSelectedLevelNo(List<HierarchyLevelDefinitionBean> hierarchyList,
			int selectedLevelNo) {
		int count = 0;
		for (int i = selectedLevelNo; i > 0 && i < hierarchyList.size(); i--) {
			HierarchyLevelDefinitionBean currentBean = getBeanByLevelNo(i, hierarchyList);
			if (currentBean != null && !currentBean.isUserDefined()) {
				count++;
			}
		}
		return count;
	}

}