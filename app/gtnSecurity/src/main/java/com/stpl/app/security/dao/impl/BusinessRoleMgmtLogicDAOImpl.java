/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.security.dao.BusinessRoleMgmtLogicDAO;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 *
 * @author santanukumar
 */
public class BusinessRoleMgmtLogicDAOImpl implements BusinessRoleMgmtLogicDAO {

    @Override
    public List<BusinessroleMaster> getBusinessroleMasterList(DynamicQuery query) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public void updateBusinessRoleMgmt(BusinessroleMaster businessroleMaster) throws SystemException {
        BusinessroleMasterLocalServiceUtil.updateBusinessroleMaster(businessroleMaster);
    }

    @Override
    public String saveBusinessRoleMgmt(BusinessroleMaster businessroleMaster) throws SystemException {
        BusinessroleMasterLocalServiceUtil.addBusinessroleMaster(businessroleMaster);
        return "success";
    }

    @Override
    public BusinessroleMaster getBusinessRoleUsingId(int businessroleMasterId) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.fetchBusinessroleMaster(businessroleMasterId);
    }

   
}
