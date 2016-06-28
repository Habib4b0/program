package com.stpl.app.galworkflow.dao;

import com.stpl.app.model.WorkflowMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import java.util.List;

/**
 *
 * @author santanukumar
 */
public interface WorkFlowLogicDao {

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    void addWorkflowMaster(WorkflowMaster workflowMaster) throws Exception;

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    void updateWorkflowMaster(WorkflowMaster workflowMaster) throws Exception;

    /**
     *
     * @param workflowMasterSystemId
     * @return
     * @throws Exception
     */
    WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws Exception;

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    List getWorkflowMasterByProjectionId(DynamicQuery dynamicQuery) throws Exception;

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    List getMailNotificationMaster(DynamicQuery dynamicQuery) throws Exception;
}
