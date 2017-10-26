package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionDetailsActionableDynamicQuery() throws SystemException {
        setBaseLocalService(ProjectionDetailsLocalServiceUtil.getService());
        setClass(ProjectionDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionDetailsSid");
    }
}
