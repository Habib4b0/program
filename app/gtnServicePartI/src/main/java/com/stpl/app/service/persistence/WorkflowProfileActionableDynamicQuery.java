package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class WorkflowProfileActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public WorkflowProfileActionableDynamicQuery() throws SystemException {
        setBaseLocalService(WorkflowProfileLocalServiceUtil.getService());
        setClass(WorkflowProfile.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("processSid");
    }
}
