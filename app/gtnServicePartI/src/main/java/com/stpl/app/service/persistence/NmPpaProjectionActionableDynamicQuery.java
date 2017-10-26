package com.stpl.app.service.persistence;

import com.stpl.app.model.NmPpaProjection;
import com.stpl.app.service.NmPpaProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class NmPpaProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public NmPpaProjectionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(NmPpaProjectionLocalServiceUtil.getService());
        setClass(NmPpaProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
