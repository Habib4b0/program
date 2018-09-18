/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryUtils;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.hameed
 */
public class PVQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PVQueryUtils.class);
    
    public static final String IMONTH = ", I.\"MONTH\"";
    public static final String YEARS_SPACE = " YEARS";
    public static final String YEARS_PERIODS = " YEARS,PERIODS ";
    public static final String IQUARTER_AS_PERIODS_COMMA = "I.QUARTER as PERIODS, ";
    public static final String AND_IYEAR_EQUAL = " and I.\"YEAR\" = ";
    public static final String SPACE_JOIN_SPACE = " JOIN ";
    public static final String IYEAR_AS_I = "  I.\"YEAR\"";
    public static final String CASE_WHEN_CURRENT_ISNULL = " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN ";
    public static final String ORDER_BY = " order by ";
    public static final String DISCOUNTS_YEARSPERIODS = " DISCOUNTS, YEARS,PERIODS ";
    public static final String EMI_ANNUAL_AS_PERIODS = "I.SEMI_ANNUAL as PERIODS, ";
    
    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        char quotes = '\'';
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql;

            if (workflowStatus.equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),Constant.GET_PROJECTION_LISTS));
            } else {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace('*', '%');
                marketTypeVal = quotes + marketTypeVal + quotes;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE  " ).append( marketTypeVal ).append( ')');
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace('*', '%');
                brandVal = quotes + brandVal + quotes;
            }
            customSql.append("   AND (BM.BRAND_NAME LIKE " ).append( brandVal ).append( " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace('*', '%');
                projNameVal = quotes + projNameVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( projNameVal ).append( " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace('*', '%');
                contHldrVal = quotes + contHldrVal + quotes;
            }
            customSql.append("AND  (C.CONTRACT_NO LIKE " ).append( contHldrVal ).append( " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace('*', '%');
                ndcNameVal = quotes + ndcNameVal + quotes;
            }
            customSql.append("AND  (IM.ITEM_NAME LIKE " ).append( ndcNameVal ).append( " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace('*', '%');
                ndcNoVal = quotes + ndcNoVal + quotes;
            }
            customSql.append("AND  (IM.ITEM_NO LIKE " ).append( ndcNoVal ).append( "or  IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace('*', '%');
                contractVal = quotes + contractVal + quotes;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " ).append( contractVal ).append( "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace('*', '%');
                descVal = quotes + descVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( descVal ).append( "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " ).append( quotes ).append( workflowStatus ).append( quotes);
            }
            if (from != null && to != null && !StringUtils.isEmpty(from) && !StringUtils.isEmpty(to)
                    && !Constant.NULL.equals(to) && !Constant.NULL.equals(from) ) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND  PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append(Constant.AND_SPACE);
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (" ).append( notSearchProjId ).append( ')');
            customSql.append("AND PM.FORECASTING_TYPE='Non Mandated'");

            customSql.append(" group by  pm.projection_name,pm.projection_description,ht.description ,cm.company_no  ,c.contract_no  ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql.toString();
        } catch (ParseException e) {
            LOGGER.error("at PvQueryUtils -> getComparisonSearch= {}", e);
            return null;
        }
    }

    /**
     * Logic to make persistency of already added projection in the selected
     * table - comparison look up
     *
     * @param projId
     * @return query -String
     */
    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = SQlUtil.getQuery(getClass(),Constant.GET_PROJECTION_LISTS);
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CommonUtils.collectionToStringMethod(projId, false) + ")");
            } else {
                customSql += (" PM.PROJECTION_MASTER_SID IN ('')");
            }
            customSql += (" group by  pm.projection_name,pm.projection_description,ht.description ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql;
        } catch (Exception e) {
            LOGGER.error("at PvQueryUtils ->  getComparisonSearch= {}", e);
            return null;
        }
    }
    /**
     * 
     * @param comparisonLookupDTO
     * @param screenName
     * @param parameters
     * @param sortColumns
     * @param start
     * @param offset
     * @param isCount
     * @return 
     */
    public String getComparisionSearchResults(final ComparisonLookupDTO comparisonLookupDTO, final String screenName, final Map<String, Object> parameters, final List<SortByColumn> sortColumns, final int start, final int offset, boolean isCount) {
        try {
            String quotes = "'";
            String marketTypeVal;
            String brandVal;
            String projNameVal;
            String contHldrVal;
            String ndcNoVal;
            String ndcNameVal;
            String descVal;
            String contractVal;
            boolean isProjectionStatus = false;
            Map<Object, String> columns = new HashMap<>();
            columns.put(Constant.PROJECTION_NAME, "pm.projection_name");
            columns.put(Constant.PROJECTIONDESCRIPTION, "pm.projection_description");
            columns.put(Constant.MARKET_TYPE, "market_type");
            columns.put("customer", Constant.CUSTOMER_SMALL);
            columns.put(Constant.CONTRACT, Constant.CONTRACT_SMALL);
            columns.put(Constant.BRAND, Constant.BRAND_CAPS);
            columns.put("createdDateFrom", "PM.created_date");
            columns.put("createdBy", "PM.created_by");

            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }

            String workflowQuery = isProjectionStatus ? StringUtils.EMPTY : "INNER JOIN WORKFLOW_MASTER WM\n"
                    + "               ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                      LEFT JOIN HELPER_TABLE HT1\n"
                    + "              ON HT1.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID\n"
                    + "                 AND HT1.LIST_NAME = 'WORKFLOWSTATUS'";
            String replaceQuery = SQlUtil.getQuery("PVComparisonQuery");
            replaceQuery = replaceQuery.replace("?COUNT", isCount ? " SELECT COUNT(*) FROM ( " : StringUtils.EMPTY);
            replaceQuery = replaceQuery.replaceAll("\\?PROJECTIONMASTERSID", comparisonLookupDTO.getCurrentProjId());
            replaceQuery = replaceQuery.replace("?WORKFLOWJOIN", workflowQuery);

            StringBuilder customSql = new StringBuilder(replaceQuery);

            if (isProjectionStatus) {
                customSql.append(" ISNULL(PM.IS_APPROVED,'') NOT IN ('Y','C','A','R') AND PM.SAVE_FLAG = 1 ");
            } else {
                customSql.append(" HT1.list_name = 'WorkFlowStatus' and HT1.description = ").append('\'').append(comparisonLookupDTO.getWorkflowStatus()).append('\'');
            }

            if (isValidateSearchCriteria(comparisonLookupDTO.getMarketType())) {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace('*', '%');
                marketTypeVal = quotes + marketTypeVal + quotes;
                customSql.append(" AND ( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(')');
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getBrand())) {
                brandVal = comparisonLookupDTO.getBrand().replace('*', '%');
                brandVal = quotes + brandVal + quotes;
                customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME  is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getProjectionName())) {
                projNameVal = comparisonLookupDTO.getProjectionName().replace('*', '%');
                projNameVal = quotes + projNameVal + quotes;
                customSql.append(" AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME  is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getCustomer())) {
                contHldrVal = comparisonLookupDTO.getCustomer().replace('*', '%');
                contHldrVal = quotes + contHldrVal + quotes;
                customSql.append(" AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or  C.CONTRACT_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getNdcName())) {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace('*', '%');
                ndcNameVal = quotes + ndcNameVal + quotes;
                customSql.append(" AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME  is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getNdcNo())) {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace('*', '%');
                ndcNoVal = quotes + ndcNoVal + quotes;
                customSql.append(" AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getContract())) {
                contractVal = comparisonLookupDTO.getContract().replace('*', '%');
                contractVal = quotes + contractVal + quotes;
                customSql.append(" AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or  CM.COMPANY_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getProjectionDescription())) {
                descVal = comparisonLookupDTO.getProjectionDescription().replace('*', '%');
                descVal = quotes + descVal + quotes;
                customSql.append(" AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            }

            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND  PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(Constant.AND_PMCREATED_DATE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append("  AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append(Constant.AND_SPACE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get(Constant.FILTERPROJECTION_NAME) != null) {
                customSql.append(" AND (PM.PROJECTION_NAME LIKE '").append(parameters.get(Constant.FILTERPROJECTION_NAME)).append("') ");
            }
            if (parameters.get(Constant.FILTERPROJECTION_DESCRIPTION) != null) {
                customSql.append(" AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERPROJECTION_DESCRIPTION)).append("') ");
            }
            if (parameters.get(Constant.FILTERMARKET_TYPE) != null) {
                customSql.append(" AND (HT.DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERMARKET_TYPE)).append("') ");
            }
            if (parameters.get(Constant.FILTER_CONTRACT) != null) {
                customSql.append(" AND (C.CONTRACT_NO LIKE '").append(parameters.get(Constant.FILTER_CONTRACT)).append("')");
            }
            if (parameters.get(Constant.FILTER_CUSTOMER) != null) {
                customSql.append(" AND (CM.COMPANY_NO LIKE '").append(parameters.get(Constant.FILTER_CUSTOMER)).append("') ");
            }
            if (parameters.get(Constant.FILTERBRAND) != null) {
                String value = String.valueOf(parameters.get(Constant.FILTERBRAND));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if (Constant.MULTIPLE_SMALL.contains(value.toLowerCase(Locale.ENGLISH))) {
                    customSql.append(" AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append(" AND (BM.BRAND_NAME LIKE '").append(parameters.get(Constant.FILTERBRAND)).append("') ");
                }
            }
            if (parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM) != null && parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO) != null) {
                customSql.append(" AND PM.CREATED_DATE BETWEEN '").append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM)).append(Constant.AND_SPACE).append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM) != null) {
                customSql.append(" AND PM.CREATED_DATE > = '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO) != null) {
                customSql.append(" AND PM.CREATED_DATE < = '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO)).append("' ");
            }
            if (parameters.get(Constant.FILTERCREATED_BY) != null) {
                List<String> strList;
                final DynamicQuery dynamicQuery = UserLocalServiceUtil.dynamicQuery();
                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get(Constant.FILTERCREATED_BY) + Constant.PERCENT);
                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get(Constant.FILTERCREATED_BY) + Constant.PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                String userID = CommonUtils.collectionToStringMethod(strList, false);
                customSql.append(" AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append(" AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ");

            if (isCount) {
                customSql.append(" ) RSLT ");
            } else {
                customSql.append(" ORDER BY ");

                if (sortColumns == null || sortColumns.isEmpty()) {
                    customSql.append(" pm.projection_name, pm.projection_description, PM.created_date, PM.created_by,  pm.projection_master_sid ");
                } else {
                    Boolean temp = Boolean.FALSE;
                    for (Iterator<SortByColumn> it = sortColumns.iterator(); it.hasNext();) {
                        SortByColumn sortByColumn = it.next();
                        if (temp) {
                            customSql.append(", ");
                            temp = Boolean.TRUE;
                        }
                        customSql.append(columns.get(sortByColumn.getName())).append(' ').append(sortByColumn.getType());
                    }
                }

                customSql.append(" OFFSET ");
                customSql.append(start);
                customSql.append(Constant.ROWS_FETCH_NEXT_SPACE);
                customSql.append(offset);
                customSql.append(" ROWS ONLY ;");
            }

            return customSql.toString();
        } catch (ParseException | SystemException ex) {
            LOGGER.error(ex.getMessage());
            return StringUtils.EMPTY;
        }
    }

    private boolean isValidateSearchCriteria(String dto) {
        if (dto == null || StringUtils.isBlank(dto)) {
            return BooleanConstant.getFalseFlag();
        } else {
            return BooleanConstant.getTrueFlag();
        }
    }
    
}
