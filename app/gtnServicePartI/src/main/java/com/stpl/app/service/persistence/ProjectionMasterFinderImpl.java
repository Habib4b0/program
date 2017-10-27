
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ProjectionMasterFinderImpl extends BasePersistenceImpl<ProjectionMaster> implements ProjectionMasterFinder {

    private static final Logger LOGGER = Logger.getLogger(ProjectionMasterFinderImpl.class);

    /**
     * Gets the hierarchy level and its values from Relationship builder
     *
     * @param parameters contains all the parameters
     * @return result list
     */
    @Override
    public List getRelationshipHierarchy(final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder customSql = new StringBuilder(CustomSQLUtil.get("getRelationForCCP1"));
        try {
            LOGGER.debug("Entering getRelationshipHierarchy method");
            session = openSession();
            if (parameters.get("projectionCustHierarchy") != null) {
                customSql.append(" ProjectionCustHierarchy PH where PH.projectionMasterSid='");
            } else if (parameters.get("projectionProdHierarchy") != null) {
                customSql.append(" ProjectionProdHierarchy PH where PH.projectionMasterSid='");
            }
            customSql.append(String.valueOf(parameters.get("projectionId")));
            customSql.append("'");
            customSql.append(CustomSQLUtil.get("getRelationForCCP2"));
            if (parameters.get("projectionCustHierarchy") != null) {
                customSql.append(" ProjectionCustHierarchy PHT where PHT.projectionMasterSid='");
            } else if (parameters.get("projectionProdHierarchy") != null) {
                customSql.append(" ProjectionProdHierarchy PHT where PHT.projectionMasterSid='");
            }
            customSql.append(String.valueOf(parameters.get("projectionId")));
            customSql.append("'");
            customSql.append(CustomSQLUtil.get("getRelationForCCP3"));

            Query sqlQuery = session.createQuery(customSql.toString());
//            LOGGER.debug("\n\n getRelationshipHierarchy query is -----> " + customSql.toString() + "\n\n");
            LOGGER.debug("End of getRelationshipHierarchy method");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error("In getRelationshipHierarchy " + e.getMessage());
            LOGGER.error(customSql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    /**
     * Gets the ccp details value from CCP_DETAILS
     *
     * @param parameters contains all the parameters
     * @return result list
     */
    @Override
    public List getCcpDetails(final Map<String, Object> parameters) {
        Session session = null;
        try {
            LOGGER.debug("Entering getCcpDetails method");
            session = openSession();
//            LOGGER.debug("QUERY: " + String.valueOf(parameters.get("ccpQuery")));
            Query query = session.createQuery(String.valueOf(parameters.get("ccpQuery")));
            return query.list();
        } catch (Exception e) {
            LOGGER.error("In getCcpDetails   " + e.getMessage());
            LOGGER.error(String.valueOf(parameters.get("ccpQuery")));
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List getCustomerProductGroup(final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getCustomerProductGroup method");
            session = openSession();
            if (parameters.get("indicator") != null && "CustomerGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get("getCustomerGroup"));
                if (parameters.get("customerNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerNo")))) {
                    queryString.append(" AND CG.companyGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerNo")));
                    queryString.append("'");
                }
                if (parameters.get("customerName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerName")))) {
                    queryString.append(" AND CG.companyGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerName")));
                    queryString.append("'");
                }
                if (parameters.get("companySids") != null) {
                    List<String> companySids = (ArrayList<String>) (parameters.get("companySids"));
                    StringBuilder companiesList = new StringBuilder(StringUtils.EMPTY);
                    if (companySids != null && !companySids.isEmpty()) {
                        for (int loop = 0, limit = companySids.size(); loop < limit; loop++) {
                            companiesList.append("'");
                            companiesList.append(companySids.get(loop));
                            companiesList.append("'");
                            if (loop != (limit - 1)) {
                                companiesList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND CGD.companyMasterSid in ( ");
                    queryString.append(companiesList.toString());
                    queryString.append(" )");
                }

            } else if (parameters.get("indicator") != null && "ProductGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get("getProductGroup"));
                if (parameters.get("productNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productNo")))) {
                    queryString.append(" AND IG.itemGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get("productNo")));
                    queryString.append("' ");
                }
                if (parameters.get("productName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productName")))) {
                    queryString.append(" AND IG.itemGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get("productName")));
                    queryString.append("'");
                }

                if (parameters.get("itemSids") != null) {
                    List<String> itemSids = (ArrayList<String>) (parameters.get("itemSids"));
                    if (itemSids != null) {
                        StringBuilder itemsList = new StringBuilder(StringUtils.EMPTY);
                        if (itemSids != null && !itemSids.isEmpty()) {
                            for (int loop = 0, limit = itemSids.size(); loop < limit; loop++) {
                                itemsList.append("'");
                                itemsList.append(itemSids.get(loop));
                                itemsList.append("'");
                                if (loop != (limit - 1)) {
                                    itemsList.append(", ");
                                }
                            }
                        }
                        queryString.append(" AND IGD.itemMasterSid in ( ");
                        queryString.append(itemsList.toString());
                        queryString.append(" )");
                    }
                }
            } else {
                return null;
            }

//            LOGGER.debug("Customer/Product group query: \n" + queryString.toString() + "\n");
            Query query = session.createQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error("In getCustomerProductGroup  " + e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List searchDsProjection(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        String accProjJoin="@ACCRUALPROJJOIN";
        String accFieldValue="@ACCFIELDVALUE";
        String filterValue="@WHEREFILTERVALUE";
//        String and = StringUtils.EMPTY;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering searchDsProjection method");
            session = openSession();
            if ("Returns".equals(parameters.get("moduleName"))) {
                if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                    queryString.append(CustomSQLUtil.get("searchProjectionForReturns"));
                    queryString.append(" AND PM.FORECASTING_TYPE like '" + String.valueOf(parameters.get("moduleName")) + "'");
                } else {
                    queryString.append(CustomSQLUtil.get("searchProjectionCountForReturns"));
                    queryString.append(" AND PM.FORECASTING_TYPE like '" + String.valueOf(parameters.get("moduleName")) + "'");
                }
            } else {
                if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                    queryString.append(CustomSQLUtil.get("searchProjection"));
//                    LOGGER.debug("String.valueOf(parameters.get(\"selectValue\"))================================>"+String.valueOf(parameters.get("selectValue")));
//                    LOGGER.debug("String.valueOf(parameters.get(\"leftJoinValue\"))===================================================>"+String.valueOf(parameters.get("leftJoinValue")));
                    queryString.replace(queryString.indexOf(accFieldValue),queryString.indexOf(accFieldValue)+accFieldValue.length(),String.valueOf(parameters.get("selectValue")));
                    queryString.replace(queryString.indexOf(accProjJoin),queryString.indexOf(accProjJoin)+accProjJoin.length(),String.valueOf(parameters.get("leftJoinValue")));
                     queryString.replace(queryString.indexOf(filterValue),queryString.indexOf(filterValue)+filterValue.length(),String.valueOf(parameters.get("whereFilterValue"))); 
                    queryString.append(" AND PM.FORECASTING_TYPE like '" + String.valueOf(parameters.get("moduleName")) + "'");
                    
                } else {
                    queryString.append(CustomSQLUtil.get("searchProjectionCount"));
                    queryString.replace(queryString.indexOf(accProjJoin),queryString.indexOf(accProjJoin)+accProjJoin.length(),String.valueOf(parameters.get("leftJoinValue")));
                     queryString.replace(queryString.indexOf(filterValue),queryString.indexOf(filterValue)+filterValue.length(),String.valueOf(parameters.get("whereFilterValue"))); 
                    queryString.append(" AND PM.FORECASTING_TYPE like '" + String.valueOf(parameters.get("moduleName")) + "'");
                }
            }
            

             if (parameters.get("projectionDescription") != null) {
                queryString.append(" AND PM.PROJECTION_DESCRIPTION like '");
                queryString.append(String.valueOf(parameters.get("projectionDescription")));
                queryString.append("' ");
//                and = " AND ";
            }
            if (parameters.get("projectionName") != null) {

//                queryString.append(and);
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get("projectionName")));
                queryString.append("' ");
            }
            if (parameters.get("customerHierarchySid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("customerHierarchySid")))) {
                queryString.append(" AND PM.CUSTOMER_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get("customerHierarchySid")).trim());
                queryString.append("' ");
            }
            if (parameters.get("prodHierarchySid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("prodHierarchySid")))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get("prodHierarchySid")));
                queryString.append("' ");
            }
            if (parameters.get("customerGroupSid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("customerGroupSid")))) {
                queryString.append(" AND PM.COMPANY_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get("customerGroupSid")));
                queryString.append("' ");
            }
            if (parameters.get("prodGroupSid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("prodGroupSid")))) {
                queryString.append(" AND PM.ITEM_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get("prodGroupSid")));
                queryString.append("' ");
            }
            if (parameters.get("companyMasterSid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("companyMasterSid")))) {
                queryString.append(" AND PM.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get("companyMasterSid")));
                queryString.append("' ");
            }
            if (parameters.get("businessUnit") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("businessUnit")))&& !ConstantsUtils.ZERO.equals(String.valueOf(parameters.get("businessUnit")))) {
                queryString.append(" AND PM.BUSINESS_UNIT = ");
                queryString.append(String.valueOf(parameters.get("businessUnit")));
                queryString.append(" ");
            }
             if (parameters.get("companySid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("companySid")))&& !ConstantsUtils.ZERO.equals(String.valueOf(parameters.get("companySid")))) {
                 queryString.append(" AND PM.COMPANY_MASTER_SID = ");
                 queryString.append(String.valueOf(parameters.get("companySid")));
                 queryString.append(" ");
            }
//=================================
            if ("AccrualRateProjection".equals(parameters.get("moduleName"))) {
            
            if (parameters.get("deductionLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("deductionLevel")))) {
                queryString.append(" AND APS.FIELD_NAME like '");
                queryString.append(String.valueOf(parameters.get("deductionLevel")));
                queryString.append("' ");
            }
             if (parameters.get("deductionValue") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("deductionValue")))) {
                queryString.append(" AND APS.FIELD_VALUES like '");
                queryString.append(String.valueOf(parameters.get("deductionValue")));
                queryString.append("' ");
            }   
                
            }
//            =================================
            
            if (!"Returns".equals(parameters.get("moduleName"))) {
                if (parameters.get("selectedCustomerRelationSid") != null) {
                    String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                    List<String> selectedCustomerRelationSid = (ArrayList<String>) parameters.get("selectedCustomerRelationSid");
                    if (!selectedCustomerRelationSid.isEmpty()) {
                        queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                        queryString.append(" AND PCH.RELATIONSHIP_LEVEL_SID in (");

                        for (int loop = 0, limit = selectedCustomerRelationSid.size(); loop < limit; loop++) {
                            queryString.append("'");
                            queryString.append(selectedCustomerRelationSid.get(loop));
                            queryString.append("'");
                            if (loop != (limit - 1)) {
                                queryString.append(", ");
                            }
                        }
                        queryString.append(") ");

                    } else {
                        queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                    }
                } else {
                    String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            }

            if (parameters.get("selectedProductRelationSid") != null) {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                List<String> selectedProductRelationSid = (ArrayList<String>) parameters.get("selectedProductRelationSid");
                if (!selectedProductRelationSid.isEmpty()) {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_PROD_HIERARCHY PPH ON PPH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                    queryString.append(" AND PPH.RELATIONSHIP_LEVEL_SID in (");

                    for (int loop = 0, limit = selectedProductRelationSid.size(); loop < limit; loop++) {
                        queryString.append("'");
                        queryString.append(selectedProductRelationSid.get(loop));
                        queryString.append("'");
                        if (loop != (limit - 1)) {
                            queryString.append(", ");
                        }
                    }
                    queryString.append(") ");

                } else {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            } else {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
            }
            if (parameters.get("discountSid") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("discountSid")))) {
                queryString.append(" AND PM.DISCOUNT_TYPE like '");
                queryString.append(String.valueOf(parameters.get("discountSid")));
                queryString.append("' ");
            }
            if ((parameters.get("fromDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("fromDate"))))
                    && (parameters.get("toDate") == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("toDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE >= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("fromDate")))));
                queryString.append("' ");
            } else if ((parameters.get("toDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("toDate"))))
                    && (parameters.get("fromDate") == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("fromDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE <= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("toDate")))));
                queryString.append("' ");
            } else if ((parameters.get("fromDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("fromDate"))))
                    && (parameters.get("toDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("toDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE BETWEEN '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("fromDate")))));
                queryString.append("' AND '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("toDate")))));
                queryString.append("' ");
            }
            if (parameters.get("filter~projectionName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~projectionName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~projectionName")))) {
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~projectionName")));
                queryString.append("' ");
            }

            if (parameters.get("filter~description") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~description"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~description")))) {
                queryString.append(" AND PM.PROJECTION_DESCRIPTION like '");
                queryString.append(String.valueOf(parameters.get("filter~description")));
                queryString.append("' ");
            }

            if (parameters.get("filter~customerHierarchyLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchyLevel")))) {
                queryString.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get("filter~customerHierarchyLevel")));
                queryString.append("' ");
            }

            if (parameters.get("filter~customerHierarchy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchy")))) {
                queryString.append(" AND HDC.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~customerHierarchy")));
                queryString.append("' ");
            }

            if (parameters.get("filter~productHierarchy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~productHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchy")))) {
                queryString.append(" AND HDP.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~productHierarchy")));
                queryString.append("' ");
            }

            if (parameters.get("filter~productHierarchyLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~productHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchyLevel")))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get("filter~productHierarchyLevel")));
                queryString.append("' ");
            }

            if (parameters.get("filter~createdBy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdBy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdBy")))) {
                queryString.append(" AND PM.CREATED_BY in (");
                queryString.append(String.valueOf(parameters.get("filter~createdBy")));
                queryString.append(") ");
            }
