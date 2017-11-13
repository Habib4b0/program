package com.stpl.gtn.gtn2o.ws.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GtnUIFrameworkDataTable {

	private List<GtnUIFrameworkDataRow> dataTable = new ArrayList<>();

	public void addDataRow(GtnUIFrameworkDataRow dataRow) {
		dataTable.add(dataRow);
	}

	public void addDataRow(List<Object> colList) {
		GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
		newDataRow.setColList(colList);
		dataTable.add(newDataRow);
	}

	public void addData(List<Object[]> colList) {
		GtnUIFrameworkDataRow newDataRow;

		for (Object[] ob : colList) {
			newDataRow = new GtnUIFrameworkDataRow();
			newDataRow.setColList(Arrays.asList(ob));
			dataTable.add(newDataRow);
		}
	}

	public List<GtnUIFrameworkDataRow> getDataTable() {
		return this.dataTable;
	}

	public GtnUIFrameworkDataRow getDataRow(int rowIndex) {
		return this.dataTable.get(rowIndex);
	}
}
