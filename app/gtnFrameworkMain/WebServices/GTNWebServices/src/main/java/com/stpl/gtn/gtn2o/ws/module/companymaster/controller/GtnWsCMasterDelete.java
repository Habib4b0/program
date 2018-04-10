
package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER)
public class GtnWsCMasterDelete {
    public GtnWsCMasterDelete(){
        /**
         * empty constructor
         */
    }
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterDelete.class);

	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_GET_DELETE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse companyMasterDeleteService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyMasterDeleteService");
		GtnCMasterRequest companyMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		GtnCMasterBean companyMasterBean = companyMasterRequest.getGtnCMasterBean();
		GtnCMasterInformationBean companyInformationBean = companyMasterBean.getGtnCMasterInformationBean();
		logger.info("Exit companyMasterDeleteService");
		return deleteResponseForCompanyMaster(companyInformationBean);
	}

	private GtnUIFrameworkWebserviceResponse deleteResponseForCompanyMaster(
			GtnCMasterInformationBean companyInformationBean) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnCompanyMasterResponse generalWSResponse = new GtnCompanyMasterResponse();
		try {
			if (!parentDetailsValidation(companyInformationBean)) {
				generalWSResponse.setSuccess(false);
				generalWSResponse.setReasonForFailure(
						"Company cannot be deleted as it is associated as parent to another Company");
			} else if (!cfpCompanyValidation(companyInformationBean)) {
				generalWSResponse.setSuccess(false);
				generalWSResponse.setReasonForFailure("Company cannot be deleted as it is associated with CFP");
			} else if (!contractCompanyValidation(companyInformationBean)) {
				generalWSResponse.setSuccess(false);
				generalWSResponse.setReasonForFailure("Company cannot be deleted as it is associated with Contract");
			} else {
				deleteCmIdentifierDetails(companyInformationBean);
				deleteCmTradeClassDetails(companyInformationBean);
				deleteCmParentDetails(companyInformationBean);
				deleteCompanyMasterInfoDetails(companyInformationBean);
				generalWSResponse.setSuccess(true);
			}
		} catch (GtnFrameworkGeneralException exception) {
			logger.error("Exception in deleting service -", exception);
			generalWSResponse.setSuccess(false);
			generalWSResponse.setReasonForFailure("Delete Failed");
		}
		gtnResponse.setGtnCompanyMasterResponse(generalWSResponse);
		return gtnResponse;
	}

	private void deleteCompanyMasterInfoDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmInfoQuery = "UPDATE COMPANY_MASTER SET \"INBOUND_STATUS\" = 'D' WHERE COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		gtnGeneralServiceController.executeUpdateQuery(deleteCmInfoQuery);
	}

	private void deleteCmParentDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmParentDetails = "UPDATE COMPANY_PARENT_DETAILS SET \"INBOUND_STATUS\" = 'D' WHERE COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		gtnGeneralServiceController.executeUpdateQuery(deleteCmParentDetails);
	}

	private void deleteCmTradeClassDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmTradeClassDetails = "UPDATE Company_Trade_Class SET \"inbound_Status\" = 'D' WHERE  company_Master_Sid ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		gtnGeneralServiceController.executeUpdateQuery(deleteCmTradeClassDetails);
	}

	private void deleteCmIdentifierDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmIdentifierDetails = "UPDATE COMPANY_IDENTIFIER SET \"INBOUND_STATUS\" = 'D' WHERE COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		gtnGeneralServiceController.executeUpdateQuery(deleteCmIdentifierDetails);
	}

	private boolean parentDetailsValidation(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmParentDetails = "Select count(*) from COMPANY_PARENT_DETAILS where  \"INBOUND_STATUS\" <> 'D' AND PARENT_COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		List<?> result = gtnSqlQueryEngine.executeSelectQuery(deleteCmParentDetails);
		return result == null || result.isEmpty() || Integer.parseInt(result.get(0).toString()) == 0 ? true : false;
	}

	private boolean cfpCompanyValidation(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmParentDetails = "Select count(*) from CFP_DETAILS where  \"INBOUND_STATUS\" <> 'D' AND COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		List<?> result = gtnSqlQueryEngine.executeSelectQuery(deleteCmParentDetails);
		return result == null || result.isEmpty() || Integer.parseInt(result.get(0).toString()) == 0 ? true : false;
	}

	private boolean contractCompanyValidation(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		final String deleteCmParentDetails = "Select count(*) from CFP_CONTRACT_DETAILS where  \"INBOUND_STATUS\" <> 'D' AND COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		List<?> result = gtnSqlQueryEngine.executeSelectQuery(deleteCmParentDetails);
		return result == null || result.isEmpty() || Integer.parseInt(result.get(0).toString()) == 0 ? true : false;
	}

}
