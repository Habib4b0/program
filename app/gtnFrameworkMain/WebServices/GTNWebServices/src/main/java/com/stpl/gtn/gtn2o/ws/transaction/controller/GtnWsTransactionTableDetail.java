/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.controller;

import java.lang.reflect.Field;

import org.apache.commons.lang.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.transaction.GtnWsTransactionResponse;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;

/**
 *
 * @author Vinoth.Parthasarathy
 */
@RestController
@RequestMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_SERVICE)
public class GtnWsTransactionTableDetail {

	public GtnWsTransactionTableDetail() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsTransactionTableDetail.class);
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@PostMapping(value = GtnWsTransactionConstants.GTN_WS_TRANSACTION_GETDATATYPE_SERVICE)
	public GtnUIFrameworkWebserviceResponse getColumnDataType(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		logger.info("-------getColumnDataTypegetColumnDataTypegetColumnDataTypegetColumnDataType-------"
				+ gtnWsRequest.getGtnWsGeneralRequest().getModuleName());
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsTransactionResponse gtnWsTransactionResponse = new GtnWsTransactionResponse();
		try {
			for (Field object : gtnWebServiceAllListConfig.getTransctionDynamicClass(
					"com.stpl.gtn.gtn2o.ws.entity.transaction." + gtnWsRequest.getGtnWsGeneralRequest().getModuleName())
					.getDeclaredFields()) {
				String columnID = object.getName();
				logger.info("--object.getName()-" + object.getName());
				logger.info("--object.getType()-" + object.getType().getName() + "--" + object.getType().isPrimitive());
				Class<?> columnType = object.getType();
				if (object.getType().isPrimitive()) {
					logger.info("---" + ClassUtils.primitiveToWrapper(object.getType()));
					columnType = ClassUtils.primitiveToWrapper(object.getType());
				} else if (object.getType().equals(HelperTable.class)) {
					columnType = String.class;
				}
				gtnWsTransactionResponse.getColumnDataTypeMap().put(columnID, columnType);
			}
		} catch (SecurityException ex) {
			throw new GtnFrameworkGeneralException("Error in getColumnDataType : ", ex);
		}
		gtnUIFrameworkWebserviceResponse.setGtnWsTransactionResponse(gtnWsTransactionResponse);
		return gtnUIFrameworkWebserviceResponse;

	}

}
