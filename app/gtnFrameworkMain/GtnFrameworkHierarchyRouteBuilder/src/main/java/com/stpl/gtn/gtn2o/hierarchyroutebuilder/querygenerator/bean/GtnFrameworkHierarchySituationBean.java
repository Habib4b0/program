package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.bean;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationDetails;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationFilter;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationSelect;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationTable;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
@Scope(value = "prototype")
public class GtnFrameworkHierarchySituationBean {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private List<GtnFrameworkSituationDetailsBean> detailsBeanList;

	private List<GtnFrameworkSituationSelectBean> selecetClauseBeanList;
	private List<HierarchySitiationFilterBean> filterClauseBeanList;

	private String situationName;

	public String getSituationName() {
		return situationName;
	}

	public void setSituationName(String situationName) {
		this.situationName = situationName;
	}

	public GtnFrameworkHierarchySituationBean() {
		super();
	}
	
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkHierarchySituationBean.class);
	public void loadSituationTableData(String situationType) {
		this.situationName = situationType;
		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(HierarchySitutationTable.class);
			criteria.add(Restrictions.eq("situtationName", situationType));
			List<HierarchySitutationTable> results = criteria.list();
			if (results != null && !results.isEmpty()) {
				HierarchySitutationTable tableData = results.get(0);
				Criteria detailsCriteria = session.createCriteria(HierarchySitutationDetails.class);
				detailsCriteria.add(
						Restrictions.eq(GtnFrameworkCommonConstants.HIERARCHY_SITUATION_TABLE_ID,
								tableData.getHierarchySitutationTableSid()));
				List<HierarchySitutationDetails> detailsData = detailsCriteria.list();
				detailsBeanList = new ArrayList<>();
				for (HierarchySitutationDetails hierarchySitutationTableDetails : detailsData) {
					GtnFrameworkSituationDetailsBean detailsbean = applicationContext
							.getBean(GtnFrameworkSituationDetailsBean.class);
					detailsbean.setHierarchySitutationTableBean(hierarchySitutationTableDetails);
					detailsbean.loadTableDetailsData();
					detailsbean.setAliasName(hierarchySitutationTableDetails.getAliasName());
					detailsbean.setTableName(hierarchySitutationTableDetails.getTableName());
					detailsbean
							.setSituationDetailsSid(hierarchySitutationTableDetails.getHierarchySitutationDetailsSid());
					detailsbean.setSituationTableSid(hierarchySitutationTableDetails.getHierarchySitutationTableSid());
					detailsbean.setJoinType(hierarchySitutationTableDetails.getJoinType());
					detailsbean.setFilterColumn(hierarchySitutationTableDetails.getFilterColumn());
					detailsBeanList.add(detailsbean);
				}
				loadSelectClauseBean(tableData, session);
				loadFilterClauseBean(tableData, session);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void loadSelectClauseBean(HierarchySitutationTable tableData, Session session) {
		Criteria detailsCriteria = session.createCriteria(HierarchySitutationSelect.class);
		detailsCriteria.add(Restrictions.eq(GtnFrameworkCommonConstants.HIERARCHY_SITUATION_TABLE_ID,
				tableData.getHierarchySitutationTableSid()));
		List<HierarchySitutationSelect> detailsData = detailsCriteria.list();
		selecetClauseBeanList = new ArrayList<>();
		for (HierarchySitutationSelect hierarchySitutationTableDetails : detailsData) {
			GtnFrameworkSituationSelectBean detailsbean = applicationContext
					.getBean(GtnFrameworkSituationSelectBean.class);
			detailsbean.setAliasName(hierarchySitutationTableDetails.getAliasName());
			detailsbean.setColumnName(hierarchySitutationTableDetails.getSelectColumn());
			selecetClauseBeanList.add(detailsbean);
		}

	}

	private void loadFilterClauseBean(HierarchySitutationTable tableData, Session session) {
		Criteria detailsCriteria = session.createCriteria(HierarchySitutationFilter.class);
		detailsCriteria.add(Restrictions.eq(GtnFrameworkCommonConstants.HIERARCHY_SITUATION_TABLE_ID,
				tableData.getHierarchySitutationTableSid()));
		List<HierarchySitutationFilter> detailsData = detailsCriteria.list();
		filterClauseBeanList = new ArrayList<>();
		for (HierarchySitutationFilter hierarchySitutationTableDetails : detailsData) {
			HierarchySitiationFilterBean detailsbean = applicationContext.getBean(HierarchySitiationFilterBean.class);
			detailsbean.setLeftColumn(hierarchySitutationTableDetails.getLeftColumn());
			detailsbean.setRightColumn(hierarchySitutationTableDetails.getRightColumn());
			detailsbean.setOperationType(hierarchySitutationTableDetails.getOperatorType());
			detailsbean.setHierarchySituationTableid(hierarchySitutationTableDetails.getHierarchySitutationTableSid());
			filterClauseBeanList.add(detailsbean);
		}

	}

	public void addJoinConditions(GtnFrameworkQueryGeneratorBean queryBean) {
		for (GtnFrameworkSituationDetailsBean gtnFrameworkSituationDetailsBean : detailsBeanList) {
			gtnFrameworkSituationDetailsBean.addTableJoin(queryBean);
		}

	}

	public void addSelectClause(GtnFrameworkQueryGeneratorBean queryBean) {
		for (GtnFrameworkSituationSelectBean gtnFrameworkSelectClauseBean : selecetClauseBeanList) {
			gtnFrameworkSelectClauseBean.addSelectClause(queryBean);
		}

	}

	public void addWhereClause(GtnFrameworkQueryGeneratorBean queryBean) {
		for (HierarchySitiationFilterBean gtnFrameworkSituationFilterBean : filterClauseBeanList) {
			gtnFrameworkSituationFilterBean.addWhereClause(queryBean);
		}

	}

}
