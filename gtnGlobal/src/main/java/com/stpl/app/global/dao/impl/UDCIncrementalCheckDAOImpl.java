/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.domain.global.udccheck.UDCIncrementalCheckDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/** 
 * DAO operations for UDCIncrementalCheck
 * @author shrihariharan
 */
public class UDCIncrementalCheckDAOImpl implements UDCIncrementalCheckDAO{
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query -dynamic query of HelperTable 
     * @return list of HelperTable
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * Updates the helper table in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners
     * @param helperTable
     * @return HelperTable modal object
     * @throws SystemException 
     */
    public HelperTable updateHelperTable(final HelperTable helperTable) throws SystemException {
        return HelperTableLocalServiceUtil.updateHelperTable(helperTable);
    }
    
    
    
}
