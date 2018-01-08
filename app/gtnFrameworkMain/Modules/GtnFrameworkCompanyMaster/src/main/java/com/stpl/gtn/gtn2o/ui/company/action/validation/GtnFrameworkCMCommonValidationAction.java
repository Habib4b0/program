package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterValidationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCMCommonValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCMCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		gtnLogger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String companyId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyId")
				.getStringFromField();
		String companyNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyNo")
				.getStringFromField();
		validateFieldsBeforeSave(componentId, companyId, companyNo);

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest imRequest = new GtnCMasterRequest();
		GtnCMasterBean companyMasterBean = new GtnCMasterBean();
		GtnCMasterInformationBean infoBean = new GtnCMasterInformationBean();
		infoBean.setCompanyId(companyId);
		infoBean.setCompanyNo(companyNo);
		Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("companyMasterSid");
		infoBean.setCompanyMasterSystemId(systemId == null ? 0 : systemId);
		companyMasterBean.setGtnCMasterInformationBean(infoBean);
		imRequest.setGtnCMasterBean(companyMasterBean);
		gtnRequest.setGtnCMasterRequest(imRequest);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER + GtnWebServiceUrlConstants.GTN_WS_CM_VALIDATION_EXISTS,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnCMasterValidationBean reponseBean = response.getGtnCompanyMasterResponse().getGtnWsCMValidationBean();
		if (reponseBean.isCompanyIdExist()) {
			String errormsg = GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_COMPANY_ID;
			throw new GtnFrameworkValidationFailedException(errormsg, componentId);
		}
		if (reponseBean.isCompanyNoExist()) {
			String errormsg = GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_COMPANY_NO;
			throw new GtnFrameworkValidationFailedException(errormsg, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkCMCommonValidationAction();
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

	private void dateValidationDuringSaveForTabs(List<GtnWsRecordBean> recordBeanList, String componentid,
			String equalMsg, String lessThanMsg, List<String> properties) throws GtnFrameworkGeneralException {
		if (recordBeanList != null) {
			for (GtnWsRecordBean recordBeanList1 : recordBeanList) {
				Date startDate = recordBeanList1.getDateProperty(properties.get(0));
				Date endDate = recordBeanList1.getDateProperty(properties.get(1));
				checkDateConditionsEqualOrGreater(startDate, endDate, componentid, equalMsg, lessThanMsg);
			}
		}
	}

	private void validateFieldsBeforeSave(String componentId, String companyId, String companyNo)
			throws GtnFrameworkGeneralException {

		String msg = GtnFrameworkCompanyStringContants.STRING_EMPTY;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<List<GtnWsRecordBean>> tabList = new ArrayList();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<List<String>> messageList = new ArrayList();

		String companyName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyName")
				.getStringFromField();
		Integer status = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyStatus")
				.getIntegerFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyStartDate")
				.getDateFromDateField();
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyEndDate")
				.getDateFromDateField();
		Integer companyType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyType")
				.getIntegerFromField();
		String appender = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		StringBuilder fieldMsg = new StringBuilder();

		if (companyId.isEmpty()) {
			fieldMsg.append(appender).append("Company ID");
			appender = ",";
		}
		if (companyNo.isEmpty()) {
			fieldMsg.append(appender).append("Company No");
			appender = ",";
		}
		if (companyName.isEmpty()) {
			fieldMsg.append(appender).append("Company Name");
			appender = ",";
		}
		if (status == null || status == 0) {
			fieldMsg.append(appender).append("Company Status");
			appender = ",";
		}
		if (startDate == null) {
			fieldMsg.append(appender).append("Company Start Date");
			appender = ",";
		}
		if (companyType == null || companyType == 0) {
			fieldMsg.append(appender).append("Company Type");
		}

		checkDateConditionsEqualOrGreater(startDate, endDate, componentId,
				GtnFrameworkCompanyStringContants.COMPANY_DATE_EQUAL_VALIDATION,
				GtnFrameworkCompanyStringContants.COMPANY_DATE_LESS_THAN_VALIDATION);

		if (fieldMsg.length() > 0) {
			msg = GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG + " <br> " + fieldMsg + "<br>";
		}

		List<GtnWsRecordBean> identifierList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("identifierattachResultTable").getItemsFromDataTable();
		if (identifierList.isEmpty()) {
			msg = msg + GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_ONE_REMOVE
					+ "<br>";
		}

		List<GtnWsRecordBean> tradeClassList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("tradeClassattachResultTable").getItemsFromDataTable();
		if (tradeClassList.isEmpty()) {
			msg = msg + GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_TRADECLASS_REMOVE;
		} else {
			msg = gtnCMTradeClassTabDuplicateBeanCheck(tradeClassList);
		}
		if (!msg.isEmpty()) {
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		List<GtnWsRecordBean> parentCompanyList = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("parentCompanyattachResultTable").getItemsFromDataTable();

		tabList.add(identifierList);
		messageList.add(Arrays.asList(GtnFrameworkCompanyStringContants.COMPANY_DATE_SAVE_IDETIFIER_EQUAL_TO_VALIDATION,
				GtnFrameworkCompanyStringContants.COMPANY_DATE_SAVE_IDETIFIER_LESS_THAN_VALIDATION));
		tabList.add(tradeClassList);
		messageList.add(Arrays.asList(GtnFrameworkCompanyStringContants.TRADE_CLASS_DATE_EQUAL,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_DATE_LESS_THAN));
		tabList.add(parentCompanyList);
		messageList.add(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_DATE_EQUAL,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_DATE_LESS_THAN));

		for (int i = 0; i < tabList.size(); i++) {

			dateValidationDuringSaveForTabs(tabList.get(i), componentId, messageList.get(i).get(0),
					messageList.get(i).get(1),
					GtnFrameworkCompanyStringContants.getGtnCompanyDateTabComponentsList().get(i));
		}
	}

	private String gtnCMTradeClassTabDuplicateBeanCheck(List<GtnWsRecordBean> tradeClassList) {
		Set<Integer> tradeClassSidset = new HashSet<>();
		StringBuilder tradeClassDuplicateAlertMsg=new StringBuilder();
		for (int i = 0; i < tradeClassList.size(); i++) {
			GtnWsRecordBean tradeClassBean = tradeClassList.get(i);
			Object tradeClassSid = tradeClassBean
					.getPropertyValueByIndex(tradeClassBean.getProperties().size() - 1);
			Boolean tradeClassSidCheck = tradeClassSidset.add((int) tradeClassSid);
			if (!tradeClassSidCheck) {
				tradeClassDuplicateAlertMsg.append(GtnFrameworkCompanyStringContants.TRADE_CLASS_SID_DISTINCT);
				break;
			}
		}
		return tradeClassDuplicateAlertMsg.toString();
	}

}
