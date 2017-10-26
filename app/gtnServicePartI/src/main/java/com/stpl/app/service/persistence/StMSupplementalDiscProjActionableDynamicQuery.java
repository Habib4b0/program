package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.service.StMSupplementalDiscProjLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StMSupplementalDiscProjActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StMSupplementalDiscProjActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(StMSupplementalDiscProjLocalServiceUtil.getService());
        setClass(StMSupplementalDiscProj.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.userId");
    }
}
