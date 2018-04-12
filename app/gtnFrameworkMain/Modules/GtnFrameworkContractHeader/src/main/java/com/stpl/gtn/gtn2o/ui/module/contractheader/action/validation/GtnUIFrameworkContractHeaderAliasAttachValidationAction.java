package com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnUIFrameworkContractHeaderAliasAttachValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkContractHeaderAliasAttachValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkContractAliasAttachValidationAction");
		String msg;

		final String aliasNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasNo")
				.getStringFromField();
		final Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasStartDate")
				.getDateFromDateField();
		final Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasEndDate")
				.getDateFromDateField();
		final Integer aliasType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasType")
				.getIntegerFromField();

		final Object[] values = new Object[] { aliasNo, aliasType, startDate };
		final String[] mesaages = GtnUIFrameworkContractHeaderStringContants.getDO_ACTION_MESAAGES();
		validateFields(mesaages, values, componentId);

		if (endDate != null) {
			if (startDate.equals(endDate)) {
				msg = GtnUIFrameworkContractHeaderStringContants.GTN_CUSTOMER_GRP_VALIDATION_MSG_ALIAS_START_MANDATORY_001;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (startDate.after(endDate)) {
				msg = GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_ALIAS_END_DATE_VALIDATION;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public static void validateFields(String[] mesaages, Object[] values, String componentId)
			throws GtnFrameworkGeneralException {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || values[i] instanceof String && String.valueOf(values[i]).isEmpty()
					|| values[i] instanceof Integer && Integer.valueOf(String.valueOf(values[i])) == 0) {
				throw new GtnFrameworkValidationFailedException(mesaages[i], componentId);
			}
		}
	}
}
