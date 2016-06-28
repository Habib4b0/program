package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldReturns;
import com.stpl.app.service.IvldReturnsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldReturnsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldReturnsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldReturnsLocalServiceUtil.getService());
        setClass(IvldReturns.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldReturnsSid");
    }
}
