package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ForecastingViewMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ForecastingViewMasterActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ForecastingViewMasterLocalServiceUtil.getService());
        setClass(ForecastingViewMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("viewId");
    }
}
