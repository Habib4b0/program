package com.stpl.app.service.persistence;

import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.app.service.HistWorkflowMasterLocalServiceUtil;

import com.stpl.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * @author
 * @generated
 */
public abstract class HistWorkflowMasterActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public HistWorkflowMasterActionableDynamicQuery() throws SystemException {
        setBaseLocalService(HistWorkflowMasterLocalServiceUtil.getService());
        setClass(HistWorkflowMaster.class);

        setClassLoader(com.stpl.app.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("workflowMasterSid");
    }
}
