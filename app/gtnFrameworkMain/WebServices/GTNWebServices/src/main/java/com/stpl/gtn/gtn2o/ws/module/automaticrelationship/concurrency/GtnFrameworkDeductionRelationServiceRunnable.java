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
	GtnFrameworkAutomaticRelationUpdateService automaticService;

	private static final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkDeductionRelationServiceRunnable.class);

	public GtnFrameworkDeductionRelationServiceRunnable() {
		super();
	}


	public void saveRelationship(GtnWsRelationshipBuilderBean relationBuilderBean)
			throws GtnFrameworkGeneralException, InterruptedException {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			RelationshipBuilder relationBuilder;
			relationBuilder = saveRelationShipBuilder(relationBuilderBean, session);
			GtnWsRelationshipBuilderBean relationBean = customizeDeductionRelation(relationBuilder);
			tx.commit();
			automaticService.checkAndUpdateAutomaticRelationship(relationBean.getRelationshipBuilderSid());
		}
	}

	@SuppressWarnings("unchecked")
	private RelationshipBuilder saveRelationShipBuilder(GtnWsRelationshipBuilderBean productRelationBuilderBean,
			Session session) throws GtnFrameworkGeneralException {
		RelationshipBuilder relationBuilder = null;
		Date date = new Date();
		List<String> input = new ArrayList<>();
		input.add(String.valueOf(productRelationBuilderBean.getRelationshipBuilderSid()));
		List<Integer> resultset = (List<Integer>) gtnSqlQueryEngine
				.executeSelectQuery(getQueryReplaced(input, "getProductRelationId"));
		if (resultset == null || resultset.isEmpty())
		{
			relationBuilder = new RelationshipBuilder();
			relationBuilder.setVersionNo(0);
			relationBuilder.setCreatedDate(date);
			relationBuilder.setCreatedBy(productRelationBuilderBean.getCreatedBy());
			relationBuilder.setModifiedDate(date);
			updateRelationshipBuilderFromRequest(relationBuilder, session, productRelationBuilderBean);
			session.saveOrUpdate(relationBuilder);
		} else {
			relationBuilder = session.get(RelationshipBuilder.class, resultset.get(0));
		}

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
