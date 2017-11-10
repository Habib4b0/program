package com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller.GtnWsProjectionDeleteController;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.service.GtnWsWorkflowPrivateDeleteService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

@RestController
public class GtnWsPrivateDeleteController {
	public GtnWsPrivateDeleteController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProjectionDeleteController.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@PostMapping(value = GtnWsWorkFlowConstants.GTN_WS_DELETE_SERVICE
			+ GtnFrameworkCommonStringConstants.WORKFLOW_MODULE_NAME)

	public GtnUIFrameworkWebserviceResponse privatedeleteView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse deleteResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse workflowresponse = new GtnWsCommonWorkflowResponse();
		try {
			deleteResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			deleteResponse.getGtnWsGeneralResponse().setSucess(true);

			logger.info("Enter GtnWsProjectionDeleteController");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			GtnWsWorkflowPrivateDeleteService privatedeleteWebservice = new GtnWsWorkflowPrivateDeleteService();
			String query = privatedeleteWebservice.getProductHierarchyDeleteQuery(gtnWsRequest);
			gtnSqlQueryEngine.executeSelectQuery(query);

			deleteResponse.setGtnSerachResponse(gtnSerachResponse);
			deleteResponse.setGtnWSCommonWorkflowResponse(workflowresponse);
			return deleteResponse;
		} catch (GtnFrameworkGeneralException ex) {
			deleteResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while privatedeleteView web service", ex);
			deleteResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return deleteResponse;
		} finally {
			logger.info("Exit privatedeleteView");
		}
	}

}
