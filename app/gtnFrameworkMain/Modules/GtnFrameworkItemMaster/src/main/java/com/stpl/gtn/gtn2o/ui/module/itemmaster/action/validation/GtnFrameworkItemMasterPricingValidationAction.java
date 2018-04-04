package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GntWsItemPricingBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.math.BigDecimal;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemMasterPricingValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterPricingValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemPricingValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String msg;
		StringBuilder feildMsg = new StringBuilder();

		Integer qualifierId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingQualifierName")
				.getIntegerFromField();
		String itemPrice = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingItemPrice").getStringFromField();
		Integer status = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingIdentifierStatus")
				.getIntegerFromField();
		Integer uom = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingItemUOM").getIntegerFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingStartDate").getDateFromDateField();
		String appender = "  ";
		if (qualifierId == null || qualifierId == 0) {
			feildMsg.append(appender).append("Pricing Qualifier Name");
			appender = " , ";
		}
		if (itemPrice.isEmpty()) {
			feildMsg.append(appender).append("Item Price");
			appender = " , ";
		}
		if (status == null || status == 0) {
			feildMsg.append(appender).append("Pricing Status");
			appender = " , ";
		}
		if (uom == null || uom == 0) {
			feildMsg.append(appender).append("Item UOM");
			appender = " , ";
		}
		if (startDate == null) {
			feildMsg.append(appender).append("Start Date");
		}
		if (feildMsg.length() > 0) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_ATTACH
					+ feildMsg.toString();

			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		if (!isDouble(itemPrice)) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_009;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GntWsItemPricingBean indenBean = new GntWsItemPricingBean();
		indenBean.setItemPrice(new BigDecimal(itemPrice));
		indenBean.setPricingCodeStatus(status);
		indenBean.setItemUom(uom);
		indenBean.setStartDate(startDate);
		indenBean.setItemPricingQualifierSid(qualifierId);

		getValidationForPricingtable(componentId, startDate);
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	private void getValidationForPricingtable(String componentId, Date startDate) throws GtnFrameworkGeneralException {
		String msg;
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingEndDate").getDateFromDateField();
		if (endDate != null && startDate != null) {
			if (startDate.equals(endDate)) {
				msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_START;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (startDate.after(endDate)) {
				msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_END;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterPricingValidationAction();
	}

	boolean isDouble(String str) {
		try {
			Double doubleValue = Double.parseDouble(str);
			gtnLogger.debug("doubleValue is " + doubleValue);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
