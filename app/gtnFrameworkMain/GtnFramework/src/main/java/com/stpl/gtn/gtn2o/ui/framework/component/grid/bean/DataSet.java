package com.stpl.gtn.gtn2o.ui.framework.component.grid.bean;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.List;

public class DataSet {
	private List<Object> columns;
	private List<GtnWsRecordBean> rows;

	public DataSet(List<Object> columns, List<GtnWsRecordBean> rows) {
		this.columns = columns;
		this.rows = rows;
	}

	public List<Object> getColumns() {
		return columns;
	}

	public List<GtnWsRecordBean> getRows() {
		return rows;
	}
}