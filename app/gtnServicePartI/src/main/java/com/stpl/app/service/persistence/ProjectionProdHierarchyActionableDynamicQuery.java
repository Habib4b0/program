package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionProdHierarchyActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionProdHierarchyActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProjectionProdHierarchyLocalServiceUtil.getService());
        setClass(ProjectionProdHierarchy.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionProdHierarchySid");
    }
}
