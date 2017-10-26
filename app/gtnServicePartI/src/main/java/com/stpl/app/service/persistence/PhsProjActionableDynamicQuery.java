package com.stpl.app.service.persistence;

import com.stpl.app.model.PhsProj;
import com.stpl.app.service.PhsProjLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class PhsProjActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public PhsProjActionableDynamicQuery() throws SystemException {
        setBaseLocalService(PhsProjLocalServiceUtil.getService());
        setClass(PhsProj.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
