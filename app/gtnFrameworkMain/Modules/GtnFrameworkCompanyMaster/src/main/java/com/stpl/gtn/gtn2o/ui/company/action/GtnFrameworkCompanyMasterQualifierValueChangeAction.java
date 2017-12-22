package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.ui.TextField;

public class GtnFrameworkCompanyMasterQualifierValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterQualifierValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkCompanyMasterQualifierValueChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			Integer value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyQualifierName").getIntegerFromField();
			TextField identifier = (TextField) GtnUIFrameworkGlobalUI.getVaadinComponent("companyIdentifier",
					componentId);
			identifier.setEnabled(value != 0);
			if (value == 0) {
				identifier.setValue(GtnFrameworkCompanyStringContants.STRING_EMPTY);
			}

		} catch (Exception e) {
			logger.error("In GtnFrameworkCompanyMasterQualifierValueChangeAction ", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
