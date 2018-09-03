/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.security;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.security.service.SecurityImpl;
import java.util.List;

/**
 *DAO operations for Security
 * @author nimisha.rakesh
 */
public class StplSecurityDAOImpl implements StplSecurityDAO {
	/**
	 * Returns the user details corresponding to the userId
	 * @param userId
	 * @return User modal object
	 * @throws SystemException
	 * @throws PortalException
	 */
    @Override
	public User getUserByUserId(final long userId)throws PortalException{
		return UserLocalServiceUtil.getUser(userId);
	}
	/**
	 * Performs a dynamic query on the database and returns the matching rows
	 * @param query - dynamic query of UsergroupBusinessroleMaster
	 * @return list of UsergroupBusinessroleMaster
	 * @throws SystemException
	 */
    @Override
	public List<UsergroupBusinessrole> getUsergroupBusinessroleMasterList(final DynamicQuery query)throws PortalException{
		return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
	}
	
	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * @param query - dynamic query of UsergroupDomainMaster
	 * @return list of UsergroupDomainMaster
	 * @throws SystemException
	 */
    @Override
	public List<UsergroupDomainMaster> getUsergroupDomainMasterList(final DynamicQuery query)throws SystemException{
		return UsergroupDomainMasterLocalServiceUtil.dynamicQuery(query);
	}

	/**
	 * Returns the list of BusinessroleModuleMaster
	 * @param businessRoleIds
	 * @param moduleName
	 * @return list of BusinessroleModuleMaster
	 * @throws SystemException
	 * @throws PortalException
	 */@Override
    public List getBusinessroleModuleMasterTabList(final String businessRoleIds,final String moduleName){
		return new SecurityImpl().getBusinessTabPermission(businessRoleIds, moduleName);
	}
	
	/**
	 * Returns the list of BusinessroleModuleMaster
	 * @param businessRoleIds
	 * @param moduleName
	 * @return list of BusinessroleModuleMaster
	 * @throws SystemException
	 * @throws PortalException
	 */
    @Override
	public List getBusinessroleModuleMasterFieldList(final String businessRoleIds,final String moduleName) throws PortalException{
		return new SecurityImpl().getBusinessFieldPermission(businessRoleIds, moduleName);
	}
	
	/**
	 * Returns the list of BusinessroleModuleMaster
	 * @param businessRoleIds
	 * @param moduleName
	 * @return list of BusinessroleModuleMaster
	 * @throws SystemException
	 * @throws PortalException
	 */
    @Override
	public List getBusinessroleModuleMasterFunctionList(final String businessRoleIds,final String moduleName)  throws PortalException{
		return new SecurityImpl().getBusinessFunctionPermission(businessRoleIds, moduleName);
	}
	
}


