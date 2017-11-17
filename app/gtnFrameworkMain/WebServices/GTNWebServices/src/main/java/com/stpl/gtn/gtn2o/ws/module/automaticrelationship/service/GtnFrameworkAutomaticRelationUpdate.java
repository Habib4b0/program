package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
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
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkAutomaticRelationUpdate {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;
	@Autowired
	private ApplicationContext applicationContext;

	public GtnFrameworkAutomaticRelationUpdate() {
		super();
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public void setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean) {
		this.gtnFrameworkEntityMasterBean = gtnFrameworkEntityMasterBean;
	}

	public GtnFrameworkHierarchyService getHierarchyService() {
		return hierarchyService;
	}

	public void setHierarchyService(GtnFrameworkHierarchyService hierarchyService) {
		this.hierarchyService = hierarchyService;
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkAutomaticRelationUpdate.class);
	public void checkAndUpdateAutomaticRelationship(Integer relationshipBuilderSid, String userId)
			throws GtnFrameworkGeneralException, InterruptedException {
		GtnWsRelationshipBuilderBean relationBean = getRelationtionshipBuilder(relationshipBuilderSid);
		if (relationBean != null) {
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = getHierarchyBuilder(
					relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
			if (checkAutomaticRelation(relationshipBuilderSid)
					&& checkForAutoUpdate(relationBean, hierarchyDefinitionList)) {
				doAutomaticUpdate(hierarchyDefinitionList, relationBean, userId);
			}
			LOGGER.info("checkAndUpdateAutomaticRelationship has finihsed");
		}
	}

	private void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, String userId) throws GtnFrameworkGeneralException {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		int updatedVersionNo = insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo, relationBean, userId);
		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
			GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
					hierarchyLevelBean.getHierarchyDefinitionSid(),
					hierarchyLevelBean.getHierarchyLevelDefinitionSid(), hierarchyLevelBean.getVersionNo());
			HierarchyLevelDefinitionBean previousHierarchyLevelBean = i > 0 ? hierarchyLevelDefinitionList.get(i - 1)
					: null;
			GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();
			addSelectClauseAndJoinClause(hierarchyLevelBean, relationBean, querygeneratorBean,
					previousHierarchyLevelBean, updatedVersionNo, userId);
			List<String> whereQueryList = getRelationQueries(relationBean.getRelationshipBuilderSid(), updatedVersionNo,
					hierarchyLevelDefinitionList.subList(0, i).toArray(new HierarchyLevelDefinitionBean[i]));
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean.getLevelNo());
			inputs.add(updatedVersionNo);
			inputs.addAll(whereQueryList);
			hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
			String finalQuery = gtnWsSqlService.getReplacedQuery(inputs, querygeneratorBean.generateQuery());
			List<String> insertQueryInput = new ArrayList<>();
			insertQueryInput.add(finalQuery);
			String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
					"relationShipSubQueryToInsertAutomaticData");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
		}

	}

	private int insertRelationTillFirstLevelAndGetVersionNo(int firstLinkedLevelNo,
			GtnWsRelationshipBuilderBean relationBean, String userId) throws GtnFrameworkGeneralException {
		int relationVersionNo = updateRelationShipVersionNo(relationBean, userId);
		insertRelationshipLevelValues(relationBean, firstLinkedLevelNo);
		return relationVersionNo;
	}

	private void insertRelationshipLevelValues(GtnWsRelationshipBuilderBean relationBean, int firstLinkedLevelNo)
			throws GtnFrameworkGeneralException {
		List<Integer> input = new ArrayList<>();
		input.add(relationBean.getRelationshipBuilderSid());
		input.add(firstLinkedLevelNo + 1);
		input.add(relationBean.getVersionNo());
		String sqlquery = gtnWsSqlService.getQuery(input, "relationShipLevelInsert");
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlquery);
	}

	private int updateRelationShipVersionNo(GtnWsRelationshipBuilderBean relationBean, String userId) {

		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			RelationshipBuilder relationshipBuilder = session.load(RelationshipBuilder.class,
					relationBean.getRelationshipBuilderSid());
			relationshipBuilder.setModifiedBy(Integer.valueOf(userId));
			relationshipBuilder.setModifiedDate(new Date());
			relationshipBuilder.setCreatedBy(Integer.valueOf(userId));
			relationshipBuilder.setCreatedDate(new Date());
			relationshipBuilder.setVersionNo(relationshipBuilder.getVersionNo() + 1);
			session.update("RelationshipBuilder", relationshipBuilder);
			tx.commit();
			return relationshipBuilder.getVersionNo();
		}
	}

	private void addSelectClauseAndJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean, int updatedVersionNo, String userId) {
		addSelectClause(hierarchyLevelBean, relationBean, querygeneratorBean, previousHierarchyLevelBean,
				updatedVersionNo, userId);
		querygeneratorBean.removeSelectClauseByIndex(0);
		addJoinClause(previousHierarchyLevelBean, querygeneratorBean);
	}

	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean) {

		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = queryGenerartorBean.addJoinClauseBean("RELATIONSHIP_LEVEL_DEFINITION",
				"RELATIONSHIP_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	private void addSelectClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean, int updatedVersionNo, String userId) {
		querygeneratorBean.addSelectClauseBean(null, "RELATIONSHIP_BUILDER_SID", Boolean.FALSE,
				String.valueOf(relationBean.getRelationshipBuilderSid()));
		querygeneratorBean.addSelectClauseBean(null, "HIERARCHY_LEVEL_DEFINITION_SID", Boolean.FALSE,
				String.valueOf(hierarchyLevelBean.getHierarchyLevelDefinitionSid()));

		querygeneratorBean.addSelectClauseBean(null, "LEVEL_NO", Boolean.FALSE,
				String.valueOf(hierarchyLevelBean.getLevelNo()));
		querygeneratorBean.addSelectClauseBean(null, "LEVEL_NAME", Boolean.FALSE,
				"'" + hierarchyLevelBean.getLevelName() + "'");
		String parentNode = getParentNode(previousHierarchyLevelBean);
		String hierarchyNo = getHierarchyNo(hierarchyLevelBean);
		querygeneratorBean.addSelectClauseBean(null, "PARENT_NODE", Boolean.FALSE, parentNode);
		querygeneratorBean.addSelectClauseBean(null, "HIERARCHY_NO", Boolean.FALSE, hierarchyNo);
		querygeneratorBean.addSelectClauseBean(null, "FLAG", Boolean.FALSE, "'F'");
		querygeneratorBean.addSelectClauseBean(null, "CREATED_BY", Boolean.FALSE, userId);
		querygeneratorBean.addSelectClauseBean(null, "CREATED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_BY", Boolean.FALSE, userId);
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "VERSION_NO", Boolean.FALSE, String.valueOf(updatedVersionNo));
		querygeneratorBean.addSelectClauseBean(null, "PARENT_HIERARCHY_NO", Boolean.FALSE, hierarchyNo);
	}

	private String getHierarchyNo(HierarchyLevelDefinitionBean hierarchyLevelBean) {
		StringBuilder hierarcyNo = new StringBuilder();
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		hierarcyNo.append("CONCAT( RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO,");
		hierarcyNo.append(keyBean.getActualTtableName());
		hierarcyNo.append(".");
		hierarcyNo.append(keyBean.getWhereClauseColumn());
		hierarcyNo.append(",'.')");
		return hierarcyNo.toString();
	}

	private String getParentNode(HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		StringBuilder parentNode = new StringBuilder();
		String tempString;
		parentNode.append("CONCAT(");
		parentNode.append(previousHierarchyLevelBean.getLevelNo());
		parentNode.append(",'~',");
		GtnFrameworkSingleColumnRelationBean previousKeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		tempString = previousKeyBean.getActualTtableName() + "." + previousKeyBean.getWhereClauseColumn();
		parentNode.append(tempString);
		parentNode.append(")");
		return parentNode.toString();
	}

	@SuppressWarnings("unchecked")
	private GtnWsRelationshipBuilderBean getRelationtionshipBuilder(Integer relationshipBuilderSid)
	{
		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		List<GtnWsRelationshipBuilderBean> relationBuilderBeanList = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(RelationshipBuilder.class);
			criteria.add(Restrictions.eq("relationshipBuilderSid", relationshipBuilderSid));
			List<RelationshipBuilder> results = criteria.list();
			for (RelationshipBuilder relationshipBuilder : results) {
				GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
				relationBean.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
				relationBean.setRelationshipName(relationshipBuilder.getRelationshipName());
				relationBean.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
				relationBean.setHierarchyDefinitionSid(
						relationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
				relationBean.setStartDate(relationshipBuilder.getStartDate());
				relationBean.setBuildType(relationshipBuilder.getBuildType());
				relationBean.setVersionNo(relationshipBuilder.getVersionNo());
				relationBean.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
				relationBean.setCreatedBy(relationshipBuilder.getCreatedBy());
				relationBean.setCreatedDate(relationshipBuilder.getCreatedDate());
				relationBean.setModifiedBy(relationshipBuilder.getModifiedBy());
				relationBean.setModifiedDate(relationshipBuilder.getModifiedDate());
				relationBean.setDeductionRelation(relationshipBuilder.getDeductionRelation());
				relationBuilderBeanList.add(relationBean);
			}
		}
		if (relationBuilderBeanList.isEmpty())
			return null;
		return relationBuilderBeanList.get(0);
	}

	@SuppressWarnings("unchecked")
	private List<HierarchyLevelDefinitionBean> getHierarchyBuilder(Integer hierarchyBuilderSid, int hierarchyVersionNo)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(hierarchyBuilderSid);
		input.add(hierarchyVersionNo);
		String finalQuery = gtnWsSqlService.getQuery(input, "hierarchyByidandVersionNo");
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(finalQuery);
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = new ArrayList<>();
		for (Object[] objects : resultList) {
			HierarchyLevelDefinitionBean hierarchyBean = new HierarchyLevelDefinitionBean();
			getIntegerValue(0, objects);
			hierarchyBean.setHierarchyLevelDefinitionSid(getIntegerValue(0, objects));
			hierarchyBean.setHierarchyDefinitionSid(getIntegerValue(1, objects));
			hierarchyBean.setLevelName(objects[2] == null ? "" : objects[2].toString());
			hierarchyBean.setLevelValueReference(objects[3] == null ? "" : objects[3].toString());
			hierarchyBean.setLevelNo(getIntegerValue(4, objects));
			hierarchyBean.setTableName(objects[5] == null ? "" : objects[5].toString());
			hierarchyBean.setFieldName(objects[6] == null ? "" : objects[6].toString());
			hierarchyBean.setVersionNo(getIntegerValue(7, objects));
			hierarchyDefinitionList.add(hierarchyBean);
		}

		return hierarchyDefinitionList;
	}

	private int getIntegerValue(int index, Object[] objects) {
		return objects[index] == null ? index : Integer.parseInt(objects[index].toString());
	}


	private boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList)
			throws InterruptedException {

		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		final ExecutorService executorService = Executors
				.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);

		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {

			GtnFramworkCheckForAutoUpdateRunnable runnableTarget = applicationContext
					.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
			runnableTarget.setAtomicBoolean(atomicBoolean);
			runnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
			runnableTarget.setIndex(i);
			runnableTarget.setRelationBean(relationBean);
			executorService.submit(runnableTarget);
		}
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.MINUTES);
		return atomicBoolean.get();
	}


	@SuppressWarnings("unchecked")
	private boolean checkAutomaticRelation(Integer relationshipBuilderSid) throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("automaticRelationCheck");
		List<Integer> resultData = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query,
				new Object[] { relationshipBuilderSid }, new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		return (int) resultData.get(0) == 1;
	}

	private List<String> getRelationQueries(int relationshipSid, int versionNo,
			HierarchyLevelDefinitionBean... levelHierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(levelDto.getLevelNo());
				input.add(relationshipSid);
				input.add(versionNo);
				String relationQuery = gtnWsSqlService.getQuery(input, "relationShipSubQueryForSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

}
