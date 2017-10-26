package com.stpl.app.service.persistence;

import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class UsergroupBusinessroleActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public UsergroupBusinessroleActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(UsergroupBusinessroleLocalServiceUtil.getService());
        setClass(UsergroupBusinessrole.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("usergroupBusinessroleSid");
    }
}
