package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwCustomerGtsForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwCustomerGtsForecastActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwCustomerGtsForecastLocalServiceUtil.getService());
        setClass(VwCustomerGtsForecast.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customerGtsForecastSid");
    }
}
