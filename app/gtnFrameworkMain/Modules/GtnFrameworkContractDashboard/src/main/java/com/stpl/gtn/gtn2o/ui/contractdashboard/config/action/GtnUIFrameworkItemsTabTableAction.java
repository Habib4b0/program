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

public class GtnUIFrameworkItemsTabTableAction
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

		GtnUIFrameworkBaseComponent itemDetailTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);

		GtnUIFrameworkBaseComponent itemDetailViewTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE + "View");
					
		GtnUIFrameworkBaseComponent ifpContractId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpContractId",
				componentId);

		ifpContractId.loadFieldValue(String.valueOf(processDataBean.getProcessBean().getIfpContractId()));

		if (gtnUIFrameWorkActionConfig.getFieldValues() != null) {
			GtnUIFrameworkBaseComponent recordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
			if (String.valueOf(recordType.getValueFromComponent()).replace("[", "").replace("]", "")
					.equals(GtnFrameworkContractDashboardContants.PENDING)) {
				configurePendingTable(itemDetailTable);
				configureViewTable(itemDetailViewTable);
				configureItemsPendingViewTableColumn(itemDetailViewTable);		
			} else {
				configureTable(itemDetailTable);
				configureViewTable(itemDetailViewTable);
				configureItemsViewTableColumn(itemDetailViewTable);
			}
		}

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.ITEMS_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_IFP_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(gtnUIFrameWorkActionConfig.getFieldValues().get(0),
				GtnFrameworkContractDashboardContants.HIDDEN_IFP_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePendingTable(GtnUIFrameworkBaseComponent itemDetailTable) {

		itemDetailTable.getExtPagedTable().setFilterBarVisible(false);
		itemDetailTable.getExtPagedTable().setEditable(false);
		itemDetailTable.getExtPagedTable().setReadOnly(true);
		configureItemsPendingTableColumn(itemDetailTable);

	}

	private void configureTable(GtnUIFrameworkBaseComponent itemDetailTable) {

		itemDetailTable.getExtPagedTable().setFilterBarVisible(true);
		itemDetailTable.getExtPagedTable().setEditable(true);
		itemDetailTable.getExtPagedTable().setReadOnly(false);
		configureItemsTableColumn(itemDetailTable);

	}

	private void configureItemsPendingTableColumn(GtnUIFrameworkBaseComponent ifpCompaniesTable) {

		ifpCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getItemDetailPendingColumn());
		ifpCompaniesTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getItemDetailPendingHeader());

	}

	private void configureItemsTableColumn(GtnUIFrameworkBaseComponent ifpCompaniesTable) {

		ifpCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getItemDetailColumn());
		ifpCompaniesTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getItemDetailHeader());

	}

	private void configureViewTable(GtnUIFrameworkBaseComponent itemDetailViewTable) {

		itemDetailViewTable.getExtPagedTable().setFilterBarVisible(false);
		itemDetailViewTable.getExtPagedTable().setEditable(false);
		itemDetailViewTable.getExtPagedTable().setReadOnly(true);

	}

	private void configureItemsPendingViewTableColumn(GtnUIFrameworkBaseComponent itemDetailViewTable) {

		itemDetailViewTable.setTableColumns(GtnFrameworkContractDashboardContants.getItemDetailPendingViewColumn());
		itemDetailViewTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getItemDetailViewHeader());

	}

	private void configureItemsViewTableColumn(GtnUIFrameworkBaseComponent itemDetailViewTable) {

		itemDetailViewTable.setTableColumns(GtnFrameworkContractDashboardContants.getItemDetailViewColumn());
		itemDetailViewTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getItemDetailViewHeader());

	}

}
