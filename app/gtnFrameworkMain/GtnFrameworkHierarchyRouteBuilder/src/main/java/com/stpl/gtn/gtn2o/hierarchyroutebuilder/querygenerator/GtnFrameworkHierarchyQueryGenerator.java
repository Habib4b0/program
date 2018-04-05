package com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.bean.GtnFrameworkHierarchySituationBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkHierarchyQueryGenerator {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	public GtnFrameworkHierarchyQueryGenerator() {
		super();
	}

	public void generateQuery(String situationType, GtnFrameworkQueryGeneratorBean queryBean) {
		GtnFrameworkHierarchySituationBean situationTypeBean = applicationContext
				.getBean(GtnFrameworkHierarchySituationBean.class);
		situationTypeBean.setSituationName(situationType);
		situationTypeBean.loadSituationTableData(situationType);
		situationTypeBean.addJoinConditions(queryBean);
		situationTypeBean.addSelectClause(queryBean);
		situationTypeBean.addWhereClause(queryBean);
	}

	public void replaceWhereClauseColumns(HierarchyLevelDefinitionBean customerHierarchyLevelBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnFrameworkQueryGeneratorBean querygeneratorBean) {
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(hierarchyLevelDefinitionList, customerHierarchyLevelBean);
		if (previousHierarchyLevelBean != null) {
			replaceWhereClauseColumn(querygeneratorBean, previousHierarchyLevelBean, "$WhereClauseColumnPreviousLevel");
			replaceWhereClauseColumn(querygeneratorBean, customerHierarchyLevelBean, "$WhereClauseColumnCurrentLevel");
			
			String hierarchyNo = getHierarchyNoForRelationShip(hierarchyLevelDefinitionList,
					customerHierarchyLevelBean);
			String previousHierarchyNo = getHierarchyNoForRelationShip(hierarchyLevelDefinitionList,
					previousHierarchyLevelBean);

			querygeneratorBean.replaceWhereCluaseColumn("$HierachyNoFormationCurrentLevel", hierarchyNo);
			querygeneratorBean.replaceWhereCluaseColumn("$HierachyNoFormationPreviousLevel", previousHierarchyNo);
		}
		String deductionHierarchyNo = getHierarchyNoForDedutionSelect(hierarchyLevelDefinitionList,
				customerHierarchyLevelBean.getLevelNo());
		querygeneratorBean.replaceSelectCluaseColumn("$HierarchyNoFormationForDeduction", deductionHierarchyNo);
	}

	private void replaceWhereClauseColumn(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean, String replaceString) {
		GtnFrameworkSelectColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		querygeneratorBean.replaceWhereCluaseColumn(replaceString,
				keyListBean.getActualTtableName() + "." + keyListBean.getWhereClauseColumn());
	}

	public String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean hierarchyBean) {
		StringBuilder tempQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < hierarchyBean.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				tempQuery.append(",'%'");
				tempQuery.append(",'.'");
				continue;
			}
			tempQuery.append(",");
			GtnFrameworkSelectColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			tempQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			tempQuery.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(tempQuery);
		tempQuery.append(",'%'");
		finalQuery.append(")");
		return finalQuery.toString();
	}

	public void replaceSelectClauseColumns(HierarchyLevelDefinitionBean currnetHierarchyLevelBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, GtnFrameworkQueryGeneratorBean queryBean) {
		String hierarchyNo = getHierarchyNo(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
		String parentNode = getParentNodeForCustomerAndProdHie(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
		GtnFrameworkSelectColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(currnetHierarchyLevelBean.getTableName(),
						currnetHierarchyLevelBean.getFieldName());

		String hierarchyNoSelect = getHierarchyNoForSelectClause(hierarchyLevelDefinitionList,
				currnetHierarchyLevelBean);
		queryBean.replaceSelectCluaseColumn("$SelectHierarchyNo", hierarchyNo);
		queryBean.replaceSelectCluaseColumn("$ParentNode", parentNode);
		queryBean.replaceSelectCluaseColumn("$SelectColumn", singleColumnRelationBean.getLevelValueColumnName());
		queryBean.replaceSelectCluaseColumn("$SelectMasterSid", singleColumnRelationBean.getMasterIdColumn());
		queryBean.replaceSelectCluaseColumn("$SelectCheckHierarchyNo", hierarchyNoSelect);
	}

	private String getHierarchyNo(List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		HierarchyLevelDefinitionBean previousHierarchyBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(customerHierarchyLevelDefinitionList, selectedCustomerHierarchyLevelDto);
		int i;
		if (previousHierarchyBean == null)
			i = selectedCustomerHierarchyLevelDto.getLevelNo() - 1;
		else
			i = previousHierarchyBean.getLevelNo();
		for (; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append("," + leveldto.getDefaultVlaue());
				query.append(",'.'");
				continue;
			}
			query.append(",");
			GtnFrameworkSelectColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("isnull(\r\n" + "				USERDEFINED_RELATION_JOIN.HIERARCHY_NO,\r\n"
				+ "				concat(RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO");
		finalQuery.append(query);
		finalQuery.append("))");
		return finalQuery.toString();
	}

	private String getParentNodeForCustomerAndProdHie(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean hierarchyLevelBean) {
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(hierarchyLevelDefinitionList, hierarchyLevelBean);
		if (previousHierarchyLevelBean == null)
			return "''";
		StringBuilder parentNodeString = new StringBuilder();
		String tempString;
		parentNodeString.append("CONCAT(");
		parentNodeString.append(previousHierarchyLevelBean.getLevelNo());
		parentNodeString.append(",'~',");
		if (previousHierarchyLevelBean.isUserDefined()) {
			parentNodeString.append(previousHierarchyLevelBean.getDefaultVlaue());
			parentNodeString.append(")");
			return parentNodeString.toString();
		}
		GtnFrameworkSelectColumnRelationBean previousKeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		tempString = previousKeyBean.getActualTtableName() + "." + previousKeyBean.getWhereClauseColumn();
		parentNodeString.append(tempString);
		parentNodeString.append(")");
		return parentNodeString.toString();
	}

	private String getHierarchyNoForSelectClause(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder initialQuery = new StringBuilder();
		StringBuilder query = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				initialQuery.append(",'%'");
				initialQuery.append(",'.'");
				continue;
			}
			initialQuery.append(",");
			GtnFrameworkSelectColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			initialQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			initialQuery.append(",'.'");
		}
		query.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		query.append(initialQuery);
		query.append(")");
		return query.toString();
	}

	private String getHierarchyNoForDedutionSelect(
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int levelNo) {
		StringBuilder hierarcyNo = new StringBuilder();
		hierarcyNo.append("CONCAT(?,'-'");
		for (int i = 0; i < levelNo; i++) {
			if (!hierarchyLevelDefinitionList.get(i).isUserDefined()) {
			GtnFrameworkSelectColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelDefinitionList.get(i).getTableName(),
							hierarchyLevelDefinitionList.get(i).getFieldName());
			hierarcyNo.append(",");
			hierarcyNo.append(keyBean.getActualTtableName());
			hierarcyNo.append(".");
			hierarcyNo.append(keyBean.getWhereClauseColumn());
			hierarcyNo.append(",'.'");
			}
		}
		hierarcyNo.append(")");
		return hierarcyNo.toString();
	}

}
