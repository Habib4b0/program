/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.displayformat.main;

import com.stpl.app.cff.displayformat.bean.GtnFrameworkDisplayFormatBean;
import com.stpl.app.cff.displayformat.bean.RelationshipLevelValuesBean;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class RelationshipLevelValuesMasterBean {

	public static final String DEFAULT_QUESTION = " ?DEFAULT ";
	public static final String UNION_ALL = " UNION ALL ";
	private List<Object[]> tempList = null;
	private final String relationshipBuilderSid;
	private final String hierarchyNoType;
	private final List<RelationshipLevelValuesBean> queryList = new ArrayList<>();
	private int defaultCount = 0;
	private final GtnDisplayFormatMasterBean masterBean = new GtnDisplayFormatMasterBean();
	private final StringBuilder finalQry = new StringBuilder();
	public static final String FIELD_VALUE = "?FIELD_VALUE";

	public RelationshipLevelValuesMasterBean(List<Object[]> tempList, String relationshipBuilderSid,
			String hierarchyNoType, SessionDTO sessionDTO) {
		this.tempList = tempList;
		this.relationshipBuilderSid = relationshipBuilderSid;
		this.hierarchyNoType = hierarchyNoType;
		if (!"D".equals(hierarchyNoType)) {
			createQuery(sessionDTO);
		} else {
			createDeductionQuery();
		}
	}

	public String getFinalQuery() {
		finalQry.append(";WITH CTE AS(");
		for (int i = 0; i < queryList.size(); i++) {
			RelationshipLevelValuesBean query = queryList.get(i);
			if (i != 0) {
				finalQry.append(UNION_ALL);
			}
			finalQry.append(
					query.getQuery().replace(DEFAULT_QUESTION, generateDefaultSelect(query.getNoOfSelectFormed()))
							.replace("?", String.valueOf(i)));
		}
		finalQry.append(") SELECT * FROM CTE ORDER BY SID,VALUE DESC");
		return finalQry.toString();
	}

	public String getDeductionFinalQuery() {
		for (int i = 0; i < queryList.size(); i++) {
			RelationshipLevelValuesBean query = queryList.get(i);
			if (i != 0) {
				finalQry.append(UNION_ALL);
			}
			finalQry.append(
					query.getQuery().replace(DEFAULT_QUESTION, generateDefaultSelect(query.getNoOfSelectFormed())));
		}
		return finalQry.toString();
	}

	private void createQuery(SessionDTO sessionDTO) {
		for (int i = 0; i < tempList.size(); i++) {
			queryList.add(getCustomisedQuery((Object[]) tempList.get(i), sessionDTO));
		}
	}

	private void createDeductionQuery() {
		for (int i = 0; i < tempList.size(); i++) {
			queryList.add(getDeductionCustomisedQuery((Object[]) tempList.get(i)));
		}
	}

	private RelationshipLevelValuesBean getCustomisedQuery(Object[] tempListObject, SessionDTO sessionDTO) {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		String customSql = SQlUtil.getQuery("getRelationshipLevelValues");
		customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
		customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
		customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[NumericConstants.TWO]));
		customSql = customSql.replace("?LNO", String.valueOf(tempListObject[NumericConstants.THREE]));
		customSql = customSql.replace(StringConstantsUtil.RBSID, relationshipBuilderSid);
		customSql = customSql.replace("?HIERARCHY_NO", hierarchyNoType);
		customSql = customSql.replace("?RLDV",
				isCustomer() ? sessionDTO.getCustomerRelationVersion() + StringUtils.EMPTY
						: sessionDTO.getProductRelationVersion() + StringUtils.EMPTY);
		customSql = customSql.replace("?DISPLAYFORMATCOLUMN",
				getDisplayFormatColumn(masterBean.getDisplayFormatList(), String.valueOf(tempListObject[0]), bean));
		bean.setQuery(customSql);
		return bean;
	}

	private RelationshipLevelValuesBean getDeductionCustomisedQuery(Object[] tempListObject) {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		String customSql = SQlUtil.getQuery("getRelationshipLevelValuesForDeduction");
		customSql = customSql.replace("?LNO", String.valueOf(tempListObject[NumericConstants.ZERO]));
		customSql = customSql.replace(StringConstantsUtil.RBSID, relationshipBuilderSid);
		boolean isUDC = tempListObject[2].equals(1) && tempListObject[3].equals(1);
		boolean isRSID = tempListObject[2].equals(0) && tempListObject[3].equals(0);
		boolean isHelperTableJoin = tempListObject[2].equals(1);
		if (isUDC) {
			customSql = customSql.replace(FIELD_VALUE,
					" RS.RS_CONTRACT_SID=U.MASTER_SID AND U.MASTER_TYPE='RS_CONTRACT' ");
		} else if (isRSID) {
			customSql = customSql.replace(FIELD_VALUE, " RS.RS_CONTRACT_SID = RLD.RELATIONSHIP_LEVEL_VALUES");
		} else {
			customSql = customSql.replace(FIELD_VALUE,
					" RS." + tempListObject[NumericConstants.ONE] + "=RLD.RELATIONSHIP_LEVEL_VALUES");
		}

		if (isUDC) {
			customSql = customSql.replace("?UDCJOIN",
					" JOIN UDCS U ON U." + tempListObject[NumericConstants.ONE] + "=RLD.RELATIONSHIP_LEVEL_VALUES ");
			customSql = customSql.replace("?HELPERTABLEJOIN",
					" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=U." + tempListObject[NumericConstants.ONE]);
			customSql = customSql.replace("?REBATEJOIN", StringUtils.EMPTY);
			customSql = customSql.replace("?HTDESCRIPTION", ConstantsUtil.HT_DESCRIPTION);
			customSql = customSql.replace("?DEDGROUPBY", ConstantsUtil.HT_DESCRIPTION);
		} else {
			customSql = customSql.replace("?REBATEJOIN",
					isHelperTableJoin
							? " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=RS." + tempListObject[NumericConstants.ONE]
							: StringUtils.EMPTY);
			customSql = customSql.replace("?HELPERTABLEJOIN", StringUtils.EMPTY);
			customSql = customSql.replace("?UDCJOIN", StringUtils.EMPTY);
			customSql = customSql.replace("?HTDESCRIPTION",
					isHelperTableJoin ? ConstantsUtil.HT_DESCRIPTION : "RS.RS_ID");
			customSql = customSql.replace("?DEDGROUPBY", isHelperTableJoin ? ConstantsUtil.HT_DESCRIPTION : "RS.RS_ID");
		}
		customSql = customSql.replace("?DISPLAYFORMATCOLUMN",
				getDisplayFormatColumnRS(masterBean.getDisplayFormatList(), String.valueOf(tempListObject[1]), bean));
		customSql = customSql.replace("?DEFGRPBY", bean.getDefaultGroupBy());
		bean.setQuery(customSql);
		return bean;
	}

	private String getDisplayFormatColumnRS(List<GtnFrameworkDisplayFormatBean> hierarchyList, String columnName,
			RelationshipLevelValuesBean bean) {
		StringBuilder finalStr = new StringBuilder();
		int count = 0;
		if (!hierarchyList.isEmpty()) {
			for (int i = 0; i < hierarchyList.size(); i++) {
				GtnFrameworkDisplayFormatBean gtnFrameworkDisplayFormatBean = hierarchyList.get(i);
				if (!StringUtils.isBlank(columnName)
						&& columnName.equalsIgnoreCase(gtnFrameworkDisplayFormatBean.getColumnName())) {
					finalStr.append(", RS.").append(gtnFrameworkDisplayFormatBean.getSelectedColumnName())
							.append(" AS COLUMN_").append(i);
					setDefaultCount(++count);
					bean.setNoOfSelectFormed(count);
					bean.setDefaultGroupBy(", RS." + gtnFrameworkDisplayFormatBean.getSelectedColumnName());
				}
			}
		}
		finalStr.append(DEFAULT_QUESTION);
		return finalStr.toString();
	}

	private String getDisplayFormatColumn(List<GtnFrameworkDisplayFormatBean> hierarchyList, String columnName,
			RelationshipLevelValuesBean bean) {
		StringBuilder finalStr = new StringBuilder();
		int count = 0;
		if (!hierarchyList.isEmpty()) {
			for (int i = 0; i < hierarchyList.size(); i++) {
				GtnFrameworkDisplayFormatBean gtnFrameworkDisplayFormatBean = hierarchyList.get(i);
				if (!StringUtils.isBlank(columnName)
						&& columnName.equalsIgnoreCase(gtnFrameworkDisplayFormatBean.getColumnName())) {
					finalStr.append(", TEMP.").append(gtnFrameworkDisplayFormatBean.getSelectedColumnName())
							.append(" AS COLUMN_").append(i);
					setDefaultCount(++count);
					bean.setNoOfSelectFormed(count);
				}
			}
		}
		finalStr.append(DEFAULT_QUESTION);
		return finalStr.toString();
	}

	private void setDefaultCount(int count) {
		if (count >= defaultCount) {
			defaultCount = count;
		}
	}

	private String generateDefaultSelect(int alreadySelected) {
		StringBuilder defaultStr = new StringBuilder(StringUtils.EMPTY);
		int count = defaultCount - alreadySelected;
		for (int i = 0; i < count; i++) {
			defaultStr.append(" ,NULL AS COLUMN_").append(i);
		}
		return defaultStr.toString();
	}

	private boolean isCustomer() {
		return hierarchyNoType.equals("CUST_HIERARCHY_NO");
	}

}
