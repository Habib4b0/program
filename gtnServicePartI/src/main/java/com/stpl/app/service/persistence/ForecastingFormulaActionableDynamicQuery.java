package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.service.ForecastingFormulaLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ForecastingFormulaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ForecastingFormulaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ForecastingFormulaLocalServiceUtil.getService());
        setClass(ForecastingFormula.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("forecastingFormulaSid");
    }
}
