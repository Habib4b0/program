package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.service.MSupplementalDiscActualsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MSupplementalDiscActualsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MSupplementalDiscActualsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MSupplementalDiscActualsLocalServiceUtil.getService());
        setClass(MSupplementalDiscActuals.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
