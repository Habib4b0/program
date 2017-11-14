package com.stpl.gtn.gtn2o.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stpl.gtn.gtn2o.ws.entity.role.Role;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReturnsDatabaseService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsWorkFlowOpenViewService;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

@Controller
@RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_BPM_OPEN_VIEW_SAVE_SERVICE)
public class GtnWsWorkFlowOpenViewController {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkFlowOpenViewController.class);

	@Autowired
	private GtnWsReturnsDatabaseService gtnWsReturnsDatabaseService;

	@Autowired
	private GtnWsUserRoleService gtnWsUserRoleService;

	public GtnWsWorkFlowOpenViewController() {
		super();
	}

	public GtnWsWorkFlowOpenViewController(GtnWsReturnsDatabaseService gtnWsReturnsDatabaseService,
			GtnWsUserRoleService gtnWsUserRoleService) {
		super();
		this.gtnWsReturnsDatabaseService = gtnWsReturnsDatabaseService;
		this.gtnWsUserRoleService = gtnWsUserRoleService;
	}

	@RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_BPM_OPEN_VIEW_SAVE_SERVICE_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse openViewSearch(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsWorkFlowOpenViewService openviewWebservice = new GtnWsWorkFlowOpenViewService();
		GtnUIFrameworkWebserviceResponse openViewBpmResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse workflowresponse = new GtnWsCommonWorkflowResponse();
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
		User userModel = gtnWsUserRoleService.getUser(Long.parseLong(gtnWsGeneralRequest.getUserId().trim()));
		List<String> roleList = new ArrayList<>();
		try {
			openViewBpmResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			openViewBpmResponse.getGtnWsGeneralResponse().setSucess(true);
			logger.info("Enter openviewButtonMethod");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String bpmOpenViewQuery = openviewWebservice.openviewSearchQuery();
			Object[] bpmOpenViewQueryParams = { gtnWsRequest.getGtnWSCommonWorkflowRequest().getProcessInstanceId() };
			@SuppressWarnings("unchecked")
			List<Object[]> resultList = gtnWsReturnsDatabaseService.bpmexecuteQuery(bpmOpenViewQuery, bpmOpenViewQueryParams);
			if (resultList != null) {
				for (Object[] obj : resultList) {
					roleList.add(String.valueOf(obj[1]));
				}
			}
			boolean roleMatched = false;
			List<Role> userRoles = gtnWsUserRoleService.getUserRoles(userModel.getUserId());

			for (Role r : userRoles) {
				if (roleList.contains(r.getName())) {
					roleMatched = true;
					break;
				}

			}
			workflowresponse.setRoleMatched(roleMatched);
			openViewBpmResponse.setGtnSerachResponse(gtnSerachResponse);
			openViewBpmResponse.setGtnWSCommonWorkflowResponse(workflowresponse);
			return openViewBpmResponse;
		} catch (GtnFrameworkGeneralException ex) {
			openViewBpmResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting openViewSearch in bpm", ex);
			openViewBpmResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return openViewBpmResponse;
		} finally {
			logger.info("Exit openViewSearch");
		}
	}

}