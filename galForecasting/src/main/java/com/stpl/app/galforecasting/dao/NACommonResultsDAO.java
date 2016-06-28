/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dao;


import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author vinodhini
 */
public interface NACommonResultsDAO {

    public Object executeSelectQuery(String query) throws PortalException, SystemException;

    public Object executeBulkUpdateQuery(String query) throws PortalException, SystemException;

    public Object executeUpdateQuery(String query) throws SystemException, PortalException, Exception;
        
    public Object executeUpdateQuery(List<StringBuilder> fcpList) throws PortalException, SystemException;
}
