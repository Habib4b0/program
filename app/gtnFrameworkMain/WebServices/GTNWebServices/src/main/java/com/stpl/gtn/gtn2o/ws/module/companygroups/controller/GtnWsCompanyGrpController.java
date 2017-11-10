package com.stpl.gtn.gtn2o.ws.module.companygroups.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.constants.GtnWsCompanyGrpContants;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companygroups.service.GtnWsCompanyGrpService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.companygroupreponse.GtnWsCompanyGroupResponse;

@RestController
@RequestMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SERVICE)
public class GtnWsCompanyGrpController {
	public GtnWsCompanyGrpController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCompanyGrpController.class);

	@Autowired
	private GtnWsCompanyGrpService companyGrpWebservice;

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_ADD_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyAddService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAddService");
			count = companyGrpWebservice.addCompanyQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAddService Query", ex);
			return gtnResponse;
		} finally {
			logger.info(
					"Exit companyAddService and inserted or updated " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_ADDALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyAddAllService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAddService");
			count = companyGrpWebservice.getAddAllQuery(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionMoveLeft Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionMoveLeft and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_REMOVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyRemoveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyRemoveService");
			count = companyGrpWebservice.getRemoveQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyRemoveService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyRemoveService and deleted " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_REMOVEALL_SERVICE)
	public GtnUIFrameworkWebserviceResponse removeAllService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter removeAllService");
			count = companyGrpWebservice.getRemoveAllQuery(gtnWsRequest);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting removeAllService Query", ex);
			return gtnResponse;
		} finally {
			logger.info("Exit removeAllService and inserted or updated " + count + GtnFrameworkWebserviceConstant.ROWS);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SELECTED_TABLE_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyGroupSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest companyGroupRequest) {
		GtnUIFrameworkWebserviceResponse companyGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			companyGroupResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			companyGroupResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyGroupSearch");
			GtnSerachResponse companyGroupSerachResponse = new GtnSerachResponse();
			List<Object[]> resultList = companyGrpWebservice.getSelectedTableData(companyGroupRequest);
			if (companyGroupRequest.getGtnWsSearchRequest().isCount()) {
				companyGroupSerachResponse.setCount(Integer.valueOf(String.valueOf(resultList.get(0))));
			} else {
				GtnUIFrameworkDataTable companyGroupDataTable = new GtnUIFrameworkDataTable();
				companyGroupDataTable.addData(resultList);
				companyGroupSerachResponse.setResultSet(companyGroupDataTable);
			}
			companyGroupResponse.setGtnSerachResponse(companyGroupSerachResponse);
			return companyGroupResponse;
		} catch (GtnFrameworkGeneralException ex) {
			companyGroupResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionSearch Query", ex);
			companyGroupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return companyGroupResponse;
		} finally {
			logger.info("Exit companyAdditionSearch");
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_FETCH_SERVICE)
	public GtnUIFrameworkWebserviceResponse fetchCfpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companiesResultTableData");
			companyGrpWebservice.getCompanyGrpFetchQuery(gtnWsRequest, gtnResponse);
			companyGrpWebservice.updateCompanyGrpDetails(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companiesResultTableData Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companiesColumnUpdate ");
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_COPY_SERVICE)
	public GtnUIFrameworkWebserviceResponse copyCompanyGrpInformation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter copyCompanyGrpInformation");
			companyGrpWebservice.updateCompanyGrpDetails(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting copyCompanyGrpInformation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit copyCompanyGrpInformation ");
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		GtnCompanyGroupBean companyGrpBean = gtnWsRequest.getGtnCompanyGroupRequest().getGtnCompanyGroupBean();
		int companyGrpSid = companyGrpBean.getGtnCompanyGrpInformationBean().getCompanyGrpSid();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter saveService");
			if (companyGrpSid == 0) {
				companyGrpSid = companyGrpWebservice.saveCompanyGrpQuery(companyGrpBean);
			} else {
				companyGrpWebservice.updateCompanyGrpQuery(companyGrpBean);
			}
			count = companyGrpWebservice.updateCompanyGrpDetailsTable(gtnWsRequest, companyGrpSid);
			companyGrpBean.getGtnCompanyGrpInformationBean().setCompanyGrpSid(companyGrpSid);
			GtnWsCompanyGroupResponse gtnCompanyGroupResponse = new GtnWsCompanyGroupResponse();
			gtnCompanyGroupResponse.setGtnCompanyGrpInformationBean(companyGrpBean.getGtnCompanyGrpInformationBean());
			gtnResponse.setGtnWsCompanyGroupResponse(gtnCompanyGroupResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit saveService with sid :" + companyGrpSid + " and update in details :" + count);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getCompanyGroupDelete(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCompanyGroupDelete");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			companyGrpWebservice.getCompanyGrpDeleteQuery(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting getCompanyGroupDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit getCompanyGroupDelete and deleted count-:");
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_COMMON_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse validationCompanyGroup(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter validationCompanyGroup");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);

			companyGrpWebservice.getCompanyGroupImtdCount(gtnWsRequest, gtnResponse);

			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting validationCompanyGroup Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit validationCompanyGroup  :");
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_NAME_DUPLICATE_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyGroupNameDuplicateValidationService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyGroupNameDuplicateValidationService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			companyGrpWebservice.getCompanyGroupNameValidation(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyGroupNameDuplicateValidationService Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyGroupNameDuplicateValidationService with table count :" + count);
		}
	}

	@PostMapping(value = GtnWsCompanyGrpContants.GTN_WS_COMPANY_GRP_TEMP_TABLE_CLEAR_SERVICE)
	public GtnUIFrameworkWebserviceResponse companyGroupTempTableClearService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter companyGroup Temp clear Sevice");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			companyGrpWebservice.clearCustomerGroupTempTable(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyGroup Temp clear Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyGroup Temp clear with table ");
		}
	}

}
