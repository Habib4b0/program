package com.stpl.app.global.dao.impl;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.global.service.GlobalImpl;
import java.util.List;
/**
 * DAO operations for Security
 * @author shrihariharan
 *
 */
public class StplSecurityDAOImpl implements StplSecurityDAO{

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
         * @Override
	 */public List getBusinessroleModuleMasterTabList(final String businessRoleIds,final String moduleName){
		return new GlobalImpl().getBusinessTabPermission(businessRoleIds, moduleName);
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
	public List getBusinessroleModuleMasterFieldList(final String businessRoleIds,final String moduleName) throws PortalException {
		return new GlobalImpl().getBusinessFieldPermission(businessRoleIds, moduleName);
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
		return new GlobalImpl().getBusinessFunctionPermission(businessRoleIds, moduleName);
	}
	/**
	 * To get permission for accessing Business fields.
	 * 
	 * @param businessRoleId
	 * @param moduleName
	 * @return
	 */
	}
