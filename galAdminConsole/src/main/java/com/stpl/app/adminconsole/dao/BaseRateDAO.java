/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;


import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import java.util.List;

/**
 *
 * @author alok.v
 */
public interface BaseRateDAO {

    /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public Object executeSelectQuery(String query) throws SystemException, PortalException, Exception;

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception;

    public int executeUpdateQuery(String query) throws Exception;

    public void setWorkflow(String status, int masterId) throws Exception;
   
    User getUser(Long systemId) throws SystemException, PortalException, Exception;
}
