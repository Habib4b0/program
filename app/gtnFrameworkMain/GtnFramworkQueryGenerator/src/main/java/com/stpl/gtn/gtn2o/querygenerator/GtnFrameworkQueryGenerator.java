package com.stpl.gtn.gtn2o.querygenerator;

import java.util.List;
import java.util.Locale;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkColumnBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinConditionBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkOrderByClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkSelectClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkWhereClauseBean;

public final class GtnFrameworkQueryGenerator {
	private GtnFrameworkQueryGenerator() {
		super();
	}

	private static volatile GtnFrameworkQueryGenerator gtnFrameworkQueryGenerator = null;

	public static GtnFrameworkQueryGenerator getInstance() {
		GtnFrameworkQueryGenerator frameworkQueryGenerator = GtnFrameworkQueryGenerator.gtnFrameworkQueryGenerator;
		if (frameworkQueryGenerator == null) {
			synchronized (GtnFrameworkQueryGenerator.class) {
				frameworkQueryGenerator = GtnFrameworkQueryGenerator.gtnFrameworkQueryGenerator;
				if (frameworkQueryGenerator == null) {
					GtnFrameworkQueryGenerator.gtnFrameworkQueryGenerator = frameworkQueryGenerator = new GtnFrameworkQueryGenerator();
				}
			}
		}
		return frameworkQueryGenerator;
	}

	public StringBuilder generateSelectClauseQuery(GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		List<GtnFrameworkSelectClauseBean> selectClauseConfigList = queryGeneratorConfig.getSelectClauseConfigList();
		StringBuilder generatedSelectClause = new StringBuilder(0);
		int countForAddingComma = 0;
		int listSize = selectClauseConfigList.size();
		if (!selectClauseConfigList.isEmpty()) {
			for (GtnFrameworkSelectClauseBean gtnFrameworkSelectClauseConfig : selectClauseConfigList) {
				GtnFrameworkColumnBean columnConfig = gtnFrameworkSelectClauseConfig.getColumnBean();
				generatedSelectClause.append(columnConfig.getAliesName()).append(".")
						.append(columnConfig.getColumnName()).append(" as ")
						.append(columnConfig.getAliesName().toLowerCase(Locale.ENGLISH).trim()).append(columnConfig.getColumnName().toLowerCase(Locale.ENGLISH).trim());
				if (listSize > 1 && countForAddingComma != (listSize - 1)) {
					generatedSelectClause.append(",");
				}
				countForAddingComma++;
			}

		}
		return generatedSelectClause;
	}

	public StringBuilder generateJoinClauseQuery(GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		List<GtnFrameworkJoinClauseBean> joinClauseConfigList = queryGeneratorConfig.getJoinClauseConfigList();
		StringBuilder generatedJoinClause = new StringBuilder(0);
		if (!joinClauseConfigList.isEmpty()) {
			for (GtnFrameworkJoinClauseBean gtnFrameworkJoinClauseConfig : joinClauseConfigList) {
				generatedJoinClause.append(gtnFrameworkJoinClauseConfig.getJoinType().getJoinType())
						.append(gtnFrameworkJoinClauseConfig.getJoinTableName()).append(" ")
						.append(gtnFrameworkJoinClauseConfig.getJoinTableAliesName()).append(" ON ")
						.append(generateJoinConditionQuery(gtnFrameworkJoinClauseConfig));
			}
		}
		return generatedJoinClause;
	}

