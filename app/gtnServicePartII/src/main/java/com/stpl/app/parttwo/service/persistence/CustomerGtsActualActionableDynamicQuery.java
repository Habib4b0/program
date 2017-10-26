package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.service.CustomerGtsActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CustomerGtsActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CustomerGtsActualActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CustomerGtsActualLocalServiceUtil.getService());
        setClass(CustomerGtsActual.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customerGtsActualSid");
    }
}
