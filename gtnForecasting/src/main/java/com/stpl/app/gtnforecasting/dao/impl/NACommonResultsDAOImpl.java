/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.service.FcpActualsLocalServiceUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;



/**
 *
 * @author vinodhini
 */
public class NACommonResultsDAOImpl implements NACommonResultsDAO {

    public Object executeSelectQuery(String query) throws PortalException, SystemException {
        return FcpActualsLocalServiceUtil.executeSelectQuery(query);
    }

    public Object executeBulkUpdateQuery(String query) throws PortalException, SystemException {
        return FcpActualsLocalServiceUtil.executeBulkUpdateQuery(query);
    }
    public Object executeUpdateQuery(String query) throws SystemException, PortalException{
        return FcpActualsLocalServiceUtil.executeUpdateQuery(query);
    }
       
    public Object executeUpdateQuery(List<StringBuilder> fcpList) throws PortalException, SystemException {
        return FcpActualsLocalServiceUtil.executeUpdateQuery(fcpList);
    }
}
