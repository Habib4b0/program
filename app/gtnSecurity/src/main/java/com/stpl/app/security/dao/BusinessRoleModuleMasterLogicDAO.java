/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.BusinessroleModule;
import com.stpl.app.model.ModuleSubmoduleMaster;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 *
 * @author santanukumar
 */
@Transactional
public interface BusinessRoleModuleMasterLogicDAO {
    //for businessroleModuleMaster
    public List<BusinessroleMaster> getBusinessroleMasterList(DynamicQuery query)throws SystemException;
    public List getBusinessRoleModuleList(String businessRoleName,String subModuleName,String moduleName)throws SystemException;
     public List getSubModuleProperyList(String businessRoleName,String subModuleName,String moduleName)throws SystemException;
     public List findFieldAccessDetails(String businessRoleName,String subModuleName,String moduleName)throws SystemException;
     public List findSubModuleFieldDetails(String businessRoleName,String subModuleName,String moduleName)throws SystemException;     
     public List<ModuleSubmoduleMaster>getModuleSubmoduleMaster(DynamicQuery moduleSubmoduleMasterDynamicQuery )throws SystemException;
     public int count()throws SystemException;
     public List<BusinessroleMaster> getBusinessroleMasters(int startIndex,int endIndex)throws SystemException;
     public BusinessroleModule getBusinessroleModuleMaster(int id )throws SystemException;
     public void updateBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster)throws SystemException;
     public void saveBusinessroleModuleMaster(BusinessroleModule businessroleModuleMaster)throws SystemException;
     public List<ModuleSubmoduleMaster>getSubModules(DynamicQuery query)throws SystemException;
}
