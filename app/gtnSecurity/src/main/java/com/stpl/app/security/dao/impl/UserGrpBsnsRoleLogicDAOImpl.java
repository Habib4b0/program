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
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.UserGroup;
import com.stpl.portal.service.UserGroupLocalServiceUtil;

/**
 *
 * @author santanukumar
 */
public class UserGrpBsnsRoleLogicDAOImpl implements UserGrpBsnsRoleLogicDAO{

    public List<UsergroupBusinessrole> getBsnsRoles(DynamicQuery query) throws SystemException {
        return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    public List<BusinessroleMaster> getBusinessroleMaster(DynamicQuery query) throws SystemException {
      return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    public List<UserGroup> getUserGroups(DynamicQuery query) throws SystemException {
       return UserGroupLocalServiceUtil.dynamicQuery(query);
    }
     public int count() throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMastersCount();
    }
      public List<BusinessroleMaster> getBusinessroleMasters(int startIndex, int endIndex) throws SystemException {
        return BusinessroleMasterLocalServiceUtil.getBusinessroleMasters(startIndex, endIndex);
    }

    public void deleteUsergroupBusinessroleMaster(UsergroupBusinessrole usergroupBusinessroleMaster ) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.deleteUsergroupBusinessrole(usergroupBusinessroleMaster);
    }

    public void updateUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.updateUsergroupBusinessrole(usergroupBusinessroleMaster);
    }

    public void saveUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster) throws SystemException {
        UsergroupBusinessroleLocalServiceUtil.addUsergroupBusinessrole(usergroupBusinessroleMaster);
    }
}
