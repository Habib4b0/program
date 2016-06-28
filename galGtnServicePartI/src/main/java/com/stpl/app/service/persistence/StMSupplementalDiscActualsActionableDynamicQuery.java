package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscActuals;
import com.stpl.app.service.StMSupplementalDiscActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StMSupplementalDiscActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StMSupplementalDiscActualsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StMSupplementalDiscActualsLocalServiceUtil.getService());
        setClass(StMSupplementalDiscActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
