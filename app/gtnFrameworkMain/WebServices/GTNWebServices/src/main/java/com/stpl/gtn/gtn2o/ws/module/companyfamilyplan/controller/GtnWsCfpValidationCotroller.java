package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service.GtnWsCfpValidationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.cfpresponse.GtnWsCfpReponse;

@RestController
@RequestMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE)
public class GtnWsCfpValidationCotroller {
    public GtnWsCfpValidationCotroller(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCfpValidationCotroller.class);

	@Autowired
	private GtnWsCfpValidationService cfpWebservice;

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_CFPID_CFPNO_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfpIdAndCfpNoValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter companyAdditionSearch");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			List<Object> resultList = cfpWebservice.cfpIdAndCfpNoValidation(gtnWsRequest);
			HashMap<String, Object> map = new HashMap<>();
			boolean cfpId = false;
			boolean cfpNo = false;
			if (resultList != null && !resultList.isEmpty()) {
				String returnValue = resultList.get(0).toString().trim();
				cfpId = !returnValue.isEmpty() && returnValue.contains("CFP_ID");
				cfpNo = !returnValue.isEmpty() && returnValue.contains("CFP_NO");
			}
			map.put("cfpId", cfpId);
			map.put("cfpNo", cfpNo);
			gtnResponse.setEditRecord(map);
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting companyAdditionSearch Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit companyAdditionSearch");
		}
	}

	@PostMapping(value = GtnWsCFamilyPlanContants.GTN_WS_CFP_COMMON_VALIDATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse cfpCommonValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter cfpCommonValidation");

			GtnCFamilyPlanValidationBean validationBean = cfpWebservice.cfpCommonValidation(gtnWsRequest);
			GtnWsCfpReponse cfpResponse = new GtnWsCfpReponse();
			cfpResponse.setGtnCFamilyPlanValidationBean(validationBean);
			gtnResponse.setGtnWsCfpReponse(cfpResponse);

			return gtnResponse;
		} catch (Exception ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting cfpCommonValidation Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			logger.info("Exit cfpCommonValidation");
		}
	}

}
