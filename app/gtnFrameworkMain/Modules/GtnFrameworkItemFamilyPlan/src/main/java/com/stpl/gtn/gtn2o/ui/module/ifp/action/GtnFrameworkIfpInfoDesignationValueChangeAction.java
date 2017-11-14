package com.stpl.gtn.gtn2o.ui.module.ifp.action;

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

public class GtnFrameworkIfpInfoDesignationValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpInfoDesignationValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpInfoDesignationValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String ifpDesignationDdlbValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getCaptionFromComboBox();
		boolean isPopUpEnable = "Child".equalsIgnoreCase(ifpDesignationDdlbValue);
		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkBaseComponent parentifpId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(0));
		parentifpId.setComponentEnable(isPopUpEnable);
		parentifpId.setComponentReadOnly(isPopUpEnable);
		GtnUIFrameworkBaseComponent parentifpname = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentIdList.get(1));
		parentifpname.setComponentEnable(isPopUpEnable);
		parentifpname.setComponentReadOnly(isPopUpEnable);
		if (!isPopUpEnable) {
			parentifpId.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			parentifpname.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
