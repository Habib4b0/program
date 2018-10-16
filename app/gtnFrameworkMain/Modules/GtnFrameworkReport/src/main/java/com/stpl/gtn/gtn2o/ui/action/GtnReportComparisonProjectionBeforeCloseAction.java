package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionBeforeCloseAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			@SuppressWarnings("unchecked")
			ListDataProvider<GtnWsRecordBean> selectedTableItems = (ListDataProvider<GtnWsRecordBean>) grid
					.getDataProvider();
			List<GtnWsRecordBean> selectedRecords = (List<GtnWsRecordBean>) selectedTableItems.getItems();
			if (selectedRecords == null || selectedRecords.isEmpty()) {
				GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(
								gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
						.getComponentData();
				GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(idComponentData.getViewId()).getComponentData().getSharedPopupData();
				if (Optional.ofNullable(dataSelectionBean).isPresent()) {
					dataSelectionBean.setComparisonProjectionBeanList(null);
				}
				idComponentData.setCustomData(null);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(
								gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
						.setV8PopupFieldValue(" ");

				loadReportComparisonBasisOnClose(gtnUIFrameWorkActionConfig.getActionParameterList(), componentId,
						dataSelectionBean);
			}
		}
		GtnUIFrameWorkActionConfig closeAction = (GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(5);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closeAction);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadReportComparisonBasisOnClose(List<Object> actionParamList, String componentId,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String nameSpace = actionParamList.get(4).toString();
		if (!nameSpace.equals("comparisonLookup")) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkReportStringConstants.REPORT_DASHBOARD_COMPARISON_LOOKUP, componentId)
					.getComponentData().setCustomData(dataSelectionBean.getComparisonProjectionBeanList());

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							GtnFrameworkReportStringConstants.REPORT_DASHBOARD_COMPARISON_LOOKUP, componentId)
					.setV8PopupFieldValue(" ");

			List<String> comparisonBasisList = new ArrayList<>(15);
			comparisonBasisList.add("Actuals");
			comparisonBasisList.add("Accruals");
			comparisonBasisList.add("Current Projection");
			List<Integer> idList = IntStream.range(1, comparisonBasisList.size() + 1).boxed()
					.collect(Collectors.toList());
			GtnUIFrameworkComboBoxConfig comparisonBasisComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(actionParamList.get(3).toString(), componentId)
					.getComponentConfig().getGtnComboboxConfig();
			comparisonBasisComboboxConfig.setItemCaptionValues(comparisonBasisList);
			comparisonBasisComboboxConfig.setItemValues(idList);

			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponentFromParent(actionParamList.get(3).toString(), componentId, Arrays.asList(""));
		}
	}

}
