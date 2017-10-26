package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptionsProj;
import com.stpl.app.service.NationalAssumptionsProjLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NationalAssumptionsProjActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NationalAssumptionsProjActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(NationalAssumptionsProjLocalServiceUtil.getService());
        setClass(NationalAssumptionsProj.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
