package com.stpl.app.service.persistence;

import com.stpl.app.model.MSupplementalDiscProj;
import com.stpl.app.service.MSupplementalDiscProjLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class MSupplementalDiscProjActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public MSupplementalDiscProjActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(MSupplementalDiscProjLocalServiceUtil.getService());
        setClass(MSupplementalDiscProj.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
