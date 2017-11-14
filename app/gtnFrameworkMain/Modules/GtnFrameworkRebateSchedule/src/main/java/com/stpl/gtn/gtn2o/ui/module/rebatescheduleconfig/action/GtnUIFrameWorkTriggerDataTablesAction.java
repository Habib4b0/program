
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSCommonLogic;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.TabSheet;

public class GtnUIFrameWorkTriggerDataTablesAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		TabSheet cfpTabSheet = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getAsTabSheet();

		int index = cfpTabSheet.getTabPosition(cfpTabSheet.getTab(cfpTabSheet.getSelectedTab()));
		if (index == 2) {

			GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPrightResultTable")
					.getValueFromDataTable();
			String ifpId = gtnWsRecordBean.getPropertyValueByIndex(7).toString();
			GtnFrameworkRSCommonLogic.generateSessionId();
			List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = GtnFrameworkRSCommonLogic
					.addDataTableLoadAction(ifpId,
							"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_ITEM_ADDITION_SERVICE);

			GtnUIFrameworkComponentData psPricingTabResultDataTablecomponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData("psPricingTabResultDataTable");
			GtnUIFrameworkPagedTableLogic priceTabTableLogic = psPricingTabResultDataTablecomponentData
					.getCurrentPageTableLogic();
			priceTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
			priceTabTableLogic.startSearchProcess(new ArrayList<String>(), Boolean.TRUE);

			GtnUIFrameworkComponentData psPriceProtectionTabResultDataTableomponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData("psPriceProtectionTabResultDataTable");
			GtnUIFrameworkPagedTableLogic priceProtectionTabTableLogic = psPriceProtectionTabResultDataTableomponentData
					.getCurrentPageTableLogic();
			priceProtectionTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
			priceProtectionTabTableLogic.startSearchProcess(new ArrayList<String>(), Boolean.TRUE);
			GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", true);
		}

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
