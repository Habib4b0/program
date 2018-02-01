package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkDeductionRelationServiceRunnable {
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	@Qualifier("Deduction")
	private GtnFrameworkAutoupdateService deDuctionAutoUpdateService;

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	private static final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkDeductionRelationServiceRunnable.class);

	public GtnFrameworkDeductionRelationServiceRunnable() {
		super();
	}


	public void saveRelationship(GtnWsRelationshipBuilderBean relationBuilderBean, boolean isRelationSaved)
			throws GtnFrameworkGeneralException {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			RelationshipBuilder relationBuilder;
			relationBuilder = saveRelationShipBuilder(relationBuilderBean, session, isRelationSaved);
			GtnWsRelationshipBuilderBean relationBean = customizeDeductionRelation(relationBuilder);
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
			tx.commit();
			deDuctionAutoUpdateService.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);
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

	private GtnWsRelationshipBuilderBean customizeDeductionRelation(RelationshipBuilder deductionRelationshipBuilder) {
		GtnWsRelationshipBuilderBean deductionRelationBean = new GtnWsRelationshipBuilderBean();
		deductionRelationBean.setRelationshipBuilderSid(deductionRelationshipBuilder.getRelationshipBuilderSid());
		deductionRelationBean.setRelationshipName(deductionRelationshipBuilder.getRelationshipName());
		deductionRelationBean.setRelationshipDescription(deductionRelationshipBuilder.getRelationshipDescription());
		deductionRelationBean.setHierarchyDefinitionSid(
				deductionRelationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
		deductionRelationBean.setStartDate(deductionRelationshipBuilder.getStartDate());
		deductionRelationBean.setBuildType(deductionRelationshipBuilder.getBuildType());
		deductionRelationBean.setVersionNo(deductionRelationshipBuilder.getVersionNo());
		deductionRelationBean.setHierarchyVersion(deductionRelationshipBuilder.getHierarchyVersion());
		deductionRelationBean.setCreatedBy(deductionRelationshipBuilder.getCreatedBy());
		deductionRelationBean.setCreatedDate(deductionRelationshipBuilder.getCreatedDate());
		deductionRelationBean.setModifiedBy(deductionRelationshipBuilder.getModifiedBy());
		deductionRelationBean.setModifiedDate(deductionRelationshipBuilder.getModifiedDate());
		deductionRelationBean.setDeductionRelation(deductionRelationshipBuilder.getDeductionRelation());
		return deductionRelationBean;
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
			throw new GtnFrameworkGeneralException("Exception in updateRelationshipBuilderFromRequest", e);
		}
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
