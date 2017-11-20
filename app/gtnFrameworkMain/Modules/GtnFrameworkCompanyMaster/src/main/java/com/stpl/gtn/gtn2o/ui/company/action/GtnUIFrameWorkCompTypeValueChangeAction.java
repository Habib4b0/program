/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnUIFrameWorkCompTypeValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkCompTypeValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering inside configureParams ");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String companyTypeDesc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyType")
				.getCaptionFromComboBox();
		if ("Business Unit".equalsIgnoreCase(companyTypeDesc) || "GLCOMP".equalsIgnoreCase(companyTypeDesc)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet", componentId)
					.setTabComponentVisible(GtnWsNumericConstants.FIVE, true);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet", componentId)
					.setTabComponentVisible(GtnWsNumericConstants.FIVE, false);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
