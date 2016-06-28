package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCustomerGtsActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCustomerGtsActualActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldCustomerGtsActualLocalServiceUtil.getService());
        setClass(IvldCustomerGtsActual.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCustomerGtsActualSid");
    }
}
