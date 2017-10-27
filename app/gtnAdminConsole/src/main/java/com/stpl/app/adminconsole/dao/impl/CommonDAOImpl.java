/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author mohamed.hameed
 */
public class CommonDAOImpl implements CommonDAO {

    @Override
    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        
       return ForecastingMasterLocalServiceUtil.executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        return ForecastingMasterLocalServiceUtil.executeBulkUpdateQuery(query, udc1, udc2);
    }


       @Override
    public Object executeSelectQuery(List input, String queryName1, String queryName2) {
        return AccClosureMasterLocalServiceUtil.executeSelectQuery(input, queryName1, queryName2);
    }

        /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public int executeUpdateQuery(String query) {

        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }
    
     /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public Object executeSelectQuery(String query) {

        return CompanyMasterLocalServiceUtil.executeSelectQuery(query, null, null);
    }
      /**
     * This method will retrieve the values from Helper Table based on the
     * listName
     *
     * @param listName
     * @return list of type HelperTable
     * @throws SystemException
     */


    public List<HelperTable> getHelperTableDetailsByListName(String listName) throws SystemException {
       return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }
}
