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

public class GtnUIFrameworkPriceProtectionTabTableAction
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

		GtnUIFrameworkBaseComponent priceprotectionpricingTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		
		GtnUIFrameworkBaseComponent priceprotectionviewpricingTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE + "View");

		GtnUIFrameworkBaseComponent pspricingContractId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("pspricingContractId", componentId);

		pspricingContractId.loadFieldValue(String.valueOf(processDataBean.getProcessBean().getPsContractId()));

		if (gtnUIFrameWorkActionConfig.getFieldValues() != null) {
			GtnUIFrameworkBaseComponent recordType = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
			if (String.valueOf(recordType.getValueFromComponent()).replace("[", "").replace("]", "")
					.equals(GtnFrameworkContractDashboardContants.PENDING)) {
				configurePendingTable(priceprotectionpricingTable);
				configureViewTable(priceprotectionviewpricingTable);
				configurePriceProtectionPendingViewTableColumn(priceprotectionviewpricingTable);
				
			} else {
				configureTable(priceprotectionpricingTable);	
				configureViewTable(priceprotectionviewpricingTable);
				configurePriceProtectionViewTableColumn(priceprotectionviewpricingTable);
			}
		}

		GtnUIFrameWorkActionConfig pricingRecordLoadActionConfig = new GtnUIFrameWorkActionConfig();
		pricingRecordLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		pricingRecordLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		pricingRecordLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
		pricingRecordLoadActionConfig
				.addActionParameter(GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_COMPONENT);
		pricingRecordLoadActionConfig.setFieldValues(Arrays.asList(gtnUIFrameWorkActionConfig.getFieldValues().get(0),
				GtnFrameworkContractDashboardContants.HIDDEN_PRICING_PS_COMPONENT));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, pricingRecordLoadActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void configurePendingTable(GtnUIFrameworkBaseComponent priceprotectionpricingTable) {

		priceprotectionpricingTable.getExtPagedTable().setFilterBarVisible(false);
		priceprotectionpricingTable.getExtPagedTable().setEditable(false);
		priceprotectionpricingTable.getExtPagedTable().setReadOnly(true);
		configurePriceProtectionPendingTableColumn(priceprotectionpricingTable);

	}

	private void configureTable(GtnUIFrameworkBaseComponent priceprotectionpricingTable) {

		priceprotectionpricingTable.getExtPagedTable().setFilterBarVisible(true);
		priceprotectionpricingTable.getExtPagedTable().setEditable(true);
		priceprotectionpricingTable.getExtPagedTable().setReadOnly(false);
		configurePriceProtectionTableColumn(priceprotectionpricingTable);

	}

	private void configurePriceProtectionPendingTableColumn(GtnUIFrameworkBaseComponent priceprotectionpricingTable) {

		priceprotectionpricingTable
				.setTableColumns(GtnFrameworkContractDashboardContants.getPriceProtectionPendingColumn());
		priceprotectionpricingTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceProtectionPendingColumnHeader());

	}

	private void configurePriceProtectionTableColumn(GtnUIFrameworkBaseComponent priceprotectionpricingTable) {

		priceprotectionpricingTable.setTableColumns(GtnFrameworkContractDashboardContants.getPriceProtectionColumn());
		priceprotectionpricingTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceProtectionColumnHeader());

	}


	private void configurePriceProtectionPendingViewTableColumn(
			GtnUIFrameworkBaseComponent priceprotectionviewpricingTable) {

		priceprotectionviewpricingTable
				.setTableColumns(GtnFrameworkContractDashboardContants.getPriceProtectionViewPendingColumn());
		priceprotectionviewpricingTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceProtectionViewHeader());

	}

	private void configureViewTable(GtnUIFrameworkBaseComponent priceprotectionviewpricingTable) {

		priceprotectionviewpricingTable.getExtPagedTable().setFilterBarVisible(false);
		priceprotectionviewpricingTable.getExtPagedTable().setEditable(false);
		priceprotectionviewpricingTable.getExtPagedTable().setReadOnly(true);

	}

	private void configurePriceProtectionViewTableColumn(GtnUIFrameworkBaseComponent priceprotectionviewpricingTable) {

		priceprotectionviewpricingTable
				.setTableColumns(GtnFrameworkContractDashboardContants.getPriceProtectionViewColumn());
		priceprotectionviewpricingTable
				.setTableColumnHeaders(GtnFrameworkContractDashboardContants.getPriceProtectionViewHeader());

	}

}
