/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class HierarchyDefinitionImpl {

    private static final Logger LOGGER = Logger.getLogger(HierarchyDefinitionImpl.class);

    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action) {
        List list = new ArrayList();

        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {

//            if (Constants.ButtonConstants.BTN_SEARCH.getConstant().equals(action)) {
            queryBuilder.append("SELECT c.hierarchyDefinitionSid,c.hierarchyName,a.levelName, a.levelNo , ");
            queryBuilder.append("b.levelNo , c.createdDate, c.modifiedDate, c.versionNo ");
            queryBuilder.append("from HierarchyLevelDefinition a , HierarchyLevelDefinition b ,HierarchyDefinition c ");
            queryBuilder.append("where a.hierarchyDefinitionSid = b.hierarchyDefinitionSid and a.hierarchyDefinitionSid = c.hierarchyDefinitionSid ");
            queryBuilder.append("AND c.hierarchyCategory in (select ht.helperTableSid from HelperTable ht where ht.listName = 'HIERARCHY_CATEGORY' and ht.description like '");

            if (Constants.INDICATOR_CUSTOMER_HIERARCHY.equals(customerOrProduct)) {
                queryBuilder.append("Customer Hierarchy");
            } else {
                queryBuilder.append("Product Hierarchy");
            }
            queryBuilder.append("') ");
            queryBuilder.append(" AND b.hierarchyDefinitionSid = c.hierarchyDefinitionSid ");
            queryBuilder.append(" AND a.levelNo in (SELECT MAX(h.levelNo) FROM HierarchyLevelDefinition h WHERE h.hierarchyDefinitionSid = a.hierarchyDefinitionSid) ");
            queryBuilder.append(" AND b.levelNo in  (SELECT MIN(h.levelNo) FROM HierarchyLevelDefinition h WHERE h.hierarchyDefinitionSid = b.hierarchyDefinitionSid) ");
            if (hierarchyName != null && !"null".equals(hierarchyName) && !StringUtils.isEmpty(hierarchyName) && !StringUtils.isBlank(hierarchyName)) {
                queryBuilder.append(" AND c.hierarchyName like '");
                queryBuilder.append(hierarchyName);
                queryBuilder.append("' ");
            }
            if (hierarchyType != null && !"null".equals(hierarchyType) && !StringUtils.isEmpty(hierarchyType) && !StringUtils.isBlank(hierarchyType)) {
                queryBuilder.append(" AND c.hierarchyType in (select ht.helperTableSid from HelperTable ht where ht.description like '");
                queryBuilder.append(hierarchyType);
                queryBuilder.append("') ");
            }
//            LOGGER.debug("CUSTOMER/PRODUCT GROUP QUERY: " + queryBuilder.toString());
            list.add(HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString()));
//            LOGGER.debug("Query:\n" + queryBuilder.toString() + "\n\nquery hit list size: " + list.size() + "\n\n");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
        }

        return (List) list.get(0);
    }

    public List getLevelsFromHierarchy(final Map<String, Object> parameters) {

        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.debug("Entering findViewByName method ");

            customSql = CustomSQLUtil.get("getLevelsFromHierarchy");
            if (StringUtils.isNotEmpty(String.valueOf(parameters.get("hierarchyId")))
                    && StringUtils.isNotBlank(String.valueOf(parameters.get("hierarchyId")))) {
                customSql += String.valueOf(parameters.get("hierarchyId")).trim();
            }
            customSql += CustomSQLUtil.get("getLevelsFromHierarchy2");
//            LOGGER.debug("\n\nSearch query is -----> " + customSql + "\n\n");
            LOGGER.debug("End of findViewByName method");
            return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        }
    }
}
