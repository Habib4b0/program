package com.stpl.gtn.gtn2o.ui.framework.component.grid.config;

import java.util.Set;

public class PagedTreeTableConfig extends PagedTableConfig {

	String hierarchyNoColumn;
	String frequency;
	int levelNo = 1;
	String levelNoColumn;
	Set<String> leftVisibleColumns;
	int rowsPerLevelItem;

	public PagedTreeTableConfig() {
		super();
	}

	public String getHierarchyNoColumn() {
		return hierarchyNoColumn;
	}

	public void setHierarchyNoColumn(String hierarchyNoColumn) {
		this.hierarchyNoColumn = hierarchyNoColumn;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public String getLevelNoColumn() {
		return levelNoColumn;
	}

	public void setLevelNoColumn(String levelNoColumn) {
		this.levelNoColumn = levelNoColumn;
	}

	public Set<String> getLeftVisibleColumns() {
		return leftVisibleColumns;
	}

	public void setLeftVisibleColumns(Set<String> leftVisibleColumns) {
		this.leftVisibleColumns = leftVisibleColumns;
	}

	public int getRowsPerLevelItem() {
		return rowsPerLevelItem;
	}

	public void setRowsPerLevelItem(int rowsPerLevelItem) {
		this.rowsPerLevelItem = rowsPerLevelItem;
	}

}
