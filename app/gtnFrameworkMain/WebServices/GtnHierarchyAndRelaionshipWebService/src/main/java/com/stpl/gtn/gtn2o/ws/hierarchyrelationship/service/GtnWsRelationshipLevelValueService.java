package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice.GtnWsHierarchyAndRelationshipSqlService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsRelationshipLevelValueService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private GtnWsHierarchyAndRelationshipSqlService gtnWsHierarchyAndRelationshipSqlService;

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	private static final String RELATIONSHIP_BUILD_VERSION = "RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO";
	private static final String RELATIONSHIP_BUILD_HIERARCHY_NO = "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO";
	private static final String RELATIONSHIP_LEVEL_DEFN = "RELATIONSHIP_LEVEL_DEFINITION";
	private static final String RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";

	@PostConstruct
	private void initializeLogger() {
		super.logInformation(GtnWsRelationshipLevelValueService.class);
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}

	private GtnWsRelationshipLevelValueService() {
		super();
	}

	public String getQueryForLevelValueMap(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		try {
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
			StringBuilder finalQuery = new StringBuilder();
			List<GtnFrameworkQueryGeneratorBean> queryBeanList = new ArrayList<>();
			for (HierarchyLevelDefinitionBean leveldto : hierarchyLevelDefinitionList) {
				if (leveldto.isUserDefined()) {
					List<Object> input = new ArrayList<>();
					input.add(inputBean.getRelationShipBuilderSid());
					input.add(inputBean.getRelationVersionNo());

					finalQuery.append(
							gtnWsHierarchyAndRelationshipSqlService.getQuery(input, "RelationShipUserDefinedLoading"));
					finalQuery.append(" union ");
					continue;
				}
				GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
				GtnFrameworkSelectColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
				queryBean.addSelectClauseBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, Boolean.TRUE, null);
				queryBean.addSelectClauseBean(keyBean.getJoinColumnTable() + "." + keyBean.getDescriptionClauseColumn(),
						null, Boolean.TRUE, null);
				queryBean.setFromTableNameWithAlies(RELATIONSHIP_LEVEL_DEFN, RELATIONSHIP_LEVEL_DEFN);
				GtnFrameworkJoinClauseBean tableJoin = queryBean.addJoinClauseBean(keyBean.getJoinColumnTable(),
						keyBean.getJoinColumnTable(), GtnFrameworkJoinType.JOIN);
				tableJoin.addConditionBean(keyBean.getJoinColumnTable() + "." + keyBean.getMasterSidColumn(),
						"RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES", GtnFrameworkOperatorType.EQUAL_TO);
				queryBean.addWhereClauseBean(RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID, null,
						GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER,
						inputBean.getRelationShipBuilderSid());
				queryBean.addWhereClauseBean("RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO", null,
						GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER, leveldto.getLevelNo());
				queryBean.addWhereClauseBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO,
						GtnFrameworkDataType.INTEGER, inputBean.getRelationVersionNo());
				queryBeanList.add(queryBean);

			}

			for (GtnFrameworkQueryGeneratorBean queryBean : queryBeanList) {
				finalQuery.append(queryBean.generateQuery());
				finalQuery.append(" union ");
			}
			finalQuery.replace(finalQuery.lastIndexOf("union"), finalQuery.length() - 1, "");
			return finalQuery.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	public List<Object[]> getLevelValueMap(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(inputBean.getHieraryQuery());
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsRelationshipLevelValueService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				gtnQueryEngineWebServiceRequest);
		return response.getQueryResponseBean().getResultList();
	}

	public List<Object[]> getHierarchyLevelValues(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		String query = gtnWsHierarchyAndRelationshipSqlService.getQuery("getHierarchyLevelVlaues");
		Object[] params = { inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo() };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(query);
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(paramsType);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsRelationshipLevelValueService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				gtnQueryEngineWebServiceRequest);
		return response.getQueryResponseBean().getResultList();
	}

	public String getCustomerLevelQuery(GtnForecastHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
				.getHierarchyBuilder(inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		HierarchyLevelDefinitionBean selectedHierarchyLevelDto = HierarchyLevelDefinitionBean
				.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
				"LOAD_AVAILABLE_TABLE", hierarchyDefinitionList);
		queryGeneratorService.getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(),
				inputBean.getGroupFilterCompenies(), queryBean);
		StringBuilder finalQuery = new StringBuilder(queryBean.generateQuery());
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputBean.getRelationShipBuilderSid());
		inputList.add(String.valueOf(lastLevelDto.getLevelNo()));
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(String.valueOf(selectedHierarchyLevelDto.getLevelNo()));
		if (inputBean.getForecastEligibleDate() != null) {
			inputList.add(dateFormatter.format(inputBean.getForecastEligibleDate()));
			inputList.add(dateFormatter.format(inputBean.getForecastEligibleDate()));
			finalQuery.append("AND (CONTRACT_ELIGIBLE_DATE >= '?' OR CONTRACT_ELIGIBLE_DATE IS NULL)");
			finalQuery.append("AND (CFP_ELIGIBLE_DATE >= '?' OR CFP_ELIGIBLE_DATE IS NULL)");
		}
		return gtnWsHierarchyAndRelationshipSqlService.getReplacedQuery(inputList, finalQuery.toString());
	}
}
