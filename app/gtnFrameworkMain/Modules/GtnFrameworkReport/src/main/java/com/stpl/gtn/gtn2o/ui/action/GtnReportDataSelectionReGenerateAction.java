package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionReGenerateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		List<GtnWsRecordBean> selectedCustomerList = getSelectedList("dataSelectionTab_customerDualListBox",
				componentId);
		List<GtnWsRecordBean> selectedProductList = getSelectedList("dataSelectionTab_productdualListBoxComp",
				componentId);
		String customViewName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabCustomView",componentId).getCaptionFromV8ComboBox();
		List<GtnReportComparisonProjectionBean> comparisonProjectionsList = (List<GtnReportComparisonProjectionBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_comparisonLookup",componentId).getComponentData().getCustomData();
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_landingScreenVariableBreakdownFrequencyConfig",componentId)
				.getCaptionFromV8ComboBox();

	}

	private List<GtnWsRecordBean> getSelectedList(String tableComponentId, String componentId) {
		GtnUIFrameworkComponentData selectedTableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableComponentId, componentId);
		GtnFrameworkV8DualListBoxBean selectedDualListBoxBean = (GtnFrameworkV8DualListBoxBean) selectedTableComponentData
				.getCustomData();
		TreeGrid<GtnWsRecordBean> selectedRightTable = selectedDualListBoxBean.getRightTable();
		selectedRightTable.expand(selectedRightTable.getTreeData().getRootItems());
		List<GtnWsRecordBean> selectedValues = selectedRightTable.getTreeData().getRootItems();

		List<GtnWsRecordBean> selectedRecordList = new ArrayList<>(10);
		for (GtnWsRecordBean gtnWsRecordBean : selectedValues) {

			selectedRecordList.add(gtnWsRecordBean);
			addSelectedValues(selectedRightTable, gtnWsRecordBean, selectedRecordList);
		}
		return selectedRecordList;
	}

	private void addSelectedValues(TreeGrid<GtnWsRecordBean> rightTable, GtnWsRecordBean selectedvalues,
			List<GtnWsRecordBean> selectedList) {
		for (GtnWsRecordBean gtnWsRecordBean : rightTable.getTreeData().getChildren(selectedvalues)) {
			selectedList.add(gtnWsRecordBean);
			addSelectedValues(rightTable, gtnWsRecordBean, selectedList);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
