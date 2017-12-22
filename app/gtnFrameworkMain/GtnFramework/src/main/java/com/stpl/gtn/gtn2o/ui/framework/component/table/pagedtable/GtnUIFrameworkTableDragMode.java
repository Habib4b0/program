package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable;

import org.asi.ui.extfilteringtable.ExtCustomTable.TableDragMode;

public enum GtnUIFrameworkTableDragMode {
	NONE(TableDragMode.NONE), ROW(TableDragMode.ROW), MULTIROW(TableDragMode.MULTIROW);
	private final TableDragMode dragMode;

	private GtnUIFrameworkTableDragMode(TableDragMode dragMode) {
		this.dragMode = dragMode;
	}

	public TableDragMode getDragMode() {
		return dragMode;
	}

}
