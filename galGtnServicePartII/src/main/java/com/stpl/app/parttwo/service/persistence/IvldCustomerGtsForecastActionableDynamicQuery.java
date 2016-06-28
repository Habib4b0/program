package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldCustomerGtsForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldCustomerGtsForecastActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(IvldCustomerGtsForecastLocalServiceUtil.getService());
        setClass(IvldCustomerGtsForecast.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldCustomerGtsForecastSid");
    }
}
