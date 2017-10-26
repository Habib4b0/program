package com.stpl.app.service.persistence;

import com.stpl.app.model.VwIvldDemandForecastActual;
import com.stpl.app.service.VwIvldDemandForecastActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwIvldDemandForecastActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwIvldDemandForecastActualActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwIvldDemandForecastActualLocalServiceUtil.getService());
        setClass(VwIvldDemandForecastActual.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldDemandActualForecastSid");
    }
}
