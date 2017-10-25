/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewMaster;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CustomViewMasterFinderImpl extends BasePersistenceImpl<CustomViewMaster> implements CustomViewMasterFinder{
    private static final Logger LOGGER = Logger.getLogger(CustomViewMasterFinderImpl.class);
    
    
     public List getHierarchyLevelsForDiscount(int projectionId, String hierarchyIndicator,int levelNo,int hierarchyLevelDefId){
        Session session = null;
        String customSql = StringUtils.EMPTY;
            try {
                session = openSession();
                String selectClause1 = "select RELATIONSHIP_LEVEL_SID from ";
                if("C".equals(hierarchyIndicator)){
                    selectClause1+= "PROJECTION_CUST_HIERARCHY";
                }else if("P".equals(hierarchyIndicator)){
                    selectClause1+= "PROJECTION_PROD_HIERARCHY";
                }
                selectClause1+=" where PROJECTION_MASTER_SID="+projectionId;
                String selectClause="select"
                        + " LEVEL_NO, LEVEL_NAME,"
                        + " RELATIONSHIP_LEVEL_VALUES,"
                        + " PARENT_NODE,"
                        + " HIERARCHY_LEVEL_DEFINITION_SID,"
                        + " RELATIONSHIP_BUILDER_SID";
                customSql = selectClause+" from RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_LEVEL_SID in ("+selectClause1+")"
                        +" and HIERARCHY_LEVEL_DEFINITION_SID="+hierarchyLevelDefId
                        +" and LEVEL_NO="+levelNo;
//                LOGGER.debug("-------sql query update----------------------->>>>>"+customSql);
                //LOGGER.debug("hierarchy customSql="+customSql);
                Query sqlQuery = session.createSQLQuery(customSql);
                
                return sqlQuery.list();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(customSql);
                return null;
            } finally {
                closeSession(session);
            }
    }
}

