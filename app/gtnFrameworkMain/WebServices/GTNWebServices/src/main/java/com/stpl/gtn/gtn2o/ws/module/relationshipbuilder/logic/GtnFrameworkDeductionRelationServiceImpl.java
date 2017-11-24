package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkDeductionRelationServiceImpl {
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	GtnWsRelationshipBuilderLogic relationLogic;

	@Autowired
	@Qualifier("Deduction")
	GtnFrameworkAutoupdateService deDuctionAutoUpdateService;

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkDeductionRelationServiceImpl.class);

	public void saveRelationship(GtnWsRelationshipBuilderBean relationBuilderBean, boolean isRelationSaved)
			throws GtnFrameworkGeneralException {
		try (Session session = sessionFactory.openSession()) {
		Transaction tx = session.beginTransaction();
		RelationshipBuilder relationBuilder;
		relationBuilder = saveRelationShipBuilder(relationBuilderBean, session, isRelationSaved);
		GtnWsRelationshipBuilderBean relationBean = customizeRelation(relationBuilder);
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = relationUpdateService
				.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
		tx.commit();
		deDuctionAutoUpdateService.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean,
				String.valueOf(relationBuilderBean.getCreatedBy()));
		}
	}

	@SuppressWarnings("unchecked")
	private RelationshipBuilder saveRelationShipBuilder(GtnWsRelationshipBuilderBean relationBuilderBean,
			Session session, boolean isRelationSaved) throws GtnFrameworkGeneralException {
		RelationshipBuilder relationBuilder;
		Date date = new Date();
		if (isRelationSaved) {
			List<String> input = new ArrayList<>();
			input.add(String.valueOf(relationBuilderBean.getRelationshipBuilderSid()));
			List<Integer> resultset = (List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(getQueryReplaced(input, "getProductRelationId"));
			relationBuilder = session.load(RelationshipBuilder.class, resultset.get(0));
			relationBuilder.setModifiedBy(relationBuilderBean.getCreatedBy());
			relationBuilder.setVersionNo(relationBuilder.getVersionNo() + 1);
		} else {
			relationBuilder = new RelationshipBuilder();
			relationBuilder.setVersionNo(1);

		}
		relationBuilder.setCreatedBy(relationBuilderBean.getCreatedBy());
		relationBuilder.setCreatedDate(date);
		relationBuilder.setModifiedDate(date);
		updateRelationshipBuilderFromRequest(relationBuilder, session, relationBuilderBean);
		session.saveOrUpdate(relationBuilder);
		return relationBuilder;
	}

	private GtnWsRelationshipBuilderBean customizeRelation(RelationshipBuilder relationshipBuilder) {
		GtnWsRelationshipBuilderBean relationBean = new GtnWsRelationshipBuilderBean();
		relationBean.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
		relationBean.setRelationshipName(relationshipBuilder.getRelationshipName());
		relationBean.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
		relationBean
				.setHierarchyDefinitionSid(relationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
		relationBean.setStartDate(relationshipBuilder.getStartDate());
		relationBean.setBuildType(relationshipBuilder.getBuildType());
		relationBean.setVersionNo(relationshipBuilder.getVersionNo());
		relationBean.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
		relationBean.setCreatedBy(relationshipBuilder.getCreatedBy());
		relationBean.setCreatedDate(relationshipBuilder.getCreatedDate());
		relationBean.setModifiedBy(relationshipBuilder.getModifiedBy());
		relationBean.setModifiedDate(relationshipBuilder.getModifiedDate());
		relationBean.setDeductionRelation(relationshipBuilder.getDeductionRelation());
		return relationBean;
	}

	@SuppressWarnings("unchecked")
	private void updateRelationshipBuilderFromRequest(RelationshipBuilder relationshipBuilder, Session session,
			GtnWsRelationshipBuilderBean relationBuilderBean) throws GtnFrameworkGeneralException {
		try {
			List<Integer> resultsetHierarchy = (List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery("getHierarchyDefSidRB"));
			relationshipBuilder.setRelationshipName(relationBuilderBean.getRelationshipName() + "Deduction");
			relationshipBuilder.setRelationshipDescription(relationBuilderBean.getRelationshipDescription());
			relationshipBuilder
					.setHierarchyDefinition(session.load(HierarchyDefinition.class, resultsetHierarchy.get(0)));
			relationshipBuilder.setHierarchyVersion(relationshipBuilder.getHierarchyDefinition().getVersionNo());
			relationshipBuilder.setStartDate(relationBuilderBean.getStartDate());
			relationshipBuilder.setBuildType(relationBuilderBean.getBuildType());
			relationshipBuilder.setDeductionRelation(relationBuilderBean.getRelationshipBuilderSid());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GtnFrameworkGeneralException("Exception in updateRelationshipBuilderFromRequest", e);
		}
	}

	@SuppressWarnings("unchecked")
	public String getItemMasterSidForProductRelation(RelationshipBuilder productrelationshipBuilder)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		String itemMastersids = StringUtils.EMPTY;

		List<HierarchyLevelDefinitionBean> hierarchyList = relationLogic.getRBHierarchyLevelDefinitionBySid(
				productrelationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid(),
				productrelationshipBuilder.getHierarchyVersion(),
				productrelationshipBuilder.getRelationshipBuilderSid());

		int levelNo = HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList);
		HierarchyLevelDefinitionBean destinationHierarchyBean = HierarchyLevelDefinitionBean.getBeanByLevelNo(levelNo,
				hierarchyList);
		if (destinationHierarchyBean != null) {
			GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
							destinationHierarchyBean.getFieldName());
			List<String> whereClauseColumn = hierarchyService.getMappingColumns(keyBean);
			List<String> whereQueries = relationLogic.getRelationQueries(
					productrelationshipBuilder.getRelationshipBuilderSid(), hierarchyList.subList(0, levelNo),
					productrelationshipBuilder.getVersionNo());
			GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
			GtnFrameworkHierarchyQueryBean queryBean = fileReadWriteService.getQueryFromFile(
					destinationHierarchyBean.getHierarchyDefinitionSid(),
					destinationHierarchyBean.getHierarchyLevelDefinitionSid(), destinationHierarchyBean.getVersionNo());
			GtnFrameworkQueryGeneratorBean finalQueryBean = queryBean.getQuery();
			finalQueryBean.addSelectClauseBean(GtnWsRelationshipBuilderConstants.ITEM_MASTER_TABLE_COLUMN_NAME, null,
					Boolean.TRUE, null);
			finalQueryBean.addWhereClauseBean(whereClauseColumn.get(1), null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.NULL_ALLOWED, "?");
			String finalQuery = finalQueryBean.generateQuery();
			List<Object[]> result = (List<Object[]>) gtnSqlQueryEngine
					.executeSelectQuery(getQueryReplaced(whereQueries, finalQuery));
			for (Object[] objects : result) {
				input.add(String.valueOf(objects[objects.length - 1]));
				itemMastersids = Arrays.toString(input.toArray()).replace("[", StringUtils.EMPTY).replace("]",
						StringUtils.EMPTY);
			}
		}
		return itemMastersids;
	}

	public String getQueryReplaced(List<String> input, String queryName) {
		StringBuilder sqlStringBuilder = new StringBuilder();
		try {
			sqlStringBuilder = new StringBuilder(gtnWsSqlService.getQuery(queryName));
			if (input != null) {
				for (Object tempInput : input) {
					sqlStringBuilder.replace(sqlStringBuilder.indexOf("?"), sqlStringBuilder.indexOf("?") + 1,
							String.valueOf(tempInput));
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in getQuery", ex);
		}
		return sqlStringBuilder.toString();
	}

}
