package com.stpl.app.service.persistence;

import com.stpl.app.model.ChSalesProjection;
import com.stpl.app.service.ChSalesProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChSalesProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChSalesProjectionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ChSalesProjectionLocalServiceUtil.getService());
        setClass(ChSalesProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
