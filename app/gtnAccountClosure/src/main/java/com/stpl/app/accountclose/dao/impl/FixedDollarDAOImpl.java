/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.dao.FixedDollarDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author alok.v
 */
public class FixedDollarDAOImpl implements FixedDollarDAO {

    /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public Object executeSelectQuery(String query) throws SystemException, PortalException, Exception {

        return CompanyMasterLocalServiceUtil.executeSelectQuery(query, null, null);
    }

    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
