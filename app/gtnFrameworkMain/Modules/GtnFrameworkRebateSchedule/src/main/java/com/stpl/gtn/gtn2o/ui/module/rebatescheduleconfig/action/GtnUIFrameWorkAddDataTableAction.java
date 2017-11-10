package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkAddDataTableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE);
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		GtnUIFrameworkBaseComponent rSrightResultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE);

		if (rSrightResultTable.getExtPagedTableSize() == 0) {
			tableLogic.configureContainer(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("RSleftResultTable").getValueFromPagedDataTable(),
					tableLogic.getContainerDataSource());
			rSrightResultTable.selectRow(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("RSleftResultTable").getValueFromPagedDataTable());

			GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE).getValueFromPagedDataTable();
			String ifpId = String.valueOf(gtnWsRecordBean.getPropertyValue("systemId"));
			if (GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID) == null) {
				GtnFrameworkRSCommonLogic.generateSessionId();
			}
			GtnUIFrameworkComponentData psPricingTabResultDataTablecomponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData("psRebateSetupTabResultDataTable");
			GtnUIFrameworkPagedTableLogic priceTabTableLogic = psPricingTabResultDataTablecomponentData
					.getCurrentPageTableLogic();
			List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = GtnFrameworkRSCommonLogic
					.addDataTableLoadAction(ifpId,
							"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_ITEM_ADDITION_SERVICE);
			priceTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
			priceTabTableLogic.startSearchProcess(new ArrayList<String>(), Boolean.TRUE);
			GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", true);
			GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
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
