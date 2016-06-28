package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldForecastSales;
import com.stpl.app.service.IvldForecastSalesLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldForecastSalesActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldForecastSalesActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldForecastSalesLocalServiceUtil.getService());
        setClass(IvldForecastSales.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldForecastSalesSid");
    }
}
