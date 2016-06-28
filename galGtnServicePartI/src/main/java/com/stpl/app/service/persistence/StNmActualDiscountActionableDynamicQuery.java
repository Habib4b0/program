package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmActualDiscount;
import com.stpl.app.service.StNmActualDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmActualDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmActualDiscountActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StNmActualDiscountLocalServiceUtil.getService());
        setClass(StNmActualDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
