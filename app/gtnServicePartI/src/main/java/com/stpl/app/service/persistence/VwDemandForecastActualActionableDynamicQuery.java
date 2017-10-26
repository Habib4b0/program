package com.stpl.app.service.persistence;

import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.service.VwDemandForecastActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwDemandForecastActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwDemandForecastActualActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwDemandForecastActualLocalServiceUtil.getService());
        setClass(VwDemandForecastActual.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("demandForecastActualSid");
    }
}
