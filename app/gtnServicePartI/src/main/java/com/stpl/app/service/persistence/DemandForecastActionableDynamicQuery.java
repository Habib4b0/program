package com.stpl.app.service.persistence;

import com.stpl.app.model.DemandForecast;
import com.stpl.app.service.DemandForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class DemandForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DemandForecastActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DemandForecastLocalServiceUtil.getService());
        setClass(DemandForecast.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("demandForecastSid");
    }
}
