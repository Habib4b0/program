/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ProjectionCustHierarchyFinderImpl extends BasePersistenceImpl<ProjectionCustHierarchy> implements ProjectionCustHierarchyFinder {

    /**
     * Returns the hierarchy definition details for Customer/Product hierarchy
     * lookup
     *
     * @param hierarchyName Hierarchy Name search criteria
     * @param hierarchyType Hierarchy Type search criteria
     * @param customerOrProduct Indicates customer hierarchy or product
     * hierarchy lookup
     * @param action To indicate whether the action is "SEARCH" or "SELECT"
     * @return result list of hierarchy
     */
    private static final Logger LOGGER = Logger.getLogger(ProjectionCustHierarchyFinderImpl.class);

    public void insert(List list, String string1, String string2) {
        // List<Leveldto> listdx = new ArrayList<Leveldto>();

        //listdx=(ArrayList<Leveldto>)list.get(0);
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {


//            if (Constants.ButtonConstants.BTN_SEARCH.getConstant().equals(action)) {


            queryBuilder.append("INSERT INTO PROJECTION_CUST_HIERARCHY VALUES(1," + string1 + ") ;");


//            LOGGER.debug("Query:\n" + queryBuilder.toString());
            session = openSession();
            Query sqlQuery = session.createQuery(queryBuilder.toString());
            sqlQuery.executeUpdate();




            //listdx.add(sqlQuery.list());
            // LOGGER.debug("Query:\n" + queryBuilder.toString() + "\n\nquery hit list size: " + list.size() + "\n\n");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
//            ex.printStackTrace();
        } finally {
            closeSession(session);
        }


    }

    public List retrive(List list, String string1, String string2) {

        return null;
    }

    public List getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = "%";
        String workflowStatusVal;
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        String fromVal;
        String toVal;
        Session session = null;
        boolean isProjectionStatus = false;
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        try {
//            LOGGER.debug("Entering getRelationShipValues method");
            session = openSession();
//            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (workflowStatus.equals("Saved")) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " + marketTypeVal + ")");
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE " + brandVal + " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + projNameVal + " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE " + contHldrVal + " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE " + ndcNameVal + " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE " + ndcNoVal + "or IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " + contractVal + "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + descVal + "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " + QUOTES + workflowStatus + QUOTES);
            }
            if (from != null && !"null".equals(from) && !StringUtils.isEmpty(from)
                    && to != null && !"null".equals(to) && !StringUtils.isEmpty(to)) {
//                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }

           // LOGGER.debug("queryString.toString() -- >> " + customSql.toString());

            Query query = session.createSQLQuery(customSql.toString());
            return query.list();
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }
}
