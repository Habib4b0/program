package com.stpl.gtn.gtn2o.ws.module.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Attachment;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = "/report")
public class GtnWSReportProductController {
	public GtnWSReportProductController() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWSReportProductController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkQueryEngineMain getGtnFrameworkQueryEngineMain() {
		return gtnFrameworkQueryEngineMain;
	}

	public void setGtnFrameworkQueryEngineMain(GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain) {
		this.gtnFrameworkQueryEngineMain = gtnFrameworkQueryEngineMain;
	}

	@RequestMapping(value = "/hierarchyDefinition", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getHierarchySidAndLevelDefId(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {

		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnReportHierarchyLookupBean lookupBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		String query = "SELECT distinct LEVEL_NO, LEVEL_VALUE_REFERENCE, TABLE_NAME, FIELD_NAME, LEVEL_NAME, HLD.HIERARCHY_LEVEL_DEFINITION_SID, HLD.HIERARCHY_DEFINITION_SID, HT.DESCRIPTION from HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_DEFINITION HD on HD.HIERARCHY_DEFINITION_SID = hld.HIERARCHY_DEFINITION_SID JOIN dbo.HELPER_TABLE HT on HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY where hld.HIERARCHY_DEFINITION_SID ="
				+ lookupBean.getHierarchyDefSid() + " and HLD.VERSION_NO =" + lookupBean.getVersionNo();

		List<Object[]> results = executeQuery(query);
		GtnWsReportResponse gtnWsReportResponse = new GtnWsReportResponse();
		gtnWsReportResponse.setResultList(results);
		response.setGtnWsReportResponse(gtnWsReportResponse);
		return response;
	}

	@RequestMapping(value = "/queryExecutor", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getResultSet(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {

		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> results = executeQuery(gtnWsReportRequest.getQuery());
		GtnWsReportResponse gtnWsReportResponse = new GtnWsReportResponse();
		gtnWsReportResponse.setResultList(results);
		response.setGtnWsReportResponse(gtnWsReportResponse);
		return response;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		gtnSqlQueryEngine.setSessionFactory(sessionFactory);
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}
}
