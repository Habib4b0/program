package com.stpl.app.service.persistence;

import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.service.StFederalNewNdcLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StFederalNewNdcActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StFederalNewNdcActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StFederalNewNdcLocalServiceUtil.getService());
        setClass(StFederalNewNdc.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
