/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class HierarchyDefinitionImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(HierarchyDefinitionImpl.class);

    public List getHierarchyGroup(final String HIERARCHY_NAME, final String hierarchyType, final String customerOrProduct) {
        List list = new ArrayList();

        StringBuilder queryBuilder = new StringBuilder();
        try {

            queryBuilder.append("SELECT distinct c.HIERARCHY_DEFINITION_SID,c.HIERARCHY_NAME,a.LEVEL_NAME, a.LEVEL_NO , ");
            queryBuilder.append("b.LEVEL_NO , c.CREATED_DATE, c.MODIFIED_DATE, c.VERSION_NO ");
            queryBuilder.append("from HIERARCHY_LEVEL_DEFINITION a , HIERARCHY_LEVEL_DEFINITION b ,HIERARCHY_DEFINITION c ");
            queryBuilder.append("where a.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID and a.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID ");
            queryBuilder.append("AND c.HIERARCHY_CATEGORY in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.LIST_NAME = 'HIERARCHY_CATEGORY' and ht.DESCRIPTION like '");

            if (Constants.INDICATOR_CUSTOMER_HIERARCHY.equals(customerOrProduct)) {
                queryBuilder.append("Customer Hierarchy");
            } else {
                queryBuilder.append("Product Hierarchy");
            }
            queryBuilder.append("') ");
            queryBuilder.append(" AND b.HIERARCHY_DEFINITION_SID = c.HIERARCHY_DEFINITION_SID ");
            queryBuilder.append(" AND a.LEVEL_NO in (SELECT MAX(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = a.HIERARCHY_DEFINITION_SID) ");
            queryBuilder.append(" AND b.LEVEL_NO in  (SELECT MIN(h.LEVEL_NO) FROM HIERARCHY_LEVEL_DEFINITION h WHERE h.HIERARCHY_DEFINITION_SID = b.HIERARCHY_DEFINITION_SID) ");
            if (HIERARCHY_NAME != null && !"null".equals(HIERARCHY_NAME) && !StringUtils.isEmpty(HIERARCHY_NAME) && !StringUtils.isBlank(HIERARCHY_NAME)) {
                queryBuilder.append(" AND c.HIERARCHY_NAME like '");
                queryBuilder.append(HIERARCHY_NAME);
                queryBuilder.append("' ");
            }
            if (hierarchyType != null && !"null".equals(hierarchyType) && !StringUtils.isEmpty(hierarchyType) && !StringUtils.isBlank(hierarchyType)) {
                queryBuilder.append(" AND c.HIERARCHY_TYPE in (select ht.HELPER_TABLE_SID from HELPER_TABLE ht where ht.DESCRIPTION like '");
                queryBuilder.append(hierarchyType);
                queryBuilder.append("') ");
            }
            list.add(HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString()));
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

            customSql = SQlUtil.getQuery(getClass(),"getLevelsFromHierarchy");
            if (StringUtils.isNotEmpty(String.valueOf(parameters.get(HIERARCHY_ID)))
                    && StringUtils.isNotBlank(String.valueOf(parameters.get(HIERARCHY_ID)))) {
                customSql += String.valueOf(parameters.get(HIERARCHY_ID)).trim();
            }
            customSql += SQlUtil.getQuery(getClass(),"getLevelsFromHierarchy2");
            LOGGER.debug("End of findViewByName method");
            return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        }
    }
    public static final String HIERARCHY_ID = "hierarchyId";
}
