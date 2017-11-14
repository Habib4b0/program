package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpValidationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;

@RestController
@RequestMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE)
public class GtnWsIfpValidationCotroller {
	public GtnWsIfpValidationCotroller() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsIfpValidationCotroller.class);

	@Autowired
	private GtnWsIfpValidationService ifpWebservice;

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_IFPID_IFPNO_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse ifpIdAndIfpNoValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse ifpValidationResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			ifpValidationResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			ifpValidationResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			logger.info("Enter ifpIdAndIfpNoValidation");
			List<Object> resultList = ifpWebservice.ifpIdAndIfpNoValidation(gtnWsRequest);
			HashMap<String, Object> map = new HashMap<>();
			boolean ifpId = false;
			boolean ifpNo = false;
			if (resultList != null && !resultList.isEmpty()) {
				String returnValue = resultList.get(0).toString().trim();
				ifpId = !returnValue.isEmpty() && returnValue.contains("IFP_ID");
				ifpNo = !returnValue.isEmpty() && returnValue.contains("IFP_NO");
			}
			map.put("ifpId", ifpId);
			map.put("ifpNo", ifpNo);
			ifpValidationResponse.setEditRecord(map);
			ifpValidationResponse.setGtnSerachResponse(gtnSerachResponse);
			return ifpValidationResponse;
		} catch (Exception ex) {
			ifpValidationResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting ifpIdAndIfpNoValidation Query", ex);
			ifpValidationResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return ifpValidationResponse;
		} finally {
			logger.info("Exit ifpIdAndIfpNoValidation");
		}
	}

	@PostMapping(value = GtnWsIFamilyPlanContants.GTN_WS_IFP_COMMON_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse ifpCommonValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter ifpCommonValidation");

			GtnIFamilyPlanValidationBean validationBean = ifpWebservice.ifpCommonValidation(gtnWsRequest);
			GtnWsIfpReponse ifpResponse = new GtnWsIfpReponse();
			ifpResponse.setGtnIFamilyPlanValidationBean(validationBean);
			gtnResponse.setGtnWsIfpReponse(ifpResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting ifpCommonValidation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit ifpCommonValidation");
		}
	}

}
