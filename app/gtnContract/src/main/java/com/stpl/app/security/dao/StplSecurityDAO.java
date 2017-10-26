/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author sibi
 */
public interface StplSecurityDAO {

    /**
     * To get permission for accessing Business Tabs.
     *
     * @param businessRoleId
     * @param moduleName
     * @return
     */
    List getBuisnessTabPermission(String businessRoleId, String moduleName);

    /**
     * To get permission for accessing Business Functions.
     *
     * @param businessRoleId
     * @param moduleName
     * @return
     */
    List getBuisnessFunctionPermission(String businessRoleId, String moduleName);

    /**
     * To get permission for accessing Business fields.
     *
     * @param businessRoleId
     * @param moduleName
     * @return
     */
    List getBuisnessFieldPermission(String businessRoleId, String moduleName);

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     */
    List userGroupBuisnessRoleDynamicQuery(DynamicQuery dynamicQuery) throws SystemException;
}
