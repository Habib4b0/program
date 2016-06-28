package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class CustomerGtsForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CustomerGtsForecastActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(CustomerGtsForecastLocalServiceUtil.getService());
        setClass(CustomerGtsForecast.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customerGtsForecastSid");
    }
}
