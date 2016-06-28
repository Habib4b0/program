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
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.transaction.Transactional;

/**
 *
 * @author santanukumar
 */
@Transactional
public class BusinessRoleModuleMasterLogicDAOImpl implements BusinessRoleModuleMasterLogicDAO{
     public List<BusinessroleMaster> getBusinessroleMasterList(DynamicQuery query) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.dynamicQuery(query);
    }
     public List getBusinessRoleModuleList(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.findModuleAccessDetails(businessRoleName, moduleName, subModuleName);
    }

    public List getSubModuleProperyList(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.findsubmodulePropertyDetails(businessRoleName, moduleName, subModuleName);
    }

    public List findFieldAccessDetails(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.findFieldAccessDetails(businessRoleName, moduleName, subModuleName);
    }

    public List findSubModuleFieldDetails(String businessRoleName, String subModuleName, String moduleName) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.findSubModuleFieldDetails(businessRoleName, moduleName, subModuleName);
    }

    public List<ModuleSubmoduleMaster> getModuleSubmoduleMaster(DynamicQuery moduleSubmoduleMasterDynamicQuery) throws SystemException {
        return ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery(moduleSubmoduleMasterDynamicQuery);
    }

    public int count() throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMastersCount();
    }

    public List<BusinessroleMaster> getBusinessroleMasters(int startIndex, int endIndex) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMasters(startIndex, endIndex);
    }

    public BusinessroleModule getBusinessroleModuleMaster(int id) throws SystemException {
        return BusinessroleModuleLocalServiceUtil.fetchBusinessroleModule(id);

    }

    public void updateBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster) throws SystemException {
        BusinessroleModuleLocalServiceUtil.updateBusinessroleModule(businessroleModuleMaster);
    }

    public void saveBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster) throws SystemException {
        BusinessroleModuleLocalServiceUtil.addBusinessroleModule(businessroleModuleMaster);
    }

    public List<ModuleSubmoduleMaster> getSubModules(DynamicQuery query) throws SystemException {
        return ModuleSubmoduleMasterLocalServiceUtil.dynamicQuery(query);
    }
}
