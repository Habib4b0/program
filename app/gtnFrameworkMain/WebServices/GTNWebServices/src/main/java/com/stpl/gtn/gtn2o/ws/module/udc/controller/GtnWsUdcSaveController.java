package com.stpl.gtn.gtn2o.ws.module.udc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.udc.service.GtnWsUdcSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.udc.GtnWsUdcRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsBrandMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.constants.GtnWsUdcConstants;

@RestController
@RequestMapping(value = GtnWsUdcConstants.GTN_UDC_SERVICE)
public class GtnWsUdcSaveController {

	public GtnWsUdcSaveController() {
		super();
	}

	private final GtnWSLogger gtnWsLogger = GtnWSLogger.getGTNLogger(GtnWsUdcSaveController.class);

	@Autowired
	private GtnWsUdcSaveService gtnWsUdcSaveService;

	@PostMapping(value = GtnWsUdcConstants.GTN_UDC_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveUdc(@RequestBody GtnUIFrameworkWebserviceRequest gtnRequest)
			throws GtnFrameworkGeneralException {
		gtnWsLogger.info("Enter saveUdc");
		GtnWsUdcRequest udcWsRequest = gtnRequest.getGtnWsUdcRequest();
		GtnWsUdcBean udcBean = udcWsRequest.getGtnWsUdcBean();
		return gtnWsUdcSaveService.saveUdc(udcBean);
	}

	@PostMapping(value = GtnWsUdcConstants.GTN_UDC_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteUdc(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			  {
		gtnWsLogger.info("Entering into delete Udc");

		GtnWsUdcRequest gtnWsUdcRequest = gtnUIFrameworkWebserviceRequest.getGtnWsUdcRequest();
		GtnWsUdcBean gtnWsUdcBean = gtnWsUdcRequest.getGtnWsUdcBean();
		GtnWsUdcInfoBean gtnWsUdcInfoBean = gtnWsUdcBean.getGtnWsUdcInfoBean();
		boolean isBrand = gtnWsUdcInfoBean.isBrand();
		Integer systemId = gtnWsUdcInfoBean.getHelperTableSid();
		if (isBrand) {
			BrandMaster brandMasterModel = new BrandMaster();
			brandMasterModel.setBrandMasterSid(systemId);
			return gtnWsUdcSaveService.deleteBrandMaster(brandMasterModel);
		} else {
			HelperTable helperTableModel = new HelperTable();
			helperTableModel.setHelperTableSid(systemId);

			return gtnWsUdcSaveService.deleteUdc(helperTableModel);
		}

	}

	@PostMapping(value = GtnWsUdcConstants.GTN_UDC_BRAND_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveBrandMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		gtnWsLogger.info("Entering into brand Master Save");
		GtnWsUdcRequest gtnWsUdcRequest = gtnUIFrameworkWebserviceRequest.getGtnWsUdcRequest();
		GtnWsBrandMasterInfoBean gtnWsBrandMasterInfoBean = gtnWsUdcRequest.getGtnWsBrandMasterInfoBean();
		GtnWsBrandMasterBean gtnWsBrandMasterBean = gtnWsBrandMasterInfoBean.getGtnWsBrandMasterBean();
		return gtnWsUdcSaveService.saveBrandMaster(gtnWsBrandMasterBean);

	}

}
