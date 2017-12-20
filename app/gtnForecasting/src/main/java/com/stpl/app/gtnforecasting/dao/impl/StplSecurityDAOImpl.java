package com.stpl.app.gtnforecasting.dao.impl;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.stpl.domain.forecast.security.StplSecurityDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
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
	public User getUserByUserId(final long userId)throws SystemException,PortalException{
		return UserLocalServiceUtil.getUser(userId);
	}
	/**
	 * Performs a dynamic query on the database and returns the matching rows
	 * @param query - dynamic query of UsergroupBusinessroleMaster
	 * @return list of UsergroupBusinessroleMaster
	 * @throws SystemException
	 */
        @Override
	public List<UsergroupBusinessrole> getUsergroupBusinessroleMasterList(final DynamicQuery query)throws PortalException, SystemException{
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
		return BusinessroleModuleLocalServiceUtil.getBusinessTabPermission(businessRoleIds, moduleName);
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
	public List getBusinessroleModuleMasterFieldList(final String businessRoleIds,final String moduleName) throws PortalException, SystemException {
		return BusinessroleModuleLocalServiceUtil.getBusinessFieldPermission(businessRoleIds, moduleName);
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
	public List getBusinessroleModuleMasterFunctionList(final String businessRoleIds,final String moduleName)  throws PortalException, SystemException{
		return BusinessroleModuleLocalServiceUtil.getBusinessFunctionPermission(businessRoleIds, moduleName);
	}
	
}
