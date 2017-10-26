/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.security.dao.StplSecurityDAO;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author sibi
 */
public class StplSecurityDAOImpl implements StplSecurityDAO {

	/**
	 * To get permission for accessing Business Tabs.
	 * 
	 * @param businessRoleId
	 * @param moduleName
	 * @return
	 */
	public List getBuisnessTabPermission(final String businessRoleId, final String moduleName) {
		return BusinessroleModuleLocalServiceUtil.getBusinessTabPermission(businessRoleId, moduleName);
	}

	/**
	 * To get permission for accessing Business Functions.
	 * 
	 * @param businessRoleId
	 * @param moduleName
	 * @return
	 */
	public List getBuisnessFunctionPermission(final String businessRoleId, final String moduleName) {
		return BusinessroleModuleLocalServiceUtil.getBusinessFunctionPermission(businessRoleId, moduleName);
	}

	/**
	 * To get permission for accessing Business fields.
	 * 
	 * @param businessRoleId
	 * @param moduleName
	 * @return
	 */
	public List getBuisnessFieldPermission(final String businessRoleId, final String moduleName) {
		return BusinessroleModuleLocalServiceUtil.getBusinessFieldPermission(businessRoleId, moduleName);
	}
        
	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 * @return
	 * @throws SystemException
	 */
	public List userGroupBuisnessRoleDynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

}
