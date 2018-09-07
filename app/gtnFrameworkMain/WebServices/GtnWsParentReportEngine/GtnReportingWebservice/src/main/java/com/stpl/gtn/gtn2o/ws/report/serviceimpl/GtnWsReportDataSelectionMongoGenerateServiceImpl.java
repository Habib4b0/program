package com.stpl.gtn.gtn2o.ws.report.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.constants.MongoConstants;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportSqlService;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.service.RelationshipLevelValuesMasterBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service("reportDataSelectionMongo")
@Scope(value = "singleton")
public class GtnWsReportDataSelectionMongoGenerateServiceImpl implements GtnWsReportDataSelectionGenerate {

	@Autowired
	private GtnWsTreeService gtnWsTreeService;

	@Autowired
	private GtnWsMongoService gtnWsMongoService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private ApplicationContext applicationContext;

	private static final GtnWSLogger GTNLOGGER = GtnWSLogger
			.getGTNLogger(GtnWsReportDataSelectionMongoGenerateServiceImpl.class);

	public GtnWsReportDataSelectionMongoGenerateServiceImpl() {
		super();
	}

	@Override
	public void dataSelectionGenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getReportBean()
					.getDataSelectionBean();
			callCCPInsertService(gtnWsRequest);
			callDeductionInsertQuery(dataSelectionBean);
			buildCustomerTree(dataSelectionBean);
			buildProductTree(dataSelectionBean);
			createDataSourceData(MongoConstants.KAFKA_MONGO_COLLECTION_NAME);
			createUserBasedCcpCollection(dataSelectionBean);
		} catch (GtnFrameworkGeneralException ex) {
			GTNLOGGER.error(ex.getErrorMessage());
		}
	}

	public void callCCPInsertService(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORT_CCP_INSERT_MONGO,
				gtnWsRequest, getGsnWsSecurityToken(gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId()));
	}

	public void callDeductionInsertQuery(GtnWsReportDataSelectionBean dataSelectionBean) {
		try {
			String tableName = dataSelectionBean
					.getTableNameWithUniqueId(MongoStringConstants.ST_DEDUCTION_SESSION_TABLE_NAME);
			List<String> input = new ArrayList<>();
			input.add(tableName);
			input.add(tableName);
			input.add(tableName);
			input.add(dataSelectionBean.getTableNameWithUniqueId(MongoStringConstants.ST_CCPD_SESSION_TABLE_NAME));
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlService.getQuery(input, "deductionInsertQuery"));
		} catch (GtnFrameworkGeneralException e) {
			GTNLOGGER.error(e.getMessage());
		}
	}

	public void buildCustomerTree(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		// Selected customer hierarchy all level
		// LevelMap

		List<Object> input = new ArrayList<>();
		input.add(dataSelectionBean.getCustomerRelationshipBuilderSid());
		List tempList = gtnSqlQueryEngine.executeSelectQuery(sqlService.getQuery(input, "getHierarchyTableDetails"));
		RelationshipLevelValuesMasterBean relationshipLevelValueMasterBean = applicationContext
				.getBean(RelationshipLevelValuesMasterBean.class);
		relationshipLevelValueMasterBean.createQuery(tempList, dataSelectionBean, "CUST_HIERARCHY_NO");
		List<Object[]> customerResults = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(relationshipLevelValueMasterBean.getFinalQuery());
		GtnWsReportEngineTreeNode customerNode = gtnWsTreeService.buildTree(customerResults,
				GtnWsHierarchyType.CUSTOMER);
		customerNode.setCreatedDate(new Date());
		customerNode.setSessionId(dataSelectionBean.getSessionId());
		gtnWsMongoService.writeTreeToMongo(MongoStringConstants.CUSTOMER_TREE, customerNode, true);
	}

	public void buildProductTree(GtnWsReportDataSelectionBean dataSelectionBean) throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(dataSelectionBean.getProductRelationshipBuilderSid());
		List tempList = gtnSqlQueryEngine.executeSelectQuery(sqlService.getQuery(input, "getHierarchyTableDetails"));
		RelationshipLevelValuesMasterBean relationshipLevelValueMasterBean = applicationContext
				.getBean(RelationshipLevelValuesMasterBean.class);
		relationshipLevelValueMasterBean.createQuery(tempList, dataSelectionBean, "PROD_HIERARCHY_NO");
		List<Object[]> productResults = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(relationshipLevelValueMasterBean.getFinalQuery());
		GtnWsReportEngineTreeNode productNode = gtnWsTreeService.buildTree(productResults, GtnWsHierarchyType.PRODUCT);
		productNode.setCreatedDate(new Date());
		productNode.setSessionId(dataSelectionBean.getSessionId());
		gtnWsMongoService.writeTreeToMongo(MongoStringConstants.PRODUCT_TREE, productNode, true);
	}

	public void createDataSourceData(String collectionName) {
		gtnWsMongoService.kafkaToMongoData(collectionName);
	}

	public static GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		token.setUserId(userId);
		token.setSessionId(sessionId);
		return token;
	}

	private void createUserBasedCcpCollection(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		List ccpIdList = gtnSqlQueryEngine.executeSelectQuery("Select DISTINCT CCP_DETAILS_SID from "
				+ dataSelectionBean.getTableNameWithUniqueId(MongoStringConstants.ST_CCPD_SESSION_TABLE_NAME));
		if (ccpIdList != null && !ccpIdList.isEmpty()) {
			gtnWsMongoService.createUserBasedCcpCollection(ccpIdList, dataSelectionBean.getUniqueId());
		}
	}

	@Override
	public void dataSelectionRegenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		return;
	}

	@Override
	public void regenerateTreeAndData(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		return;
	}

}
