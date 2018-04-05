package com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.service.GtnWsWorkflowOpenViewService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import java.sql.Connection;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

@RestController
public class GtnWsWorkflowOpenViewController {

	public GtnWsWorkflowOpenViewController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkflowOpenViewController.class);

	@Autowired
	private GtnWsWorkflowOpenViewService openviewWebservice;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private SessionFactory sysSessionFactory;

	public GtnWsAllListConfig getGtnWebServiceAllListConfig() {
		return gtnWebServiceAllListConfig;
	}

	public void setGtnWebServiceAllListConfig(GtnWsAllListConfig gtnWebServiceAllListConfig) {
		this.gtnWebServiceAllListConfig = gtnWebServiceAllListConfig;
	}

	@PostMapping(value = GtnWsWorkFlowConstants.GTN_WS_OPEN_VIEW_SAVE_SERVICE
			+ GtnFrameworkCommonStringConstants.WORKFLOW_MODULE_NAME)
	public GtnUIFrameworkWebserviceResponse openViewSearch(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse openViewSearchResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse workflowresponse = new GtnWsCommonWorkflowResponse();
		try {
			openViewSearchResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			openViewSearchResponse.getGtnWsGeneralResponse().setSucess(true);

			logger.info("Enter openviewButtonMethod");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = openviewWebservice.openviewSearchQuery(gtnWsRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);

			if (resultList != null && (!resultList.isEmpty())) {
				workflowresponse.setProcessInstanceId(Integer.parseInt(String.valueOf(resultList.get(0))));
			}

			openViewSearchResponse.setGtnSerachResponse(gtnSerachResponse);
			openViewSearchResponse.setGtnWSCommonWorkflowResponse(workflowresponse);
			return openViewSearchResponse;
		} catch (GtnFrameworkGeneralException ex) {
			openViewSearchResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception in openViewSearch web service", ex);
			openViewSearchResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return openViewSearchResponse;
		} finally {
			logger.info("Exit openViewSearch");
		}
	}

	@PostMapping(value = GtnWsWorkFlowConstants.GTN_WS_OPEN_VIEW_SAVE_SERVICE
			+ GtnFrameworkCommonStringConstants.FETCH_PORTLET_ID)
	public GtnUIFrameworkWebserviceResponse fetchPortletId(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse fetchportletIdResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse fetchworkflowresponse = new GtnWsCommonWorkflowResponse();
		try {
			fetchportletIdResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			fetchportletIdResponse.getGtnWsGeneralResponse().setSucess(true);

			logger.info("Enter fetchPortletIdMethod");
			GtnSerachResponse gtnfetchSerachResponse = new GtnSerachResponse();
			String query = openviewWebservice.fetchportletQuery(gtnWsRequest);
			List<?> resultList = gtnSqlQueryEngine.executeSelectQuery(query);

			if (resultList != null && (!resultList.isEmpty())) {
				fetchworkflowresponse.setFriendlyUrl(String.valueOf(resultList.get(0)));
			}

			fetchportletIdResponse.setGtnSerachResponse(gtnfetchSerachResponse);
			fetchportletIdResponse.setGtnWSCommonWorkflowResponse(fetchworkflowresponse);
			return fetchportletIdResponse;
		} catch (GtnFrameworkGeneralException ex) {
			fetchportletIdResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception in fetchPortletIdMethod web service", ex);
			fetchportletIdResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return fetchportletIdResponse;
		} finally {
			logger.info("Exit fetchPortletIdMethod");
		}
	}

	public String getSysSchemaCatalogs() throws GtnFrameworkGeneralException {
		String bpicatalog = "";
		try (Connection schconnection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			bpicatalog = schconnection.getCatalog();
		} catch (Exception ex) {
			logger.error("Exception in getSysSchemaCatalogs", ex);
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalogs", ex);
		}
		return bpicatalog;
	}

}
