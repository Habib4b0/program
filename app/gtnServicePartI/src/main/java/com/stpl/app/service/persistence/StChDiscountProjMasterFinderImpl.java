/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class StChDiscountProjMasterFinderImpl extends BasePersistenceImpl<StChDiscountProjMaster> implements  StChDiscountProjMasterFinder{
   
     public static final Logger LOGGER = Logger.getLogger(StChDiscountProjMasterFinderImpl.class);
     
    public List executeQuery(String query) {
        Session session = null;
              try {
            session = openSession();
            Query queryString = session.createSQLQuery(query);
            return queryString.list();

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(query);
            return null;
        } finally {
            closeSession(session);
        }
    }
    
    
    public int updateQuery(String query) {
        Session session = null;
//        LOGGER.debug("Inside executeBulkUpdateQuery");
        try {
            session = openSession();
            //LOGGER.debug(" update  query "+query);
            Query sqlQuery = session.createSQLQuery(query);
            return sqlQuery.executeUpdate();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
            return 0;
        } finally {
            closeSession(session);
        }
    }
    
    
}