	public StringBuilder generateWhereClauseQuery(GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		List<GtnFrameworkWhereClauseBean> whereClauseConfigList = queryGeneratorConfig.getWhereClauseConfigList();
		StringBuilder generatedWhereClause = new StringBuilder(0);
		int countForAddingAnd = 0;
		int listSize = whereClauseConfigList.size();
		if (!whereClauseConfigList.isEmpty()) {
			generatedWhereClause = new StringBuilder(GtnFrameworkQueryGeneratorBean.WHERE_KEYWORD);
			for (GtnFrameworkWhereClauseBean gtnFrameworkWhereClauseConfig : whereClauseConfigList) {
				GtnFrameworkColumnBean leftBean = gtnFrameworkWhereClauseConfig.getWhereClauseLeftbean();
				GtnFrameworkColumnBean rightBean = gtnFrameworkWhereClauseConfig.getWhereClauseRightbean();
				generatedWhereClause.append(leftBean.getAliesName()).append(".").append(leftBean.getColumnName())
						.append(gtnFrameworkWhereClauseConfig.getOperatorType().getOperaterType());
				if (rightBean == null) {
					String conditionalValue = gtnFrameworkWhereClauseConfig.getConditionalValue();
					generatedWhereClause.append(conditionalValue == null ? " ? " : conditionalValue);
					if (gtnFrameworkWhereClauseConfig.getOperatorType().equals(GtnFrameworkOperatorType.IN)) {
						generatedWhereClause.append(")");
					}
				} else {
					generatedWhereClause.append(leftBean.getAliesName()).append(".").append(leftBean.getColumnName());
				}
				if (listSize > 1 && countForAddingAnd != (listSize - 1)) {
					generatedWhereClause.append(GtnFrameworkQueryGeneratorBean.AND);
				}
				countForAddingAnd++;
			}
		}
		return generatedWhereClause;
	}

	public StringBuilder generateOrderByClauseQuery(GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		List<GtnFrameworkOrderByClauseBean> orderByClauseConfigList = queryGeneratorConfig.getOrderByClauseConfigList();
		StringBuilder generatedOrderByClause = new StringBuilder(0);
		int countForAddingAnd = 0;
		int listSize = orderByClauseConfigList.size();
		if (!orderByClauseConfigList.isEmpty()) {
			generatedOrderByClause.append(GtnFrameworkQueryGeneratorBean.ORDER_BY);
			for (GtnFrameworkOrderByClauseBean gtnFrameworkOrderByClauseConfig : orderByClauseConfigList) {
				GtnFrameworkColumnBean columnConfig = gtnFrameworkOrderByClauseConfig.getColumnBean();
				generatedOrderByClause.append(columnConfig.getAliesName()).append(".")
						.append(columnConfig.getColumnName()).append(" ")
						.append(gtnFrameworkOrderByClauseConfig.getOrderType());
				if (listSize > 1 && countForAddingAnd != (listSize - 1)) {
					generatedOrderByClause.append(",");
				}
				countForAddingAnd++;
			}
		}
		return generatedOrderByClause;
	}

	private StringBuilder generateJoinConditionQuery(GtnFrameworkJoinClauseBean gtnFrameworkJoinClauseConfig) {
		StringBuilder joinConditions = new StringBuilder();
		StringBuilder rightKeyJoinValue;
		List<GtnFrameworkJoinConditionBean> conditionList = gtnFrameworkJoinClauseConfig.getConditionList();
		int countForAddingAnd = 0;
		int listSize = conditionList.size();
		for (GtnFrameworkJoinConditionBean gtnFrameworkJoinConditionBean : conditionList) {
			GtnFrameworkColumnBean joinLeftTableColumnBean = gtnFrameworkJoinConditionBean.getJoinLeftTableColumnBean();
			GtnFrameworkColumnBean joinRightTableColumnBean = gtnFrameworkJoinConditionBean
					.getJoinRightTableColumnBean();
			joinConditions.append(joinLeftTableColumnBean.getAliesName()).append(".")
					.append(joinLeftTableColumnBean.getColumnName())
					.append(gtnFrameworkJoinConditionBean.getJoinOperator().getOperaterType());
			rightKeyJoinValue = new StringBuilder(" ? ");
			if (joinRightTableColumnBean != null) {
				rightKeyJoinValue = new StringBuilder(joinRightTableColumnBean.getAliesName()).append(".")
						.append(joinRightTableColumnBean.getColumnName());
			}
			joinConditions.append(rightKeyJoinValue);
			if (listSize > 1 && countForAddingAnd != (listSize - 1)) {
				joinConditions.append(GtnFrameworkQueryGeneratorBean.AND);
			}
			countForAddingAnd++;
		}

		return joinConditions;

	}

}
