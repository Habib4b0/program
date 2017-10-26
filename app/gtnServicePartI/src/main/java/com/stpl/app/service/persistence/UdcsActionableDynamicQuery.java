package com.stpl.app.service.persistence;

import com.stpl.app.model.Udcs;
import com.stpl.app.service.UdcsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class UdcsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public UdcsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(UdcsLocalServiceUtil.getService());
        setClass(Udcs.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("udcsSid");
    }
}
