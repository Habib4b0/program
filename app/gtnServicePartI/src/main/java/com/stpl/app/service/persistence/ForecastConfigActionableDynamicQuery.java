package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ForecastConfigActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ForecastConfigActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ForecastConfigLocalServiceUtil.getService());
        setClass(ForecastConfig.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("forecastConfigSid");
    }
}
