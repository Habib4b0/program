package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkTransactionEnableDisableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		boolean isCompanyIdAndNameEnable = true;
		boolean isForcastNameAndVerEnable = true;
		String parentLayoutId = "companyStatuslayout";
		String[] companyIdAndName = { "companyId", "companyName" };
		String[] forcastNameAndVer = { "forecastName", "forecastVer" };
		String valueFromDdlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();
		if (GtnTransactionUIConstants.SUMMARY.equals(valueFromDdlb)) {
			isCompanyIdAndNameEnable = false;
			isForcastNameAndVerEnable = true;
		} else if (GtnTransactionUIConstants.DETAILS.equals(valueFromDdlb)) {
			isCompanyIdAndNameEnable = true;
			isForcastNameAndVerEnable = false;
		}
		for (int i = 0; i < companyIdAndName.length; i++) {

			GtnUIFrameworkGlobalUI.setEnableFlagForComponent(isCompanyIdAndNameEnable,
					Arrays.asList(companyIdAndName[i]), parentLayoutId + companyIdAndName[i]);
			GtnUIFrameworkGlobalUI.setEnableFlagForComponent(isForcastNameAndVerEnable,
					Arrays.asList(forcastNameAndVer[i]), parentLayoutId + forcastNameAndVer[i]);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
