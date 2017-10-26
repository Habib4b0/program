package com.stpl.app.service.persistence;

import com.stpl.app.model.StChActualDiscount;
import com.stpl.app.service.StChActualDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StChActualDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StChActualDiscountActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StChActualDiscountLocalServiceUtil.getService());
        setClass(StChActualDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
