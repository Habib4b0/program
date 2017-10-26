package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjection;
import com.stpl.app.service.NmSalesProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmSalesProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmSalesProjectionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NmSalesProjectionLocalServiceUtil.getService());
        setClass(NmSalesProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
