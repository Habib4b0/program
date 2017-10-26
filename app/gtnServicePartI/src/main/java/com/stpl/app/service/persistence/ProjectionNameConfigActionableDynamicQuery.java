package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionNameConfigActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionNameConfigActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProjectionNameConfigLocalServiceUtil.getService());
        setClass(ProjectionNameConfig.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionNameConfigSid");
    }
}
