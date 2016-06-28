package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjection;
import com.stpl.app.service.StNmDiscountProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmDiscountProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmDiscountProjectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StNmDiscountProjectionLocalServiceUtil.getService());
        setClass(StNmDiscountProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
