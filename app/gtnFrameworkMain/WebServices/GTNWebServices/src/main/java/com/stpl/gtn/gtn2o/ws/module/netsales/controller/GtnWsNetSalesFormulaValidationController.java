package com.stpl.gtn.gtn2o.ws.module.netsales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.netsales.service.GtnWsNsfService;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

@RestController
@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_VALIDATION_SERVICE)
public class GtnWsNetSalesFormulaValidationController {
    public GtnWsNetSalesFormulaValidationController(){
        /**
         * empty constructor
         */
    }

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsNetSalesFormulaValidationController.class);

	@Autowired
	private GtnWsNsfService gtnWsNsfService;

	@RequestMapping(value = "/" + GtnWsNsfUriConstants.NSF_POPULATE_VALIDATION_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse nsfSalesBasisPopulateValidateService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			GtnWsNetSalesGeneralResponse nsfGeneralResponse = new GtnWsNetSalesGeneralResponse();
			gtnWsNsfService.isCheckedRecord(gtnWsRequest);
			GtnWsNsfUpdateBean nsfUpdateBean = gtnWsRequest.getGtnWsNetSalesGeneralRequest().getNsfUpdateBean();
			nsfGeneralResponse.setNsfUpdateBean(nsfUpdateBean);
			gtnResponse.setGtnWsNetSalesGeneralResponse(nsfGeneralResponse);
		} catch (GtnFrameworkGeneralException gtnGeneralException) {
			LOGGER.info("Exception in nsfSalesBasisPopulateValidateService() method");
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(gtnGeneralException);
		}
		return gtnResponse;

	}

}
