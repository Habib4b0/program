/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public interface CommonDAO {
 
    Object executeSelectQuery(String query, Object udc1, Object udc2);
   
    Object executeBulkUpdateQuery(String query, Object udc1, Object udc2);
   
      public Object executeSelectQuery(final List input, String queryName1, String queryName2);

     /* Gets the HELPER TABLE list.
     *
     * @param query the query
     * @return the helper table list
     * @throws SystemException the system exception
     */
   List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException; 
}
