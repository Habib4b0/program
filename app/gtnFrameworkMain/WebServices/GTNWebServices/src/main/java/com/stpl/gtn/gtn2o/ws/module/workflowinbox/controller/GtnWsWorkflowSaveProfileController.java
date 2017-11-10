package com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.service.GtnWsWorkflowSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

@RestController
public class GtnWsWorkflowSaveProfileController {
	public GtnWsWorkflowSaveProfileController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkflowSaveProfileController.class);

	@Autowired
	private GtnWsWorkflowSaveService workflowSaveWebservice;

	@PostMapping(value = GtnWsWorkFlowConstants.GTN_WS_WORKFLOW_VIEW_SAVE_SERVICE
			+ GtnFrameworkCommonStringConstants.WORKFLOW_MODULE_NAME)
	public GtnUIFrameworkWebserviceResponse saveProfileController(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse response) {
		logger.info("Enter GTN_WS_WORKFLOW_VIEW_SAVE_SERVICE");
		GtnWsGeneralResponse generalReponse = new GtnWsGeneralResponse();
		response.setGtnWsGeneralResponse(generalReponse);
		int count = 0;
		try {
			GtnWsWorkflowInboxBean bean = gtnWsRequest.getGtnWSCommonWorkflowRequest().getGtnWorkflowInboxBean();
			Integer workflowModelSid = bean.getWorkflowSid();
			if (workflowModelSid == null || workflowModelSid == 0) {
				workflowModelSid = workflowSaveWebservice.saveWorkflowQuery(bean, gtnWsRequest);
				bean.setWorkflowSid(workflowModelSid);
			} else {
				workflowSaveWebservice.updateSaveProfileQuery(gtnWsRequest, bean);

			}

			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception saveWorkflow", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit saveWorkflow and inserted " + count + "  rows");
		}

	}

}
