package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldReturnReserve;
import com.stpl.app.parttwo.service.VwIvldReturnReserveLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwIvldReturnReserveActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwIvldReturnReserveActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwIvldReturnReserveLocalServiceUtil.getService());
        setClass(VwIvldReturnReserve.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldReturnReserveSid");
    }
}
