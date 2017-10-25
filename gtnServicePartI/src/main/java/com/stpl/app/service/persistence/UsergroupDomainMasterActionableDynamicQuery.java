package com.stpl.app.service.persistence;

import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class UsergroupDomainMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public UsergroupDomainMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(UsergroupDomainMasterLocalServiceUtil.getService());
        setClass(UsergroupDomainMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("usergroupDomainSid");
    }
}
