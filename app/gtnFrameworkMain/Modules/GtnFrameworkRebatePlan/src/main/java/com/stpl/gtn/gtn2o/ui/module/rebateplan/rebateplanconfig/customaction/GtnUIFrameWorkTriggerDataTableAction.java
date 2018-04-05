
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkTriggerDataTableAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cDRPopUpsearchResultTable")
				.getValueFromPagedDataTable();
		String sysId = String.valueOf(gtnWsRecordBean.getProperties().get(4));

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("cDRRulePopUpruleDetailsattachResultTable");

		GtnUIFrameworkPagedTableLogic priceTabTableLogic = componentData.getCurrentPageTableLogic();
		boolean isActiveFlag = true;
		List<GtnWebServiceSearchCriteria> criteriList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria("systemId", sysId, "EQUALS");
		criteriList.add(criteria);
		priceTabTableLogic.setAdditioanlSearchCriteriaList(criteriList);
		priceTabTableLogic.startSearchProcess(new ArrayList<String>(), isActiveFlag);

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
