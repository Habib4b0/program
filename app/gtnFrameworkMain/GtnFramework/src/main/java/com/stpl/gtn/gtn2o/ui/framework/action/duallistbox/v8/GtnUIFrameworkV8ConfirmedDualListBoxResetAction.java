package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;

public class GtnUIFrameworkV8ConfirmedDualListBoxResetAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(String.valueOf(actionParamsList.get(0)), componentId);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) gtnUIFrameworkComponentData
				.getCustomData();

		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();

		Optional.ofNullable(leftTable).ifPresent(left -> leftTable.setItems(new ArrayList<>()));
		
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(actionParamsList.get(0)),
				componentId);
		Object[] columns = baseComponent.getComponentConfig().getGtnUIFrameworkV8DualListBoxConfig().getLeftVisibleColumns();
		for(int i = 0; i< columns.length;i++) {
			Component component = leftTable.getHeaderRow(1).getCell(columns[i].toString()).getComponent();
			HorizontalLayout horizontalLayout = (HorizontalLayout) component;
			if (horizontalLayout.getComponent(0) instanceof TextField) {				
				TextField textField=(TextField) horizontalLayout.getComponent(0);
				textField.setValue("");
				textField.setPlaceholder("Show all");
			}
		}
		Optional.ofNullable(rightTable).ifPresent(right -> {
			right.getTreeData().clear();
			right.getDataProvider().refreshAll();
		});
		Optional.ofNullable(treeBuilder).ifPresent(GtnUIFrameworkHierarchyTreeBuilder::clearRootNode);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