//            =============
            if ("AccrualRateProjection".equals(parameters.get("moduleName"))) {
            if (parameters.get("filter~deductionLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~deductionLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~deductionLevel")))) {
                queryString.append(" AND APS.FIELD_NAME  like '");
                queryString.append(String.valueOf(parameters.get("filter~deductionLevel")));
                queryString.append("' ");
            }

            if (parameters.get("filter~deductionValue") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~deductionValue"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~deductionValue")))) {
                queryString.append(" AND APS.FIELD_VALUES in (");
                queryString.append(String.valueOf(parameters.get("filter~deductionValue")));
                queryString.append(") ");
            }
            }
//============================================
            if ((parameters.get("filter~createdDateSearch~from") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE BETWEEN '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                queryString.append(from);
                queryString.append("' AND '");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~createdDateSearch~from") == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~createdDateSearch~from") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                queryString.append(from);
                queryString.append("' ");
            }

            if (parameters.get("filter~modifiedDateSearch~from") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    && parameters.get("filter~modifiedDateSearch~to") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                queryString.append(" AND PM.MODIFIED_DATE BETWEEN '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                queryString.append(from);
                queryString.append("' AND '");
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~modifiedDateSearch~from") == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                    && (parameters.get("filter~modifiedDateSearch~to") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                queryString.append(" AND PM.MODIFIED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                    && (parameters.get("filter~modifiedDateSearch~to") == null || ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                queryString.append(" AND PM.MODIFIED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                queryString.append(from);
                queryString.append("' ");
            }
            if (parameters.get("filter~discount") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~discount")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~discount")))) {
                queryString.append(" AND PM.DISCOUNT_TYPE in (");
                queryString.append(String.valueOf(parameters.get("filter~discount")));
                queryString.append(")");
            }
            if (parameters.get("filter~businessUnitName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~businessUnitName")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~businessUnitName")))) {
                queryString.append("  AND   CM1.COMPANY_NAME  LIKE '%");
                queryString.append(String.valueOf(parameters.get("filter~businessUnitName")));
                queryString.append("%'");
            }
//            
           if (parameters.get("filter~companyName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("filter~companyName")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyName")))) {
                queryString.append("  AND   CM.COMPANY_NAME  LIKE '%");
                queryString.append(String.valueOf(parameters.get("filter~companyName")));
                queryString.append("%'");
            }

            if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) && (parameters.get("lazyLoadResults") != null)) {
                queryString.append(" ORDER BY PM.CREATED_DATE DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~projectionName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~projectionName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~projectionName")))) {
                    queryString.append(" ORDER BY PM.PROJECTION_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~projectionName")));
                }

                if (parameters.get("orderBy~description") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~description"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~description")))) {
                    queryString.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                    queryString.append(String.valueOf(parameters.get("orderBy~description")));
                }

                if (parameters.get("orderBy~customerHierarchyLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchyLevel")))) {
                    queryString.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get("orderBy~customerHierarchyLevel")));
                }

                if (parameters.get("orderBy~customerHierarchy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchy")))) {
                    queryString.append(" ORDER BY HDC.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~customerHierarchy")));
                }

                if (parameters.get("orderBy~productHierarchy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchy")))) {
                    queryString.append(" ORDER BY HDP.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~productHierarchy")));
                }

                if (parameters.get("orderBy~productHierarchyLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchyLevel")))) {
                    queryString.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get("orderBy~productHierarchyLevel")));
                }

                if (parameters.get("orderBy~createdBy") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~createdBy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdBy")))) {
                    queryString.append(" ORDER BY PM.CREATED_BY ");
                    queryString.append(String.valueOf(parameters.get("orderBy~createdBy")));
                }

                if (parameters.get("orderBy~createdDateSearch") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                    queryString.append(" ORDER BY PM.CREATED_DATE ");
                    queryString.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                }

                if (parameters.get("orderBy~modifiedDateSearch") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                    queryString.append(" ORDER BY PM.MODIFIED_DATE ");
                    queryString.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                }
                 if (parameters.get("orderBy~businessUnitName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~businessUnitName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~businessUnitName")))) {
                    queryString.append(" ORDER BY PM.BUSINESS_UNIT ");
                    queryString.append(String.valueOf(parameters.get("orderBy~businessUnitName")));
                }
                if (parameters.get("orderBy~companyName") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~companyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~companyName")))) {
                    queryString.append(" ORDER BY PM.COMPANY_MASTER_SID ");
                    queryString.append(String.valueOf(parameters.get("orderBy~companyName")));
                }
                
//                ====================================
                 if ("AccrualRateProjection".equals(parameters.get("moduleName"))) {
                if (parameters.get("orderBy~deductionLevel") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~deductionLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~deductionLevel")))) {
                    queryString.append(" ORDER BY APS.FIELD_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~deductionLevel")));
                }

                if (parameters.get("orderBy~deductionValue") != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("orderBy~deductionValue"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~deductionValue")))) {
                    queryString.append(" ORDER BY APS.FIELD_VALUES ");
                    queryString.append(String.valueOf(parameters.get("orderBy~deductionValue")));
                }
                 }
//=========================================
            }

            if (parameters.get("lazyLoadResults") != null) {

                if (parameters.get("startIndex") != null && parameters.get("offset") != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get("startIndex")));
                    int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(" ROWS FETCH NEXT ");
                    queryString.append(offset);
                    queryString.append(" ROWS ONLY;");

                    /* Start and end index for lazy loading using HQL
                     query.setFirstResult(startIndex);
                     query.setMaxResults(offset);
                     */
                }
            }
            LOGGER.debug("Final Search Query: " + queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            resultList = query.list();
            return resultList;
        } catch (Exception e) {
            LOGGER.error("In searchDsProjection ->" + e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }   
    
    @Override
    public List getProjection(final int projectionId) {
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getProjection method");
            session = openSession();
            queryString.append(CustomSQLUtil.get("getProjectionJoin"));
            queryString.append(" where PM.projection_Master_Sid = ");
            queryString.append(projectionId);
//            LOGGER.debug("Final Query: " + queryString);
            Query query = session.createSQLQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error("In getProjection ->" + e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getRelationShipValuesforchannel(final Map<String, Object> parameters) {

        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getRelationShipValues method");
            session = openSession();
            if ("forecastDate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get(String.valueOf(parameters.get("Query"))));
                for (Object input : (List<Object>) parameters.get("input")) {
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(input));
                }
            } else {
                queryString.append(CustomSQLUtil.get("getRelationshipValuesforchannel"));
                if ("customer".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
//                    queryString.append(" Projection_Cust_Hierarchy PH ");
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Cust_Hierarchy ");
                } else if ("product".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
//                    queryString.append(" Projection_Prod_Hierarchy PH ");
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Prod_Hierarchy ");
                }
                queryString.append(" WHERE PH.PROJECTION_MASTER_SID='");
                queryString.append(String.valueOf(parameters.get("projectionId")));
                if (parameters.get("restrictLevelNumber") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("restrictLevelNumber")))) {
                    queryString.append("' AND RLD.LEVEL_NO <= '");
                    queryString.append(String.valueOf(parameters.get("levelNo")));
                }
                queryString.append("' ORDER by RLD.level_No");
            }

//            LOGGER.debug("getRelationShipValues Final Query: " + queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error("getRelationShipValues  -->"+e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }
    @Override
    public List getRelationShipValues(final Map<String, Object> parameters) {

        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getRelationShipValues method");
            session = openSession();
            if ("forecastDate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get(String.valueOf(parameters.get("Query"))));
                for (Object input : (List<Object>) parameters.get("input")) {
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(input));
                }
            } else {
                queryString.append(CustomSQLUtil.get("getRelationshipValues"));
                if ("customer".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
//                    queryString.append(" Projection_Cust_Hierarchy PH ");
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Cust_Hierarchy ");
                } else if ("product".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
//                    queryString.append(" Projection_Prod_Hierarchy PH ");
                    queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, " Projection_Prod_Hierarchy ");
                }
                queryString.append(" WHERE PH.PROJECTION_MASTER_SID='");
                queryString.append(String.valueOf(parameters.get("projectionId")));
                if (parameters.get("restrictLevelNumber") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("restrictLevelNumber")))) {
                    queryString.append("' AND RLD.LEVEL_NO <= '");
                    queryString.append(String.valueOf(parameters.get("levelNo")));
                }
                queryString.append("' ORDER by RLD.level_No");
            }

//            LOGGER.debug("getRelationShipValues Final Query: " + queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error("getRelationShipValues  -->"+e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public String deleteProjection(String screenName, int projectionID) {
        Session session = null;
        String foreignKeyTableName = StringUtils.EMPTY;
        String primarykeyTableName = StringUtils.EMPTY;
        if(screenName.equals("Non Mandated")){
            foreignKeyTableName = CustomSQLUtil.get("NM_FK_TableNamesFordelete");            
        } else if(screenName.equals("Mandated")){
            foreignKeyTableName = CustomSQLUtil.get("M_FK_TableNamesFordelete");            
        } else {
            foreignKeyTableName = CustomSQLUtil.get("R_FK_TableNamesFordelete");            
        }
        primarykeyTableName = CustomSQLUtil.get("TableNamesFordelete");
        String sql1 = StringUtils.EMPTY;
        String sql = StringUtils.EMPTY;
        String sql2 = StringUtils.EMPTY;
        try {
            session = openSession();
            sql = "select PROJECTION_DETAILS_SID from PROJECTION_DETAILS where PROJECTION_MASTER_SID = "+projectionID+" ";
            Query query = session.createSQLQuery(sql);
            List<Integer> idList = query.list();
            
            // Delete Foriegn key

            if (idList!=null && !idList.isEmpty()) {
                String fkTbName[] = foreignKeyTableName.split(",");
                int pksize = fkTbName.length;
                for (int i = 0; i < pksize; i++) {
                    sql1 += "delete from ";
                    if (fkTbName[i] != null && !StringUtils.EMPTY.equals(fkTbName[i]) && !ConstantsUtils.NULL.equals(fkTbName[i])) {
                        sql1 += fkTbName[i];
                    }
                    String projectionDetailsSid = CommonUtils.CollectionToString(idList, false);

                    sql1 += " where PROJECTION_DETAILS_SID IN (" + projectionDetailsSid + ");";

                }
                Query query1 = session.createSQLQuery(sql1);
                query1.executeUpdate();
            }
            
            // Delete primary key 
            
            String pkTbName[] = primarykeyTableName.split(",");
            
            if (screenName.equals("Non Mandated") || screenName.equals("Mandated")) {
                    sql2 += "delete from WORKFLOW_PROCESS_INFO where PROJECTION_MASTER_SID=" + projectionID + "; ";
                    sql2 += "delete from WORKFLOW_MASTER where PROJECTION_MASTER_SID=" + projectionID + "; ";
            }
           
            int size = pkTbName.length;
            for (int i = 0; i < size; i++) {
                sql2 += "delete from ";
                if (pkTbName[i] != null && !StringUtils.EMPTY.equals(pkTbName[i]) && !ConstantsUtils.NULL.equals(pkTbName[i])) {
                    sql2 += pkTbName[i];
                }
                if (projectionID != 0) {
                    sql2 += " where PROJECTION_MASTER_SID=" + projectionID + ";";
                }
            }
            Query query2 = session.createSQLQuery(sql2);
            query2.executeUpdate();
            
            return "Success";
        } catch (Exception ex) {
            LOGGER.error("In deleteProjection ->"+ex.getMessage());
            LOGGER.error(sql);
            LOGGER.error(sql1);
            LOGGER.error(sql2);
            return "fail";
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List getParentLevels(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            session = openSession();
            if (parameters.get("indicator") != null && !StringUtils.isBlank(String.valueOf(parameters.get("indicator")))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("indicator"))) && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryBuilder.append(CustomSQLUtil.get("getParentLevelsWithHierarchyNo"));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNos")));
            } else {
                queryBuilder.append(CustomSQLUtil.get("getParentLevels"));
                queryBuilder.append(" RBLD.relationshipLevelSid = '");
                queryBuilder.append(relationshipLevelSid);
                queryBuilder.append("')");
                if (parameters.get("parent") != null) {
                    queryBuilder.append(" AND RLD.relationshipLevelValues = '");
                    queryBuilder.append(String.valueOf(parameters.get("parent")));
                    queryBuilder.append("' ");
                }
                queryBuilder.append(" ORDER by RLD.levelNo desc");
            }
//            LOGGER.debug("\n\nParent level query: " + queryBuilder.toString()+"\n\n");

