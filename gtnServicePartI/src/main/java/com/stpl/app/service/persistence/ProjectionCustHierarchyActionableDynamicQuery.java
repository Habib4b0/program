package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionCustHierarchyActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionCustHierarchyActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProjectionCustHierarchyLocalServiceUtil.getService());
        setClass(ProjectionCustHierarchy.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("projectionCustHierarchySid");
    }
}
