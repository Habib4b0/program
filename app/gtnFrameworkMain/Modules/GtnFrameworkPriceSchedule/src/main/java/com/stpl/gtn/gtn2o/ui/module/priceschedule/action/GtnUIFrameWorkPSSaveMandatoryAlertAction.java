/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.priceschedule.GtnWsPriceScheduleGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.priceschedule.GtnWsPriceScheduleGeneralResponse;
import java.util.Date;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkPSSaveMandatoryAlertAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// empty method
	}

	@Override
	public void doAction(final String componentId, final GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		StringBuilder fieldMsg = new StringBuilder();
		String[] fields = new String[] { "priceScheduleId1", "priceScheduleNo1", "priceScheduleName1",
				"priceScheduleStatus1", "priceScheduleStartDate" };
		GtnUIFrameworkGlobalUI.validateFields(fields, fieldMsg);
                
                 Date psStartDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleStartDate")
                    .getDateFromDateField();
            Date psEndDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleEndDate")
                    .getDateFromDateField();
            validateDateEqualOrGreater(psStartDate, psEndDate, componentId);

		if (fieldMsg.length() != 0) {
			throw new GtnFrameworkValidationFailedException(
					"Information for the following Mandatory fields need to be provided: \n" + fieldMsg.toString(),
					componentId);
		}
		if (!GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cDRAddSaveButton").getCaption()
				.equalsIgnoreCase("UPDATE")) {
			GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsPriceScheduleGeneralRequest imRequest = new GtnWsPriceScheduleGeneralRequest();
			GtnUIFrameWorkPSInfoBean infoBean = new GtnUIFrameWorkPSInfoBean();
			infoBean.setPsId(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1").getStringFromField());
			infoBean.setPsNo(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleNo1").getStringFromField());
			Integer systemId = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("systemId");
			infoBean.setSystemId(systemId == null ? 0 : systemId);

			imRequest.setPsInfoBean(infoBean);

			gtnRequest.setGtnWsPriceScheduleGeneralRequest(imRequest);

			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					"/" + GtnWsCDRContants.PS_SERVICE + GtnWsCDRContants.GTN_WS_PS_ID_NO_VALIDATION_SERVICE, gtnRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsPriceScheduleGeneralResponse reponseBean = response.getGtnWsPriceScheduleGeneralResponse();
			if (reponseBean.isPsIdDuplicate()) {
				throw new GtnFrameworkValidationFailedException(
						GtnFrameworkPSConstants.GTN_CONTRACT_HEADER_PS_ID_VALIDATION, componentId);
			}
			if (reponseBean.isPsNoDuplicate()) {
				throw new GtnFrameworkValidationFailedException(
						GtnFrameworkPSConstants.GTN_CONTRACT_HEADER_PS_NO_VALIDATION, componentId);
			}
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

           public void validateDateEqualOrGreater(Date psStartDate, Date psEndDate, String componentId) throws GtnFrameworkValidationFailedException {
        if (psEndDate != null) {
            if (psStartDate.equals(psEndDate)) {
                throw new GtnFrameworkValidationFailedException(GtnFrameworkPSConstants.PS_DATE_EQUAL_VALIDATION, componentId);
            }
            if (psStartDate.after(psEndDate)) {
                throw new GtnFrameworkValidationFailedException(GtnFrameworkPSConstants.PS_DATE_LESS_THAN_VALIDATION, componentId);
            }
        }
    }
        
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
