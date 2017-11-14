package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
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
public class GtnFrameworkItemMasterIndentifierValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterIndentifierValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkItemIndentifierValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		StringBuilder feildMsg = new StringBuilder();

		Integer qualifierId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME)
				.getIntegerFromField();
		String itemidentifer = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.IDENTIFIER_INFORMATION_ITEM_IDENTIFIER).getStringFromField();
		Integer status = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierInformationIdentifierStatus")
				.getIntegerFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemIdentifierStartDate")
				.getDateFromDateField();
		String appender = " ";
		if (qualifierId == null || qualifierId == 0) {
			feildMsg.append(appender).append("Item Qualifier Name");
			appender = " , ";
		}
		if (itemidentifer.isEmpty()) {
			feildMsg.append(appender).append("Item Identifier");
			appender = " , ";
		}
		if (status == null || status == 0) {
			feildMsg.append(appender).append("Identifier Status");
			appender = " , ";
		}
		if (startDate == null) {
			feildMsg.append(appender).append("Identifier Start Date");
		}
		if (feildMsg.length() > 0) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_ATTACH
					+ feildMsg.toString();

			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		String regexErrorMsg = GtnUIFrameworkGlobalUI
				.regexValidation(new String[] { GtnFrameworkItemMasterStringContants.IDENTIFIER_INFORMATION_ITEM_IDENTIFIER });
		if (!regexErrorMsg.isEmpty()) {
			throw new GtnFrameworkValidationFailedException(regexErrorMsg, componentId);
		}
		GtnWsItemIdentifierBean indenBean = new GtnWsItemIdentifierBean();
		indenBean.setItemIdentifierValue(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("identifierInformationItemIdentifier").getStringFromField());

		indenBean.setStartDate(startDate);
		indenBean.setItemQualifierMasterSid(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME)
				.getIntegerFromField());
		getDateValidation(componentId, startDate);
		String qualifierName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.IDENTIFIER_ITEM_QUALIFIER_NAME)
				.getCaptionFromComboBox();

		List<GtnWsRecordBean> beanList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemMasterIdentifierattachResultTable").getItemsFromDataTable();
		for (GtnWsRecordBean GtnWsRecordBean : beanList) {
			if (indenBean.getItemIdentifierValue().equals(GtnWsRecordBean.getPropertyValue("itemIdentifier"))
					&& qualifierName.equals(GtnWsRecordBean.getPropertyValue("itemIrtQualifierName"))
					&& indenBean.getStartDate().equals(GtnWsRecordBean.getPropertyValue("startDate"))) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_DUPLICATE;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsItemMasterRequest imRequest = new GtnWsItemMasterRequest();
		GtnWsValidationBean valBean = new GtnWsValidationBean();
		imRequest.setGtnWsValidationBean(valBean);
		List<GtnWsItemIdentifierBean> indenBeanList = new ArrayList<>();
		indenBeanList.add(indenBean);
		valBean.setGtnWsItemMasterIdentifierBeanList(indenBeanList);
		gtnRequest.setGtnWsItemMasterRequest(imRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_SERVICE
						+ GtnWsItemMasterContants.GTN_WS_ITEM_MASTER_IM_IDENTIFIER_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response != null && response.getGtnWsItemMasterResponse() != null
				&& response.getGtnWsItemMasterResponse().isIndentifierExist()) {
			String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_IDENTIFIER_DUPLICATE_WITH_OTHER_ITEM;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

	private void getDateValidation(String componentId, Date startDate) throws GtnFrameworkGeneralException {
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemIdentifierEndDate").getDateFromDateField();
		if (endDate != null && startDate != null) {
			if (startDate.equals(endDate)) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_START;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
			if (startDate.after(endDate)) {
				String msg = GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_END;
				throw new GtnFrameworkValidationFailedException(msg, componentId);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkItemMasterIndentifierValidationAction();
	}

}
