package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProcessInfo;
import com.stpl.app.service.WorkflowProcessInfoLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class WorkflowProcessInfoActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public WorkflowProcessInfoActionableDynamicQuery()
        throws SystemException {
        setBaseLocalService(WorkflowProcessInfoLocalServiceUtil.getService());
        setClass(WorkflowProcessInfo.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("workflowProcessInfoSid");
    }
}
