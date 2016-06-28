package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;
import com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class VwIvldAdjDemandForeActualActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public VwIvldAdjDemandForeActualActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(VwIvldAdjDemandForeActualLocalServiceUtil.getService());
        setClass(VwIvldAdjDemandForeActual.class);

        setClassLoader(com.stpl.app.parttwo.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ivldAdjustedDemandForecastSid");
    }
}
