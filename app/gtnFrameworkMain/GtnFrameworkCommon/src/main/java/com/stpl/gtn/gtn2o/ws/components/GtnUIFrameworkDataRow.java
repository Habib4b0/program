package com.stpl.gtn.gtn2o.ws.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkDataRow {

	private List<Object> colList = new ArrayList<>();

	public void addColumnValue(String data) {
		colList.add(data);
	}

	public Object getColumnVAlue(int colIndex) {
		return colList.get(colIndex);
	}

	public List<Object> getColList() {
		return colList == null ? colList : Collections.unmodifiableList(colList);
	}

	public void setColList(List<Object> colList) {
		this.colList = colList == null ? colList : new ArrayList<>(colList);
	}

	public void setElement(int index, Object element) {
		colList.set(index, element);
	}

}