//            LOGGER.debug("\n\nParent level query: " + queryBuilder.toString()+"\n\n");
            Query query = session.createQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error("In getParentLevels ->"+ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List executeQuery(final Map<String, Object> parameters) {
        LOGGER.debug("----inside executeQuery in finder Impl-----------");
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get("indicator") != null && "hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("hasTradingPartner"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("projectionId")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null && "getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getUnsavedProjectionIds"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get("indicator") != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            
            if ("PROJECTION_PROD_HIERARCHY".equals(parameters.get("tableName"))) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                StringBuilder hierarchyInclusion = new StringBuilder();
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    hierarchyInclusion.append("HIERARCHY_NO LIKE '");
                    hierarchyInclusion.append(rlSids.get(loop));
                    hierarchyInclusion.append("%'");
                    if (loop != (limit - 1)) {
                        hierarchyInclusion.append(" OR ");
                    }
                }
                String hierarchyExclusion = CommonUtils.stringListToString(rlSids);
                String query = CustomSQLUtil.get("get-lower-levels-based-on-hierarchy-no");
                query = query.replace("[?BU_COMPANY_MASTER_SID]", StringUtils.EMPTY + parameters.get("businessUnit"));
                query = query.replace("[?PROJECTION_MASTER_SID]", StringUtils.EMPTY + parameters.get("projectionId"));
                query = query.replace("[?HIERARCHY_INCLUDE]", hierarchyInclusion);
                query = query.replace("[?HIERARCHY_EXCLUDE]", hierarchyExclusion);                
                
                if (parameters.get("module") != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get("module")))) {
                    query = query.replace("[?HIERARCHY_TABLE]", "CFF_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "CFF_MASTER_SID");
                } else {
                     query = query.replace("[?HIERARCHY_TABLE]", "PROJECTION_PROD_HIERARCHY");
                     query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "PROJECTION_MASTER_SID");
                }
                queryString.append(query); 
            } else {
            if (parameters.get("rlSids") != null) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(CustomSQLUtil.get("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append("HIERARCHY_NO LIKE '");
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(")");
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get("tableName")));
                   
                   if(parameters.get("module") != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get("module")))){
                         queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
                   }else{
                    queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                   }
                    queryString.append(String.valueOf(parameters.get("projectionId")));
                    queryString.append(")");
                }
            }
            }
//            LOGGER.debug("executeQuery queryString for getChildLevelRLSid " + queryString.toString());
        } else if (parameters.get("indicator") != null && "getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(CustomSQLUtil.get("getChildLevelRL"));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get("availableHierNo") != null) {
                    List<String> availableHierNo = (ArrayList<String>) parameters.get("availableHierNo");
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
//            LOGGER.debug("executeQuery queryString for getChildLevelRL " + queryString.toString());
        } else if (parameters.get("indicator") != null && "getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getRemovableChildren"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
//            LOGGER.debug("getRemovableChildren queryString " + queryString.toString());
        } else if (parameters.get("indicator") != null && "deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get("projectionId"));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : CommonUtils.getTempTableList()) {
                queryString.append(CustomSQLUtil.get("deleteTempOnUpdate"));
                queryString.replace(queryString.indexOf("?DTBL"), queryString.indexOf("?DTBL") + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
//                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
//                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
//            LOGGER.debug("deleteTempOnUpdate queryString " + queryString.toString());
        } else if (parameters.get("indicator") != null && "getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getFSValue"));
            queryString.replace(queryString.indexOf("?RLC?"), queryString.indexOf("?RLC?") + 5, String.valueOf(parameters.get("relationshipLevelValue")));
//            queryString.replace(queryString.indexOf("?FIELD?"), queryString.indexOf("?FIELD?") + 7, String.valueOf(parameters.get("fieldName")));
        } else if (parameters.get("indicator") != null && "companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("companyFilter"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append("'");
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
//            LOGGER.debug("executeQuery " + queryString.toString());
        }
//        LOGGER.debug("--query before try------------->>>>>>"+queryString);
        try {
            session = openSession();
            Query query;
            if (parameters.get("indicator") != null
                    && ("hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                    || ("getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))))
                    || ("companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
//                LOGGER.debug("-----insaide if loop query------------------>>>>>"+queryString.toString());
                query = session.createSQLQuery(queryString.toString());
            } else {
//                LOGGER.debug("--inside else query---------------------->>>>>"+queryString.toString());
                query = session.createQuery(queryString.toString());
            }
            if (parameters.get("indicator") != null
                    && ("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
                List<Integer> list = new ArrayList<Integer>();
                int returnValue = query.executeUpdate();
                list.add(returnValue);
                return list;
            } else {
                return query.list();
            }
//            LOGGER.debug("executeQuery queryString " + queryString.toString());

        } catch (Exception ex) {
            LOGGER.error("In executeQuery  ->"+ex.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List getItemsFromBrand(Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        queryBuilder.append(CustomSQLUtil.get("getItemsFromBrand"));
        try {
            session = openSession();
            List<Integer> itemMasterSids = (ArrayList<Integer>) parameters.get("itemMasterSids");
            if (itemMasterSids != null && !itemMasterSids.isEmpty()) {
                queryBuilder.append(" and IM.itemMasterSid in (");
                for (int loop = 0, limit = itemMasterSids.size(); loop < limit; loop++) {
                    queryBuilder.append("'");
                    queryBuilder.append(itemMasterSids.get(loop));
                    queryBuilder.append("'");
                    if (loop != (limit - 1)) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(") ");
            }

//            LOGGER.debug("getItemsFromBrand: " + queryBuilder.toString());
            Query query = session.createQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in getItemsFromBrand");
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List getInnerLevel(Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
              if ((parameters.get("isNdc") != null && "true".equals(String.valueOf(parameters.get("isNdc"))))
                        || (!Constants.BUSINESS_PROCESS_TYPE_CHENNALS.equals(parameters.get("screenName")) && parameters.get("level") != null && ("Ndc".equalsIgnoreCase(String.valueOf(parameters.get("level")))||"Item".equalsIgnoreCase(String.valueOf(parameters.get("level")))||"Product".equalsIgnoreCase(String.valueOf(parameters.get("level"))) ))) {

                    queryBuilder.append(CustomSQLUtil.get("get-inner-level-products"));
                    if("Product".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))
                        queryBuilder.append(" AND ( RLD.LEVEL_NAME='PRODUCT') ");
                    else
                         queryBuilder.append(" AND ( RLD.LEVEL_NAME='NDC') ");
                    
                    queryBuilder.append(" AND CM.COMPANY_MASTER_SID = ");
                    queryBuilder.append(String.valueOf(parameters.get("glCompId")));
                    queryBuilder.append(" AND GLC.COMPANY_CODE = CM.COMPANY_ID AND IM.NDC8 = GLC.NDC8 ");
                    queryBuilder.append(" AND IM.ORGANIZATION_KEY = ");
                    queryBuilder.append(String.valueOf(parameters.get("businessUnit"))).append(" ");
                    
                //queryBuilder.append(" AND ( RLD.LEVEL_NAME='PRODUCT' or RLD.LEVEL_NAME='NDC' ) ");
              } 
              
              else if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc"))) && "company".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {

                  queryBuilder.append(CustomSQLUtil.get("get-inner-level-companies"));

              } else if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc")))
                      && ("therapeutic class".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "brand".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                  
                  queryBuilder.append(CustomSQLUtil.get("get-inner-level-brand-therapeutic-class"));
                  
              }
              else if (parameters.get("level") != null && Constants.INDICATOR_LEVEL_CUSTOMER.equalsIgnoreCase(String.valueOf(parameters.get("level")))) {
                    queryBuilder.append(CustomSQLUtil.get("getInnerLevelCustomer"));

            } else if (parameters.get("level") != null && Constants.INDICATOR_LEVEL_CONTRACT.equalsIgnoreCase(String.valueOf(parameters.get("level")))) {
                    queryBuilder.append(CustomSQLUtil.get("getInnerLevelContract"));

            } else {
                    queryBuilder.append(CustomSQLUtil.get("getInnerLevel"));
            }

//        queryBuilder.append(CustomSQLUtil.get("getInnerLevel"));
            if (parameters.get("hierarchyDefinitionSid") != null) {
                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
                queryBuilder.append(String.valueOf(parameters.get("hierarchyDefinitionSid")));
            }

            if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc")))) {
                queryBuilder.append(" and RLD.level_Name = '");
                queryBuilder.append(String.valueOf(parameters.get("levelName")));
                queryBuilder.append("'");
            }

            if (parameters.get("relationshipSid") != null && !StringUtils.isBlank(String.valueOf(parameters.get("relationshipSid")))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("relationshipSid"))) && !"0".equals(String.valueOf(parameters.get("relationshipSid")))) {
                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
                queryBuilder.append(String.valueOf(parameters.get("relationshipSid")));
                queryBuilder.append("'");
            }
//            queryBuilder = getRelationLevelList(queryBuilder, parameters);
            if ("AccrualRateProjection".equalsIgnoreCase(String.valueOf(parameters.get("screenName"))) && ( !"Segment Group".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) &&!"Segment".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) &&!"Segments".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) &&!"Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) &&!"GL Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) && !"Business Unit".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                queryBuilder.append("and RLD.relationship_Level_Values IN (");

                if (parameters.get("levelName") != null && ("Market Type".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "MarketType".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  CM.CONTRACT_TYPE from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "'  )");
                } else if (parameters.get("levelName") != null && "Contract".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                    queryBuilder.append("\n"
                            + "                    Select Distinct  RS_C_TYPE.CONTRACT_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue"))+ "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  + "'    )");

                }
                else if (parameters.get("levelName") != null && "Contract Holder".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                    queryBuilder.append("\n"
                            + "                    Select Distinct  CM.CONT_HOLD_COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue"))+ "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  + "'    )");

                }
                else if (parameters.get("levelName") != null && ("Trading Partner".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Customer".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            + "-- LIST_NAME like'" + String.valueOf(parameters.get("deductionLevel"))  +  "'\n"
                            + "DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue"))+ "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))   + "' \n"
                            + "))");
                }
                else if (parameters.get("levelName") != null && ("Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            + "-- LIST_NAME like'" + String.valueOf(parameters.get("deductionLevel"))  +  "'\n"
                            + "DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  +  "' \n"
                            + "))"+"  JOIN COMPANY_MASTER COM_MAS ON COM_MAS.COMPANY_MASTER_SID=CFP_CD_SID.COMPANY_MASTER_SID\n" +
"                            AND COM_MAS.COMPANY_TYPE IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n" +
"                            DESCRIPTION like'GLCOMP' AND LIST_NAME='COMPANY_TYPE')");
                }else if (parameters.get("levelName") != null && ("Item".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Product".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Ndc".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  IFP_CD_SID.ITEM_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  " 
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue"))+ "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  +  "' )) \n");
//                    + String.valueOf(parameters.get("deductionValue")) +
                } else if (parameters.get("levelName") != null && ("Brand".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  IM.BRAND_MASTER_SID from CONTRACT_MASTER CM\n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  "
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  +  "' ))");
                } else if (parameters.get("levelName") != null && ("Therapeutic Class".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  IM.THERAPEUTIC_CLASS from CONTRACT_MASTER CM\n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel"))  +  " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  "
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel"))  +  "' ))");
                }

                queryBuilder.append(")");
            }
