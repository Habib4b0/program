package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkItemMasterEditListPricingValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterEditListPricingValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemEditListPricingValidationAction");
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		final String pricingQualifier = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("pricingEditListpricingCodeQualifier").getStringFromField();
		final String pricingQualifierName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("pricingEditListpricingCodeQualifierName").getStringFromField();
		final String editListEffectiveDates = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("pricingEditListEffectiveDates").getStringFromField();
		final String editListEntityCode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("pricingEditListEntityCode")
				.getStringFromField();
		final String editListEffectiveDatess = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("pricingEditListEffectiveDates").getCaptionFromComboBox();
		final String editListEntityCodess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("pricingEditListEntityCode")
				.getCaptionFromComboBox();

		if (pricingQualifier == null || pricingQualifier.isEmpty()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_QUALIFIER;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		if (pricingQualifierName == null || pricingQualifierName.isEmpty()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_QUALIFIER_NAME;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		if (editListEffectiveDates == null || editListEffectiveDates.isEmpty()
				|| "-Select one-".equalsIgnoreCase(editListEffectiveDatess)) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_EFFECTIVE_DATES;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		if (editListEntityCode == null || editListEntityCode.isEmpty()
				|| "-Select one-".equalsIgnoreCase(editListEntityCodess)) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_ENTITY_CODE;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

		getUpdateValidation(componentId);

		GtnWsItemPricingQualifierBean indenBean = new GtnWsItemPricingQualifierBean();
		indenBean.setPricingQualifier(pricingQualifier);
		indenBean.setItemPricingQualifierName(pricingQualifierName);

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		valBean.setGtnWsItemPricingQualifierBean(indenBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_PRICING_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		getDuplicateAlert(response, componentId);
	}

	private void getDuplicateAlert(GtnUIFrameworkWebserviceResponse response, String componentId)
			throws GtnFrameworkGeneralException {
		if (response != null && response.getGtnWsItemMasterResponse() != null && "Save".equalsIgnoreCase(
				GtnUIFrameworkGlobalUI.getVaadinComponent("pricingEditListSaveButton").getCaption())) {
			if (response.getGtnWsItemMasterResponse().isPricnigQualfierExist()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_EXIST;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (response.getGtnWsItemMasterResponse().isPricnigQualfierNameExist()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_NAME_EXIST;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}
	}

	private void getUpdateValidation(String componentId) throws GtnFrameworkGeneralException {

		if ("UPDATE".equalsIgnoreCase(
				GtnUIFrameworkGlobalUI.getVaadinComponent("pricingEditListSaveButton").getCaption())) {
			final GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			final GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

			GtnWsRecordBean dto = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("pricingEditattachResultTable")
					.getValueFromPagedDataTable();
			if (dto != null) {
				boolean recordLockStatus = (boolean) dto.getPropertyValueByIndex(10);

				if (recordLockStatus) {
					Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_PERMISSION;
					alertActionConfig.setActionParameterList(Arrays.asList("Access Denied", msg));
					alertAction.doAction(componentId, alertActionConfig);
					throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
				}

				String pricingEditListpricingCodeQualifier = String.valueOf(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("pricingEditListpricingCodeQualifier").getValueFromComponent());

				if (!String.valueOf(dto.getPropertyValueByIndex(0)).equals(pricingEditListpricingCodeQualifier)) {
					Object msg = "Pricing Code Qualifier cannot be edited. ";
					alertActionConfig
							.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
					alertAction.doAction(componentId, alertActionConfig);
					throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
				}
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterEditListPricingValidationAction();
	}

}
