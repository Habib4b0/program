/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.transactional.dao.impl;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.domain.transaction.security.STPLSecurityDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.List;

/**
 * The Class STPLSecurityDAOImpl.
 */
public class STPLSecurityDAOImpl implements STPLSecurityDAO {

	/**
	 * To get the User by user Id.
	 *
	 * @param userId
	 *            the user id
	 * @return the user
	 * @throws SystemException
	 *             the system exception
	 * @throws PortalException
	 *             the portal exception
	 */
	public User getUser(final long userId) throws SystemException, PortalException {
		return UserLocalServiceUtil.getUser(userId);
	}

	/**
	 * To get the UsergroupBusinessrole based on dynamic query.
	 *
	 * @param dynamicQuery
	 *            the dynamic query
	 * @return the list
	 * @throws SystemException
	 *             the system exception
	 */
	public List<UsergroupBusinessrole> dynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * To get the business tab permission.
	 *
	 * @param businessRoleIds
	 *            the business role ids
	 * @param moduleName
	 *            the module name
	 * @return the business tab permission
	 * @throws SystemException
	 *             the system exception
	 */
	public List getBusinessTabPermission(final String businessRoleIds, final String moduleName) throws SystemException {
		return BusinessroleModuleLocalServiceUtil.getBusinessTabPermission(businessRoleIds, moduleName);
	}

	/**
	 * To get the business function permission.
	 *
	 * @param businessRoleIds
	 *            the business role ids
	 * @param moduleName
	 *            the module name
	 * @return the business function permission
	 * @throws SystemException
	 *             the system exception
	 */
	public List getBusinessFunctionPermission(final String businessRoleIds, final String moduleName) throws SystemException {
		return BusinessroleModuleLocalServiceUtil.getBusinessFunctionPermission(businessRoleIds, moduleName);
	}

	/**
	 * To get the business field permission.
	 *
	 * @param businessRoleIds
	 *            the business role ids
	 * @param moduleName
	 *            the module name
	 * @return the business field permission
	 * @throws SystemException
	 *             the system exception
	 */
	public List getBusinessFieldPermission(final String businessRoleIds, final String moduleName) throws SystemException {
		return BusinessroleModuleLocalServiceUtil.getBusinessFieldPermission(businessRoleIds, moduleName);
	}

}
