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

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationDetails;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySitutationTableDetails;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
@Scope(value = "prototype")
public class GtnFrameworkSituationDetailsBean {
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	private int situationTableSid;
	private int situationDetailsSid;
	private String tableName;
	private String aliasName;
	private String joinType;
	private String filterColumn;
	private HierarchySitutationDetails hierarchySitutationTableBean;
	private List<GtnFrameworkSituationTableDetailsBean> tableDetailsBeanList;

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkSituationDetailsBean.class);

	public GtnFrameworkSituationDetailsBean() {
		super();
	}

	public int getSituationTableSid() {
		return situationTableSid;
	}

	public void setSituationTableSid(int situationTableSid) {
		this.situationTableSid = situationTableSid;
	}

	public int getSituationDetailsSid() {
		return situationDetailsSid;
	}

	public void setSituationDetailsSid(int situationDetailsSid) {
		this.situationDetailsSid = situationDetailsSid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJoinType() {
		return joinType;
	}

	public GtnFrameworkJoinType getJoinTypeEnum() {
		return GtnFrameworkJoinType.valueOf(getJoinType());
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public HierarchySitutationDetails getHierarchySitutationTableBean() {
		return hierarchySitutationTableBean;
	}

	public void setHierarchySitutationTableBean(HierarchySitutationDetails hierarchySitutationTableBean) {
		this.hierarchySitutationTableBean = hierarchySitutationTableBean;
	}

	public String getFilterColumn() {
		return filterColumn;
	}

	public void setFilterColumn(String filterColumn) {
		this.filterColumn = filterColumn;
	}

	public void loadTableDetailsData() {
		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(HierarchySitutationTableDetails.class);
			criteria.add(Restrictions.eq("hierarchySitutationDetailsSid",
					hierarchySitutationTableBean.getHierarchySitutationDetailsSid()));
			List<HierarchySitutationTableDetails> detailsData = criteria.list();
			tableDetailsBeanList = new ArrayList<>();
			for (HierarchySitutationTableDetails hierarchySitutationTableData : detailsData) {
				GtnFrameworkSituationTableDetailsBean tableDetailsBean = applicationContext
						.getBean(GtnFrameworkSituationTableDetailsBean.class);
				tableDetailsBean.setConditionType(hierarchySitutationTableData.getConditionType());
				tableDetailsBean.setSituationTableDetailsSid(
						hierarchySitutationTableData.getHierarchySitutationTableDetailsSid());
				tableDetailsBean.setLeftColumnName(hierarchySitutationTableData.getLeftColumnName());
				tableDetailsBean.setRightColumnName(hierarchySitutationTableData.getRightColumnName());
				tableDetailsBean.setExpressionColumnOrder(hierarchySitutationTableData.getExpressionColumnOrder());
				tableDetailsBean.setJoinCoditionOrderSid(hierarchySitutationTableData.getJoinCondtionOrderSid());
				tableDetailsBean.setJoinCoditionReferenceSid(
						hierarchySitutationTableData.getJoinConditionReferenceSid() == null ? 0
								: hierarchySitutationTableData.getJoinConditionReferenceSid());
				tableDetailsBeanList.add(tableDetailsBean);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void addTableJoin(GtnFrameworkQueryGeneratorBean queryBean) {
		GtnFrameworkJoinClauseBean joinConditionBean = queryBean.addJoinClauseBean(tableName, aliasName,
				getJoinTypeEnum());
		for (GtnFrameworkSituationTableDetailsBean gtnFrameworkSituationTableDetailsBean : tableDetailsBeanList) {
			gtnFrameworkSituationTableDetailsBean.addOnConditions(joinConditionBean);
		}
		if ("$DATE_FILTER".equals(filterColumn)) {
			addDateFilterCondition(queryBean, joinConditionBean);
		}

	}

	private void addDateFilterCondition(GtnFrameworkQueryGeneratorBean queryBean,
			GtnFrameworkJoinClauseBean joinConditionBean) {
		GtnFramworkTableBean fromTableBean = gtnFrameworkEntityMasterBean
				.getEntityBeanByTableName(queryBean.getFromTableName());
		joinConditionBean.addOrConditionBean(fromTableBean.getModifiedDateColumn(queryBean.getFromTableAlies()),
				"RELATION_DATE_FILTER.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		for (GtnFrameworkJoinClauseBean joinClauseBean : queryBean.getJoinClauseConfigList()) {
			GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
					.getEntityBeanByTableName(joinClauseBean.getJoinTableName());
			if (tableBean == null || tableBean.getModifiedDateColumn() == null)
				continue;
			joinConditionBean.addOrConditionBean(
					tableBean.getModifiedDateColumn(joinClauseBean.getJoinTableAliesName()),
					"RELATION_DATE_FILTER.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		}
		joinConditionBean.addOrConditionBean("RELATION_DATE_FILTER.HIERARCHY_NO", "?",
				GtnFrameworkOperatorType.LIKE);
	}

}
