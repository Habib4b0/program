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
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
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
public class GtnFrameworkItemMasterEditListIndentifierValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterEditListIndentifierValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemEditListIndentifierValidationAction");
	}

	@Override
	public void doAction(final String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		final String qualifier = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListidentifierCodeQualifier")
				.getStringFromField();
		final String qualifierName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("editListidentifierCodeQualifierName").getStringFromField();
		getErrrorBannerMsg(qualifier, qualifierName, componentId);
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

		getEditValidation(componentId);

		GtnWsItemQualifierBean indenBean = new GtnWsItemQualifierBean();
		indenBean.setItemQualifierValue(qualifier);
		indenBean.setItemQualifierName(qualifierName);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		valBean.setGtnWsItemQualifierBean(indenBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_IDENTIFIER_QUALIFIER_NAMEANDVALUE_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (response != null && response.getGtnWsItemMasterResponse() != null && "Save"
				.equalsIgnoreCase(GtnUIFrameworkGlobalUI.getVaadinComponent("editListSaveButton").getCaption())) {
			if (response.getGtnWsItemMasterResponse().isIndentifierQualfierValueExist()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_EXIST;

				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (response.getGtnWsItemMasterResponse().isQualfierNameExist()) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_NAME_EXIST;

				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}
	}

	private void getEditValidation(String componentId) throws GtnFrameworkGeneralException {

		if ("UPDATE".equalsIgnoreCase(GtnUIFrameworkGlobalUI.getVaadinComponent("editListSaveButton").getCaption())) {
			final GtnUIFrameWorkActionConfig imIdentifierAlertActionConfig = new GtnUIFrameWorkActionConfig();
			final GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			GtnWsRecordBean dto = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editattachResultTable")
					.getValueFromPagedDataTable();
			if (dto != null) {
				boolean recordLockStatus = (boolean) dto.getPropertyValueByIndex(10);

				if (recordLockStatus) {
					Object msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_PERMISSION;
					imIdentifierAlertActionConfig.setActionParameterList(Arrays.asList("Access Denied", msg));
					alertAction.doAction(componentId, imIdentifierAlertActionConfig);
					throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
				}

				String editListidentifierCodeQualifier = String.valueOf(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("editListidentifierCodeQualifier").getValueFromComponent());

				if (!String.valueOf(dto.getPropertyValueByIndex(0)).equals(editListidentifierCodeQualifier)) {
					Object msg = "Identifier Code Qualifier cannot be edited. ";
					imIdentifierAlertActionConfig
							.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, msg));
					alertAction.doAction(componentId, imIdentifierAlertActionConfig);
					throw new GtnFrameworkValidationFailedException("Validation Error :" + msg);
				}
			}
		}

	}

	private void getErrrorBannerMsg(String qualifier, String qualifierName, String componentId)
			throws GtnFrameworkGeneralException {
		final String editListEffectiveDates = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEffectiveDates")
				.getStringFromField();

		final String editListEffectiveDatess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEffectiveDates")
				.getCaptionFromComboBox();

		final String editListEntityCode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEntityCode")
				.getStringFromField();

		final String editListEntityCodess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editListEntityCode")
				.getCaptionFromComboBox();

		if (qualifier == null || qualifier.isEmpty()) {
			getValidationMsg(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER,
					componentId);
		}
		if (qualifierName == null || qualifierName.isEmpty()) {
			getValidationMsg(
					GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_NAME,
					componentId);
		}
		if (editListEffectiveDates == null || editListEffectiveDates.isEmpty()
				|| "-Select one-".equalsIgnoreCase(editListEffectiveDatess)) {
			getValidationMsg(
					GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_EFFECTIVE_DATES,
					componentId);
		}
		if (editListEntityCode == null || editListEntityCode.isEmpty()
				|| "-Select one-".equalsIgnoreCase(editListEntityCodess)) {
			getValidationMsg(
					GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_QUALIFIER_ENTITY_CODE,
					componentId);
		}

	}

	private void getValidationMsg(String msg, String componentId) throws GtnFrameworkGeneralException {
		throw new GtnFrameworkValidationFailedException(msg, componentId);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterEditListIndentifierValidationAction();
	}

}
