package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkDeductionCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.GtnFrameworkQueryGeneraterServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.deduction.GtnFrameworkDeductionJoinServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderHierarchyFileGeneratorService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Component("Deduction")
@Scope(value = "singleton")
public class GtnFrameworkDeductionAutoUpdateServiceImpl implements GtnFrameworkAutoupdateService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService automaticService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnFrameworkDeductionJoinServiceImpl joinService;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator;

	@Autowired
	@Qualifier("DeductionSelect")
	private GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("DeductionWhere")
	private GtnFrameworkWhereQueryGeneratorService whereService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkDeductionAutoUpdateServiceImpl.class);

	public GtnFrameworkDeductionAutoUpdateServiceImpl() {
		super();
	}

	@Override
	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException {
		return Boolean.TRUE;
	}

	@Override
	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) throws InterruptedException {
		if (relationBean.getVersionNo() == 0) {
			return true;
		}
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		final ExecutorService customerExecutorService = Executors
				.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		try (Session session = sessionFactory.openSession()) {
			RelationshipBuilder productrelationshipBuilder = session.get(RelationshipBuilder.class,
					relationBean.getDeductionRelation());
			List<Integer> itemMastersidList = getItemMasterSidForProductRelation(productrelationshipBuilder);
			for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
				if (hierarchyLevelDefinitionList.get(i).isUserDefined())
					continue;
				GtnFramworkDeductionCheckForAutoUpdateRunnable customerRunnableTarget = applicationContext
						.getBean(GtnFramworkDeductionCheckForAutoUpdateRunnable.class);
				customerRunnableTarget.setAtomicBoolean(atomicBoolean);
				customerRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
				customerRunnableTarget.setIndex(i);
				customerRunnableTarget.setRelationBean(relationBean);
				customerRunnableTarget.setItemMastersidList(itemMastersidList);

				customerExecutorService.submit(customerRunnableTarget);
			}
			customerExecutorService.shutdown();
			customerExecutorService.awaitTermination(5, TimeUnit.MINUTES);

		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error(e.getErrorMessage());
		}
		return atomicBoolean.get();
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean) {
		try (Session session = sessionFactory.openSession()) {
			GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
			int updatedVersionNo = automaticService.updateRelationShipVersionNo(
					relationBean);

			RelationshipBuilder productrelationshipBuilder = session.get(RelationshipBuilder.class,
					relationBean.getDeductionRelation());

			List<Integer> itemMastersidList = getItemMasterSidForProductRelation(productrelationshipBuilder);

			for (int i = 0; i < hierarchyLevelDefinitionList.size(); i++) {
				HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
				GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
						hierarchyLevelBean.getHierarchyDefinitionSid(),
						hierarchyLevelBean.getHierarchyLevelDefinitionSid(), hierarchyLevelBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();

				GtnFrameworkQueryGeneraterServiceImpl queryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
						selectService, joinService, whereService);
				queryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
						updatedVersionNo, i);
				List<String> firstInput = new ArrayList<>();
				firstInput.add(getListToString(itemMastersidList));
				String insertQuery = gtnWsSqlService.getReplacedQuery(firstInput, querygeneratorBean.generateQuery());
				List<String> insertQueryInput = new ArrayList<>();
				insertQueryInput.add(insertQuery);
				String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
						"relationShipSubQueryToInsertAutomaticData");
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getItemMasterSidForProductRelation(RelationshipBuilder productrelationshipBuilder)
			throws GtnFrameworkGeneralException {
		List<Integer> result = new ArrayList<>();
		try {
			List<HierarchyLevelDefinitionBean> hierarchyList = getRBHierarchyLevelDefinitionBySid(
					productrelationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid(),
					productrelationshipBuilder.getHierarchyDefinition().getVersionNo(),
					productrelationshipBuilder.getRelationshipBuilderSid());

			int levelNo = HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList);
			HierarchyLevelDefinitionBean destinationHierarchyBean = HierarchyLevelDefinitionBean
					.getBeanByLevelNo(levelNo, hierarchyList);
			if (destinationHierarchyBean != null) {
				GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
								destinationHierarchyBean.getFieldName());
				GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
				GtnFrameworkHierarchyQueryBean queryBean = fileReadWriteService.getQueryFromFile(
						destinationHierarchyBean.getHierarchyDefinitionSid(),
						destinationHierarchyBean.getHierarchyLevelDefinitionSid(),
						destinationHierarchyBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean finalQueryBean = queryBean.getQuery();
				addRelationShipJoin(hierarchyList, destinationHierarchyBean, keyBean, finalQueryBean);
				List<Object> inputsList = new ArrayList<>();
				inputsList.add(productrelationshipBuilder.getRelationshipBuilderSid());
				inputsList.add(destinationHierarchyBean.getLevelNo());
				inputsList.add(productrelationshipBuilder.getVersionNo());
				finalQueryBean.removeAllWhereClauseConfigList();
				finalQueryBean.removeSelectClauseByIndex(0);
				finalQueryBean.removeSelectClauseByIndex(0);
				finalQueryBean.addSelectClauseBean(GtnWsRelationshipBuilderConstants.ITEM_MASTER_TABLE_COLUMN_NAME,
						null, Boolean.TRUE, null);
				String finalQuery = finalQueryBean.generateQuery();
				result = (List<Integer>) gtnSqlQueryEngine
						.executeSelectQuery(gtnWsSqlService.getReplacedQuery(inputsList, finalQuery));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;

	}

	public void addRelationShipJoin(List<HierarchyLevelDefinitionBean> hierarchyList,
			HierarchyLevelDefinitionBean destinationHierarchyBean, GtnFrameworkSingleColumnRelationBean keyBean,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		String relationShipLevelDef = "RELATIONSHIP_LEVEL_DEFINITION";
		GtnFrameworkJoinClauseBean relationJoin = finalQueryBean.addJoinClauseBean(relationShipLevelDef,
				relationShipLevelDef, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(hierarchyList, destinationHierarchyBean), GtnFrameworkOperatorType.LIKE);
	}

	@SuppressWarnings("unchecked")
	public List<HierarchyLevelDefinitionBean> getRBHierarchyLevelDefinitionBySid(int hierarchyDefSid, int versionNo,
			int prodRelationShipBuilderSid) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(prodRelationShipBuilderSid));
		inputlist.add(String.valueOf(hierarchyDefSid));
		inputlist.add(String.valueOf(versionNo));
		List<Object[]> result = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
						"getRBHierarchyLevelDefinitionByProductRelationSid"));
		return gtnWsRelationshipBuilderHierarchyFileGenerator.gettHierarchyLevelDefinitionListMain(result);
	}

	public List<String> getRelationQueries(int relationshipSid,
			List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList, int versionNo) {
		List<String> queryList = new ArrayList<>();
		List<String> inputData = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				inputData.add(String.valueOf(levelDto.getLevelNo()));
				inputData.add(String.valueOf(relationshipSid));
				inputData.add(String.valueOf(versionNo));
				String relationQuery = gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputData,
						"relationShipSubQueryForSubQuery");
				queryList.add(relationQuery);
				inputData.clear();
			}
		}
		return queryList;
	}

	private String getListToString(Collection<?> masterSids) {
		StringBuilder result = new StringBuilder();
		if (masterSids != null && !masterSids.isEmpty()) {
			for (Object hirarechyNo : masterSids) {
				result.append("'" + hirarechyNo + "' ,");
			}
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		}
		return "''";
	}

	public String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		StringBuilder dedQuery = new StringBuilder();
		StringBuilder dedFinalQuery = new StringBuilder();
		for (int i = 0; i < selectedHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				dedQuery.append(",'%'");
				dedQuery.append(",'.'");
				continue;
			}
			dedQuery.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			dedQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			dedQuery.append(",'.'");
		}
		dedFinalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		dedFinalQuery.append(dedQuery);
		dedQuery.append(",'%'");
		dedFinalQuery.append(")");
		return dedFinalQuery.toString();
	}
}