//            LOGGER.debug("queryBuilder.toString()======================================>"+queryBuilder.toString());
            session = openSession();
            Query query = session.createSQLQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in getInnerLevel()");
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void saveCcp(final Map<String, Object> parameters) {

//        select * from CCP_MAP where RELATIONSHIP_LEVEL_SID =  
//        indicator/insertToCcpMap
//scrennName/Mandated
//relationshipBuilderSids/[1, 2]
//        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
//            LOGGER.debug(entry.getKey() + "/" + entry.getValue());
//        }

        Session session = null;
        String hierarchyQuery = StringUtils.EMPTY;
        String levelQuery = StringUtils.EMPTY;
        StringBuilder ccpQueryList = new StringBuilder();
        try {
            LOGGER.debug("Entering saveCcp method ");

            session = openSession();

            if (parameters.get("indicator") != null && "indicator".equalsIgnoreCase(String.valueOf(parameters.get("saveCcp")))) {
                StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);

//            customSql.append("select CCP_DETAILS_SID from CCP_MAP where RELATIONSHIP_LEVEL_SID = ");
//            if (parameters.get("relationshipLevelSid") != null) {
//                customSql.append(String.valueOf(parameters.get("relationshipLevelSid")));
//            }
//            if (parameters.get("hierarchyNo") != null) {
//                customSql.append(" AND HIERARCHY_NO = ");
//                customSql.append(String.valueOf(parameters.get("hierarchyNo")));
//            }
                customSql.append(CustomSQLUtil.get("saveCcp"));

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

//            Query sqlQuery = session.createSQLQuery(customSql.toString());
//                LOGGER.debug("\n\n saveCcp query is -----> " + customSql.toString() + "\n\n");
                LOGGER.debug("End of saveCcp method");
//            return sqlQuery.list();
            } else {
                List list = null;
                try {
                    List<String> relationshipBuilderSids = (List<String>) parameters.get("relationshipBuilderSids");
                    if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
                        for (Object temp : relationshipBuilderSids) {
                            Integer level;
                            hierarchyQuery = CustomSQLUtil.get("getHierarchyMapQuery").replace("?", String.valueOf(temp));
                            levelQuery = "SELECT max(RLD.LEVEL_NO)\n"
                                    + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                                    + "HIERARCHY_LEVEL_DEFINITION HLD \n"
                                    + "where HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                                    + "AND RLD.RELATIONSHIP_BUILDER_SID=" + temp + ";";
                            session = openSession();
                            Query sqlQuery = session.createSQLQuery(hierarchyQuery);
                            Query sqlQuery1 = session.createSQLQuery(levelQuery);
                            list = sqlQuery.list();
                            level = Integer.parseInt(String.valueOf(sqlQuery1.list().get(0)));
                            List<StringBuilder> logic = new ArrayList<StringBuilder>();
                            List<String> condition = new ArrayList<String>();
                            StringBuilder ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                    + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                            Integer prevNo = 0;
                            Integer count = 0;
                            String nextHierarchyNo = StringUtils.EMPTY;
                            for (int i = 0; i < list.size(); i++) {
                                Object[] tempRow = (Object[]) list.get(i);
                                Integer currNo = Integer.parseInt(String.valueOf(tempRow[2]));

                                if (i < list.size() - 1) {
                                    Object[] tempNextRow = (Object[]) list.get(i + 1);
                                    nextHierarchyNo = String.valueOf(tempNextRow[4]);
                                }
                                if (prevNo >= currNo) {
                                    while (currNo <= prevNo) {
                                        if (logic.size() > 0) {
                                            logic.remove(logic.get(logic.size() - 1));
                                        }
                                        if (condition.size() > 0) {
                                            condition.remove(condition.get(condition.size() - 1));
                                        }
                                        prevNo--;
                                    }
                                }
                                //ly nm
                                if ("Contract Holder".equals(tempRow[3]) && parameters.get("projectionId") != null && Constants.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(parameters.get("screenName"))) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONT_HOLD_COMPANY_MASTER_SID=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONT_HOLD_COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") CH");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CH.CONTRACT_MASTER_SID");
                                } else if ("Market Type".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONTRACT_TYPE=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_TYPE=J1.HELPER_TABLE_SID");
                                    }
                                    tempLogic.append(") MT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=MT.CONTRACT_MASTER_SID");
                                } else if ("Contract".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("=' ");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_MASTER_SID=J1.CONTRACT_MASTER_SID");
                                    }
                                    tempLogic.append(") CT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CT.CONTRACT_MASTER_SID");
                                } else if (("Trading Partner".equals(tempRow[3]) || "Customer".equals(tempRow[3])) && "Customer Hierarchy".equals(tempRow[7])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.COMPANY_MASTER_SID FROM COMPANY_MASTER CM");
                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") TP");
                                    logic.add(tempLogic);
                                    condition.add("CCP.COMPANY_MASTER_SID=TP.COMPANY_MASTER_SID");
                                } //                                else if ("Company".equals(tempRow[3]) && "Product Hierarchy".equals(tempRow[7])) {
                                //                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CCPIM.ITEM_MASTER_SID, CCPIM.COMPANY_MASTER_SID FROM CCP_DETAILS CCPIM ");
                                //                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                //                                        tempLogic.append(", ");
                                //                                        tempLogic.append(tempRow[5]);
                                //                                        tempLogic.append(" CM WHERE CM.");
                                //                                        tempLogic.append(tempRow[6]);
                                //                                        tempLogic.append("='");
                                //                                        tempLogic.append(tempRow[1]);
                                //                                        tempLogic.append("' AND CCPIM.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID");
                                //                                        condition.add("CCP.COMPANY_MASTER_SID=MAN.COMPANY_MASTER_SID");
                                //                                    } else {
                                //                                        tempLogic.append(", ");
                                //                                        tempLogic.append(tempRow[5]);
                                //                                        tempLogic.append(" J1 WHERE J1.");
                                //                                        tempLogic.append(tempRow[6]);
                                //                                        tempLogic.append("='");
                                //                                        tempLogic.append(tempRow[1]);
                                //                                        tempLogic.append("' AND CCPIM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                //                                } 
                                //                                    tempLogic.append(") MAN");
                                //                                    logic.add(tempLogic);
                                //                                    condition.add("CCP.ITEM_MASTER_SID=MAN.ITEM_MASTER_SID");
                                //                                } 
                                else if ("Brand".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("BRAND_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" BM WHERE BM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=J1.BRAND_MASTER_SID");
                                    }
                                    tempLogic.append(") BRAND");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=BRAND.ITEM_MASTER_SID");
                                } else if ("Therapeutic Class".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.THERAPEUTIC_CLASS=J1.");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append("_SID");
                                    }
                                    tempLogic.append(") TPC");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=TPC.ITEM_MASTER_SID");
                                } else if ("Item".equals(tempRow[3]) || "NDC".equals(tempRow[3]) || "Product".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.ITEM_MASTER_SID=J1.ITEM_MASTER_SID");
                                    }
                                    tempLogic.append(") ITEM");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=ITEM.ITEM_MASTER_SID");
                                }
                                prevNo = currNo;
                                if (level == currNo || !nextHierarchyNo.contains(String.valueOf(tempRow[4])) || list.size() == i + 1) {
                                    ccpQuery.replace(ccpQuery.indexOf("?"), ccpQuery.indexOf("?") + 1, String.valueOf(tempRow[0]));
                                    for (int j = logic.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(logic.get(j));
                                    }
                                    ccpQuery.append(" WHERE ");
                                    for (int j = condition.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(condition.get(j));
                                        if (j != 0) {
                                            ccpQuery.append(" AND ");
                                        }
                                    }
                                    ccpQuery.append(") AS SOURCE ON (TARGET.RELATIONSHIP_LEVEL_SID=SOURCE.RELATIONSHIP_LEVEL_SID AND "
                                            + "TARGET.CCP_DETAILS_SID=SOURCE.CCP_DETAILS_SID) WHEN NOT MATCHED THEN INSERT "
                                            + "(RELATIONSHIP_LEVEL_SID, CCP_DETAILS_SID) VALUES ( SOURCE.RELATIONSHIP_LEVEL_SID, SOURCE.CCP_DETAILS_SID);");

                                    ccpQueryList.append(ccpQuery);
                                    count++;
                                    if (count == 10 || list.size() == i + 1) {
                                        session = openSession();
//                                        LOGGER.debug("SAVE CCP: " + ccpQuery);
                                        Query insertQuery = session.createSQLQuery(ccpQueryList.toString());
                                        insertQuery.executeUpdate();
                                        ccpQueryList = new StringBuilder();
                                        count = 0;
                                    }
                                    ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                            + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                                }

                            }
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                    LOGGER.error(hierarchyQuery);
                    LOGGER.error(levelQuery);
                    LOGGER.error(ccpQueryList.toString());
                } finally {
                    closeSession(session);
                }
            }
        } catch (Exception e) {
            LOGGER.error("In saveCcp ->"+e.getMessage());
            LOGGER.error(hierarchyQuery);
            LOGGER.error(levelQuery);
            LOGGER.error(ccpQueryList.toString());
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List getCcpMap(final Map<String, Object> parameters) {

//        select * from CCP_MAP where RELATIONSHIP_LEVEL_SID =  
        Session session = null;
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getCcpMap method ");

            session = openSession();
            if (parameters.get("indicator") != null && "getRbId".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                customSql.append(CustomSQLUtil.get("getRbIdFromHier"));
                if (parameters.get("hierarchyDefinitionSid") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyDefinitionSid")));
                }

            } else if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
//            customSql.append("select CCP_DETAILS_SID from CCP_MAP where RELATIONSHIP_LEVEL_SID = ");
//            if (parameters.get("relationshipLevelSid") != null) {
//                customSql.append(String.valueOf(parameters.get("relationshipLevelSid")));
//            }
//            if (parameters.get("hierarchyNo") != null) {
//                customSql.append(" AND HIERARCHY_NO = ");
//                customSql.append(String.valueOf(parameters.get("hierarchyNo")));
//            }
//                customSql.append(CustomSQLUtil.get("getCcp"));
//                customSql.append(CustomSQLUtil.get("getCcpV2Insert"));
                customSql.append(CustomSQLUtil.get("saveCcpMerge"));

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }
                
//                 if (parameters.get("hierarchyNo") != null) {
//                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
//                }

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

            }

//            LOGGER.debug("\n\n getCcpMap query is -----> " + customSql.toString() + "\n\n");
            if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                Query sqlQuery = session.createSQLQuery(customSql.toString());
                sqlQuery.executeUpdate();
                return null;
            } else {
                Query sqlQuery = session.createSQLQuery(customSql.toString());
                return sqlQuery.list();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in getCcpMap");
            LOGGER.error(customSql.toString());
            return null;
        } finally {
            closeSession(session);
            LOGGER.debug("End of getCcpMap method");
        }
    }

    public List getLevelCcp(final Map<String, Object> parameters) {

        Session session = null;
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getLevelCcp method ");

            session = openSession();
            
//            customSql.append("select CCP_DETAILS_SID from CCP_MAP where RELATIONSHIP_LEVEL_SID = ");
//            if (parameters.get("relationshipLevelSid") != null) {
//                customSql.append(String.valueOf(parameters.get("relationshipLevelSid")));
//            }
//            if (parameters.get("hierarchyNo") != null) {
//                customSql.append(" AND HIERARCHY_NO = ");
//                customSql.append(String.valueOf(parameters.get("hierarchyNo")));
//            } 
            Query sqlQuery = session.createSQLQuery(customSql.toString());
//            LOGGER.debug("\n\n getLevelCcp query is -----> " + customSql.toString() + "\n\n");
            LOGGER.debug("End of getLevelCcp method");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error("In getLevelCcp  "+e.getMessage());
            LOGGER.error(customSql.toString());
            return null;
        } finally {
            closeSession(session);
        }
