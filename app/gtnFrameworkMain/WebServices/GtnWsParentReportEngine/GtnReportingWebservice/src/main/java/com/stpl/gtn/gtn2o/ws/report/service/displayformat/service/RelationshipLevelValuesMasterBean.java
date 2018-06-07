package com.stpl.gtn.gtn2o.ws.report.service.displayformat.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.NumericConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportSqlService;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean.GtnFrameworkDisplayFormatBean;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean.RelationshipLevelValuesBean;

@Service
@Scope(value = "prototype")
public class RelationshipLevelValuesMasterBean {

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private GtnDisplayFormatMasterBean gtnDisplayFormatMasterBean;

	public GtnDisplayFormatMasterBean getGtnDisplayFormatMasterBean() {
		return gtnDisplayFormatMasterBean;
	}

	public void setGtnDisplayFormatMasterBean(GtnDisplayFormatMasterBean gtnDisplayFormatMasterBean) {
		this.gtnDisplayFormatMasterBean = gtnDisplayFormatMasterBean;
	}

	public static final String DEFAULT_QUESTION = " @DEFAULTSELECTCLAUSE ";
	private List<Object[]> tempList = null;
	private String hierarchyNoType;
	private final List<RelationshipLevelValuesBean> queryList = new ArrayList<>();
	private int defaultCount = 0;
	private final StringBuilder finalQry = new StringBuilder();
	public static final String HT_DESC = "HT.DESCRIPTION";
	public static final String FIELD_VALUE = "?FIELD_VALUE";
	public static final String UNION_ALL = " UNION ALL ";

	public void createQuery(List<Object[]> tempList, GtnWsReportDataSelectionBean dataSelectionBean,
			String hierarchyNoType) {
		this.tempList = tempList == null ? tempList : new ArrayList<>(tempList);
		this.hierarchyNoType = hierarchyNoType;
		if (!"D".equals(hierarchyNoType)) {
			createQuery(dataSelectionBean);
		} else {
			// createDeductionQuery(sessionDTO);
		}
	}

	public String getFinalQuery() {
		for (int i = 0; i < queryList.size(); i++) {
			RelationshipLevelValuesBean query = queryList.get(i);
			if (i != 0) {
				finalQry.append(UNION_ALL);
			}
			finalQry.append(
					query.getQuery().replace(DEFAULT_QUESTION, generateDefaultSelect(query.getNoOfSelectFormed())));
		}
		finalQry.append("ORDER BY LEVEL_NO,VALUE DESC");
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

	private void createQuery(GtnWsReportDataSelectionBean dataSelectionBean) {
		for (int i = 0; i < tempList.size(); i++) {
			queryList.add(getCustomisedQuery((Object[]) tempList.get(i), dataSelectionBean));
		}
	}

	// private void createDeductionQuery(SessionDTO sessionDTO) {
	// for (int i = 0; i < tempList.size(); i++) {
	// queryList.add(getDeductionCustomisedQuery((Object[]) tempList.get(i),
	// sessionDTO));
	// }
	// }

	private RelationshipLevelValuesBean getCustomisedQuery(Object[] tempListObject,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		List<Object> input = new ArrayList<>();
		input.add(String.valueOf(tempListObject[0]));
		input.add(getDisplayFormatColumn(gtnDisplayFormatMasterBean.getDisplayFormatList(),
				String.valueOf(tempListObject[0]), bean));
		input.add(dataSelectionBean.getTableNameWithUniqueId(MongoStringConstants.ST_CCPD_SESSION_TABLE_NAME));
		input.add(hierarchyNoType);
		input.add(String.valueOf(tempListObject[1]));
		input.add(String.valueOf(tempListObject[NumericConstants.TWO]));
		input.add(isCustomer() ? dataSelectionBean.getCustomerRelationshipBuilderSid()
				: dataSelectionBean.getProductRelationshipBuilderSid());
		input.add(String.valueOf(tempListObject[NumericConstants.THREE]));
		input.add(isCustomer() ? dataSelectionBean.getCustomerRelationshipVersionNo()
				: dataSelectionBean.getProductRelationshipVersionNo());
		bean.setQuery(sqlService.getQuery(input, "getRelationshipLevelValues"));
		return bean;
	}

	// private RelationshipLevelValuesBean getDeductionCustomisedQuery(Object[]
	// tempListObject, SessionDTO sessionDTO) {
	// RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
	// String customSql;
	// customSql = SQlUtil.getQuery("getRelationshipLevelValuesForDeductionCustom");
	// customSql = customSql.replace("?LNO",
	// String.valueOf(tempListObject[NumericConstants.ZERO]));
	// customSql = customSql.replace(RBSID, relationshipBuilderSid);
	// customSql = customSql.replace("?RLDV",
	// sessionDTO.getDeductionRelationVersion() + StringUtils.EMPTY);
	// boolean isUDC = tempListObject[2].equals(1) && tempListObject[3].equals(1);
	// boolean isRSID = tempListObject[2].equals(0) && tempListObject[3].equals(0);
	// boolean isHelperTableJoin = tempListObject[2].equals(1);
	// if (isUDC) {
	// customSql = customSql.replace(FIELD_VALUE,
	// " RS.RS_CONTRACT_SID=U.MASTER_SID AND U.MASTER_TYPE='RS_CONTRACT' ");
	// } else if (isRSID) {
	// customSql = customSql.replace(FIELD_VALUE, " RS.RS_CONTRACT_SID =
	// RLD.RELATIONSHIP_LEVEL_VALUES");
	// } else {
	// customSql = customSql.replace(FIELD_VALUE,
	// " RS." + tempListObject[NumericConstants.ONE] +
	// "=RLD.RELATIONSHIP_LEVEL_VALUES");
	// }
	//
	// if (isUDC) {
	// customSql = customSql.replace("?UDCJOIN",
	// " JOIN UDCS U ON U." + tempListObject[NumericConstants.ONE] +
	// "=RLD.RELATIONSHIP_LEVEL_VALUES ");
	// customSql = customSql.replace("?HELPERTABLEJOIN",
	// " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=U." +
	// tempListObject[NumericConstants.ONE]);
	// customSql = customSql.replace("?REBATEJOIN", StringUtils.EMPTY);
	// customSql = customSql.replace("?HTDESCRIPTION", HT_DESC);
	// customSql = customSql.replace("?DEDGROUPBY", HT_DESC);
	// } else {
	// customSql = customSql.replace("?REBATEJOIN",
	// isHelperTableJoin
	// ? " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=RS." +
	// tempListObject[NumericConstants.ONE]
	// : StringUtils.EMPTY);
	// customSql = customSql.replace("?HELPERTABLEJOIN", StringUtils.EMPTY);
	// customSql = customSql.replace("?UDCJOIN", StringUtils.EMPTY);
	// customSql = customSql.replace("?HTDESCRIPTION", isHelperTableJoin ? HT_DESC :
	// "RS.RS_ID");
	// customSql = customSql.replace("?DEDGROUPBY", isHelperTableJoin ? HT_DESC :
	// "RS.RS_ID");
	// }
	// customSql = customSql.replace("?DISPLAYFORMATCOLUMN",
	// getDisplayFormatColumnRS(masterBean.getDisplayFormatList(),
	// String.valueOf(tempListObject[1]), bean));
	// customSql = customSql.replace("?DEFGRPBY", bean.getDefaultGroupBy());
	// bean.setQuery(customSql);
	// return bean;
	// }

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
		StringBuilder defaultStr = new StringBuilder();
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
