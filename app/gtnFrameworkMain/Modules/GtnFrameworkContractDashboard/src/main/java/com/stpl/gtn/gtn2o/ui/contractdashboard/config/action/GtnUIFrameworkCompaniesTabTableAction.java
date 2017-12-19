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

public class GtnUIFrameworkCompaniesTabTableAction
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

		GtnUIFrameworkBaseComponent companiesDetailTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		
		GtnUIFrameworkBaseComponent companiesDetailViewTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE + "View");	

		GtnUIFrameworkBaseComponent cfpContractId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpContractId",
				componentId);

		cfpContractId.loadFieldValue(String.valueOf(processDataBean.getProcessBean().getCfpContractId()));

		if (gtnUIFrameWorkActionConfig.getFieldValues() != null) {
			GtnUIFrameworkBaseComponent recordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
			if (String.valueOf(recordType.getValueFromComponent()).replace("[", "").replace("]", "")
					.equals(GtnFrameworkContractDashboardContants.PENDING)) {
				configurePendingTable(companiesDetailTable);
				configureViewTable(companiesDetailViewTable);
				configureCompaniesPendingViewTableColumn(companiesDetailViewTable);
				
				
			} else {
				configureTable(companiesDetailTable);
				configureViewTable(companiesDetailViewTable);
				configureCompaniesViewTableColumn(companiesDetailViewTable);
				

			}
		}

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.COMPANIES_TAB_DETAIL_TABLE);
		tableLoadActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT);
		tableLoadActionConfig.setFieldValues(Arrays.asList(gtnUIFrameWorkActionConfig.getFieldValues().get(0),
				GtnFrameworkContractDashboardContants.HIDDEN_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePendingTable(GtnUIFrameworkBaseComponent companiesDetailTable) {

		companiesDetailTable.getExtPagedTable().setFilterBarVisible(false);
		companiesDetailTable.getExtPagedTable().setEditable(false);
		companiesDetailTable.getExtPagedTable().setReadOnly(true);
		configureCompaniesPendingTableColumn(companiesDetailTable);

	}

	private void configureTable(GtnUIFrameworkBaseComponent companiesDetailTable) {

		companiesDetailTable.getExtPagedTable().setFilterBarVisible(true);
		companiesDetailTable.getExtPagedTable().setEditable(true);
		companiesDetailTable.getExtPagedTable().setReadOnly(false);
		configureCompaniesTableColumn(companiesDetailTable);

	}

	private void configureCompaniesPendingTableColumn(GtnUIFrameworkBaseComponent cfpCompaniesTable) {

		cfpCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getCompanyDetailPendingColumn());
		cfpCompaniesTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getCompanyDetailPendingHeader());

	}

	private void configureCompaniesTableColumn(GtnUIFrameworkBaseComponent cfpCompaniesTable) {

		cfpCompaniesTable.setTableColumns(GtnFrameworkContractDashboardContants.getCompanyDetailColumn());
		cfpCompaniesTable.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getCompanyDetailHeader());

	}

	private void configureViewTable(GtnUIFrameworkBaseComponent companiesDetailViewTable) {

		companiesDetailViewTable.getExtPagedTable().setFilterBarVisible(false);
		companiesDetailViewTable.getExtPagedTable().setEditable(false);
		companiesDetailViewTable.getExtPagedTable().setReadOnly(true);

	}

	private void configureCompaniesPendingViewTableColumn(GtnUIFrameworkBaseComponent companiesDetailViewTable) {

		companiesDetailViewTable
				.setTableColumns(GtnFrameworkContractDashboardContants.getCompanyDetailPendingViewColumn());
		companiesDetailViewTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getCompanyDetailViewHeader());

	}

	private void configureCompaniesViewTableColumn(GtnUIFrameworkBaseComponent companiesDetailViewTable) {

		companiesDetailViewTable.setTableColumns(GtnFrameworkContractDashboardContants.getCompanyDetailViewColumn());
		companiesDetailViewTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getCompanyDetailViewHeader());

	}

}
