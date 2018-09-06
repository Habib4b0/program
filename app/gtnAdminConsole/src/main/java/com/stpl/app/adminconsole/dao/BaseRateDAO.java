/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;


import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
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
    public Object executeSelectQuery(String query) throws PortalException;

    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException;

    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException;

    public int executeUpdateQuery(String query);

    public void setWorkflow(String status, int masterId);
   
    User getUser(Long systemId) throws PortalException;
}
