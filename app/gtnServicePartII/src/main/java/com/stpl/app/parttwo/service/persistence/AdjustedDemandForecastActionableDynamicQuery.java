package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class AdjustedDemandForecastActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public AdjustedDemandForecastActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(AdjustedDemandForecastLocalServiceUtil.getService());
        setClass(AdjustedDemandForecast.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("adjustedDemandForecastSid");
    }
}
