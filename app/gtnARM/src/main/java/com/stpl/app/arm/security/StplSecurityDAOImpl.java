/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.security;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * DAO operations for Security
 *
 * @author shrihariharan
 *
 */
public class StplSecurityDAOImpl implements StplSecurityDAO {

    /**
     * Returns the user details corresponding to the userId
     *
     * @param userId
     * @return User modal object
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public User getUserByUserId(final long userId) throws PortalException {
        return UserLocalServiceUtil.getUser(userId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows
     *
     * @param query - dynamic query of UsergroupBusinessroleMaster
     * @return list of UsergroupBusinessroleMaster
     * @throws SystemException
     */
}
