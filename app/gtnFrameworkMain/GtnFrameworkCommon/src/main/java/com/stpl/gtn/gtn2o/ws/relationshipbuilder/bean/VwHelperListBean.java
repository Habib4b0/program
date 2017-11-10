/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

/**
 *
 * @author Mahesh.James
 */
public class VwHelperListBean {

	public VwHelperListBean() {
		super();
	}

	private String uniqueId;
	private String actualTableName;
	private String actualColumnName;
	private String referenceTableName;
	private String referenceColumnName;
	private String mappingColumnName;
	private String descColumnName;
	private String listName;
	private String primaryKeyColumnName;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getActualTableName() {
		return actualTableName;
	}

	public void setActualTableName(String actualTableName) {
		this.actualTableName = actualTableName;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

	public String getReferenceTableName() {
		return referenceTableName;
	}

	public void setReferenceTableName(String referenceTableName) {
		this.referenceTableName = referenceTableName;
	}

	public String getReferenceColumnName() {
		return referenceColumnName;
	}

	public void setReferenceColumnName(String referenceColumnName) {
		this.referenceColumnName = referenceColumnName;
	}

	public String getMappingColumnName() {
		return mappingColumnName;
	}

	public void setMappingColumnName(String mappingColumnName) {
		this.mappingColumnName = mappingColumnName;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	public String getDescColumnName() {
		return descColumnName;
	}

	public void setDescColumnName(String descColumnName) {
		this.descColumnName = descColumnName;
	}

	@Override
	public String toString() {
		return "VwHelperListDto [uniqueId=" + uniqueId + ", actualTableName=" + actualTableName + ", actualColumnName="
				+ actualColumnName + ", referenceTableName=" + referenceTableName + ", referenceColumnName="
				+ referenceColumnName + ", mappingColumnName=" + mappingColumnName + ", listName=" + listName
				+ ", primaryKeyColumnName=" + primaryKeyColumnName + "]";
	}

}
