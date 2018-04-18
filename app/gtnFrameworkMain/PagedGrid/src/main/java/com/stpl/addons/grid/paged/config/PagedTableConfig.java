package com.stpl.addons.grid.paged.config;

import java.util.HashMap;
import java.util.List;

import com.stpl.addons.grid.paged.bean.QueryBean;

public class PagedTableConfig {

	private List<String> visibleColumns;
	private List<String> columnHeaders;
	private QueryBean queryBean;
	private HashMap<String, Object> filterValueMap;
	
	public QueryBean getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(QueryBean queryBean) {
		this.queryBean = queryBean;
	}

	public List<String> getVisibleColumns() {
		return visibleColumns;
	}

	public void setVisibleColumns(List<String> visibleColumns) {
		this.visibleColumns = visibleColumns;
	}

	public List<String> getColumnHeaders() {
		return columnHeaders;
	}

	public void setColumnHeaders(List<String> columnHeaders) {
		this.columnHeaders = columnHeaders;
	}

	public HashMap<String, Object> getFilterValueMap() {
		return filterValueMap;
	}

	public void setFilterValueMap(HashMap<String, Object> filterValueMap) {
		this.filterValueMap = filterValueMap;
	}

}
