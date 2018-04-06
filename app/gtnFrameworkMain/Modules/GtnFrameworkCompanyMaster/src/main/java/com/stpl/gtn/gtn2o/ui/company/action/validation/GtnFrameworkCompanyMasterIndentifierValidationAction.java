package com.stpl.gtn.gtn2o.ui.company.action.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterValidationBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCompanyMasterIndentifierValidationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterIndentifierValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCompanyMasterIndentifierValidationAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		StringBuilder failedMsg = new StringBuilder();

		Integer qualifierId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_QUALIFIER_NAME)
				.getIntegerFromField();
		String identifer = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierInformationCompanyIdentifier")
				.getStringFromField();
		Integer status = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierInformationCompanyStatus")
				.getIntegerFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyIdentifierStartDate")
				.getDateFromDateField();
                
                validationForMandatory(qualifierId, failedMsg, identifer, status, startDate, componentId);

		GtnCMasterIdentifierInfoBean indenBean = new GtnCMasterIdentifierInfoBean();
		indenBean.setCompanyIdentifierValue(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("identifierInformationCompanyIdentifier").getStringFromField());
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyIdentifierEndDate").getDateFromDateField();
		indenBean.setCompanyIdentifierStartDate(startDate);
		indenBean.setCompanyQualifierSid(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierCompanyQualifierName").getIntegerFromField());
                
                checkDateEqualOrGreater(endDate, startDate, componentId, indenBean);

		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest cmRequest = new GtnCMasterRequest();
		GtnCMasterValidationBean valBean = new GtnCMasterValidationBean();
		cmRequest.setGtnCMasterValidationBean(valBean);
		List<GtnCMasterIdentifierInfoBean> indenBeanList = new ArrayList<>();
		indenBeanList.add(indenBean);
		valBean.setGtnWsCompanyMasterIdentifierBeanList(indenBeanList);
		gtnRequest.setGtnCMasterRequest(cmRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER
						+ GtnWebServiceUrlConstants.GTN_WS_CM_VALIDATION_SERVICE,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response != null && response.getGtnCompanyMasterResponse() != null
				&& response.getGtnCompanyMasterResponse().isIndentifierExist()) {
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_DUPLICATE_WITH_OTHER_ITEM, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

    public void checkDateEqualOrGreater(Date endDate, Date startDate, String componentId, GtnCMasterIdentifierInfoBean indenBean) throws GtnFrameworkValidationFailedException {
        if (endDate != null) {
            if (startDate.equals(endDate)) {
                throw new GtnFrameworkValidationFailedException(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_START_END_EQUAL, componentId);
            }
            if (startDate.after(endDate)) {
                throw new GtnFrameworkValidationFailedException(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_START_END_GREATER, componentId);
            }
        }
        String qualifierName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierCompanyQualifierName")
                .getCaptionFromComboBox();
        
        List<GtnWsRecordBean> beanList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierattachResultTable")
                .getItemsFromDataTable();
        for (GtnWsRecordBean GtnWsRecordBean : beanList) {
            if (indenBean.getCompanyIdentifierValue().equals(GtnWsRecordBean.getPropertyValue("identifier"))
                    && qualifierName.equals(GtnWsRecordBean.getPropertyValue("qualifierName")) && indenBean
                            .getCompanyIdentifierStartDate().equals(GtnWsRecordBean.getPropertyValue("startDate"))) {
                throw new GtnFrameworkValidationFailedException(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_DUPLICATE, componentId);
            }
        }
    }

    public void validationForMandatory(Integer qualifierId, StringBuilder failedMsg, String identifer, Integer status, Date startDate, String componentId) throws GtnFrameworkValidationFailedException {
        String msg;
        char appender = ' ';
        if (qualifierId == null || qualifierId == 0) {
            failedMsg.append(appender).append("Company Qualifier Name");
            appender =  ',';
        }
        if (identifer.isEmpty()) {
            failedMsg.append(appender).append("Company Identifier");
            appender =  ',';
        }
        if (status == null || status == 0) {
            failedMsg.append(appender).append("Identifier Status");
            appender =  ',';
        }
        if (startDate == null) {
            failedMsg.append(appender).append("Start Date");
        }
        if (failedMsg.length() > 0) {
            msg = GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VALIDATION_MSG_IDENTIFIER_ATTACH + "<br>"
                    + failedMsg.toString();
            throw new GtnFrameworkValidationFailedException(msg, componentId);
        }
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkCompanyMasterIndentifierValidationAction();
	}

}