//        return null;
    }

    public Object tempOperation(final Map<String, Object> input, final String queryName) {
        Session session = null;
        String customSql = CustomSQLUtil.get(queryName);
        String finalQuery = StringUtils.EMPTY;
        try {
            session = openSession();
            Object temp;
//            LOGGER.debug("\n\n tempOperation query is -----> " + customSql.toString() + "\n\n");
            if ("getHierarchyTableDetails".equals(queryName)) {
                for (String key : input.keySet()) {
//                    LOGGER.debug("Key : " + key);
                    customSql = customSql.replace(key, String.valueOf(input.get(key)));
//                    LOGGER.debug("getHierarchyTableDetails: "+customSql);
                }
                Query sqlQuery = session.createSQLQuery(customSql);
                List tempList = sqlQuery.list();
                Map<String, String> valueList = new HashMap<String, String>();

                for (int i = tempList.size() - 1; i >= 0; i--) {
                    customSql = CustomSQLUtil.get("getRelationshipLevelValues");
                    Object[] tempListObject = (Object[]) tempList.get(i);
                    customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
                    customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
                    customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[2]));
                    customSql = customSql.replace("?LNO", String.valueOf(tempListObject[3]));
                    customSql = customSql.replace("?RBSID", String.valueOf(input.get("?RBSID")));
                    if (i != 0) {
                        customSql = customSql.concat(" UNION ALL ");
                    }
                    finalQuery += customSql;
                }
                sqlQuery = session.createSQLQuery(finalQuery);
                List tempValueList = sqlQuery.list();
                for (int j = tempValueList.size() - 1; j >= 0; j--) {
                    Object[] tempObject = (Object[]) tempValueList.get(j);
                    valueList.put(String.valueOf(tempObject[0]), String.valueOf(tempObject[1]));
                    }
                temp = valueList;
            }else {
                for (String key : input.keySet()) {
//                    LOGGER.debug("Key : " + key);
                    if(customSql.contains(key)){
                    customSql = customSql.replace(key, String.valueOf(input.get(key)));
                }
                }

                Query sqlQuery = session.createSQLQuery(customSql);

                if ("ds.singleBrand".equals(queryName)) {
                    temp = sqlQuery.list();
                } else {
                    temp = sqlQuery.executeUpdate();
                }
                
            }
            return temp;
        } catch (Exception e) {
            LOGGER.error("In tempOperation ->"+e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getChildLevels(final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            session = openSession();
            String finalQuery;
            if (parameters.containsKey("businessUnit")) {
                finalQuery = CustomSQLUtil.get("getChildLevelsBasedonBU");
                finalQuery=finalQuery.replace("@HIERNO1", String.valueOf(parameters.get("hierarchyNo")));
                finalQuery=finalQuery.replace("@HIERNO2", String.valueOf(parameters.get("hierarchyNo")));
                finalQuery=finalQuery.replace("@LVLNO", String.valueOf(parameters.get("lowestLevelNo")));
                finalQuery=finalQuery.replace("@BU_COMPANY_MASTER_SID", String.valueOf(parameters.get("businessUnit")));
               
            }else{
            queryBuilder.append(CustomSQLUtil.get("getChildLevelsWithHierarchyNo"));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("lowestLevelNo")));
            finalQuery = queryBuilder.toString();
            }
            Query query = session.createSQLQuery(finalQuery);
            return query.list();
        } catch (Exception ex) {
            LOGGER.error("In getChildLevels ->"+ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    private StringBuilder getRelationLevelList(StringBuilder queryBuilder, Map<String, Object> parameters) {
        List<Integer> relationshipLevelSidList = (ArrayList<Integer>) parameters.get("relationshipLevelSidList");
        if (relationshipLevelSidList != null && !relationshipLevelSidList.isEmpty()) {
            queryBuilder.append(" and RLD.relationship_Level_Sid not in (");
            for (int loop = 0, limit = relationshipLevelSidList.size(); loop < limit; loop++) {
                queryBuilder.append("'");
                queryBuilder.append(relationshipLevelSidList.get(loop));
                queryBuilder.append("'");
                if (loop != (limit - 1)) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(") ");
        }
          queryBuilder = queryBuilder==null?new StringBuilder():queryBuilder;
        return queryBuilder;
    }

    public void saveCcpForMandated(final Map<String, Object> parameters) {
        Session session = null;
        List list = null;
        String hierarchyQuery = StringUtils.EMPTY;
        String levelQuery = StringUtils.EMPTY;
        StringBuilder ccpQueryList = new StringBuilder();
        try {
            List<String> relationshipBuilderSids = (List<String>) parameters.get("relationshipBuilderSids");
            if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
                for (Object temp : relationshipBuilderSids) {
                    Integer level;
//                            Boolean delete = true;
//                            String ccpDeleteQuery = "DELETE FROM CCP_MAP WHERE RELATIONSHIP_LEVEL_SID in(SELECT RELATIONSHIP_LEVEL_SID FROM RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_BUILDER_SID=" + temp + ");";

                    hierarchyQuery = CustomSQLUtil.get("getHierarchyMapQuery").replace("?", String.valueOf(temp));
                    levelQuery = "SELECT max(RLD.LEVEL_NO)\n"
                            + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                            + "HIERARCHY_LEVEL_DEFINITION HLD \n"
                            + "where HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                            + "AND RLD.RELATIONSHIP_BUILDER_SID=" + temp + ";";
                    session = openSession();
                    Query sqlQuery = session.createSQLQuery(hierarchyQuery);
                    Query sqlQuery1 = session.createSQLQuery(levelQuery);
                    list = sqlQuery.list();
                    level = Integer.parseInt(String.valueOf(sqlQuery1.list().get(0)));
                    List<StringBuilder> logic = new ArrayList<StringBuilder>();
                    List<String> condition = new ArrayList<String>();
                    StringBuilder ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                            + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                    Integer prevNo = 0;
                    Integer count = 0;
                    String nextHierarchyNo = StringUtils.EMPTY;
                    for (int i = 0; i < list.size(); i++) {
                        Object[] tempRow = (Object[]) list.get(i);
                        Integer currNo = Integer.parseInt(String.valueOf(tempRow[2]));

                        if (i < list.size() - 1) {
                            Object[] tempNextRow = (Object[]) list.get(i + 1);
                            nextHierarchyNo = String.valueOf(tempNextRow[4]);
                        }
                        if (prevNo >= currNo) {
                            while (currNo <= prevNo) {
                                if(logic.size()>0){
                                    logic.remove(logic.get(logic.size() - 1));
                                }
                                    	if(condition.size()>0){
                                    condition.remove(condition.get(condition.size() - 1));
                                }
                                prevNo--;

                            }
                        }
                        if ("Market Type".equals(tempRow[3])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                            if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                tempLogic.append(" WHERE CM.CONTRACT_TYPE=");
                                tempLogic.append(tempRow[1]);
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND CM.CONTRACT_TYPE=J1.HELPER_TABLE_SID");
                            }
                            tempLogic.append(") MT");
                            logic.add(tempLogic);
                            condition.add("CCP.CONTRACT_MASTER_SID=MT.CONTRACT_MASTER_SID");
                        } else if ("Contract".equals(tempRow[3])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                            if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                tempLogic.append(" WHERE CM.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("'");
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("=' ");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND CM.CONTRACT_MASTER_SID=J1.CONTRACT_MASTER_SID");
                            }
                            tempLogic.append(") CT");
                            logic.add(tempLogic);
                            condition.add("CCP.CONTRACT_MASTER_SID=CT.CONTRACT_MASTER_SID");
                        } else if (("Customer".equals(tempRow[3]) || "Company".equals(tempRow[3])) && "Customer Hierarchy".equals(tempRow[7])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT COMPANY_MASTER_SID FROM COMPANY_MASTER CM");
                            if ("COMPANY_MASTER".equals(tempRow[5])) {
                                tempLogic.append(" WHERE CM.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("'");
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND CM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                            }
                            tempLogic.append(") TP");
                            logic.add(tempLogic);
                            condition.add("CCP.COMPANY_MASTER_SID=TP.COMPANY_MASTER_SID");
                        } 
                        else if ("Brand".equals(tempRow[3])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                            if ("BRAND_MASTER".equals(tempRow[5])) {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" BM WHERE BM.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ");
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND IM.BRAND_MASTER_SID=J1.BRAND_MASTER_SID");
                            }
                            tempLogic.append(") BRAND");
                            logic.add(tempLogic);
                            condition.add("CCP.ITEM_MASTER_SID=BRAND.ITEM_MASTER_SID");
                        } else if ("Therapeutic Class".equals(tempRow[3])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                            if ("ITEM_MASTER".equals(tempRow[5])) {
                                tempLogic.append(" WHERE IM.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' ");
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND IM.THERAPEUTIC_CLASS=J1.");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append("_SID");
                            }
                            tempLogic.append(") TPC");
                            logic.add(tempLogic);
                            condition.add("CCP.ITEM_MASTER_SID=TPC.ITEM_MASTER_SID");
                        } else if ("Item".equals(tempRow[3]) || "NDC".equals(tempRow[3]) || "Product".equals(tempRow[3])) {
                            StringBuilder tempLogic = new StringBuilder(", (SELECT ITEM_MASTER_SID FROM ITEM_MASTER IM");
                            if ("ITEM_MASTER".equals(tempRow[5])) {
                                tempLogic.append(" WHERE IM.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("'");
                            } else {
                                tempLogic.append(", ");
                                tempLogic.append(tempRow[5]);
                                tempLogic.append(" J1 WHERE J1.");
                                tempLogic.append(tempRow[6]);
                                tempLogic.append("='");
                                tempLogic.append(tempRow[1]);
                                tempLogic.append("' AND IM.ITEM_MASTER_SID=J1.ITEM_MASTER_SID");
                            }
                            tempLogic.append(") ITEM");
                            logic.add(tempLogic);
                            condition.add("CCP.ITEM_MASTER_SID=ITEM.ITEM_MASTER_SID");
                        }
                        prevNo = currNo;
                        if (level == currNo || !nextHierarchyNo.contains(String.valueOf(tempRow[4])) || list.size() == i + 1) {
                            ccpQuery.replace(ccpQuery.indexOf("?"), ccpQuery.indexOf("?") + 1, String.valueOf(tempRow[0]));
                            for (int j = logic.size() - 1; j >= 0; j--) {
                                ccpQuery.append(logic.get(j));
                            }
                            ccpQuery.append(" WHERE ");
                            for (int j = condition.size() - 1; j >= 0; j--) {
                                ccpQuery.append(condition.get(j));
                                if (j != 0) {
                                    ccpQuery.append(" AND ");
                                }
                            }
//                                    ccpQuery.append("; ");
                            ccpQuery.append(") AS SOURCE ON (TARGET.RELATIONSHIP_LEVEL_SID=SOURCE.RELATIONSHIP_LEVEL_SID AND "
                                    + "TARGET.CCP_DETAILS_SID=SOURCE.CCP_DETAILS_SID) WHEN NOT MATCHED THEN INSERT "
                                    + "(RELATIONSHIP_LEVEL_SID, CCP_DETAILS_SID) VALUES ( SOURCE.RELATIONSHIP_LEVEL_SID, SOURCE.CCP_DETAILS_SID);");

                            ccpQueryList.append(ccpQuery);
                            count++;
                            if (count == 10 || list.size() == i + 1) {
                                session = openSession();
//                                        if (delete) {
//                                            Query deleteQuery = session.createSQLQuery(ccpDeleteQuery.toString());
//                                            deleteQuery.executeUpdate();
//                                            delete = false;
//                                        }
//                                LOGGER.debug("****************ccpQueryList.toString********************" + ccpQueryList.toString());
                                Query insertQuery = session.createSQLQuery(ccpQueryList.toString());
                                insertQuery.executeUpdate();
                                ccpQueryList = new StringBuilder();
                                count = 0;
                            }
                            ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                    + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
//                            LOGGER.debug("*************************ccpQuery************************"+ccpQuery.toString());
                        }

                    }
                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
            LOGGER.error(hierarchyQuery);
            LOGGER.error(levelQuery);
            LOGGER.error(ccpQueryList.toString());
        }finally {
            closeSession(session);
        }
    }
    public List searchProjections(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering searchDsProjection method");
            session = openSession();
            if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                queryString.append(CustomSQLUtil.get("searchProjectionforchannel"));
            } else {
                queryString.append(CustomSQLUtil.get("searchProjectionCountforchannel"));
            }
            if (parameters.get("projectionDescription") != null) {
                queryString.append(" AND PM.PROJECTION_DESCRIPTION like '");
                queryString.append(String.valueOf(parameters.get("projectionDescription")));
                queryString.append("' ");
            }
            if (parameters.get("projectionName") != null) {
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get("projectionName")));
                queryString.append("' ");
            }
            if (parameters.get("customerHierarchySid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("customerHierarchySid")))) {
                queryString.append(" AND PM.CUSTOMER_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get("customerHierarchySid")).trim());
                queryString.append("' ");
            }
            if (parameters.get("prodHierarchySid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("prodHierarchySid")))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_SID like '");
                queryString.append(String.valueOf(parameters.get("prodHierarchySid")));
                queryString.append("' ");
            }
            if (parameters.get("customerGroupSid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("customerGroupSid")))) {
                queryString.append(" AND PM.COMPANY_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get("customerGroupSid")));
                queryString.append("' ");
            }
            if (parameters.get("prodGroupSid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("prodGroupSid")))) {
                queryString.append(" AND PM.ITEM_GROUP_SID like '");
                queryString.append(String.valueOf(parameters.get("prodGroupSid")));
                queryString.append("' ");
            }
            if (parameters.get("companyMasterSid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("companyMasterSid")))) {
                queryString.append(" AND PM.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get("companyMasterSid")));
                queryString.append("' ");
            }
            if (parameters.get("discountSid") != null && !Constants.NULL.equals(String.valueOf(parameters.get("discountSid")))) {
                queryString.append(" AND PM.DISCOUNT_TYPE like '");
                queryString.append(String.valueOf(parameters.get("discountSid")));
                queryString.append("' ");
            }
            if (parameters.get("selectedCustomerRelationSid") != null) {
                String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                List<String> selectedCustomerRelationSid = (ArrayList<String>) parameters.get("selectedCustomerRelationSid");
                if (!selectedCustomerRelationSid.isEmpty()) {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                    queryString.append(" AND PCH.RELATIONSHIP_LEVEL_SID in (");

                    for (int loop = 0, limit = selectedCustomerRelationSid.size(); loop < limit; loop++) {
                        queryString.append("'");
                        queryString.append(selectedCustomerRelationSid.get(loop));
                        queryString.append("'");
                        if (loop != (limit - 1)) {
                            queryString.append(", ");
                        }
                    }
                    queryString.append(") ");

                } else {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            } else {
                String replaceMe = "JOINPROJECTIONCUSTHIERARCHY";
                queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
            }
            if (parameters.get("selectedProductRelationSid") != null) {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                List<String> selectedProductRelationSid = (ArrayList<String>) parameters.get("selectedProductRelationSid");
                if (!selectedProductRelationSid.isEmpty()) {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), " JOIN PROJECTION_PROD_HIERARCHY PPH ON PPH.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID ");
                    queryString.append(" AND PPH.RELATIONSHIP_LEVEL_SID in (");

                    for (int loop = 0, limit = selectedProductRelationSid.size(); loop < limit; loop++) {
                        queryString.append("'");
                        queryString.append(selectedProductRelationSid.get(loop));
                        queryString.append("'");
                        if (loop != (limit - 1)) {
                            queryString.append(", ");
                        }
                    }
                    queryString.append(") ");

                } else {
                    queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
                }
            } else {
                String replaceMe = "JOINPROJECTIONPRODHIERARCHY";
                queryString.replace(queryString.indexOf(replaceMe), queryString.indexOf(replaceMe) + replaceMe.length(), StringUtils.EMPTY);
            }
            if ((parameters.get("fromDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) && !Constants.NULL.equals(String.valueOf(parameters.get("fromDate"))))
                    && (parameters.get("toDate") == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) || Constants.NULL.equals(String.valueOf(parameters.get("toDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE >= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("fromDate")))));
                queryString.append("' ");
            } else if ((parameters.get("toDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) && !Constants.NULL.equals(String.valueOf(parameters.get("toDate"))))
                    && (parameters.get("fromDate") == null || StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) || Constants.NULL.equals(String.valueOf(parameters.get("fromDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE <= '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("toDate")))));
                queryString.append("' ");
            } else if ((parameters.get("fromDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("fromDate"))) && !Constants.NULL.equals(String.valueOf(parameters.get("fromDate"))))
                    && (parameters.get("toDate") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("toDate"))) && !Constants.NULL.equals(String.valueOf(parameters.get("toDate"))))) {
                SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
                queryString.append(" AND PM.CREATED_DATE BETWEEN '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("fromDate")))));
                queryString.append("' AND '");
                queryString.append(format2.format(format2.parse(String.valueOf(parameters.get("toDate")))));
                queryString.append("' ");
            }
            if (parameters.get("filter~projectionName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~projectionName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~projectionName")))) {
                queryString.append(" AND PM.PROJECTION_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~projectionName")));
                queryString.append("' ");
            }

            if (parameters.get("filter~description") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~description"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~description")))) {
                queryString.append(" AND PM.PROJECTION_DESCRIPTION like '");
                queryString.append(String.valueOf(parameters.get("filter~description")));
                queryString.append("' ");
            }

            if (parameters.get("filter~customerHierarchyLevel") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchyLevel")))) {
                queryString.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get("filter~customerHierarchyLevel")));
                queryString.append("' ");
            }

            if (parameters.get("filter~customerHierarchy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchy")))) {
                queryString.append(" AND HDC.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~customerHierarchy")));
                queryString.append("' ");
            }

            if (parameters.get("filter~productHierarchy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchy")))) {
                queryString.append(" AND HDP.HIERARCHY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~productHierarchy")));
                queryString.append("' ");
            }

            if (parameters.get("filter~productHierarchyLevel") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchyLevel")))) {
                queryString.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                queryString.append(String.valueOf(parameters.get("filter~productHierarchyLevel")));
                queryString.append("' ");
            }

            if (parameters.get("filter~createdBy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdBy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdBy")))) {
                queryString.append(" AND PM.CREATED_BY in (");
                queryString.append(String.valueOf(parameters.get("filter~createdBy")));
                queryString.append(") ");
            }

            if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE BETWEEN '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                queryString.append(from);
                queryString.append("' AND '");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                    && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                queryString.append(" AND PM.CREATED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                queryString.append(from);
                queryString.append("' ");
            }

            if (parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    && parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                queryString.append(" AND PM.MODIFIED_DATE BETWEEN '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                queryString.append(from);
                queryString.append("' AND '");
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                    && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                queryString.append(" AND PM.MODIFIED_DATE < '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                queryString.append(to);
                queryString.append("' ");
            } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                    && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                    || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                queryString.append(" AND PM.MODIFIED_DATE > '");
                SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                queryString.append(from);
                queryString.append("' ");
            } else if (parameters.get("filter~discount") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~discount")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~discount")))) {
                queryString.append(" AND PM.DISCOUNT_TYPE in (");
                queryString.append(String.valueOf(parameters.get("filter~discount")));
                queryString.append(")");
            }
