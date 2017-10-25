package com.stpl.app.service.persistence;

import com.stpl.app.model.ChProjectionDiscount;
import com.stpl.app.service.ChProjectionDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChProjectionDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChProjectionDiscountActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ChProjectionDiscountLocalServiceUtil.getService());
        setClass(ChProjectionDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.projectionDetailsSid");
    }
}
