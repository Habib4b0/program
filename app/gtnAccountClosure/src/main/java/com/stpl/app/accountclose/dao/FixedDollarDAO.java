/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author alok.v
 */
public interface FixedDollarDAO {
    
    public Object executeSelectQuery(String query) throws SystemException, PortalException, Exception;
     List<HelperTable> getHelperTableList(DynamicQuery dynamicQuery)
            throws SystemException;
    
}
