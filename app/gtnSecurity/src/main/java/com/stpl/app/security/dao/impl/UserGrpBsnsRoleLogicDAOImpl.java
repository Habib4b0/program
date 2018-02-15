/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao.impl;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.security.dao.UserGrpBsnsRoleLogicDAO;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;

/**
 *
 * @author santanukumar
 */
public class UserGrpBsnsRoleLogicDAOImpl implements UserGrpBsnsRoleLogicDAO{

    @Override
    public List<UsergroupBusinessrole> getBsnsRoles(DynamicQuery query) throws SystemException {
        return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<BusinessroleMaster> getBusinessroleMaster(DynamicQuery query) throws SystemException {
      return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<UserGroup> getUserGroups(DynamicQuery query) throws SystemException {
       return UserGroupLocalServiceUtil.dynamicQuery(query);
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
    public void deleteUsergroupBusinessroleMaster(UsergroupBusinessrole usergroupBusinessroleMaster ) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.deleteUsergroupBusinessrole(usergroupBusinessroleMaster);
    }

    @Override
    public void updateUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.updateUsergroupBusinessrole(usergroupBusinessroleMaster);
    }

    @Override
    public void saveUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.addUsergroupBusinessrole(usergroupBusinessroleMaster);
    }
}
