package com.stpl.app.service.persistence;

import com.stpl.app.model.NmActualDiscount;
import com.stpl.app.service.NmActualDiscountLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmActualDiscountActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmActualDiscountActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NmActualDiscountLocalServiceUtil.getService());
        setClass(NmActualDiscount.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
