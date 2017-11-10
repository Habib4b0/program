package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCfpInfoDesignationValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpInfoDesignationValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpInfoDesignationValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String cfpDesignationDdlbValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getCaptionFromComboBox();
		boolean isPopUpEnable = "Child".equalsIgnoreCase(cfpDesignationDdlbValue);
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkBaseComponent parentCfpId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(0));
		parentCfpId.setComponentEnable(isPopUpEnable);
		parentCfpId.setComponentReadOnly(isPopUpEnable);
		GtnUIFrameworkBaseComponent parentCfpname = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(1));
		parentCfpname.setComponentEnable(isPopUpEnable);
		parentCfpname.setComponentReadOnly(isPopUpEnable);
		if (!isPopUpEnable) {
			parentCfpId.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			parentCfpname.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
