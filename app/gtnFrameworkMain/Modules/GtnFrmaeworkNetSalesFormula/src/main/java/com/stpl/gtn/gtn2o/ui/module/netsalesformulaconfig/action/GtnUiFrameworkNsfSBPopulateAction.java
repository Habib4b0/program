package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;

public class GtnUiFrameworkNsfSBPopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		GtnWsRecordBean dto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewId + "massUpdateNetSalesRuleNo").getCustomData();

		Object newValue = null;

		if (dto.getPropertyValue("systemId") != null) {
			newValue = dto.getPropertyValue("systemId");
		} else {
			newValue = dto.getPropertyValueByIndex(dto.getProperties().size() - 1);
		}
		int ruleSid = Integer.parseInt(String.valueOf(newValue));
		if (GtnFrameworkNSFCommonLogic.updateField("ruleSid", ruleSid, false, 0, true,
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_UPDATE_SERVICE, true)) {
			GtnFrameworkNSFCommonLogic.reloadTable(viewId, "selectedCustomersResultTable");

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
