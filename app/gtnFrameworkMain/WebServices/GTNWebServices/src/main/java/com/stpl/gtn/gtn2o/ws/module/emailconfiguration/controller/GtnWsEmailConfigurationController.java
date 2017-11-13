/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.emailconfiguration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.emailconfiguration.service.GtnWsEmailConfigurationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;

@RestController
@RequestMapping(value = GtnWsEMailConfigurationConstants.MAIL_CONFIG_SAVE_ACTION_SAVE)
public class GtnWsEmailConfigurationController {
	public GtnWsEmailConfigurationController() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsEmailConfigurationService gtnWsMailConfigurationService;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsEmailConfigurationController.class);

	@RequestMapping(value = GtnWsEMailConfigurationConstants.SAVE_MAIL_CONF, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveMailConfig(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			saveMailConfiguration(gtnWsRequest.getMailConfigurationRequest(), gtnWsRequest.getGtnWsGeneralRequest());
			gtnResponse.setGtnWsMailConfigurationResponse(new GtnWsMailConfigurationResponse());
			gtnResponse.getGtnWsMailConfigurationResponse().setSuccess(true);
			gtnResponse.getGtnWsMailConfigurationResponse()
					.setMessage(GtnWsEMailConfigurationConstants.SUCCESS_MESSAGE_AFTER_SAVE);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_IN + GtnWsEMailConfigurationConstants.SAVE_MAIL_CONF, ex);
		}
		logger.info(GtnFrameworkWebserviceConstant.EXIT + "saveMailConfig");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsEMailConfigurationConstants.SAVE_MAIL_NOTIFICATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveMailNotification(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			saveMailNotification(gtnWsRequest.getMailConfigurationRequest(), gtnWsRequest.getGtnWsGeneralRequest());
			gtnResponse.setGtnWsMailConfigurationResponse(new GtnWsMailConfigurationResponse());
			gtnResponse.getGtnWsMailConfigurationResponse().setNotificationSuccess(true);

		} catch (Exception ex) {
			logger.error(
					GtnFrameworkWebserviceConstant.ERROR_IN + GtnWsEMailConfigurationConstants.SAVE_MAIL_NOTIFICATION,
					ex);
		}
		logger.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsEMailConfigurationConstants.SAVE_MAIL_NOTIFICATION);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsEMailConfigurationConstants.MAIL_CONFIG_COMBOBOX_ONCHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse setComboboxOnchange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
		GtnWsMailConfigurationResponse configurationResponse = new GtnWsMailConfigurationResponse();
		List<Object[]> comboBoxOnChange = gtnWsMailConfigurationService
				.comboBoxOnChangeLogic(gtnWsRequest.getMailConfigurationRequest());

		try {
			if (!comboBoxOnChange.isEmpty()) {
				configurationBean.setComboboxOnChangeDataLoad(comboBoxOnChange);
				configurationResponse.seteMailConfigurationBean(configurationBean);
				gtnResponse.setGtnWsMailConfigurationResponse(configurationResponse);
			} else {
				logger.debug("No data in table");
			}
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_IN + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE,
					ex);
		}
		logger.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse setDefaultValues(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceResponse gtnResponse = gtnWsMailConfigurationService.setDefaultValueLogic();
		logger.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE);
		return gtnResponse;
	}

	public void saveMailNotification(GtnWsMailConfigurationRequest mcRequest, GtnWsGeneralRequest gtnWsGeneralRequest)
			throws GtnFrameworkGeneralException {

		gtnWsMailConfigurationService.saveMailNotificationLogic(mcRequest, gtnWsGeneralRequest);

	}

	public void saveMailConfiguration(GtnWsMailConfigurationRequest mcRequest, GtnWsGeneralRequest gtnWsGeneralRequest)
			throws GtnFrameworkGeneralException {
		gtnWsMailConfigurationService.saveMailConfigurationLogic(mcRequest, gtnWsGeneralRequest);
	}
}
