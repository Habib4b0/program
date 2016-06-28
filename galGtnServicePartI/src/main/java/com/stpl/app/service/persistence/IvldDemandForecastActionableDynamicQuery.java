package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldDemandForecast;
import com.stpl.app.service.IvldDemandForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class IvldDemandForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IvldDemandForecastActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IvldDemandForecastLocalServiceUtil.getService());
        setClass(IvldDemandForecast.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldDemandForecastSid");
    }
}
