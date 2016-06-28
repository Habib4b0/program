package com.stpl.app.service.persistence;

import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NetSalesFormulaMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NetSalesFormulaMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NetSalesFormulaMasterLocalServiceUtil.getService());
        setClass(NetSalesFormulaMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("netSalesFormulaMasterSid");
    }
}
