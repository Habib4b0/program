package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class WorkflowMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public WorkflowMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(WorkflowMasterLocalServiceUtil.getService());
        setClass(WorkflowMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("workflowMasterSid");
    }
}
