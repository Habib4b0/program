package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFrameworkDeductionRelationServiceRunnable;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkAutomaticRelationUpdateService {
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnFrameworkDeductionRelationServiceRunnable deductionRelationService;

	public GtnFrameworkAutomaticRelationUpdateService() {
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

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkAutomaticRelationUpdateService.class);

	public boolean checkAndUpdateAutomaticRelationship(Integer relationshipBuilderSid)
			throws GtnFrameworkGeneralException, InterruptedException {
		GtnWsRelationshipBuilderBean relationBean = getRelationtionshipBuilder(relationshipBuilderSid);
		if (relationBean != null) {
			GtnFrameworkAutoupdateService automaticService = getAutomaticserviceObject(
					relationBean.getHierarchycategory());
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = getHierarchyBuilder(
					relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
			if (automaticService.checkAutomaticRelation(relationshipBuilderSid)
					&& automaticService.checkForAutoUpdate(relationBean, hierarchyDefinitionList)
			) {
				automaticService.doAutomaticUpdate(hierarchyDefinitionList, relationBean);
				return true;
			}
			LOGGER.info("checkAndUpdateAutomaticRelationship has finihsed");
		}
		return false;
	}

	public GtnFrameworkAutoupdateService getAutomaticserviceObject(String hierarchyCat) {
		if ("Deduction Hierarchy".equals(hierarchyCat))
			return (GtnFrameworkAutoupdateService) applicationContext.getBean("Deduction");
		if ("Product Hierarchy".equals(hierarchyCat))
			return (GtnFrameworkAutoupdateService) applicationContext.getBean("ProdService");
		return (GtnFrameworkAutoupdateService) applicationContext.getBean("CustService");
	}

	public int insertRelationTillFirstLevelAndGetVersionNo(int firstLinkedLevelNo,
			GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
		int relationVersionNo = updateRelationShipVersionNo(relationBean);
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

	public int updateRelationShipVersionNo(GtnWsRelationshipBuilderBean relationBean) {

		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			RelationshipBuilder relationshipBuilder = session.load(RelationshipBuilder.class,
					relationBean.getRelationshipBuilderSid());
			relationshipBuilder.setModifiedBy(relationshipBuilder.getModifiedBy());
			relationshipBuilder.setModifiedDate(new Date());
			relationshipBuilder.setVersionNo(relationshipBuilder.getVersionNo() + 1);
			session.update("RelationshipBuilder", relationshipBuilder);
			tx.commit();
			return relationshipBuilder.getVersionNo();
		}
	}

	@SuppressWarnings("unchecked")
	public GtnWsRelationshipBuilderBean getRelationtionshipBuilder(Integer relationshipBuilderSid) {
		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		List<GtnWsRelationshipBuilderBean> relationBuilderBeanList = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(RelationshipBuilder.class);
			criteria.add(Restrictions.eq("relationshipBuilderSid", relationshipBuilderSid));
			List<RelationshipBuilder> results = criteria.list();
			for (RelationshipBuilder relationshipBuilder : results) {
				GtnWsRelationshipBuilderBean relationBean = getCustomizedRelationBean(relationshipBuilder, session);
				relationBuilderBeanList.add(relationBean);
			}
		}
		if (relationBuilderBeanList.isEmpty())
			return null;
		return relationBuilderBeanList.get(0);
	}

	public GtnWsRelationshipBuilderBean getCustomizedRelationBean(RelationshipBuilder relationshipBuilder,
			Session session) {
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		relationBean.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
		relationBean.setRelationshipName(relationshipBuilder.getRelationshipName());
		relationBean.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
		relationBean.setHierarchyDefinitionSid(relationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
		relationBean.setStartDate(relationshipBuilder.getStartDate());
		relationBean.setBuildType(relationshipBuilder.getBuildType());
		relationBean.setVersionNo(relationshipBuilder.getVersionNo());
		relationBean.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
		relationBean.setCreatedBy(relationshipBuilder.getCreatedBy());
		relationBean.setCreatedDate(relationshipBuilder.getCreatedDate());
		relationBean.setModifiedBy(relationshipBuilder.getModifiedBy());
		relationBean.setModifiedDate(relationshipBuilder.getModifiedDate());
		relationBean.setDeductionRelation(relationshipBuilder.getDeductionRelation());
		HelperTable hierarchyCat = session.load(HelperTable.class,
				relationshipBuilder.getHierarchyDefinition().getHierarchyCategory());
		relationBean.setHierarchycategory(hierarchyCat.getDescription());
		return relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyBuilder(Integer hierarchyBuilderSid, int hierarchyVersionNo)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(hierarchyBuilderSid);
		input.add(hierarchyVersionNo);
		String finalQuery = gtnWsSqlService.getQuery(input, "hierarchyByidandVersionNo");
		return executeAndGetCustomizedResult(finalQuery);
	}

	private List<HierarchyLevelDefinitionBean> executeAndGetCustomizedResult(String finalQuery)
			throws GtnFrameworkGeneralException {
		@SuppressWarnings("unchecked")
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
			hierarchyBean.setDefaultVlaue(getIntegerValue(8, objects));
			hierarchyDefinitionList.add(hierarchyBean);
		}
		return hierarchyDefinitionList;
	}

	public int getIntegerValue(int index, Object[] objects) {
		return objects[index] == null ? index : Integer.parseInt(objects[index].toString());
	}

	public List<String> getRelationQueries(int relationshipSid, int versionNo,
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
        
	public boolean checkManualRelation(int relationshipBuilderSid)
			throws GtnFrameworkGeneralException, InterruptedException {
            String query = gtnWsSqlService.getQuery("manualRelationCheck");
		@SuppressWarnings("unchecked")
		List<Integer> resultData = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query,
                    new Object[]{relationshipBuilderSid}, new GtnFrameworkDataType[]{GtnFrameworkDataType.INTEGER});
            GtnWsRelationshipBuilderBean relationBeanManual = getRelationtionshipBuilder(relationshipBuilderSid);
		if (relationBeanManual != null) {
                if ((int) resultData.get(0) == 1) {
				deductionRelationService.saveRelationship(relationBeanManual);
                }
                return true;
            }
            LOGGER.info("checkAndUpdateAutomaticRelationship has finihsed");
            return false;
        }

	public void deleteUnwantedUserDefinedLevels(int relationshipBuilderSid, int customertUpdatedVersionNo)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(relationshipBuilderSid);
		input.add(customertUpdatedVersionNo);
		input.add(relationshipBuilderSid);
		input.add(relationshipBuilderSid);
		input.add(customertUpdatedVersionNo);
		String sqlquery = gtnWsSqlService.getQuery(input, "Delete unwanted Userdefined Level");
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlquery);
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyBuilderBasedOnProjectionId(int projectionId,
			String hierarchyIndicator) throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(projectionId);
		String queryName;
		if (hierarchyIndicator.equalsIgnoreCase("C")) {
			queryName = "hierarchyByidandVersionNoBy ProjectionMasterSId Customer";
		} else {
			queryName = "hierarchyByidandVersionNoBy ProjectionMasterSId PRoduct";
		}
		String finalQuery = gtnWsSqlService.getQuery(input, queryName);
		return executeAndGetCustomizedResult(finalQuery);
	}

}
