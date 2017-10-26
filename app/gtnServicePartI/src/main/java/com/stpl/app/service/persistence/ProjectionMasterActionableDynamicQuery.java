package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProjectionMasterLocalServiceUtil.getService());
        setClass(ProjectionMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionMasterSid");
    }
}
