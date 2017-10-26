package com.stpl.app.service.persistence;

import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class BusinessroleMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public BusinessroleMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(BusinessroleMasterLocalServiceUtil.getService());
        setClass(BusinessroleMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("businessroleMasterSid");
    }
}
