package com.stpl.app.service.persistence;

import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.service.StChProjectionDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StChProjectionDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StChProjectionDiscountActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StChProjectionDiscountLocalServiceUtil.getService());
        setClass(StChProjectionDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
