package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();

		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) paramsList.get(1), componentId)
				.getItemsFromDataTable().isEmpty()) {
			throw new GtnFrameworkValidationFailedException((String) paramsList.get(GtnWsNumericConstants.THREE),
					componentId);
		}
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) paramsList.get(1), componentId)
				.getValueFromDataTable() == null) {
			throw new GtnFrameworkValidationFailedException((String) paramsList.get(GtnWsNumericConstants.TWO),
					componentId);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
