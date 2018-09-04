/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public class ProjectionCustHierarchyImpl {

    public List getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to) {
        char QUOTES = '\'';
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        boolean isProjectionStatus = false;
        StringBuilder customSql = new StringBuilder();
        try {

            if (workflowStatus.equals("Saved")) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getProjectionLists"));
            } else {
                customSql = new StringBuilder(SQlUtil.getQuery(getClass(),"getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace('*', '%');
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " ).append( marketTypeVal ).append( ')');
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace('*', '%');
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE " ).append( brandVal ).append( " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace('*', '%');
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( projNameVal ).append( " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace('*', '%');
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE " ).append( contHldrVal ).append( " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace('*', '%');
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE " ).append( ndcNameVal ).append( " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace('*', '%');
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE " ).append( ndcNoVal ).append( "or IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace('*', '%');
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " ).append( contractVal ).append( "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace('*', '%');
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " ).append( descVal ).append( "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " ).append( QUOTES ).append( workflowStatus ).append( QUOTES);
            }
            if (from != null && to != null && !StringUtils.isEmpty(to) && !StringUtils.isEmpty(from) && !"null".equals(from) 
                     && !"null".equals(to) ) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(customSql.toString());
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql.toString());
            return null;
        }
    }
}
