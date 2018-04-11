package com.stpl.addons.grid.paged.config;

import java.util.List;
import java.util.Set;

import com.stpl.addons.grid.paged.bean.QueryBean;

public class PagedTableConfig {

	private List<String> visibleColumns;
	private List<String> columnHeaders;
	private QueryBean queryBean;

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

}
