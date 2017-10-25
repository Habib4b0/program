package com.stpl.app.service.persistence;

import com.stpl.app.model.StNewNdc;
import com.stpl.app.service.StNewNdcLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNewNdcActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNewNdcActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StNewNdcLocalServiceUtil.getService());
        setClass(StNewNdc.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.naProjDetailsSid");
    }
}
