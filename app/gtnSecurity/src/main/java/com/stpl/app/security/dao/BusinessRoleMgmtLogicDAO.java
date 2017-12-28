/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public interface BusinessRoleMgmtLogicDAO {
    public List<BusinessroleMaster> getBusinessroleMasterList(DynamicQuery query)throws SystemException;
    public void updateBusinessRoleMgmt(BusinessroleMaster businessroleMaster)throws SystemException;
    public String saveBusinessRoleMgmt(BusinessroleMaster businessroleMaster)throws SystemException;
    public BusinessroleMaster getBusinessRoleUsingId(int businessroleMasterId)throws SystemException;
    
    
}
