package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;

public class GtnUiFrameworkNsfDeductionPopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		String massUpdateDdlbValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "selectedDeductionsmassUpdateDdlb").getCaptionFromComboBox();
		if (GtnFrameworkNSFConstants.getNetSalesRuleNo().equals(massUpdateDdlbValue)) {
			GtnWsRecordBean dto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinComponentData(viewId + "selectedDeductionsmassUpdateNetSalesRuleNo").getCustomData();

			Object newValue = null;

			if (dto.getPropertyValue("systemId") != null) {
				newValue = dto.getPropertyValue("systemId");
			} else {
				newValue = dto.getPropertyValueByIndex(dto.getProperties().size() - 1);
			}
			int ruleSid = Integer.parseInt(String.valueOf(newValue));
			reloadTable(GtnFrameworkNSFCommonLogic.updateField("ruleSid", ruleSid, false, 0, false,
					"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_UPDATE_SERVICE, true),
					viewId);
		} else {  
			String indicatorValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(viewId + "selectedDeductionsAddSubtractDdlb").getCaptionFromComboBox();
			reloadTable(GtnFrameworkNSFCommonLogic.updateField("indicator", indicatorValue, false, 0, false,
					"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_UPDATE_SERVICE, true),
					viewId);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void reloadTable(boolean isSuccess, String viewId) throws GtnFrameworkValidationFailedException {
		if (isSuccess) {
			GtnFrameworkNSFCommonLogic.reloadTable(viewId, "selectedDeductionsResultTable");
		}
	}
}
