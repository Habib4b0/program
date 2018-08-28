

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dao;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author vinodhini
 */
public interface CommonResultsDAO {

    public Object executeSelectQuery(String query) throws SystemException;

    public Object executeBulkUpdateQuery(String query) throws SystemException;

    public Object executeUpdateQuery(String query) throws PortalException;
        
    public Object executeUpdateQuery(List<StringBuilder> fcpList) throws SystemException;
}
