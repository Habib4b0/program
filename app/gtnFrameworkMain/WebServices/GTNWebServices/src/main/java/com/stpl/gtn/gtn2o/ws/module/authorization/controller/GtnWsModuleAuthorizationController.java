package com.stpl.gtn.gtn2o.ws.module.authorization.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.authorization.constants.GtnWsModuleAuthorizationConstants;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.authorization.service.GtnWsModuleAuthorizationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;

@RestController
@RequestMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SERVICE_URI)
public class GtnWsModuleAuthorizationController {
	public GtnWsModuleAuthorizationController() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsModuleAuthorizationController.class);
	@Autowired
	private GtnWsModuleAuthorizationService gtnWsModuleSecurityService;

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SAVE_SERVICE_URI)
	public GtnUIFrameworkWebserviceResponse saveAuthorizationDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering saveSecurityDetails method");
		GtnUIFrameworkWebserviceResponse gtnSecuritySaveResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnSecuritySaveGeneralResponse = new GtnWsGeneralResponse();
		try {
			gtnSecuritySaveGeneralResponse.setSucess(true);
			gtnWsModuleSecurityService.saveAuthorizationDetails(gtnWsRequest);
		} catch (GtnFrameworkGeneralException ex) {
			gtnSecuritySaveGeneralResponse.setSucess(false);
			gtnSecuritySaveGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  saveSecurityDetails method", ex);
		}
		gtnSecuritySaveResponse.setGtnWsGeneralResponse(gtnSecuritySaveGeneralResponse);
		LOGGER.debug("Exit saveSecurityDetails method");
		return gtnSecuritySaveResponse;

	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_GET_COMPONENT_SECURITY_DETAILS_URI)
	public GtnUIFrameworkWebserviceResponse getAuthorizationDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering getSecurityDetails method");
		GtnUIFrameworkWebserviceResponse finalResultesponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse getSecurityDetailsGeneralResponse = new GtnWsGeneralResponse();
		try {
			getSecurityDetailsGeneralResponse.setSucess(true);
			List<Object[]> roleDetailsResultList = gtnWsModuleSecurityService
					.getComponentAuthorizationDetails(gtnWsRequest);
			List<Object[]> emptyList = Collections.emptyList();
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(roleDetailsResultList == null ? emptyList : roleDetailsResultList);
			gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			finalResultesponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			getSecurityDetailsGeneralResponse.setSucess(false);
			getSecurityDetailsGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  getSecurityDetails method", ex);
		}
		finalResultesponse.setGtnWsGeneralResponse(getSecurityDetailsGeneralResponse);
		LOGGER.debug("Exit getSecurityDetails method");
		return finalResultesponse;

	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_GET_MODULEWISE_SECURITY_DETAILS_URI)
	public GtnUIFrameworkWebserviceResponse getModuleWiseAuthorizationDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering getModuleWiseSecurityDetails method");
		GtnUIFrameworkWebserviceResponse moduleWiseResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse moduleWiseGeneralResponse = new GtnWsGeneralResponse();
		try {
			moduleWiseGeneralResponse.setSucess(true);
			GtnWsModuleAuthorizationGeneralResponse moduleWiseSecurityResponse = new GtnWsModuleAuthorizationGeneralResponse();
			gtnWsModuleSecurityService.getModuleWiseAuthorizationDetails(gtnWsRequest, moduleWiseSecurityResponse);
			moduleWiseResponse.setGtnWsModuleAuthorizationGeneralResponse(moduleWiseSecurityResponse);
		} catch (GtnFrameworkGeneralException ex) {
			moduleWiseGeneralResponse.setSucess(false);
			moduleWiseGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  getModuleWiseSecurityDetails method", ex);
		}
		moduleWiseResponse.setGtnWsGeneralResponse(moduleWiseGeneralResponse);
		LOGGER.debug("Exit getModuleWiseSecurityDetails method");
		return moduleWiseResponse;

	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_SECURITY_SAVE_COMPONENT_URI)
	public GtnUIFrameworkWebserviceResponse saveComponentDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering saveComponentDetails method");
		GtnUIFrameworkWebserviceResponse gtnSaveComponentResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnSecurityGeneralResponse = new GtnWsGeneralResponse();
		try {
			gtnSecurityGeneralResponse.setSucess(true);
			gtnWsModuleSecurityService.saveComponentDetails(gtnWsRequest);
			gtnWsModuleSecurityService.populateGtnModuleComponentRoleDetails();
		} catch (GtnFrameworkGeneralException ex) {
			gtnSecurityGeneralResponse.setSucess(false);
			gtnSecurityGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  saveComponentDetails method", ex);
		}
		gtnSaveComponentResponse.setGtnWsGeneralResponse(gtnSecurityGeneralResponse);
		LOGGER.debug("Exit saveComponentDetails method");
		return gtnSaveComponentResponse;

	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.GTN_UPDATE_MODULE_FLAGE)
	public GtnUIFrameworkWebserviceResponse getUpdateModuleFlage(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering getUpdateModuleFlage method");
		GtnUIFrameworkWebserviceResponse gtnUpdateModuleFlageResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnUpdateModuleGeneralResponse = new GtnWsGeneralResponse();
		try {
			GtnWsModuleAuthorizationGeneralResponse authorizationResponse = new GtnWsModuleAuthorizationGeneralResponse();
			gtnUpdateModuleGeneralResponse.setSucess(true);
			authorizationResponse.setModuleUpdate(gtnWsModuleSecurityService.getUpdateModuleFlag(gtnWsRequest));
			gtnUpdateModuleFlageResponse.setGtnWsModuleAuthorizationGeneralResponse(authorizationResponse);
		} catch (GtnFrameworkGeneralException ex) {
			gtnUpdateModuleGeneralResponse.setSucess(false);
			gtnUpdateModuleGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  getUpdateModuleFlage method", ex);
		}
		gtnUpdateModuleFlageResponse.setGtnWsGeneralResponse(gtnUpdateModuleGeneralResponse);
		LOGGER.debug("Exit getUpdateModuleFlage method");
		return gtnUpdateModuleFlageResponse;
	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.UPDATE_MODULE_UPDATE_FLAGE)
	public GtnUIFrameworkWebserviceResponse updateModuleUpdateFlag(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering updateModuleUpdateFlag method");
		GtnUIFrameworkWebserviceResponse updateModuleUpdateFlagResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse updateModuleUpdateFlagGeneralResponse = new GtnWsGeneralResponse();
		try {
			updateModuleUpdateFlagGeneralResponse.setSucess(true);
			gtnWsModuleSecurityService.updateUpdateModuleFlag(gtnWsRequest);
		} catch (GtnFrameworkGeneralException ex) {
			updateModuleUpdateFlagGeneralResponse.setSucess(false);
			updateModuleUpdateFlagGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  updateModuleUpdateFlag method", ex);
		}
		updateModuleUpdateFlagResponse.setGtnWsGeneralResponse(updateModuleUpdateFlagGeneralResponse);
		LOGGER.debug("Exit updateModuleUpdateFlag method");
		return updateModuleUpdateFlagResponse;
	}

	@PostMapping(value = "/" + GtnWsModuleAuthorizationConstants.UPDATE_MODULE_SUBMODULE_UPDATE_FLAG)
	public GtnUIFrameworkWebserviceResponse updateModuleFlagInModuleMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.debug("Entering updateModuleFlagInModuleMaster method");
		GtnUIFrameworkWebserviceResponse updateModuleMasterResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse updateModuleMasterGeneralResponse = new GtnWsGeneralResponse();
		try {
			updateModuleMasterGeneralResponse.setSucess(true);
			gtnWsModuleSecurityService.updateModuleFlagInModuleMaster(gtnWsRequest);
		} catch (GtnFrameworkGeneralException ex) {
			updateModuleMasterGeneralResponse.setSucess(false);
			updateModuleMasterGeneralResponse.setGtnGeneralException(ex);
			LOGGER.error("Exception in  updateModuleFlagInModuleMaster method", ex);
		}
		updateModuleMasterResponse.setGtnWsGeneralResponse(updateModuleMasterGeneralResponse);
		LOGGER.debug("Exit updateModuleFlagInModuleMaster method");
		return updateModuleMasterResponse;
	}

}
