/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderimpl;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public class ProjectionCustHierarchyImpl {

    public List getComparisonSearch(String workflowStatusCs, String marketTypeCs, String brand,
            String projNameCs, String contHldrCs, String ndcNoCs, String ndcNameCs, String descCs, String contractCs,
            String fromCs, String toCs) {
        char quotesCustHiearchy = '\'';
        String marketTypeValCustHiearchy;
        String brandValCustHiearchy;
        String projNameValCustHiearchy;
        String contHldrValCustHiearchy;
        String ndcNoValCustHiearchy;
        String ndcNameValCustHiearchy;
        String descValCustHiearchy;
        String contractValCustHiearchy;
        boolean isProjectionStatusCustHiearchy = false;
        StringBuilder customSqlCustHiearchy = new StringBuilder();
        try {

            if (workflowStatusCs.equals("Saved")) {
                isProjectionStatusCustHiearchy = true;
            }
            if (isProjectionStatusCustHiearchy) {
                customSqlCustHiearchy = new StringBuilder(SQlUtil.getQuery(getClass(),"getProjectionLists"));
            } else {
                customSqlCustHiearchy = new StringBuilder(SQlUtil.getQuery(getClass(),"getWorkFlowLists"));
            }

            if (marketTypeCs == null || marketTypeCs.equals(StringUtils.EMPTY)) {
                marketTypeValCustHiearchy = "'%'";
            } else {
                marketTypeValCustHiearchy = marketTypeCs.replace('*', '%');
                marketTypeValCustHiearchy = quotesCustHiearchy + marketTypeValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " ).append( marketTypeValCustHiearchy ).append( ')');
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandValCustHiearchy = "'%'";
            } else {
                brandValCustHiearchy = brand.replace('*', '%');
                brandValCustHiearchy = quotesCustHiearchy + brandValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("  AND (BM.BRAND_NAME LIKE " ).append( brandValCustHiearchy ).append( " or BM.BRAND_NAME is null)");
            if (projNameCs == null || projNameCs.equals(StringUtils.EMPTY)) {
                projNameValCustHiearchy = "'%'";
            } else {
                projNameValCustHiearchy = projNameCs.replace('*', '%');
                projNameValCustHiearchy = quotesCustHiearchy + projNameValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (PM.PROJECTION_NAME LIKE " ).append( projNameValCustHiearchy ).append( " or PM.PROJECTION_NAME is null)");
            if (contHldrCs == null || contHldrCs.equals(StringUtils.EMPTY)) {
                contHldrValCustHiearchy = "'%'";
            } else {
                contHldrValCustHiearchy = contHldrCs.replace('*', '%');
                contHldrValCustHiearchy = quotesCustHiearchy + contHldrValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (C.CONTRACT_NO LIKE " ).append( contHldrValCustHiearchy ).append( " or C.CONTRACT_NO is null)");
            if (ndcNameCs == null || ndcNameCs.equals(StringUtils.EMPTY)) {
                ndcNameValCustHiearchy = "'%'";
            } else {
                ndcNameValCustHiearchy = ndcNameCs.replace('*', '%');
                ndcNameValCustHiearchy = quotesCustHiearchy + ndcNameValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (IM.ITEM_NAME LIKE " ).append( ndcNameValCustHiearchy ).append( " or IM.ITEM_NAME is null)");
            if (ndcNoCs == null || ndcNoCs.equals(StringUtils.EMPTY)) {
                ndcNoValCustHiearchy = "'%'";
            } else {
                ndcNoValCustHiearchy = ndcNoCs.replace('*', '%');
                ndcNoValCustHiearchy = quotesCustHiearchy + ndcNoValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (IM.ITEM_NO LIKE " ).append( ndcNoValCustHiearchy ).append( "or IM.ITEM_NO is null)");
            if (contractCs == null || contractCs.equals(StringUtils.EMPTY)) {
                contractValCustHiearchy = "'%'";
            } else {
                contractValCustHiearchy = contractCs.replace('*', '%');
                contractValCustHiearchy = quotesCustHiearchy + contractValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (CM.COMPANY_NO LIKE " ).append( contractValCustHiearchy ).append( "or CM.COMPANY_NO is null)");
            if (descCs == null || descCs.equals(StringUtils.EMPTY)) {
                descValCustHiearchy = "'%'";
            } else {
                descValCustHiearchy = descCs.replace('*', '%');
                descValCustHiearchy = quotesCustHiearchy + descValCustHiearchy + quotesCustHiearchy;
            }
            customSqlCustHiearchy.append("AND (PM.PROJECTION_NAME LIKE " ).append( descValCustHiearchy ).append( "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatusCustHiearchy) {
                customSqlCustHiearchy.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSqlCustHiearchy.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " ).append( quotesCustHiearchy ).append( workflowStatusCs ).append( quotesCustHiearchy);
            }
            if (fromCs != null && toCs != null && !StringUtils.isEmpty(toCs) && !StringUtils.isEmpty(fromCs) && !"null".equals(fromCs) 
                     && !"null".equals(toCs) ) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSqlCustHiearchy.append(" AND PM.CREATED_DATE BETWEEN '");
                customSqlCustHiearchy.append(format2.format(format2.parse(fromCs)));
                customSqlCustHiearchy.append("' AND '");
                customSqlCustHiearchy.append(format2.format(format2.parse(toCs)));
                customSqlCustHiearchy.append("' ");
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(customSqlCustHiearchy.toString());
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSqlCustHiearchy.toString());
            return Collections.emptyList();
        }
    }
}
