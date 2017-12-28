package com.stpl.app.gtnworkflow.dao.impl;

import com.stpl.app.gtnworkflow.dao.WorkFlowLogicDao;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public class WorkFlowLogicDaoImpl implements WorkFlowLogicDao {

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    @Override
    public void addWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException{
        WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
    }

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    @Override
    public void updateWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException {
        WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
    }

    /**
     *
     * @param workflowMasterSystemId
     * @return
     * @throws Exception
     */
    @Override
    public WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws SystemException,PortalException{
        return WorkflowMasterLocalServiceUtil.getWorkflowMaster(workflowMasterSystemId);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws Exception
     */
    @Override
    public List getWorkflowMasterByProjectionId(DynamicQuery dynamicQuery) throws SystemException{
        return WorkflowMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    @Override
    public List getMailNotificationMaster(DynamicQuery dynamicQuery)  throws SystemException {
        return MailNotificationMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
