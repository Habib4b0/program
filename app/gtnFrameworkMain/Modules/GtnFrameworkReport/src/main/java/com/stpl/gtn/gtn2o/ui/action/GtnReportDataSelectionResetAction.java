package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_privateViews").setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("reportLandingScreen_publicViews").setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(1).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(2).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(6).toString()).setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(7).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(8).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(11).toString()).getComponentData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) componentData.getCustomData();
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		leftTable.setItems(new ArrayList<>());

		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		if (rightTable.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean recordBean = rightTable.getTreeData().getRootItems().get(0);
			rightTable.getTreeData().removeItem(recordBean);
			rightTable.getDataProvider().refreshAll();
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(12).toString()).setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(13).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(14).toString())
				.loadV8ComboBoxComponentValue(0);

		GtnUIFrameworkComponentData productComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(16).toString()).getComponentData();
		GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productComponentData
				.getCustomData();
		Grid<GtnWsRecordBean> productLeftTable = productDualListBoxBean.getLeftTable();
		productLeftTable.setItems(new ArrayList<>());

		TreeGrid<GtnWsRecordBean> productRightTable = productDualListBoxBean.getRightTable();
		if (productRightTable.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean productRecordBean = productRightTable.getTreeData().getRootItems().get(0);
			productRightTable.getTreeData().removeItem(productRecordBean);
			productRightTable.getDataProvider().refreshAll();
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(17).toString())
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(18).toString()).loadV8MultiSelectValue();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(19).toString()).setV8PopupFieldValue(" ");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(20).toString())
				.loadV8ComboBoxComponentValue(0);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
