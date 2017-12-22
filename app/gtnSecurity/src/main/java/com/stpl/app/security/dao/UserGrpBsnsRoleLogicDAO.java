/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.dao;

import java.util.List;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.UsergroupBusinessrole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.UserGroup;

/**
 *
 * @author santanukumar
 */
public interface UserGrpBsnsRoleLogicDAO {
    public List<UsergroupBusinessrole>getBsnsRoles(DynamicQuery query)throws SystemException;
    public List<BusinessroleMaster>getBusinessroleMaster(DynamicQuery query)throws SystemException;
    public List<UserGroup>getUserGroups(DynamicQuery query)throws SystemException;
     public int count()throws SystemException;
     public List<BusinessroleMaster> getBusinessroleMasters(int startIndex,int endIndex)throws SystemException;
     public void deleteUsergroupBusinessroleMaster(UsergroupBusinessrole usergroupBusinessroleMaster)throws SystemException;
     public void  updateUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster)throws SystemException;
       public  void saveUserGroup(UsergroupBusinessrole usergroupBusinessroleMaster)throws SystemException;
}
