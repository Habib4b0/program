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

public class GtnUIFrameworkRebateTabTableAction
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

		GtnUIFrameworkBaseComponent rebateDetailTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);

		GtnUIFrameworkBaseComponent rsContractId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("rsContractId",
				componentId);

		rsContractId.loadFieldValue(String.valueOf(processDataBean.getProcessBean().getRsContractId()));

		if (gtnUIFrameWorkActionConfig.getFieldValues() != null) {
			GtnUIFrameworkBaseComponent recordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
			if (String.valueOf(recordType.getValueFromComponent()).replace("[", "").replace("]", "")
					.equals(GtnFrameworkContractDashboardContants.PENDING)) {
				configurePendingTable(rebateDetailTable);
			} else {
				configureTable(rebateDetailTable);

			}
		}

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.REBATE_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_RS_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(gtnUIFrameWorkActionConfig.getFieldValues().get(0),
				GtnFrameworkContractDashboardContants.HIDDEN_RS_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePendingTable(GtnUIFrameworkBaseComponent rebateDetailTable) {

		rebateDetailTable.getExtPagedTable().setFilterBarVisible(false);
		rebateDetailTable.getExtPagedTable().setEditable(false);
		rebateDetailTable.getExtPagedTable().setReadOnly(true);
		configureRebatesPendingTableColumn(rebateDetailTable);

	}

	private void configureTable(GtnUIFrameworkBaseComponent rebateDetailTable) {

		rebateDetailTable.getExtPagedTable().setFilterBarVisible(true);
		rebateDetailTable.getExtPagedTable().setEditable(true);
		rebateDetailTable.getExtPagedTable().setReadOnly(false);
		configureRebatesTableColumn(rebateDetailTable);

	}

	private void configureRebatesPendingTableColumn(GtnUIFrameworkBaseComponent rsCompaniesTable) {

		rsCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getRebatePendingDetailsColumn());
		rsCompaniesTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getRebateDetailsPendingColumnHeader());

	}

	private void configureRebatesTableColumn(GtnUIFrameworkBaseComponent rsCompaniesTable) {

		rsCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getRebateDetailsColumn());
		rsCompaniesTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getRebateDetailsColumnHeader());

	}

}
