package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCompanyMasterTradeClassParentTabValidation
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterTradeClassParentTabValidation.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
		StringBuilder errorMessage = new StringBuilder(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		char appender = ' ';
		if (position == GtnWsNumericConstants.THREE) {
			Integer tradeClass = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeTabtradeClass")
					.getIntegerFromField();

			Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassStartDate")
					.getDateFromDateField();

			if (tradeClass == 0) {
				errorMessage.append(appender).append(" Trade Class ");
				appender = ',';
			}
			if (startDate == null) {
				errorMessage.append(appender).append(" Trade Class Start Date ");
			}
		} else {
			String parentcompanyNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyNo")
					.getStringFromField();

			Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyStartDate")
					.getDateFromDateField();

			if (parentcompanyNo == null || parentcompanyNo.isEmpty()) {
				errorMessage.append(appender).append(" Parent Company No ");
				appender = ',';
			}
			if (startDate == null) {
				errorMessage.append(appender).append(" Parent Company Start Date ");
			}
		}
		if (errorMessage.length() > 0) {
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_ATTACH + "<br>"
							+ errorMessage.toString(),
					componentId);
		}
		if (position == GtnWsNumericConstants.THREE) {

			Date tradeClassStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassStartDate")
					.getDateFromDateField();
			Date tradeClassEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tradeClassEndDate")
					.getDateFromDateField();
			checkDateConditionsEqualOrGreater(tradeClassStartDate, tradeClassEndDate, componentId,
					GtnFrameworkCompanyStringContants.TRADE_CLASS_DATE_EQUAL,
					GtnFrameworkCompanyStringContants.TRADE_CLASS_DATE_LESS_THAN);

		} else {
			Date parentCompanyStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyStartDate")
					.getDateFromDateField();
			Date parentCompanyEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("parentCompanyEndDate")
					.getDateFromDateField();
			checkDateConditionsEqualOrGreater(parentCompanyStartDate, parentCompanyEndDate, componentId,
					GtnFrameworkCompanyStringContants.PARENT_COMPANY_DATE_EQUAL,
					GtnFrameworkCompanyStringContants.PARENT_COMPANY_DATE_LESS_THAN);

		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void checkDateConditionsEqualOrGreater(Date startDate, Date endDate, String componentId, String equalMsg,
			String lessThanMsg) throws GtnFrameworkGeneralException {
		if (endDate != null) {
			if (startDate.equals(endDate)) {
				throw new GtnFrameworkValidationFailedException(equalMsg, componentId);
			}
			if (startDate.after(endDate)) {
				throw new GtnFrameworkValidationFailedException(lessThanMsg, componentId);
			}
		}
	}

}
