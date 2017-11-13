/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.netsales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.netsales.service.GtnWsNsfService;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

@RestController
@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_SERVICE)
public class GtnWsNetSalesFormulaAddController {
	public GtnWsNetSalesFormulaAddController() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsNetSalesFormulaAddController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSearchServiceController gTNSearchServiceController;
	@Autowired
	private GtnWsNsfService gtnWsNsfService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_SEARCH_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList();
		GtnWebServiceSearchCriteria searchFieldCriteria = gtnWebServiceSearchCriteriaList.get(0);
		GtnWebServiceSearchCriteria searchValueCriteria = gtnWebServiceSearchCriteriaList.get(1);
		searchValueCriteria.setFieldId(searchFieldCriteria.getFilterValue1());
		gtnWsRequest.getGtnWsSearchRequest().removeGtnWebServiceSearchCriteriaList(0);
		return gTNSearchServiceController.getSearchResultForAllModule(gtnWsRequest);
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_SALESBASIS_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse insertSelectedCustomersIntoTempTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			LOGGER.info("Enter gtnWsNsfService");
			count = gtnWsNsfService.insertSelectedCustomersIntoTempTable(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting gtnWsNsfService Query", ex);
			return gtnResponse;
		} finally {
			LOGGER.info("Exit gtnWsNsfService and inserted or updated " + count + " Rows");
		}
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_DEDUCTION_SELECTION_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse insertSelectedDeductionsIntoTempTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		int count = 0;
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			LOGGER.info("Enter gtnWsNsfService");
			count = gtnWsNsfService.insertSelectedDeductionsIntoTempTable(gtnWsRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting gtnWsNsfService Query", ex);
			return gtnResponse;
		} finally {
			LOGGER.info("Exit gtnWsNsfService and inserted or updated " + count + " Rows");
		}
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse netSalesSaveService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter netSalesSaveService");
		int count = 0;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnUIFrameworkNsfInfoBean nsfInfoBean = gtnWsRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean();
			Integer nsfSystemId = nsfInfoBean.getSystemId();
			if (nsfSystemId == null || nsfSystemId == 0) {
				nsfSystemId = gtnWsNsfService.saveNsfInfo(gtnWsRequest);
				nsfInfoBean.setSystemId(nsfSystemId);
			} else {
				gtnWsNsfService.updateNsfInfo(gtnWsRequest);
			}
			response.getGtnWsGeneralResponse().setSucess(true);
			GtnWsNetSalesGeneralResponse netSalesGeneralResponse = new GtnWsNetSalesGeneralResponse();
			netSalesGeneralResponse.setNsfInfoBean(nsfInfoBean);
			response.setGtnWsNetSalesGeneralResponse(netSalesGeneralResponse);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting netSalesSaveService", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			LOGGER.info("Exit netSalesSaveService and inserted " + count + "  rows");
		}
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadOnEdit(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Enter loadOnEdit");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnUIFrameworkNsfInfoBean nsfInfoBean = gtnWsNsfService.getNsfData(gtnWsRequest);
			GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = new GtnWsNetSalesGeneralResponse();
			gtnWsNetSalesGeneralResponse.setNsfInfoBean(nsfInfoBean);
			gtnResponse.setGtnWsNetSalesGeneralResponse(gtnWsNetSalesGeneralResponse);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting nsfData Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		}
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_DELETE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter deleteService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			gtnWsNsfService.deleteNsfRecord(gtnWsRequest);
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting nsfDelete Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		}

		LOGGER.info("Exit deleteService");
		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_REMOVE_RECORD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse nsfRemoveRecordService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			gtnWsNsfService.removeRecords(gtnWsRequest);
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error(ex.getMessage());
		}

		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NS_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse nsfSalesBasisUpdateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			gtnWsNsfService.populateColumns(gtnWsRequest);
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error(ex.getMessage());
		}

		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_RESET_TABLE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse nsfResetTableService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			gtnWsNsfService.resetTables(gtnWsRequest);
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error(ex.getMessage());
		}

		return gtnResponse;
	}

}
