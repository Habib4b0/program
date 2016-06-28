package com.stpl.app.galworkflow.dao.impl;

import com.stpl.app.galworkflow.dao.WorkFlowLogicDao;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
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
    public void addWorkflowMaster(WorkflowMaster workflowMaster) throws Exception {
        WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
    }

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    @Override
    public void updateWorkflowMaster(WorkflowMaster workflowMaster) throws Exception {
        WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
    }

    /**
     *
     * @param workflowMasterSystemId
     * @return
     * @throws Exception
     */
    @Override
    public WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws Exception {
        return WorkflowMasterLocalServiceUtil.getWorkflowMaster(workflowMasterSystemId);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    @Override
    public List getWorkflowMasterByProjectionId(DynamicQuery dynamicQuery) throws Exception {
        return WorkflowMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    @Override
    public List getMailNotificationMaster(DynamicQuery dynamicQuery) throws Exception {
        return MailNotificationMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
}
