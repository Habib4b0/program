package com.stpl.addons.grid.paged.bean;

import java.util.List;
import java.util.Set;

public class DataSet {
	private Set<String> columns;
	private List<Row> rows;

	public DataSet(Set<String> columns, List<Row> rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public Set<String> getColumns() {
		return columns;
	}

	public List<Row> getRows() {
		return rows;
	}
}