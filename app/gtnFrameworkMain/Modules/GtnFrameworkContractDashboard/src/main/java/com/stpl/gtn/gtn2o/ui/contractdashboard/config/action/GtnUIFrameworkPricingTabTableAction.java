package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkPricingTabTableAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	/*
	 * Param 0 - Custom Action Class Name , Param 1 - Source Table Id to fetch
	 * value, Param 2 - Index of the property to send as extra parameter, Param
	 * 3 - Destination Table Id to Load
	 */
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);

		GtnUIFrameworkBaseComponent pricingDetailTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);

		GtnUIFrameworkBaseComponent pricingDetailViewTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE + "View");

		GtnUIFrameworkBaseComponent psContractId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psContractId",
				componentId);

		psContractId.loadFieldValue(String.valueOf(processDataBean.getProcessBean().getPsContractId()));

		if (gtnUIFrameWorkActionConfig.getFieldValues() != null) {
			GtnUIFrameworkBaseComponent recordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
			if (String.valueOf(recordType.getValueFromComponent()).replace("[", "").replace("]", "")
					.equals(GtnFrameworkContractDashboardContants.PENDING)) {
				configurePendingTable(pricingDetailTable);
				configureViewTable(pricingDetailViewTable);
				configurePriceDetailsPendingViewTableColumn(pricingDetailViewTable);
			} else {
				configureTable(pricingDetailTable);
				configureViewTable(pricingDetailViewTable);
				configurePriceDetailsViewTableColumn(pricingDetailViewTable);

			}
		}

		GtnUIFrameWorkActionConfig cdPricingTabRecordTableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		cdPricingTabRecordTableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cdPricingTabRecordTableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		cdPricingTabRecordTableLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_DETAIL_TABLE);
		cdPricingTabRecordTableLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_PS_COMPONENT);
		cdPricingTabRecordTableLoadActionConfig
				.setFieldValues(Arrays.asList(gtnUIFrameWorkActionConfig.getFieldValues().get(0),
						GtnFrameworkContractDashboardContants.HIDDEN_PS_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, cdPricingTabRecordTableLoadActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePendingTable(GtnUIFrameworkBaseComponent pricingDetailTable) {

		pricingDetailTable.getExtPagedTable().setFilterBarVisible(false);
		pricingDetailTable.getExtPagedTable().setEditable(false);
		pricingDetailTable.getExtPagedTable().setReadOnly(true);
		configurePriceDetailsPendingTableColumn(pricingDetailTable);

	}

	private void configureTable(GtnUIFrameworkBaseComponent pricingDetailTable) {

		pricingDetailTable.getExtPagedTable().setFilterBarVisible(true);
		pricingDetailTable.getExtPagedTable().setEditable(true);
		pricingDetailTable.getExtPagedTable().setReadOnly(false);
		configurePriceDetailsTableColumn(pricingDetailTable);

	}

	private void configurePriceDetailsPendingTableColumn(GtnUIFrameworkBaseComponent pricingDetailTable) {

		pricingDetailTable.setTableColumns(GtnFrameworkContractDashboardContants.getPriceDetailPendingColumn());
		pricingDetailTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceDetailPendingColumnHeader());

	}

	private void configurePriceDetailsTableColumn(GtnUIFrameworkBaseComponent pricingDetailTable) {

		pricingDetailTable.setTableColumns(GtnFrameworkContractDashboardContants.getPriceDetailColumn());
		pricingDetailTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceDetailColumnHeader());

	}

	private void configureViewTable(GtnUIFrameworkBaseComponent pricingDetailViewTable) {

		pricingDetailViewTable.getExtPagedTable().setFilterBarVisible(false);
		pricingDetailViewTable.getExtPagedTable().setEditable(false);
		pricingDetailViewTable.getExtPagedTable().setReadOnly(true);

	}

	private void configurePriceDetailsPendingViewTableColumn(GtnUIFrameworkBaseComponent pricingDetailViewTable) {

		pricingDetailViewTable.setTableColumns(GtnFrameworkContractDashboardContants.getPriceDetailPendingViewColumn());
		pricingDetailViewTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceDetailViewColumnHeader());

	}

	private void configurePriceDetailsViewTableColumn(GtnUIFrameworkBaseComponent pricingDetailViewTable) {

		pricingDetailViewTable.setTableColumns(GtnFrameworkContractDashboardContants.getPriceDetailViewColumn());
		pricingDetailViewTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceDetailViewColumnHeader());

	}

}
