package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualSales;
import com.stpl.app.service.ChActualSalesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ChActualSalesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ChActualSalesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ChActualSalesLocalServiceUtil.getService());
        setClass(ChActualSales.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
