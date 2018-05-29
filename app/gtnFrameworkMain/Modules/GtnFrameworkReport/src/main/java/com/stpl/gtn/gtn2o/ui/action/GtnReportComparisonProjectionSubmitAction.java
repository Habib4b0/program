package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionSubmitAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			ListDataProvider<GtnWsRecordBean> selectedTableItems = (ListDataProvider<GtnWsRecordBean>) grid
					.getDataProvider();
			List<GtnWsRecordBean> selectedRecords = (List<GtnWsRecordBean>) selectedTableItems.getItems();
			List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = new ArrayList<>();
			for (GtnWsRecordBean recordBean : selectedRecords) {
				GtnReportComparisonProjectionBean comparisonProjectionBean = new GtnReportComparisonProjectionBean();
				comparisonProjectionBean
						.setProjectionName(String.valueOf(recordBean.getPropertyValue("projectionName")));
				comparisonProjectionBean
						.setProjectionDescription(String.valueOf(recordBean.getPropertyValue("description")));
				comparisonProjectionBean.setMarketType(String.valueOf(recordBean.getPropertyValue("marketType")));
				comparisonProjectionBean
						.setContractHolder(String.valueOf(recordBean.getPropertyValue("contractHolder")));
				comparisonProjectionBean.setContract(String.valueOf(recordBean.getPropertyValue("contract")));
				comparisonProjectionBean.setBrand(String.valueOf(recordBean.getPropertyValue("brand")));
				comparisonProjectionBean.setItemNo(String.valueOf(recordBean.getPropertyValueByIndex(6)));
				comparisonProjectionBean.setItemName(String.valueOf(recordBean.getPropertyValueByIndex(7)));
				comparisonProjectionBean
						.setProjectionMasterSid(Integer.parseInt(recordBean.getStringPropertyByIndex(8)));
				comparisonProjectionBean.setCreatedBy(Integer.parseInt(recordBean.getStringPropertyByIndex(10)));
				comparisonProjectionBeanList.add(comparisonProjectionBean);
			}
			GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromParent(
							gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
					.getComponentData();
			idComponentData.setCustomData(comparisonProjectionBeanList);
			String displayValue = getRecordDisplayValue(selectedRecords);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(),
							componentId)
					.setV8PopupFieldValue(displayValue);
		} else {
			GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
			alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			alertAction.addActionParameter("Error");
			alertAction.addActionParameter("No Data is available to submit");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
		}
	}

	private String getRecordDisplayValue(List<GtnWsRecordBean> selectedRecords) {
		if (selectedRecords.size() > 1) {
			return "MULTIPLE";
		} else {
			return selectedRecords.get(0).getPropertyValue("projectionName").toString();
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
