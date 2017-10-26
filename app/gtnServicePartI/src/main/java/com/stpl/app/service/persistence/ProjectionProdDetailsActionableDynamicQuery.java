package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionProdDetails;
import com.stpl.app.service.ProjectionProdDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionProdDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionProdDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProjectionProdDetailsLocalServiceUtil.getService());
        setClass(ProjectionProdDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("productDetailsId");
    }
}
