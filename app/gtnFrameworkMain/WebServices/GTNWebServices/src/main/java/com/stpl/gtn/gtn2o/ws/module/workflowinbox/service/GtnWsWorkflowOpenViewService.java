package com.stpl.gtn.gtn2o.ws.module.workflowinbox.service;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller.GtnWsWorkflowOpenViewController;
import java.util.ArrayList;
import java.util.List;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import java.io.FileInputStream;

@Service()
@Scope(value = "singleton")
public class GtnWsWorkflowOpenViewService {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkflowOpenViewService.class);

	@Autowired
	private final GtnWsWorkflowOpenViewController controller;

	public GtnWsWorkflowOpenViewService(GtnWsWorkflowOpenViewController controller) {
		this.controller = controller;
	}

	public GtnWsWorkflowOpenViewController getController() {
		return controller;
	}
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String openviewSearchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsWorkflowInboxBean projMasterBean = gtnWsRequest.getGtnWSCommonWorkflowRequest().getGtnWorkflowInboxBean();

		StringBuilder sql = new StringBuilder();
		String workflowId = projMasterBean.getWorkflowId();
		Integer projectionmasterSid = projMasterBean.getProjectionMasterSid();
		sql.append(gtnWsSqlService.getQuery("getOpenViewQuery"));

		if (workflowId != null && (workflowId.startsWith("BR") || workflowId.startsWith("FD"))) {
			sql.append("WHERE WPI.ACC_CLOSURE_MASTER_SID = " + projectionmasterSid);
		} else if (workflowId != null && workflowId.startsWith("CF")) {
			sql.append("WHERE WPI.CONTRACT_MASTER_SID = " + projectionmasterSid + " AND WPI.CONTRACT_STRUCTURE = "
					+ projectionmasterSid);

		} else {
			sql.append("WHERE WPI.PROJECTION_MASTER_SID = " + projectionmasterSid);
		}

		return sql.toString();

	}

	public String fetchportletQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		logger.debug("In fetchportletQuery Query");
		GtnWsWorkflowInboxBean fetchprojMasterBean = gtnWsRequest.getGtnWSCommonWorkflowRequest()
				.getGtnWorkflowInboxBean();
		String fetchworkflowId = fetchprojMasterBean.getWorkflowId();
		java.util.Properties fetchpath = getPropertyFile(
				System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH_PROPERTY));
		String valueforCommercial = fetchpath.getProperty(GtnFrameworkCommonStringConstants.CF);
		String valueforContractDashboard = fetchpath.getProperty(GtnFrameworkCommonStringConstants.CM);
		StringBuilder sqlQuery = new StringBuilder();
		List<Object> portletidList = new ArrayList<>();
		String bpicatalog = getController().getSysSchemaCatalogs();
		portletidList.add(bpicatalog);
		portletidList.add(bpicatalog);
		if (fetchworkflowId != null) {
			if ((fetchworkflowId.startsWith(GtnFrameworkCommonStringConstants.CM)
					|| fetchworkflowId.startsWith(GtnFrameworkCommonStringConstants.CMF))) {
				portletidList.add(valueforContractDashboard);
			} else {
				portletidList.add(valueforCommercial);
			}
		}
		sqlQuery.append(gtnWsSqlService.getQuery(portletidList, GtnFrameworkCommonStringConstants.GETFRIENDLYURLQUERY));
		return sqlQuery.toString();
	}

	public java.util.Properties getPropertyFile(String bpiPropLoc) {
		java.util.Properties prop = new java.util.Properties();
		try {
			FileInputStream fileIS;
			fileIS = GtnFileNameUtils.getFileInputStream(bpiPropLoc);
			prop.load(fileIS);
		} catch (Exception ex) {
			logger.error("Exception", ex);
		}
		return prop;

	}
}