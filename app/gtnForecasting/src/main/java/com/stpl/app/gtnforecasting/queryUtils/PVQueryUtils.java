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
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
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
    
    public String getCCPQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + "JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + SPACE_JOIN_SPACE + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID =" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like  '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + SPACE_JOIN_SPACE + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID = " + projectionId
                + " WHERE RLD2.LEVEL_NO =" + projSelDTO.getLevelNo() + ")) CCP ";

        return ccpQuery;
    }

    public String getCCPGeneralQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + SPACE_JOIN_SPACE + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + SPACE_JOIN_SPACE + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") ";
        return ccpQuery;
    }

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String ccp) {
        String ccpWhereCond = Constant.AND_SMALL_SPACE + ccp + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        String quotes = "'";
        String asterik = "*";
        String percent = Constant.PERCENT;
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
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE  " ).append( marketTypeVal ).append( ")");
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
            if (from != null && !Constant.NULL.equals(from) && !StringUtils.isEmpty(from)
                    && to != null && !Constant.NULL.equals(to) && !StringUtils.isEmpty(to)) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND  PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append(Constant.AND_SPACE);
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (" ).append( notSearchProjId ).append( ")");
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
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CommonUtils.CollectionToString(projId, false) + ")");
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

    public String getGeneralCCPQuery(PVSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + SPACE_JOIN_SPACE + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + SPACE_JOIN_SPACE + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    public String getCustomCCPQuery(PVSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + SPACE_JOIN_SPACE
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + SPACE_JOIN_SPACE
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE;

        return ccpQuery;
    }

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @param screenName
     * @param parameters
     * @return String for Query
     */
    public String getComparisonSearchCount(final ComparisonLookupDTO comparisonLookupDTO, String screenName, Map<String, Object> parameters) {
        String quotes = "'";
        String asterik = "*";
        String percent = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql;
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getProjectionListsCount"));
            } else {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getWorkFlowListsCount"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace('*', '%');
                marketTypeVal = quotes + marketTypeVal + quotes;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(')');
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace('*', '%');
                brandVal = quotes + brandVal + quotes;
            }
            customSql.append(" AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace('*', '%');
                projNameVal = quotes + projNameVal + quotes;
            }
            customSql.append("AND  (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace('*', '%');
                contHldrVal = quotes + contHldrVal + quotes;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace('*', '%');
                ndcNameVal = quotes + ndcNameVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace('*', '%');
                ndcNoVal = quotes + ndcNoVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or  IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace('*', '%');
                contractVal = quotes + contractVal + quotes;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace('*', '%');
                descVal = quotes + descVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or  PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and ISNULL(PM.IS_APPROVED,'') not  in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append('\'').append(comparisonLookupDTO.getWorkflowStatus()).append('\'');
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(Constant.AND_PMCREATED_DATE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND  PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append(Constant.AND_SPACE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get(Constant.FILTERPROJECTION_NAME) != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get(Constant.FILTERPROJECTION_NAME)).append("') ");
            }
            if (parameters.get(Constant.FILTERPROJECTION_DESCRIPTION) != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERPROJECTION_DESCRIPTION)).append("') ");
            }
            if (parameters.get(Constant.FILTERMARKET_TYPE) != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERMARKET_TYPE)).append("') ");
            }
            if (parameters.get(Constant.FILTER_CONTRACT) != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get(Constant.FILTER_CONTRACT)).append("')");
            }
            if (parameters.get(Constant.FILTER_CUSTOMER) != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get(Constant.FILTER_CUSTOMER)).append("') ");
            }
            if (parameters.get(Constant.FILTERBRAND) != null) {
                String value = String.valueOf(parameters.get(Constant.FILTERBRAND));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if (Constant.MULTIPLE_SMALL.contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get(Constant.FILTERBRAND)).append("') ");
                }
            }
            if (parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM) != null && parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO) != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM)).append(Constant.AND_SPACE).append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM) != null) {
                customSql.append("AND PM.CREATED_DATE >= '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO) != null) {
                customSql.append("AND PM.CREATED_DATE <= '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO)).append("' ");
            }
            if ((parameters.get(Constant.FILTERCREATED_BY) != null) && (parameters.get(Constant.FILTERCREATED_BY) != null)) {
                    List<String> strList;
                    final DynamicQuery dynamicQuery = UserLocalServiceUtil.dynamicQuery();
                    Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get(Constant.FILTERCREATED_BY) + Constant.PERCENT);
                    Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get(Constant.FILTERCREATED_BY) + Constant.PERCENT);
                    dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                    final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                    productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                    dynamicQuery.setProjection(productProjectionList);
                    strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    String userID = CommonUtils.CollectionToString(strList, false);
                    customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(')');
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ) RSLT ");
            return customSql.toString();
        } catch (SystemException | ParseException e) {
            LOGGER.error("at PvQueryUtils -> getComparisonSearchCount= {}", e);
            return null;
        }
    }

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearch(final ComparisonLookupDTO comparisonLookupDTO, int start, int offset, List<SortByColumn> sortColumns, String screenName, Map<String, Object> parameters) {
        String quotes = "'";
        String asterik = "*";
        String percent = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
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
        try {
            StringBuilder customSql;
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }

            if (isProjectionStatus) {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),Constant.GET_PROJECTION_LISTS));
            } else {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getWorkFlowLists"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace('*', '%');
                marketTypeVal = quotes + marketTypeVal + quotes;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(')');
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace('*', '%');
                brandVal = quotes + brandVal + quotes;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is  null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace('*', '%');
                projNameVal = quotes + projNameVal + quotes;
            }
            customSql.append("AND  (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is  null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace('*', '%');
                contHldrVal = quotes + contHldrVal + quotes;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or  C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace('*', '%');
                ndcNameVal = quotes + ndcNameVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is  null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace('*', '%');
                ndcNoVal = quotes + ndcNoVal + quotes;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace('*', '%');
                contractVal = quotes + contractVal + quotes;
            }
            customSql.append("AND  (CM.COMPANY_NO LIKE ").append(contractVal).append("or  CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace('*', '%');
                descVal = quotes + descVal + quotes;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and ISNULL(PM.IS_APPROVED,'') not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name =  'WorkFlowStatus' and ht1.description = ").append('\'').append(comparisonLookupDTO.getWorkflowStatus()).append('\'');
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(Constant.AND_PMCREATED_DATE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append("  AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append(Constant.AND_SPACE);
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get(Constant.FILTERPROJECTION_NAME) != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get(Constant.FILTERPROJECTION_NAME)).append("') ");
            }
            if (parameters.get(Constant.FILTERPROJECTION_DESCRIPTION) != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERPROJECTION_DESCRIPTION)).append("') ");
            }
            if (parameters.get(Constant.FILTERMARKET_TYPE) != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get(Constant.FILTERMARKET_TYPE)).append("') ");
            }
            if (parameters.get(Constant.FILTER_CONTRACT) != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get(Constant.FILTER_CONTRACT)).append("')");
            }
            if (parameters.get(Constant.FILTER_CUSTOMER) != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get(Constant.FILTER_CUSTOMER)).append("') ");
            }
            if (parameters.get(Constant.FILTERBRAND) != null) {
                String value = String.valueOf(parameters.get(Constant.FILTERBRAND));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if (Constant.MULTIPLE_SMALL.contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get(Constant.FILTERBRAND)).append("') ");
                }
            }
            if (parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM) != null && parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO) != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMFROM)).append(Constant.AND_SPACE).append(parameters.get(Constant.FILTER_BETWEENCREATED_DATE_FROMTO)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM) != null) {
                customSql.append("AND PM.CREATED_DATE > = '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMFROM)).append("' ");
            }
            if (parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO) != null) {
                customSql.append("AND PM.CREATED_DATE < = '").append(parameters.get(Constant.FILTER_GO_ECREATED_DATE_FROMTO)).append("' ");
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
                String userID = CommonUtils.CollectionToString(strList, false);
                customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID  NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(')');
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ");
            customSql.append(" ORDER BY ");

            if (sortColumns == null || sortColumns.size() == 0) {
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
            return customSql.toString();
        } catch (SystemException | ParseException e) {
            LOGGER.error("at PvQueryUtils -> getComparisonSearch method= {}", e);
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
            String asterik = "*";
            String percent = Constant.PERCENT;
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
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
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
                if (Constant.MULTIPLE_SMALL.contains(value.toLowerCase())) {
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
                String userID = CommonUtils.CollectionToString(strList, false);
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
