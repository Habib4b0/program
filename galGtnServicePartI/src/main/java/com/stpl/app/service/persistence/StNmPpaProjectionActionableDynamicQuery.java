package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmPpaProjection;
import com.stpl.app.service.StNmPpaProjectionLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class StNmPpaProjectionActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public StNmPpaProjectionActionableDynamicQuery() throws SystemException {
        setBaseLocalService(StNmPpaProjectionLocalServiceUtil.getService());
        setClass(StNmPpaProjection.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("primaryKey.periodSid");
    }
}
