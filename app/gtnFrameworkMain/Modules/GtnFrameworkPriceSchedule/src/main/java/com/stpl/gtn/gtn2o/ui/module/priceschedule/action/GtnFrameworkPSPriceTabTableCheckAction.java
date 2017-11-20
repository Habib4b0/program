package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceTabValueChangeManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPSPriceTabTableCheckAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsCheckAllUpdateBean checkAllUpdateBean = new GtnWsCheckAllUpdateBean();
		checkAllUpdateBean.setCheckAll(true);
		checkAllUpdateBean.setPropertyId("checkRecordId");
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("psPricingTabResultDataTable");

		ExtPagedTable<?> compniesTable = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPricingTabResultDataTable")
				.getExtPagedTable();
		checkAllUpdateBean.setValue(compniesTable.getColumnCheckBox("checkRecordId"));

		GtnUIFrameworkGlobalUI.updateFieldByMassUpdate(checkAllUpdateBean,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PRICETAB_POPULATE_SERVICE);

		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		GtnUIFrameworkPagedTableLogic logic = tableBaseComponent.getLogicFromPagedDataTable();
		logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), Boolean.TRUE);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkPSPriceTabTableCheckAction();
	}

}
