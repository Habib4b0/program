package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.GtnItemMasterRequestBuilder;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.constants.GtnWsItemMasterContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterCommonValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		gtnLogger.debug("inside GtnFrameworkItemMasterCommonValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String itemId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_ITEM_ID)
				.getStringFromField();
		String itemNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_ITEM_NO)
				.getStringFromField();
		String ndc8 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_NDC8)
				.getStringFromField();
		int identifierTableSize = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterIdentifierattachResultTable")
				.getExtPagedTableSize();
		int pricingTableSize = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemMasterPricingattachResultTable")
				.getExtPagedTableSize();

		String appender = " ";
		String msg = "";

		StringBuilder fieldMsg = new StringBuilder();
		getItemInfoTabValidation(fieldMsg);
		if (identifierTableSize == 0) {
			fieldMsg.append(appender)
					.append(GtnFrameworkCommonConstants.BR + "   Add at least One Identifier in Identifier tab");
			appender = " , ";
		}

		if (pricingTableSize == 0) {
			fieldMsg.append(appender)
					.append(GtnFrameworkCommonConstants.BR + "   Add at least One Pricing Identifier in Pricing Tab");
		}

		if (fieldMsg.length() > 0) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_ATTACH
					+ GtnFrameworkCommonConstants.BR + fieldMsg.toString();

			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		Date itemStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationItemStartDate")
				.getDateFromDateField();

		Date formStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormStartDate")
				.getDateFromDateField();

		if (formStartDate != null && itemStartDate.after(formStartDate)) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_FOR_START_DATE;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		Date itemEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationItemEndDate")
				.getDateFromDateField();

		Date formEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabBaseNewFormEndDate")
				.getDateFromDateField();

		if (itemEndDate != null && formEndDate != null && itemEndDate.before(formEndDate)) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_FOR_END_DATE;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnWsItemMasterBean itemMasterBean = new GtnWsItemMasterBean();
		GtnWsItemMasterInfoBean infoBean = new GtnWsItemMasterInfoBean();
		infoBean.setItemId(itemId);
		infoBean.setItemNo(itemNo);
		infoBean.setNdc8(ndc8);
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("itemMasterSid");
		infoBean.setItemMasterSid(systemId == null ? 0 : systemId);
		itemMasterBean.setGtnWsItemMasterInfoBean(infoBean);
		imRequest.setGtnWsItemMasterBean(itemMasterBean);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_ID_NAME_NDC8_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsValidationBean reponseBean = response.getGtnWsItemMasterResponse().getGtnWsValidationBean();
		if (reponseBean.isItemIdExist()) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_ITEM_ID;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		if (reponseBean.isItemNoExist()) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_ITEM_NO;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		if (reponseBean.isNdc8Exist()) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_NDC8;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		List<GtnWsItemIdentifierBean> indenBeanList = getIdentifierTabValidation(componentId);

		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		valBean.setGtnWsItemMasterIdentifierBeanList(indenBeanList);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response2 = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_IM_IDENTIFIER_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response2 != null && response2.getGtnWsItemMasterResponse() != null
				&& response2.getGtnWsItemMasterResponse().isIndentifierExist()) {
			msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_008;
			throw new GtnFrameworkValidationFailedException(msg, componentId);

		}

		getPricingTabValidation(componentId);

		String regexErrorMsg = GtnUIFrameworkGlobalUI.regexValidation(
				new String[] { GtnFrameworkItemMasterStringContants.ADDITIONAL_INFORMATION_TAB_ACQUIRED_AMP,
						GtnFrameworkItemMasterStringContants.ADDITIONAL_INFORMATION_TAB_BASE_CPI,
						GtnFrameworkItemMasterStringContants.ADDITIONAL_INFORMATION_TAB_BASELINE_AMP });
		if (!regexErrorMsg.isEmpty()) {
			throw new GtnFrameworkValidationFailedException(regexErrorMsg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	private void getItemInfoTabValidation(StringBuilder errorMessage) throws GtnFrameworkGeneralException {
		String appender = " ";
		String itemType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemType")
				.getCaptionFromComboBox();
		String ndcId = "NDC-11".equals(itemType) ? "ifpItemInformationTabValueDropDown" : "itemInformationTabNDC9";
		String[] ifpInfoFields = new String[] { "itemInformationTabItemId", "itemInformationTabItemNo",
				"itemInformationTabItemName", "itemInformationTabItemStatus", "itemInformationTabitemMasterBrand",
				"itemInformationTabBrandId", "itemInformationTabItemClass", "itemInformationTabItemForm",
				"itemInformationTabItemStrength", "itemInformationItemStartDate",
				GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_ITEM_TYPE, ndcId,
				"itemInformationTabItemCode", "itemInformationTabOrganizationKey",
				GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_NDC8 };

		GtnUIFrameworkGlobalUI.validateFields(ifpInfoFields, errorMessage);

		Date formStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormStartDate")
				.getDateFromDateField();

		String formulationLookUp = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("additionalInformationTabNewFormulationLookup").getStringFromField();

		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormulationIndicator")
				.getStringFromField();

		if ("Yes".equals(value)) {
			if (formStartDate == null) {
				errorMessage.append(appender).append("New Form Start Date");
				appender = " , ";
			}

			if (formulationLookUp != null && formulationLookUp.isEmpty()) {
				errorMessage.append(appender).append("New Formulation");
			}
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_NDC8)
				.validate();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemCode").validate();

	}

	private List<GtnWsItemIdentifierBean> getIdentifierTabValidation(String componentId)
			throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> identifierList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterIdentifierattachResultTable").getItemsFromDataTable();

		if (identifierList.isEmpty()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_DUPLICATE_001;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		List<GtnWsItemIdentifierBean> indenBeanList = new ArrayList<>();
		for (GtnWsRecordBean gtnWsRecordBean : identifierList) {

			if (StringUtils.isEmpty(gtnWsRecordBean.getPropertyValue("itemIrtQualifierName"))) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_002;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}

			if (StringUtils.isEmpty(gtnWsRecordBean.getPropertyValue("itemIdentifier"))) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_003;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			getDateValidation(componentId, gtnWsRecordBean,
					GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_004);

			if (gtnWsRecordBean.getPropertyValue("identifierStatus") == null) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_005;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}

			GtnWsItemIdentifierBean indenBean = new GtnWsItemIdentifierBean();
			indenBean.setItemQualifierMasterSid((int) gtnWsRecordBean.getPropertyValueByIndex(12));
			indenBean.setItemIdentifierValue(String.valueOf(gtnWsRecordBean.getPropertyValue("itemIdentifier")));
			indenBean.setStartDate((Date) gtnWsRecordBean.getPropertyValue(GtnFrameworkCommonConstants.START_DATE));
			Object itemIdentifierSid = gtnWsRecordBean.getPropertyValueByIndex(13);
			indenBean.setItemIdentifierSid(itemIdentifierSid == null ? 0 : (int) itemIdentifierSid);
			indenBeanList.add(indenBean);
		}

		return indenBeanList;

	}

	private void getPricingTabValidation(String componentId) throws GtnFrameworkGeneralException {
		List<GtnWsRecordBean> pricingList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterPricingattachResultTable").getItemsFromDataTable();
		if (pricingList.isEmpty()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_PRICING_001;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnItemMasterRequestBuilder().getItemMasterWebServiceRequest();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_PRICING_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		if (response.getGtnWsItemMasterResponse().getGtnWsValidationBean().getValidationMsg() != null) {
			throw new GtnFrameworkValidationFailedException(
					response.getGtnWsItemMasterResponse().getGtnWsValidationBean().getValidationMsg(), componentId);

		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	private void getDateValidation(String componentId, GtnWsRecordBean gtnWsRecordBean, String validationMsg)
			throws GtnFrameworkGeneralException {
		Date idenStartDate = (Date) gtnWsRecordBean.getPropertyValue(GtnFrameworkCommonConstants.START_DATE);
		if (idenStartDate == null) {
			String msg = validationMsg;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		Date endDate = (Date) gtnWsRecordBean.getPropertyValue("endDate");
		if (endDate != null) {
			if (idenStartDate.equals(endDate)) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_START;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (idenStartDate.after(endDate)) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_END;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterCommonValidationAction();
	}

}
