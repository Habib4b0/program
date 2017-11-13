/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkNewTableConfig {

	private String[] tableVisibleHeader = null;
	private Class<?>[] tableColumnDataType = null;
	private Object[] tableColumnMappingId = null;
	private String resultSetUrl = null;
	private boolean filterBar = false;
	private boolean selectable = false;
	private boolean editable = false;
	private int pageLength = 15;
	private boolean multiSelect = false;
	private List<GtnUIFrameWorkActionConfig> postCreationActionConfigList = new ArrayList<>();
	private List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList = new ArrayList<>();
	private List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
	private GtnUIFrameWorkActionConfig recordTypeManageActionConfig = null;

	public String[] getTableVisibleHeader() {
		return tableVisibleHeader == null ? tableVisibleHeader : tableVisibleHeader.clone();
	}

	public void setTableVisibleHeader(String[] tableVisibleHeader) {
		this.tableVisibleHeader = tableVisibleHeader.clone();
	}

	public Class<?>[] getTableColumnDataType() {
		return tableColumnDataType == null ? tableColumnDataType : tableColumnDataType.clone();
	}

	public void setTableColumnDataType(Class<?>[] tableColumnDataType) {
		this.tableColumnDataType = tableColumnDataType.clone();
	}

	public Object[] getTableColumnMappingId() {
		return tableColumnMappingId == null ? tableColumnMappingId : tableColumnMappingId.clone();
	}

	public void setTableColumnMappingId(Object[] tableColumnMappingId) {
		this.tableColumnMappingId = tableColumnMappingId.clone();
	}

	public String getResultSetUrl() {
		return resultSetUrl;
	}

	public void setResultSetUrl(String resultSetUrl) {
		this.resultSetUrl = resultSetUrl;
	}

	public boolean isFilterBar() {
		return filterBar;
	}

	public void setFilterBar(boolean filterBar) {
		this.filterBar = filterBar;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<GtnUIFrameWorkActionConfig> getPostCreationActionConfigList() {
		return postCreationActionConfigList == null ? postCreationActionConfigList
				: Collections.unmodifiableList(postCreationActionConfigList);
	}

	public void setPostCreationActionConfigList(List<GtnUIFrameWorkActionConfig> postCreationActionConfigList) {
		this.postCreationActionConfigList = new ArrayList<>(postCreationActionConfigList);
	}

	public void setItemDoubleClickActionConfigList(List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList) {
		this.itemDoubleClickActionConfigList = new ArrayList<>(itemDoubleClickActionConfigList);
	}

	public void addItemDoubleClickActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.itemDoubleClickActionConfigList == null) {
			this.itemDoubleClickActionConfigList = new ArrayList<>();
		}
		this.itemDoubleClickActionConfigList.add(actionConfig);
	}

	public void addPostCreationActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.postCreationActionConfigList == null) {
			this.postCreationActionConfigList = new ArrayList<>();
		}
		this.postCreationActionConfigList.add(actionConfig);
	}

	public List<GtnUIFrameWorkActionConfig> getItemDoubleClickActionConfigList() {
		return itemDoubleClickActionConfigList == null ? itemDoubleClickActionConfigList
				: Collections.unmodifiableList(itemDoubleClickActionConfigList);
	}

	public List<GtnUIFrameWorkActionConfig> getItemClickActionConfigList() {
		return itemClickActionConfigList == null ? itemClickActionConfigList
				: Collections.unmodifiableList(itemClickActionConfigList);
	}

	public void setItemClickActionConfigList(List<GtnUIFrameWorkActionConfig> itemClickActionConfigList) {
		this.itemClickActionConfigList = new ArrayList<>(itemClickActionConfigList);
	}

	public void addItemClickActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.itemClickActionConfigList == null) {
			this.itemClickActionConfigList = new ArrayList<>();
		}
		this.itemClickActionConfigList.add(actionConfig);
	}

	public GtnUIFrameWorkActionConfig getRecordTypeManageActionConfig() {
		return recordTypeManageActionConfig;
	}

	public void setRecordTypeManageActionConfig(GtnUIFrameWorkActionConfig recordTypeManageActionConfig) {
		this.recordTypeManageActionConfig = recordTypeManageActionConfig;
	}

}
