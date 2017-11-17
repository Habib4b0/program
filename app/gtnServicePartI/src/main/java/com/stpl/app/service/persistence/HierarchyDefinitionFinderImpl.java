/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class HierarchyDefinitionFinderImpl extends BasePersistenceImpl<HierarchyDefinition> implements HierarchyDefinitionFinder {

    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(HierarchyDefinitionFinderImpl.class);

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
    @Override
    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action) {
        List list = new ArrayList();
        Session session = null;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {
            
//            if (Constants.ButtonConstants.BTN_SEARCH.getConstant().equals(action)) {

            queryBuilder.append("SELECT distinct c.hierarchyDefinitionSid,c.hierarchyName,a.levelName, a.levelNo , ");
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

            session = openSession();
//            LOGGER.debug("CUSTOMER/PRODUCT GROUP QUERY: " + queryBuilder.toString());
            Query sqlQuery = session.createQuery(queryBuilder.toString());

            list.add(sqlQuery.list());
//            LOGGER.debug("Query:\n" + queryBuilder.toString() + "\n\nquery hit list size: " + list.size() + "\n\n");
        } catch (Exception ex) {
             LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
        } finally {
            closeSession(session);
        }

        return (List) list.get(0);
    }

    /**
     * Gets the levels from hierarchy and relationship builder
     *
     * @param parameters contains set of parameters
     * @return
     */
    @Override
    public List getLevelsFromHierarchy(final Map<String, Object> parameters) {
        Session session = null;
        String customSql = StringUtils.EMPTY;
        try {
            LOGGER.debug("Entering findViewByName method ");

            session = openSession();
            customSql = CustomSQLUtil.get("getLevelsFromHierarchy");
            if (StringUtils.isNotEmpty(String.valueOf(parameters.get("hierarchyId")))
                    && StringUtils.isNotBlank(String.valueOf(parameters.get("hierarchyId")))) {
                customSql += String.valueOf(parameters.get("hierarchyId")).trim();
            }
            customSql += CustomSQLUtil.get("getLevelsFromHierarchy2");
            Query sqlQuery = session.createSQLQuery(customSql);
//            LOGGER.debug("\n\nSearch query is -----> " + customSql + "\n\n");
            LOGGER.debug("End of findViewByName method");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(customSql);
            return null;
        } finally {
            closeSession(session);
        }
    }
    
        public List getHierarchySystemId(int relationshipLevelId) {
        
             Session session = null;
             String sql = StringUtils.EMPTY;
             try{
                session = openSession();

                sql="select A.HIERARCHY_DEFINITION_SID from HIERARCHY_LEVEL_DEFINITION A, RELATIONSHIP_LEVEL_DEFINITION B where B.HIERARCHY_LEVEL_DEFINITION_SID=A.HIERARCHY_LEVEL_DEFINITION_SID and B.RELATIONSHIP_LEVEL_SID='"+relationshipLevelId+"'";
                Query sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
             }catch(Exception e){
                  LOGGER.error(e.getMessage());
                 LOGGER.error(sql);
             }finally{
                closeSession(session);  
             }
          return null;   
        
     }
    
    
}
