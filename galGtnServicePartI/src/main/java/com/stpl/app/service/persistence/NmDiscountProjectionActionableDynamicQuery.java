package com.stpl.app.service.persistence;

import com.stpl.app.model.NmDiscountProjection;
import com.stpl.app.service.NmDiscountProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmDiscountProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmDiscountProjectionActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NmDiscountProjectionLocalServiceUtil.getService());
        setClass(NmDiscountProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
