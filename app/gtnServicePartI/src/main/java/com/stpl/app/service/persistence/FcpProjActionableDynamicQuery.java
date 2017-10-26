package com.stpl.app.service.persistence;

import com.stpl.app.model.FcpProj;
import com.stpl.app.service.FcpProjLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class FcpProjActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public FcpProjActionableDynamicQuery() throws SystemException {
        setBaseLocalService(FcpProjLocalServiceUtil.getService());
        setClass(FcpProj.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
