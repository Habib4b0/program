package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwReturnReserve;
import com.stpl.app.parttwo.service.VwReturnReserveLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwReturnReserveActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwReturnReserveActionableDynamicQuery() throws SystemException {
        setBaseLocalService(VwReturnReserveLocalServiceUtil.getService());
        setClass(VwReturnReserve.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("returnReserveSid");
    }
}
