/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import java.util.Collections;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class GtnWsTransactionRequest {

	public GtnWsTransactionRequest() {
		super();
	}

	private String tableName = "";
	private List<String> projectionColumns = new ArrayList<>();
	private String primayColumnID = "";
	private Integer primayColumnValue = 0;
	private List<String> helpercomponentList = new ArrayList<>();
	private String stagingTableName;
	private List<Integer> reprocessIds = new ArrayList<>();
	private List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
	private boolean reprocessFlag = false;
	private List<Integer> uncheckedIds = new ArrayList<>();
	private String demandTypeColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private Integer demandTypeColumnValue = 0;
	private String inventoryLevelColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private Integer inventoryLevelColumnValue = 0;
	private String inventoryTypeColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private Integer inventoryTypeColumnValue = 0;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getProjectionColumns() {
		return projectionColumns != null ? new ArrayList<>(projectionColumns) : projectionColumns;
	}

	public void setProjectionColumns(List<Object> projectionColumns) {
		List<String> columns = new ArrayList<>(projectionColumns.size());
		for (Object column : projectionColumns) {
			columns.add(Objects.toString(column, null));
		}

		this.projectionColumns = columns;
	}

	public String getPrimayColumnID() {
		return primayColumnID;
	}

	public void setPrimayColumnID(String primayColumnID) {
		this.primayColumnID = primayColumnID;
	}

	public Integer getPrimayColumnValue() {
		return primayColumnValue;
	}

	public List<Integer> getUncheckedIds() {
		return uncheckedIds != null ? Collections.unmodifiableList(uncheckedIds) : uncheckedIds;
	}

	public void setUncheckedIds(List<Integer> uncheckedIds) {
		this.uncheckedIds = uncheckedIds != null ? Collections.unmodifiableList(uncheckedIds) : uncheckedIds;
	}

	public void setPrimayColumnValue(Integer primayColumnValue) {
		this.primayColumnValue = primayColumnValue;
	}

	public List<String> getHelpercomponentList() {
		return helpercomponentList != null ? Collections.unmodifiableList(helpercomponentList) : helpercomponentList;
	}

	public void setHelpercomponentList(List<String> helpercomponentList) {
		this.helpercomponentList = helpercomponentList != null ? Collections.unmodifiableList(helpercomponentList)
				: helpercomponentList;
	}

	public String getStagingTableName() {
		return stagingTableName;
	}

	public void setStagingTableName(String stagingTableName) {
		this.stagingTableName = stagingTableName;
	}

	public List<Integer> getReprocessIds() {
		return reprocessIds != null ? Collections.unmodifiableList(reprocessIds) : reprocessIds;
	}

	public void setReprocessIds(List<Integer> reprocessIds) {
		this.reprocessIds = reprocessIds != null ? Collections.unmodifiableList(reprocessIds) : reprocessIds;
	}

	public List<GtnWebServiceSearchCriteria> getSearchCriteria() {
		return searchCriteria != null ? new ArrayList<>(searchCriteria) : searchCriteria;
	}

	public void setSearchCriteria(List<GtnWebServiceSearchCriteria> searchCriteria) {
		this.searchCriteria = searchCriteria != null ? new ArrayList<>(searchCriteria) : searchCriteria;
	}

	public boolean isReprocessFlag() {
		return reprocessFlag;
	}

	public void setReprocessFlag(boolean reprocessFlag) {
		this.reprocessFlag = reprocessFlag;
	}

	public String getDemandTypeColumnName() {
		return demandTypeColumnName;
	}

	public void setDemandTypeColumnName(String demandTypeColumnName) {
		this.demandTypeColumnName = demandTypeColumnName;
	}

	public int getDemandTypeColumnValue() {
		return demandTypeColumnValue;
	}

	public void setDemandTypeColumnValue(int demandTypeColumnValue) {
		this.demandTypeColumnValue = demandTypeColumnValue;
	}

	public String getInventoryLevelColumnName() {
		return inventoryLevelColumnName;
	}

	public void setInventoryLevelColumnName(String inventoryLevelColumnName) {
		this.inventoryLevelColumnName = inventoryLevelColumnName;
	}

	public Integer getInventoryLevelColumnValue() {
		return inventoryLevelColumnValue;
	}

	public void setInventoryLevelColumnValue(Integer inventoryLevelColumnValue) {
		this.inventoryLevelColumnValue = inventoryLevelColumnValue;
	}

	public String getInventoryTypeColumnName() {
		return inventoryTypeColumnName;
	}

	public void setInventoryTypeColumnName(String inventoryTypeColumnName) {
		this.inventoryTypeColumnName = inventoryTypeColumnName;
	}

	public Integer getInventoryTypeColumnValue() {
		return inventoryTypeColumnValue;
	}

	public void setInventoryTypeColumnValue(Integer inventoryTypeColumnValue) {
		this.inventoryTypeColumnValue = inventoryTypeColumnValue;
	}

}
