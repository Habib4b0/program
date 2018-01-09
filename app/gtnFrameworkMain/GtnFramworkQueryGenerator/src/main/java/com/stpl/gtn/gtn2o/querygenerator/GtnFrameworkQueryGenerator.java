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
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;

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
				String selectClause = getSelectClause(gtnFrameworkSelectClauseConfig);
				generatedSelectClause.append(selectClause);
				if (listSize > 1 && countForAddingComma != (listSize - 1)) {
					generatedSelectClause.append(",");
				}
				countForAddingComma++;
			}

		}
		return generatedSelectClause;
	}

	private String getSelectClause(GtnFrameworkSelectClauseBean gtnFrameworkSelectClauseConfig) {
		StringBuilder generatedSelectClause = new StringBuilder();
		String selectClause = gtnFrameworkSelectClauseConfig.getSelectClauseValue();
		String selectClauseAliesName = gtnFrameworkSelectClauseConfig.getSelectClauseAliesName();
		if (gtnFrameworkSelectClauseConfig.getIsValueFromColumnBean()) {
			GtnFrameworkColumnBean columnConfig = gtnFrameworkSelectClauseConfig.getColumnBean();
			selectClause = columnConfig.getAliesName() + "." + columnConfig.getColumnName();
			selectClauseAliesName = columnConfig.getAliesName().toLowerCase(Locale.ENGLISH).trim()
					+ columnConfig.getColumnName().toLowerCase(Locale.ENGLISH).trim();

		}
		generatedSelectClause.append(selectClause).append(" as ").append(selectClauseAliesName);
		return generatedSelectClause.toString();

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
				setRightBean(gtnFrameworkWhereClauseConfig, leftBean, rightBean, generatedWhereClause);
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
			String joinRightTableColumnBean = gtnFrameworkJoinConditionBean
					.getJoinRightTableColumnBean();
			joinConditions.append(joinLeftTableColumnBean.getAliesName()).append(".")
					.append(joinLeftTableColumnBean.getColumnName())
					.append(gtnFrameworkJoinConditionBean.getJoinOperator().getOperaterType());
			rightKeyJoinValue = new StringBuilder(" ? ");
			if (joinRightTableColumnBean != null) {
				rightKeyJoinValue = new StringBuilder(joinRightTableColumnBean);
			}
			if (gtnFrameworkJoinConditionBean.getJoinOperator().equals(GtnFrameworkOperatorType.IN)
					|| gtnFrameworkJoinConditionBean.getJoinOperator().equals(GtnFrameworkOperatorType.NOT_IN)) {
				rightKeyJoinValue.append(")");
			}
			joinConditions.append(rightKeyJoinValue);
			if (listSize > 1 && countForAddingAnd != (listSize - 1)) {
				joinConditions.append(GtnFrameworkQueryGeneratorBean.AND);
			}
			countForAddingAnd++;
		}

		return joinConditions;

	}

	private void setRightBean(GtnFrameworkWhereClauseBean gtnFrameworkWhereClauseConfig,
			GtnFrameworkColumnBean leftBean, GtnFrameworkColumnBean rightBean, StringBuilder generatedWhereClause) {
		if (rightBean == null) {
			String conditionalValue = gtnFrameworkWhereClauseConfig.getConditionalValue();
			boolean inCondition = gtnFrameworkWhereClauseConfig.getConditionValueType()
					.equals(GtnFrameworkDataType.IN_LIST);
			if (conditionalValue == null) {
				if (inCondition) {
					generatedWhereClause.append(":inParameter");
				} else {
					generatedWhereClause.append("?");
				}
			} else {
				generatedWhereClause.append(conditionalValue);
			}
			GtnFrameworkOperatorType operatorType = gtnFrameworkWhereClauseConfig.getOperatorType();
			if (operatorType.equals(GtnFrameworkOperatorType.IN)
					|| operatorType.equals(GtnFrameworkOperatorType.NOT_IN)) {
				generatedWhereClause.append(")");
			}
		} else {
			generatedWhereClause.append(leftBean.getAliesName()).append(".").append(leftBean.getColumnName());
		}
	}

}
