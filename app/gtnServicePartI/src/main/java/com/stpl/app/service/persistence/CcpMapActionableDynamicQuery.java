package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpMap;
import com.stpl.app.service.CcpMapLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CcpMapActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CcpMapActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CcpMapLocalServiceUtil.getService());
        setClass(CcpMap.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ccpMapSid");
    }
}
