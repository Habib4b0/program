package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustDetails;
import com.stpl.app.service.ProjectionCustDetailsLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class ProjectionCustDetailsActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public ProjectionCustDetailsActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(ProjectionCustDetailsLocalServiceUtil.getService());
        setClass(ProjectionCustDetails.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("customerDetailsId");
    }
}
