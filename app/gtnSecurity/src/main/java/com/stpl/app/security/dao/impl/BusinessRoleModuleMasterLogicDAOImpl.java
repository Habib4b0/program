/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.stpl.app.security.dao.BusinessRoleModuleMasterLogicDAO;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.stpl.app.security.service.SecurityImpl;

/**
 *
 * @author santanukumar
 */
@Transactional
public class BusinessRoleModuleMasterLogicDAOImpl implements BusinessRoleModuleMasterLogicDAO{
     @Override
     public List<BusinessroleMaster> getBusinessroleMasterList(DynamicQuery query) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.dynamicQuery(query);
    }
     @Override
     public List getBusinessRoleModuleList(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return new SecurityImpl().findModuleAccessDetails(businessRoleName, moduleName, subModuleName);
    }

     @Override
    public List getSubModuleProperyList(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return new SecurityImpl().findsubmodulePropertyDetails(businessRoleName, moduleName, subModuleName);
    }

     @Override
    public List findFieldAccessDetails(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return new SecurityImpl().findFieldAccessDetails(businessRoleName, moduleName, subModuleName);
    }

     @Override
    public List findSubModuleFieldDetails(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return new SecurityImpl().findSubModuleFieldDetails(businessRoleName, moduleName, subModuleName);
    }

     @Override
    public List<ModuleSubmoduleMaster> getModuleSubmoduleMaster(DynamicQuery moduleSubmoduleMasterDynamicQuery) throws SystemException {
        return ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery(moduleSubmoduleMasterDynamicQuery);
    }

     @Override
    public int count() throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMastersCount();
    }

     @Override
    public List<BusinessroleMaster> getBusinessroleMasters(int startIndex, int endIndex) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMasters(startIndex, endIndex);
    }

     @Override
    public BusinessroleModule getBusinessroleModuleMaster(int id) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.fetchBusinessroleModule(id);

    }

     @Override
    public void updateBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster) throws SystemException {
        BusinessroleModuleLocalServiceUtil.updateBusinessroleModule(businessroleModuleMaster);
    }

     @Override
    public void saveBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster) throws SystemException {
        BusinessroleModuleLocalServiceUtil.addBusinessroleModule(businessroleModuleMaster);
    }

     @Override
    public List<ModuleSubmoduleMaster> getSubModules(DynamicQuery query) throws SystemException {
        return ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery(query);
    }
}