//            

            if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) && (parameters.get("lazyLoadResults") != null)) {
                queryString.append(" ORDER BY PM.CREATED_DATE DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~projectionName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~projectionName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~projectionName")))) {
                    queryString.append(" ORDER BY PM.PROJECTION_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~projectionName")));
                }

                if (parameters.get("orderBy~description") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~description"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~description")))) {
                    queryString.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                    queryString.append(String.valueOf(parameters.get("orderBy~description")));
                }

                if (parameters.get("orderBy~customerHierarchyLevel") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchyLevel")))) {
                    queryString.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get("orderBy~customerHierarchyLevel")));
                }

                if (parameters.get("orderBy~customerHierarchy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchy")))) {
                    queryString.append(" ORDER BY HDC.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~customerHierarchy")));
                }

                if (parameters.get("orderBy~productHierarchy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchy")))) {
                    queryString.append(" ORDER BY HDP.HIERARCHY_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~productHierarchy")));
                }

                if (parameters.get("orderBy~productHierarchyLevel") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchyLevel"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchyLevel")))) {
                    queryString.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL ");
                    queryString.append(String.valueOf(parameters.get("orderBy~productHierarchyLevel")));
                }

                if (parameters.get("orderBy~createdBy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdBy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdBy")))) {
                    queryString.append(" ORDER BY PM.CREATED_BY ");
                    queryString.append(String.valueOf(parameters.get("orderBy~createdBy")));
                }

                if (parameters.get("orderBy~createdDateSearch") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                    queryString.append(" ORDER BY PM.CREATED_DATE ");
                    queryString.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                }

                if (parameters.get("orderBy~modifiedDateSearch") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                    queryString.append(" ORDER BY PM.MODIFIED_DATE ");
                    queryString.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                }
            }

            if (parameters.get("lazyLoadResults") != null) {

                if (parameters.get("startIndex") != null && parameters.get("offset") != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get("startIndex")));
                    int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(" ROWS FETCH NEXT ");
                    queryString.append(offset);
                    queryString.append(" ROWS ONLY;");

                    /* Start and end index for lazy loading using HQL
                     query.setFirstResult(startIndex);
                     query.setMaxResults(offset);
                     */
                }
            }
