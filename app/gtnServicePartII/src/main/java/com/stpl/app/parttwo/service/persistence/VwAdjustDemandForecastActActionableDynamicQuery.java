package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;
import com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwAdjustDemandForecastActActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwAdjustDemandForecastActActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwAdjustDemandForecastActLocalServiceUtil.getService());
        setClass(VwAdjustDemandForecastAct.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("adjustedDemandForecastId");
    }
}
