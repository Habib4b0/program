package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean;

import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnFrameworkV8DualListBoxBean {
	private Grid<GtnWsRecordBean> leftTable;
	private TreeGrid<GtnWsRecordBean> rightTable;
	private Button moveLeftBtn;
	private Button moveRightBtn;
	private Button moveAllRightBtn;
	private GtnUIFrameworkV8DualListBoxComponent gtnUIFrameworkDualListBoxComponent;
	private List<Object> gtnDualListBoxqueryParameters;
	private GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig;
	private final GtnUIFrameworkHierarchyTreeBuilder treeBuilder = new GtnUIFrameworkHierarchyTreeBuilder();

	public GtnFrameworkV8DualListBoxBean(Grid<GtnWsRecordBean> leftTable, TreeGrid<GtnWsRecordBean> rightTable, Button moveLeft,
			Button moveRight, Button moveAllRight,
			GtnUIFrameworkV8DualListBoxComponent gtnUIFrameworkDualListBoxComponent,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		super();
		this.leftTable = leftTable;
		this.rightTable = rightTable;
		this.moveLeftBtn = moveLeft;
		this.moveRightBtn = moveRight;
		this.moveAllRightBtn = moveAllRight;
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

	public Button getMoveLeftBtn() {
		return moveLeftBtn;
	}

	public void setMoveLeftBtn(Button moveLeftBtn) {
		this.moveLeftBtn = moveLeftBtn;
	}

	public Button getMoveRightBtn() {
		return moveRightBtn;
	}

	public void setMoveRightBtn(Button moveRightBtn) {
		this.moveRightBtn = moveRightBtn;
	}

	public Button getMoveAllRightBtn() {
		return moveAllRightBtn;
	}

	public void setMoveAllRightBtn(Button moveAllRightBtn) {
		this.moveAllRightBtn = moveAllRightBtn;
	}

	public GtnUIFrameworkV8DualListBoxComponent getGtnUIFrameworkDualListBoxComponent() {
		return gtnUIFrameworkDualListBoxComponent;
	}

	public void setGtnUIFrameworkDualListBoxComponent(
			GtnUIFrameworkV8DualListBoxComponent gtnUIFrameworkDualListBoxComponent) {
		this.gtnUIFrameworkDualListBoxComponent = gtnUIFrameworkDualListBoxComponent;
	}

	public List<Object> getGtnDualListBoxqueryParameters() {
		return gtnDualListBoxqueryParameters == null ? gtnDualListBoxqueryParameters
				: Collections.unmodifiableList(gtnDualListBoxqueryParameters);
	}
	
	public List<Object> getGtnDualListBoxqueryParametersList() {
		return gtnDualListBoxqueryParameters == null ? null
				: gtnDualListBoxqueryParameters;
	}
	
	public void setGtnDualListBoxqueryParameters(List<Object> gtnDualListBoxqueryParameters) {
		this.gtnDualListBoxqueryParameters = new ArrayList<>(gtnDualListBoxqueryParameters);
	}

	public GtnUIFrameworkV8DualListBoxConfig getDualListBoxConfig() {
		return dualListBoxConfig;
	}

	public void setDualListBoxConfig(GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
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