//            LOGGER.debug("Final Search Query: " + queryString);
            Query query = session.createSQLQuery(queryString.toString());
            resultList = query.list();
            return resultList;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }
    public int getForecastViewCount(final String viewName, final String viewType) {
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            session = openSession();
            queryString.append(CustomSQLUtil.get("getForcastViewCount"));
            queryString.replace(queryString.indexOf("?VN"), queryString.indexOf("?VN") + 3, viewName);
            queryString.replace(queryString.indexOf("?VT"), queryString.indexOf("?VT") + 3, viewType);
            Query query = session.createSQLQuery(queryString.toString());
            return query.list().size();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            return 0;
        } finally {
            closeSession(session);
        }
    }
       public List executeQueryforchannel(final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get("indicator") != null && "hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("hasTradingPartner"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("projectionId")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null && "getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getUnsavedProjectionIds"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get("indicator") != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            if (parameters.get("rlSids") != null) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(CustomSQLUtil.get("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append("HIERARCHY_NO LIKE '");
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(")");
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get("tableName")));
                    queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    queryString.append(String.valueOf(parameters.get("projectionId")));
                    queryString.append(")");
                }
            }
        } else if (parameters.get("indicator") != null && "getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(CustomSQLUtil.get("getChildLevelRL"));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get("availableHierNo") != null) {
                    List<String> availableHierNo = (ArrayList<String>) parameters.get("availableHierNo");
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get("indicator") != null && "getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getRemovableChildren"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
        } else if (parameters.get("indicator") != null && "deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get("projectionId"));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : CommonUtils.getTempTableList()) {
                queryString.append(CustomSQLUtil.get("deleteTempOnUpdate"));
                queryString.replace(queryString.indexOf("?DTBL"), queryString.indexOf("?DTBL") + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
        } else if (parameters.get("indicator") != null && "getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("getFSValue"));
            queryString.replace(queryString.indexOf("?RLC?"), queryString.indexOf("?RLC?") + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get("indicator") != null && "companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(CustomSQLUtil.get("companyFilter"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null
                && ("getHierarchyGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "getHierarchyGroupCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) { //searchGroup
            String query = CustomSQLUtil.get("getHierarchyGroup");

            if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                    && !"count".equals(String.valueOf(parameters.get("action")))) {
                query = query.replace("?SELECTION?", " C.HIERARCHY_DEFINITION_SID AS SID, C.HIERARCHY_NAME AS NAME, A.LEVEL_NAME AS LEVEL, "
                        + "A.LEVEL_NO AS HIGH_LEVEL, B.LEVEL_NO AS LOW_LEVEL, C.CREATED_DATE AS CREATED_DATE, C.MODIFIED_DATE AS MODIFIED_DATE, C.VERSION_NO AS VERSION ");
            } else {
                query = query.replace("?SELECTION?", " COUNT(C.HIERARCHY_DEFINITION_SID) AS SID_COUNT ");
            }

//            ?HIERARCHY_TYPE?
            if (!StringUtils.isBlank(String.valueOf(parameters.get("customerOrProduct"))) && "Customer Hierachy".equals(String.valueOf(parameters.get("customerOrProduct")))) {
                query = query.replace("?HIERARCHY_TYPE?", "Customer Hierarchy");
            } else {
                query = query.replace("?HIERARCHY_TYPE?", "Product Hierarchy");
            }

//			?HIERARCHY_NAME_CONDITION?
            if (parameters.get("hierarchyName") != null && !"null".equals(String.valueOf(parameters.get("hierarchyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("hierarchyName")))) {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", " AND C.HIERARCHY_NAME like '" + String.valueOf(parameters.get("hierarchyName")) + "' ");
            } else {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", StringUtils.EMPTY);
            }
            //?HIERARCHY_TYPE_CONDITION?
            if (parameters.get("hierarchyType") != null && !"null".equals(String.valueOf(parameters.get("hierarchyType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("hierarchyType")))) {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", " AND C.HIERARCHY_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT where HT.DESCRIPTION LIKE '"
                        + String.valueOf(parameters.get("hierarchyType")) + "') ");
            } else {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", StringUtils.EMPTY);
            }

            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                            filterAppender.append(" AND C.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~hierarchyName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~hierarchyName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~hierarchyName")))) {
                            filterAppender.append(" AND C.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~hierarchyName")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~highestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~highestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~highestLevel")))) {
                            filterAppender.append(" AND A.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~highestLevel")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~lowestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~lowestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~lowestLevel")))) {
                            filterAppender.append(" AND B.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~lowestLevel")));
                            filterAppender.append("' ");
                        }
                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        query = query.replace("?ORDER_BY?", " ORDER BY C.CREATED_DATE ");
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~hierarchyName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~hierarchyName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~hierarchyName")))) {
                            orderByAppender.append(" ORDER BY C.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~hierarchyName")));
                        }

                        if (parameters.get("orderBy~highestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~highestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~highestLevel")))) {
                            orderByAppender.append(" ORDER BY A.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~highestLevel")));
                        }

                        if (parameters.get("orderBy~lowestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~lowestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~lowestLevel")))) {
                            orderByAppender.append(" ORDER BY B.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~lowestLevel")));
                        }

                        if (parameters.get("orderBy~createdDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                            orderByAppender.append(" ORDER BY C.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                        }

                        if (parameters.get("orderBy~modifiedDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                            orderByAppender.append(" ORDER BY C.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }

                } else {
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }

            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
            }
            queryString.append(query);
            queryString.append(";");
        } else if (parameters.get("indicator") != null
                && ("searchGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "searchGroupCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
            queryString.append(prepareSearchGroupQuery(parameters));
        } else if (parameters.get("indicator") != null
                && ("searchView".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "searchViewCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
            queryString.append(prepareSearchViewQuery(parameters));
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            session = openSession();
            Query query;
            if (parameters.get("indicator") != null
                    && ("hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                    || ("getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || ("getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))
                    || (String.valueOf(parameters.get("indicator")).contains("searchView"))
                    || (String.valueOf(parameters.get("indicator")).contains("searchGroup"))
                    || (String.valueOf(parameters.get("indicator")).contains("getHierarchyGroup"))
                    || ("companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))))) {
//                LOGGER.debug("..................qury"+queryString.toString());
                query = session.createSQLQuery(queryString.toString());
            } else {
//                 LOGGER.debug("..................qury"+queryString.toString());
                query = session.createQuery(queryString.toString());
            }
            if (parameters.get("indicator") != null
                    && (("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))))) {
                List<Integer> list = new ArrayList<Integer>();
                int returnValue = query.executeUpdate();
                list.add(returnValue);
                return list;
            } else {
                return query.list();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in execute query");
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }


    public List getCcpMapforchannel(final Map<String, Object> parameters) {

        Session session = null;
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getCcpMap method ");
            session = openSession();
            if (parameters.get("indicator") != null && "getRbId".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                customSql.append(CustomSQLUtil.get("getRbIdFromHier"));
                if (parameters.get("hierarchyDefinitionSid") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyDefinitionSid")));
                }
            } else if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                customSql.append(CustomSQLUtil.get("saveCcpMerge"));

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }
            }
//            LOGGER.debug("\n\n getCcpMap query is -----> " + customSql.toString() + "\n\n");
            if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                Query sqlQuery = session.createSQLQuery(customSql.toString());
                sqlQuery.executeUpdate();
                return null;
            } else {
                Query sqlQuery = session.createSQLQuery(customSql.toString());
                return sqlQuery.list();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in getCcpMap");
            LOGGER.error(customSql.toString());
            return null;
        } finally {
            closeSession(session);
            LOGGER.debug("End of getCcpMap method");
        }
    }

    public void saveCcpforchannel(final Map<String, Object> parameters) {

        Session session = null;
        String hierarchyQuery = StringUtils.EMPTY;
        String levelQuery = StringUtils.EMPTY;
        StringBuilder ccpQueryList = new StringBuilder();
        try {
            LOGGER.debug("Entering saveCcp method ");
            session = openSession();
            if (parameters.get("indicator") != null && "indicator".equalsIgnoreCase(String.valueOf(parameters.get("saveCcp")))) {
                StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
                customSql.append(CustomSQLUtil.get("saveCcp"));

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }

                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }

                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }
                LOGGER.debug("End of saveCcp method");
            } else {
                List list = null;
                try {
                    List<String> relationshipBuilderSids = (List<String>) parameters.get("relationshipBuilderSids");
                    if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
                        for (Object temp : relationshipBuilderSids) {
                            Integer level;
                            hierarchyQuery = CustomSQLUtil.get("getHierarchyMapQuery").replace("?", String.valueOf(temp));
                            levelQuery = "SELECT max(RLD.LEVEL_NO)\n"
                                    + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                                    + "HIERARCHY_LEVEL_DEFINITION HLD \n"
                                    + "where HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                                    + "AND RLD.RELATIONSHIP_BUILDER_SID=" + temp + ";";
                            session = openSession();
                            Query sqlQuery = session.createSQLQuery(hierarchyQuery);
                            Query sqlQuery1 = session.createSQLQuery(levelQuery);
                            list = sqlQuery.list();
                            level = Integer.parseInt(String.valueOf(sqlQuery1.list().get(0)));
                            List<StringBuilder> logic = new ArrayList<StringBuilder>();
                            List<String> condition = new ArrayList<String>();
                            StringBuilder ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                    + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                            Integer prevNo = 0;
                            Integer count = 0;
                            String nextHierarchyNo = StringUtils.EMPTY;
                            for (int i = 0; i < list.size(); i++) {
                                Object[] tempRow = (Object[]) list.get(i);
                                Integer currNo = Integer.parseInt(String.valueOf(tempRow[2]));

                                if (i < list.size() - 1) {
                                    Object[] tempNextRow = (Object[]) list.get(i + 1);
                                    nextHierarchyNo = String.valueOf(tempNextRow[4]);
                                }
                                if (prevNo >= currNo) {
                                    while (currNo <= prevNo) {
                                        if (logic.size() > 0) {
                                            logic.remove(logic.get(logic.size() - 1));
                                        }
                                        if (condition.size() > 0) {
                                            condition.remove(condition.get(condition.size() - 1));
                                        }
                                        prevNo--;
                                    }
                                }
                                if ("Contract Holder".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONT_HOLD_COMPANY_MASTER_SID=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONT_HOLD_COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") CH");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CH.CONTRACT_MASTER_SID");
                                } else if ("Market Type".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONTRACT_TYPE=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_TYPE=J1.HELPER_TABLE_SID");
                                    }
                                    tempLogic.append(") MT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=MT.CONTRACT_MASTER_SID");
                                } else if ("Contract".equals(tempRow[3]) || String.valueOf(tempRow[3]).contains("Individual Owner Code")) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM");
                                    if ("CONTRACT_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("=' ");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_MASTER_SID=J1.CONTRACT_MASTER_SID");
                                    }
                                    tempLogic.append(") CT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CT.CONTRACT_MASTER_SID");
                                } else if (("Trading Partner".equals(tempRow[3]) || String.valueOf(tempRow[3]).contains("Customer") || String.valueOf(tempRow[3]).contains("Wholesaler")) && "Customer Hierarchy".equals(tempRow[7])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.COMPANY_MASTER_SID FROM COMPANY_MASTER CM");
                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") TP");
                                    logic.add(tempLogic);
                                    condition.add("CCP.COMPANY_MASTER_SID=TP.COMPANY_MASTER_SID");
                                } //                                else if ("Company".equals(tempRow[3]) && "Product Hierarchy".equals(tempRow[7])) {
                                //                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CCPIM.ITEM_MASTER_SID, CCPIM.COMPANY_MASTER_SID FROM CCP_DETAILS CCPIM ");
                                //                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                //                                        tempLogic.append(", ");
                                //                                        tempLogic.append(tempRow[5]);
                                //                                        tempLogic.append(" CM WHERE CM.");
                                //                                        tempLogic.append(tempRow[6]);
                                //                                        tempLogic.append("='");
                                //                                        tempLogic.append(tempRow[1]);
                                //                                        tempLogic.append("' AND CCPIM.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID");
                                //                                        condition.add("CCP.COMPANY_MASTER_SID=MAN.COMPANY_MASTER_SID");
                                //                                    } else {
                                //                                        tempLogic.append(", ");
                                //                                        tempLogic.append(tempRow[5]);
                                //                                        tempLogic.append(" J1 WHERE J1.");
                                //                                        tempLogic.append(tempRow[6]);
                                //                                        tempLogic.append("='");
                                //                                        tempLogic.append(tempRow[1]);
                                //                                        tempLogic.append("' AND CCPIM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                //                                    }
                                //                                    tempLogic.append(") MAN");
                                //                                    logic.add(tempLogic);
                                //                                    condition.add("CCP.ITEM_MASTER_SID=MAN.ITEM_MASTER_SID");
                                //                                } 
                                else if ("Brand".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("BRAND_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" BM WHERE BM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=J1.BRAND_MASTER_SID");
                                    }
                                    tempLogic.append(") BRAND");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=BRAND.ITEM_MASTER_SID");
                                } else if ("Therapeutic Class".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.THERAPEUTIC_CLASS=J1.");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append("_SID");
                                    }
                                    tempLogic.append(") TPC");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=TPC.ITEM_MASTER_SID");
                                } else if ("Item".equals(tempRow[3]) || "NDC".equals(tempRow[3]) || "NDC 11".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM");
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("'");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" J1 WHERE J1.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.ITEM_MASTER_SID=J1.ITEM_MASTER_SID");
                                    }
                                    tempLogic.append(") ITEM");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=ITEM.ITEM_MASTER_SID");
                                }
                                prevNo = currNo;
                                if (level == currNo || !nextHierarchyNo.contains(String.valueOf(tempRow[4])) || list.size() == i + 1) {
                                    ccpQuery.replace(ccpQuery.indexOf("?"), ccpQuery.indexOf("?") + 1, String.valueOf(tempRow[0]));
                                    for (int j = logic.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(logic.get(j));
                                    }
                                    ccpQuery.append(" WHERE ");
                                    for (int j = condition.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(condition.get(j));
                                        if (j != 0) {
                                            ccpQuery.append(" AND ");
                                        }
                                    }
                                    ccpQuery.append(") AS SOURCE ON (TARGET.RELATIONSHIP_LEVEL_SID=SOURCE.RELATIONSHIP_LEVEL_SID AND "
                                            + "TARGET.CCP_DETAILS_SID=SOURCE.CCP_DETAILS_SID) WHEN NOT MATCHED THEN INSERT "
                                            + "(RELATIONSHIP_LEVEL_SID, CCP_DETAILS_SID) VALUES ( SOURCE.RELATIONSHIP_LEVEL_SID, SOURCE.CCP_DETAILS_SID);");

                                    ccpQueryList.append(ccpQuery);
                                    count++;
                                    if (count == 10 || list.size() == i + 1) {
                                        session = openSession();
                                        Query insertQuery = session.createSQLQuery(ccpQueryList.toString());
                                        insertQuery.executeUpdate();
                                        ccpQueryList = new StringBuilder();
                                        count = 0;
                                    }
                                    ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                            + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                                }

                            }
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                    LOGGER.error(hierarchyQuery);
                    LOGGER.error(levelQuery);
                    LOGGER.error(ccpQueryList.toString());
                } finally {
                    closeSession(session);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List getCustomerProductGroupforchannel(final Map<String, Object> parameters) {
        Session session = null;
        try {
            LOGGER.debug("Entering getCustomerProductGroup method");
            session = openSession();
            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("indicator") != null && "CustomerGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get("getCustomerGroup"));
                if (parameters.get("customerNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerNo")))) {
                    queryString.append(" AND CG.companyGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerNo")));
                    queryString.append("'");
                }
                if (parameters.get("customerName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("customerName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("customerName")))) {
                    queryString.append(" AND CG.companyGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get("customerName")));
                    queryString.append("'");
                }
                if (parameters.get("companySids") != null) {
                    List<String> companySids = (ArrayList<String>) (parameters.get("companySids"));
                    StringBuilder companiesList = new StringBuilder(StringUtils.EMPTY);
                    if (companySids != null && !companySids.isEmpty()) {
                        for (int loop = 0, limit = companySids.size(); loop < limit; loop++) {
                            companiesList.append("'");
                            companiesList.append(companySids.get(loop));
                            companiesList.append("'");
                            if (loop != (limit - 1)) {
                                companiesList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND CGD.companyMasterSid in ( ");
                    queryString.append(companiesList.toString());
                    queryString.append(" )");
                }

            } else if (parameters.get("indicator") != null && "ProductGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryString.append(CustomSQLUtil.get("getProductGroup"));
                if (parameters.get("productNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productNo")))) {
                    queryString.append(" AND IG.itemGroupNo LIKE '");
                    queryString.append(String.valueOf(parameters.get("productNo")));
                    queryString.append("' ");
                }
                if (parameters.get("productName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("productName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("productName")))) {
                    queryString.append(" AND IG.itemGroupName LIKE '");
                    queryString.append(String.valueOf(parameters.get("productName")));
                    queryString.append("'");
                }

                if (parameters.get("itemSids") != null) {
                    List<String> itemSids = (ArrayList<String>) (parameters.get("itemSids"));
                    StringBuilder itemsList = new StringBuilder(StringUtils.EMPTY);
                    if (itemSids != null && !itemSids.isEmpty()) {
                        for (int loop = 0, limit = itemSids.size(); loop < limit; loop++) {
                            itemsList.append("'");
                            itemsList.append(itemSids.get(loop));
                            itemsList.append("'");
                            if (loop != (limit - 1)) {
                                itemsList.append(", ");
                            }
                        }
                    }
                    queryString.append(" AND IGD.itemMasterSid in ( ");
                    queryString.append(itemsList.toString());
                    queryString.append(" )");
                }
            } else {
                return null;
            }

            Query query = session.createQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getInnerLevelforchannel(Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        if (parameters.get("isNdc") != null && "true".equals(String.valueOf(parameters.get("isNdc")))) {
            queryBuilder.append(CustomSQLUtil.get("getInnerLevelNdcJoin"));
//            for (int i = 0; i < 3; i++) {
//                if (parameters.get("fieldName") != null) {
//                    queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("fieldName")));
//                }
//            }
            if (parameters.get("hierarchyDefinitionSid") != null) {
                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
                queryBuilder.append(String.valueOf(parameters.get("hierarchyDefinitionSid")));
            }

            if (parameters.get("levelName") != null) {
                queryBuilder.append(" and RLD.level_Name = '");
                queryBuilder.append(String.valueOf(parameters.get("levelName")));
                queryBuilder.append("'");
            }

            if (parameters.get("relationshipSid") != null && !StringUtils.isBlank(String.valueOf(parameters.get("relationshipSid")))
                    && !Constants.NULL.equals(String.valueOf(parameters.get("relationshipSid"))) && !"0".equals(String.valueOf(parameters.get("relationshipSid")))) {
                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
                queryBuilder.append(String.valueOf(parameters.get("relationshipSid")));
                queryBuilder.append("'");
            }

            List<Integer> relationshipLevelSidList = (ArrayList<Integer>) parameters.get("relationshipLevelSidList");
            if (relationshipLevelSidList != null && !relationshipLevelSidList.isEmpty()) {
                queryBuilder.append(" and RLD.relationship_Level_Sid not in (");
                for (int loop = 0, limit = relationshipLevelSidList.size(); loop < limit; loop++) {
                    queryBuilder.append("'");
                    queryBuilder.append(relationshipLevelSidList.get(loop));
                    queryBuilder.append("'");
                    if (loop != (limit - 1)) {
                        queryBuilder.append(", ");
                    }
                }
                queryBuilder.append(") ");
            }
        } else {
            List<Integer> relationshipLevelSidList = (ArrayList<Integer>) parameters.get("relationshipLevelSidList");
            StringBuilder queryBuilderNot = new StringBuilder();
            if (relationshipLevelSidList != null && !relationshipLevelSidList.isEmpty()) {
                queryBuilderNot.append(" and RLD1.relationship_Level_Sid not in (");
                for (int loop = 0, limit = relationshipLevelSidList.size(); loop < limit; loop++) {
                    queryBuilderNot.append("'");
                    queryBuilderNot.append(relationshipLevelSidList.get(loop));
                    queryBuilderNot.append("'");
                    if (loop != (limit - 1)) {
                        queryBuilderNot.append(", ");
                    }
                }
                queryBuilderNot.append(") ");
            }
            queryBuilder.append(CustomSQLUtil.get("getInnerLevel"));
            queryBuilder.replace(queryBuilder.indexOf("?ln"), queryBuilder.indexOf("?ln") + 3, String.valueOf(parameters.get("levelNo")));
            queryBuilder.replace(queryBuilder.indexOf("?rbsid"), queryBuilder.indexOf("?rbsid") + 6, String.valueOf(parameters.get("relationshipSid")));
            queryBuilder.replace(queryBuilder.indexOf("?rstype"), queryBuilder.indexOf("?rstype") + 7, !"0".equals(String.valueOf(parameters.get("discount"))) ? String.valueOf(parameters.get("discount")) : "%");
            queryBuilder.replace(queryBuilder.indexOf("?notin"), queryBuilder.indexOf("?notin") + 6, queryBuilderNot.toString());
        }
        try {
            session = openSession();

//            if (parameters.get("hierarchyDefinitionSid") != null) {
//                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
//                queryBuilder.append(String.valueOf(parameters.get("hierarchyDefinitionSid")));
//            }
//
//            if (parameters.get("levelName") != null) {
//                queryBuilder.append(" and RLD.level_Name = '");
//                queryBuilder.append(String.valueOf(parameters.get("levelName")));
//                queryBuilder.append("'");
//            }
//
//            if (parameters.get("relationshipSid") != null && !StringUtils.isBlank(String.valueOf(parameters.get("relationshipSid")))
//                    && !Constants.NULL.equals(String.valueOf(parameters.get("relationshipSid"))) && !"0".equals(String.valueOf(parameters.get("relationshipSid")))) {
//                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
//                queryBuilder.append(String.valueOf(parameters.get("relationshipSid")));
//                queryBuilder.append("'");
//            }
//
//            List<Integer> relationshipLevelSidList = (ArrayList<Integer>) parameters.get("relationshipLevelSidList");
//            if (relationshipLevelSidList != null && !relationshipLevelSidList.isEmpty()) {
//                queryBuilder.append(" and RLD.relationship_Level_Sid not in (select RLDT.relationship_Level_Sid from Relationship_Level_Definition RLDT where RLDT.relationship_Level_Sid in (");
//                for (int loop = 0, limit = relationshipLevelSidList.size(); loop < limit; loop++) {
//                    queryBuilder.append("'");
//                    queryBuilder.append(relationshipLevelSidList.get(loop));
//                    queryBuilder.append("'");
//                    if (loop != (limit - 1)) {
//                        queryBuilder.append(", ");
//                    }
//                }
//                queryBuilder.append(")) ");
//            }
//            LOGGER.debug("getInnerLevel: " + queryBuilder.toString());
            Query query = session.createSQLQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in getInnerLevel()");
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getParentLevelsforchannel(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            session = openSession();
            if (parameters.get("indicator") != null && !StringUtils.isBlank(String.valueOf(parameters.get("indicator")))
                    && !Constants.NULL.equals(String.valueOf(parameters.get("indicator"))) && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                queryBuilder.append(CustomSQLUtil.get("getParentLevelsWithHierarchyNo"));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNos")));
            } else {
                queryBuilder.append(CustomSQLUtil.get("getParentLevels"));
                queryBuilder.append(" RBLD.relationshipLevelSid = '");
                queryBuilder.append(relationshipLevelSid);
                queryBuilder.append("')");
                if (parameters.get("parent") != null) {
                    queryBuilder.append(" AND RLD.relationshipLevelValues = '");
                    queryBuilder.append(String.valueOf(parameters.get("parent")));
                    queryBuilder.append("' ");
                }
                queryBuilder.append(" ORDER by RLD.levelNo desc");
            }
//            LOGGER.debug("Parent level query: " + queryBuilder.toString());
            Query query = session.createQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getChildLevelsforchannel(final Map<String, Object> parameters) {
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            session = openSession();
            queryBuilder.append(CustomSQLUtil.get("getChildLevelsWithHierarchyNo"));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
            queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("lowestLevelNo")));
//            LOGGER.debug("Child level query: " + queryBuilder.toString());
            Query query = session.createSQLQuery(queryBuilder.toString());
            return query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public List getProjectionforchannel(final int projectionId) {
        Session session = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getProjection method");
            session = openSession();
            queryString.append(CustomSQLUtil.get("getProjectionJoin"));
            queryString.append(" where PM.projection_Master_Sid = ");
            queryString.append(projectionId);
//            LOGGER.debug("Final Query: " + queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    public String deleteProjectionforchannel(String tableName, int projectionID) {
        Session session = null;
        tableName = CustomSQLUtil.get("TableNamesFordelete");
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            String TbName[] = tableName.split(",");
            int size = TbName.length;
            for (int i = 0; i < size; i++) {

                sql += "delete from ";
                if (TbName[i] != null && !StringUtils.EMPTY.equals(TbName[i]) && !Constants.NULL.equals(TbName[i])) {
                    sql += TbName[i];
                }
                if (projectionID != 0) {
                    sql += " where PROJECTION_MASTER_SID=" + projectionID + ";";
                }
            }
//            LOGGER.debug("Delete query: " + sql);
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            return "Success";
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(sql);
            return "fail";
        } finally {
            closeSession(session);
        }
    }

    private String prepareSearchGroupQuery(final Map<String, Object> parameters) {
        LOGGER.debug("Entering prepareSearchGroupQuery method");
        String query = StringUtils.EMPTY;
        try {
            String name = null;
            String no = null;
            String desc = null;
            String sids = null;
            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                queryString.append(CustomSQLUtil.get("getCustomerGroupPaged"));
                name = " CG.COMPANY_GROUP_NAME ";
                no = " CG.COMPANY_GROUP_NO ";
                desc = " CG.COMPANY_GROUP_DESCRIPTION ";
                sids = " CGD.COMPANY_MASTER_SID ";
            } else {
                queryString.append(CustomSQLUtil.get("getProductGroupPaged"));
                no = " IG.ITEM_GROUP_NO ";
                name = " IG.ITEM_GROUP_NAME ";
                desc = " IG.ITEM_GROUP_DESCRIPTION ";
                sids = " IGD.ITEM_MASTER_SID ";
            }
            if (parameters.get("no") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("no")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("no")))) {
                queryString.append(" AND ");
                queryString.append(no);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("no")));
                queryString.append("'");
            }
            if (parameters.get("name") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("name")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("name")))) {
                queryString.append(" AND ");
                queryString.append(name);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("name")));
                queryString.append("'");
            }
            if (parameters.get("sids") != null && !"null".equals(String.valueOf(parameters.get("sids")))) {
                queryString.append(" AND ");
                queryString.append(sids);
                queryString.append(" in (");
                queryString.append(String.valueOf(parameters.get("sids")));
                queryString.append(")");
            }

            queryString.append("?FILTER?");
            queryString.append("?ORDER_BY?");

            query = queryString.toString();
            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                        query = query.replace("?SELECTION?", " DISTINCT CG.COMPANY_GROUP_SID, CG.COMPANY_GROUP_NO, CG.COMPANY_GROUP_NAME, CG.VERSION_NO, CG.COMPANY_GROUP_DESCRIPTION ");
                    } else {
                        query = query.replace("?SELECTION?", " DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, CM.COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION ");
                    }
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("filter~customerGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroupName")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(name);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroupName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroupName")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(name);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroupName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~customerGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroupNo")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(no);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroupNo")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroupNo")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(no);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroupNo")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~customergroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customergroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customergroupDescription")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(desc);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customergroupDescription")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productgroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productgroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productgroupDescription")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(desc);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productgroupDescription")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~company")))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~company")));
                            filterAppender.append("' ");
                        }
                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                            query = query.replace("?ORDER_BY?", " ORDER BY CG.COMPANY_GROUP_SID ");
                        } else {
                            query = query.replace("?ORDER_BY?", " ORDER BY IG.ITEM_GROUP_SID ");
                        }
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~customerGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroupName")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroupName")));
                        }
                        if (parameters.get("orderBy~customerGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroupNo")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroupNo")));
                        }
                        if (parameters.get("orderBy~customergroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customergroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customergroupDescription")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customergroupDescription")));
                        }
                        if (parameters.get("orderBy~productGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupName")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroupName")));
                        }
                        if (parameters.get("orderBy~productGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupNo")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroupNo")));
                        }
                        if (parameters.get("orderBy~productgroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productgroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productgroupDescription")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productgroupDescription")));
                        }
                        if (parameters.get("orderBy~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~company")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }
                } else {
                    if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                        query = query.replace("?SELECTION?", " COUNT (DISTINCT CG.COMPANY_GROUP_SID) ");
                    } else {
                        query = query.replace("?SELECTION?", " COUNT (DISTINCT IG.ITEM_GROUP_SID) ");
                    }
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
                LOGGER.error(query);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
            }
        return query;

    }

    private String prepareSearchViewQuery(final Map<String, Object> parameters) {
        String query = StringUtils.EMPTY;
        try {
            StringBuilder queryString = new StringBuilder(CustomSQLUtil.get("findViewByNameJoin"));
            if (parameters.get("viewType") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("viewType")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("viewType")))) {
                queryString.append(" FVM.VIEW_TYPE LIKE '");
                queryString.append(String.valueOf(parameters.get("viewType")));
                queryString.append("' ");

                if (String.valueOf(parameters.get("viewType")).equalsIgnoreCase("private")
                        && parameters.get("userId") != null
                        && !StringUtils.isBlank(String.valueOf(parameters.get("userId")))) {
                    queryString.append(" AND FVM.CREATED_BY = ");
                    queryString.append(String.valueOf(parameters.get("userId")));
                }

            }
            if (parameters.get("viewName") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("viewName")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("viewName")))) {
                queryString.append(" AND FVM.VIEW_NAME LIKE '");
                queryString.append(String.valueOf(parameters.get("viewName")));
                queryString.append("' ");
            }

            if (parameters.get("forecastType") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("forecastType")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("forecastType")))) {
                queryString.append(" AND PM.FORECASTING_TYPE ='");
                queryString.append(String.valueOf(parameters.get("forecastType")));
                queryString.append("' ");
            }

            queryString.append("?FILTER?");
            queryString.append("?ORDER_BY?");
            query = queryString.toString();

            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    query = query.replace("?SELECTION?", CustomSQLUtil.get("searchViewFindSelection"));
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~viewName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~viewName")))) {
                            filterAppender.append(" AND FVM.VIEW_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~viewName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~description")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~description")))) {
                            filterAppender.append(" AND PM.PROJECTION_DESCRIPTION like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~description")));
                            filterAppender.append("' ");
                        }

