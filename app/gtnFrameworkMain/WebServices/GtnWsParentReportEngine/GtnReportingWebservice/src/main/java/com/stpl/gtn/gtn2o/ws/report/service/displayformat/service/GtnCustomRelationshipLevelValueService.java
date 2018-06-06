package com.stpl.gtn.gtn2o.ws.report.service.displayformat.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.NumericConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportSqlService;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean.GtnFrameworkDisplayFormatBean;
import com.stpl.gtn.gtn2o.ws.report.service.displayformat.bean.RelationshipLevelValuesBean;

@Service
@Scope(value = "prototype")
public class GtnCustomRelationshipLevelValueService {

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

	private List<Object[]> customViewDetails;
	private GtnWsReportDataSelectionBean dataSelectionBean;
	private final List<RelationshipLevelValuesBean> queryList = new ArrayList<>();
	private int defaultCount = 0;

	public void setInputForQueryGeneration(List<Object[]> customViewDetails,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		this.customViewDetails = customViewDetails;
		this.dataSelectionBean = dataSelectionBean;
		createQuery();
	}

	private void createQuery() {
		for (int i = 0; i < customViewDetails.size(); i++) {
			Object[] details = customViewDetails.get(i);
			if ("D".equals(details[5].toString())) {
				queryList.add(getCustomisedQueryDiscount(details));
			} else {
				queryList.add(getCustomisedQuery(details));
			}
		}
		queryList.add(addQueryForVariable());
	}

	private RelationshipLevelValuesBean getCustomisedQuery(Object[] tempListObject) {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		List<Object> input = new ArrayList<>();
		input.add(String.valueOf(tempListObject[0]));
		input.add(getDisplayFormatColumnClause(gtnDisplayFormatMasterBean.getDisplayFormatList(),
				String.valueOf(tempListObject[NumericConstants.ZERO]), bean));
		input.add(dataSelectionBean.getCustomViewMasterSid());
		input.add(String.valueOf(tempListObject[NumericConstants.ONE]));
		input.add(String.valueOf(tempListObject[NumericConstants.TWO]));
		input.add(String.valueOf(tempListObject[NumericConstants.FOUR]));
		bean.setQuery(sqlService.getQuery(input, "getCustomViewRelationshipLevelValues"));
		return bean;
	}

	private RelationshipLevelValuesBean getCustomisedQueryDiscount(Object[] tempListObject) {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		boolean isUdc = tempListObject[7].equals(NumericConstants.ONE);
		boolean isHelper = tempListObject[6].equals(NumericConstants.ONE);
		boolean isRsId = !isUdc && !isHelper;
		List<Object> input = new ArrayList<>();
		input.add(isRsId ? " TEMP.RS_ID " : " HT.DESCRIPTION ");
		input.add(getDisplayFormatColumnClause(gtnDisplayFormatMasterBean.getDisplayFormatList(),
				String.valueOf(tempListObject[NumericConstants.ZERO]), bean));
		input.add(dataSelectionBean.getCustomViewMasterSid());
		input.add(isUdc ? " TEMP.RS_CONTRACT_SID=U.MASTER_SID AND U.MASTER_TYPE='RS_CONTRACT' "
				: " TEMP." + tempListObject[NumericConstants.THREE] + " = CVH.RELATIONSHIP_LEVEL_VALUES ");
		if (isUdc) {
			input.add("JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=U." + tempListObject[NumericConstants.THREE]);
		} else if (isHelper) {
			input.add(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=TEMP." + tempListObject[NumericConstants.THREE]);
		} else {
			input.add(StringUtils.EMPTY);
		}
		input.add(String.valueOf(tempListObject[NumericConstants.FOUR]));
		bean.setQuery(sqlService.getQuery(input, "getCustomViewRelationshipLevelValuesDiscount"));
		return bean;
	}

	private RelationshipLevelValuesBean addQueryForVariable() {
		RelationshipLevelValuesBean bean = new RelationshipLevelValuesBean();
		List<Object> input = new ArrayList<>();
		input.add(RelationshipLevelValuesMasterBean.DEFAULT_QUESTION);
		input.add(dataSelectionBean.getCustomViewMasterSid());
		bean.setQuery(sqlService.getQuery(input, "getCustomViewRelationshipLevelValuesVariable"));
		return bean;
	}

	private String getDisplayFormatColumnClause(List<GtnFrameworkDisplayFormatBean> hierarchyList, String columnName,
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
		finalStr.append(RelationshipLevelValuesMasterBean.DEFAULT_QUESTION);
		return finalStr.toString();
	}

	private void setDefaultCount(int count) {
		if (count >= defaultCount) {
			defaultCount = count;
		}
	}

	private String generateDefaultSelectClause(int alreadySelected) {
		StringBuilder defaultStr = new StringBuilder();
		int count = defaultCount - alreadySelected;
		for (int i = 0; i < count; i++) {
			defaultStr.append(" ,NULL AS COLUMN_").append(i);
		}
		return defaultStr.toString();
	}

	public String getFinalQuery() {
		StringBuilder finalQry = new StringBuilder();
		for (int i = 0; i < queryList.size(); i++) {
			RelationshipLevelValuesBean query = queryList.get(i);
			if (i != 0) {
				finalQry.append(RelationshipLevelValuesMasterBean.UNION_ALL);
			}
			finalQry.append(query.getQuery().replace(RelationshipLevelValuesMasterBean.DEFAULT_QUESTION,
					generateDefaultSelectClause(query.getNoOfSelectFormed())));
		}
		finalQry.append("ORDER BY LEVEL_NO ");
		return finalQry.toString();
	}

}
