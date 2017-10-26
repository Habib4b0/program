package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualDiscount;
import com.stpl.app.service.ChActualDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChActualDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChActualDiscountActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ChActualDiscountLocalServiceUtil.getService());
        setClass(ChActualDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