//                        if (parameters.get("filter~from") != null
//                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~from")))
//                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~from")))) {
//                            filterAppender.append(" AND PM.FROM_DATE like '");
//                            filterAppender.append(String.valueOf(parameters.get("filter~from")));
//                            filterAppender.append("' ");
//                        }
//                        if (parameters.get("filter~to") != null
//                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~to")))
//                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~to")))) {
//                            filterAppender.append(" AND PM.TO_DATE like '");
//                            filterAppender.append(String.valueOf(parameters.get("filter~to")));
//                            filterAppender.append("' ");
//                        }
                        if (parameters.get("filter~customerHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchy")))) {
                            filterAppender.append(" AND CUST_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerHierarchy")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~customerLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerLevel")))) {
                            filterAppender.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerLevel")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~customerGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroup")))) {
                            filterAppender.append(" AND CMG.COMPANY_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroup")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~company")))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~company")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~brandType") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~brandType")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~brandType")))) {
                            filterAppender.append(" AND PM.BRAND_TYPE like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~brandType")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchy")))) {
                            filterAppender.append(" AND PROD_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productHierarchy")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productLevel")))) {
                            filterAppender.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productLevel")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroup")))) {
                            filterAppender.append(" AND IG.ITEM_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroup")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~createdBy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdBy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdBy")))) {
                            filterAppender.append(" AND PM.CREATED_BY in (");
                            filterAppender.append(String.valueOf(parameters.get("filter~createdBy")));
                            filterAppender.append(") ");
                        }

                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        query = query.replace("?ORDER_BY?", " ORDER BY PM.CREATED_DATE ");
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~viewName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~viewName")))) {
                            orderByAppender.append(" ORDER BY FVM.VIEW_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~viewName")));
                        }

                        if (parameters.get("orderBy~description") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~description")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~description")))) {
                            orderByAppender.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~description")));
                        }

                        if (parameters.get("orderBy~from") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~from")))) {
                            orderByAppender.append(" ORDER BY PM.FROM_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~from")));
                        }

                        if (parameters.get("orderBy~to") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~to")))) {
                            orderByAppender.append(" ORDER BY PM.TO_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~to")));
                        }

                        if (parameters.get("orderBy~customerHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchy")))) {
                            orderByAppender.append(" ORDER BY CUST_HD.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerHierarchy")));
                        }

                        if (parameters.get("orderBy~customerLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerLevel")))) {
                            orderByAppender.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerLevel")));
                        }

                        if (parameters.get("orderBy~customerGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroup")))) {
                            orderByAppender.append(" ORDER BY CMG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroup")));
                        }

                        if (parameters.get("orderBy~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~company")));
                        }

                        if (parameters.get("orderBy~brandType") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~brandType")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~brandType")))) {
                            orderByAppender.append(" ORDER BY PM.BRAND_TYPE  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~brandType")));
                        }

                        if (parameters.get("orderBy~productHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchy")))) {
                            orderByAppender.append(" ORDER BY PROD_HD.HIERARCHY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productHierarchy")));
                        }

                        if (parameters.get("orderBy~productLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productLevel")))) {
                            orderByAppender.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productLevel")));
                        }

                        if (parameters.get("orderBy~productGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroup")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroup")));
                        }
                        if (parameters.get("orderBy~createdDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                        }

                        if (parameters.get("orderBy~modifiedDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                            orderByAppender.append(" ORDER BY PM.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                        }
                        if (parameters.get("orderBy~createdBy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdBy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdBy")))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_BY ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdBy")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }
                } else {
                    query = query.replace("?SELECTION?", " COUNT (DISTINCT FVM.VIEW_ID) ");
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
                LOGGER.error(query);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
            }
        return query;
    }

}
