package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ForecastingMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ForecastingMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ForecastingMasterLocalServiceUtil.getService());
        setClass(ForecastingMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("forecastMasterSid");
    }
}
