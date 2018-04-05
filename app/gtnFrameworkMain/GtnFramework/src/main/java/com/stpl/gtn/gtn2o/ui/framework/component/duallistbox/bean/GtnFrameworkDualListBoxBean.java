package com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean;

import com.stpl.addons.grid.paged.bean.Row;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnFrameworkDualListBoxBean {
	private Grid<GtnWsRecordBean> leftTable;
	private TreeGrid<GtnWsRecordBean> rightTable;
	private Button moveLeft;
	private Button moveRight;
	private Button moveAllRight;
	private GtnUIFrameworkDualListBoxComponent gtnUIFrameworkDualListBoxComponent;
	private List<Object> gtnDualListBoxqueryParameters;
	private GtnUIFrameworkDualListBoxConfig dualListBoxConfig;
	private final GtnUIFrameworkHierarchyTreeBuilder treeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();

	public GtnFrameworkDualListBoxBean(Grid<GtnWsRecordBean> leftTable, TreeGrid<GtnWsRecordBean> rightTable, Button moveLeft,
			Button moveRight, Button moveAllRight,
			GtnUIFrameworkDualListBoxComponent gtnUIFrameworkDualListBoxComponent,
			GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		super();
		this.leftTable = leftTable;
		this.rightTable = rightTable;
		this.moveLeft = moveLeft;
		this.moveRight = moveRight;
		this.moveAllRight = moveAllRight;
		this.gtnUIFrameworkDualListBoxComponent = gtnUIFrameworkDualListBoxComponent;
		this.dualListBoxConfig = dualListBoxConfig;
	}

	public Grid<GtnWsRecordBean> getLeftTable() {
		return leftTable;
	}

	public void setLeftTable(Grid<GtnWsRecordBean> leftTable) {
		this.leftTable = leftTable;
	}

	public TreeGrid<GtnWsRecordBean> getRightTable() {
		return rightTable;
	}

	public void setRightTable(TreeGrid<GtnWsRecordBean> rightTable) {
		this.rightTable = rightTable;
	}

	public Button getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(Button moveLeft) {
		this.moveLeft = moveLeft;
	}

	public Button getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(Button moveRight) {
		this.moveRight = moveRight;
	}

	public Button getMoveAllRight() {
		return moveAllRight;
	}

	public void setMoveAllRight(Button moveAllRight) {
		this.moveAllRight = moveAllRight;
	}

	public GtnUIFrameworkDualListBoxComponent getGtnUIFrameworkDualListBoxComponent() {
		return gtnUIFrameworkDualListBoxComponent;
	}

	public void setGtnUIFrameworkDualListBoxComponent(
			GtnUIFrameworkDualListBoxComponent gtnUIFrameworkDualListBoxComponent) {
		this.gtnUIFrameworkDualListBoxComponent = gtnUIFrameworkDualListBoxComponent;
	}

	public List<Object> getGtnDualListBoxqueryParameters() {
		return gtnDualListBoxqueryParameters == null ? gtnDualListBoxqueryParameters
				: Collections.unmodifiableList(gtnDualListBoxqueryParameters);
	}

	public void setGtnDualListBoxqueryParameters(List<Object> gtnDualListBoxqueryParameters) {
		this.gtnDualListBoxqueryParameters = new ArrayList<>(gtnDualListBoxqueryParameters);
	}

	public GtnUIFrameworkDualListBoxConfig getDualListBoxConfig() {
		return dualListBoxConfig;
	}

	public void setDualListBoxConfig(GtnUIFrameworkDualListBoxConfig dualListBoxConfig) {
		this.dualListBoxConfig = dualListBoxConfig;
	}

	public GtnUIFrameworkHierarchyTreeBuilder getTreeBuilder() {
		return treeBuilder;
	}

        @SuppressWarnings("unchecked")
	public void clearLeftTableData() {
		leftTable.setItems(new ArrayList<>());
	}

	public void clearRightTableData() {
		rightTable.getTreeData().clear();
		treeBuilder.clearRootNode();
	}

	public List<GtnWsRecordBean> getDataFromRightTable() {
		return treeBuilder.getAllNodeDataAsList();
	}

	public void addGtnDualListBoxqueryParameters(Object parameter) {
		if (gtnDualListBoxqueryParameters != null) {
			gtnDualListBoxqueryParameters.add(parameter);
		}

	}
}
