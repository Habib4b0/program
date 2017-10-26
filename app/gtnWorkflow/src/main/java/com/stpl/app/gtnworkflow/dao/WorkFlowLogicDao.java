package com.stpl.app.gtnworkflow.dao;

import com.stpl.app.model.WorkflowMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
    void addWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException;

    /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    void updateWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException ;

    /**
     *
     * @param workflowMasterSystemId
     * @return
     * @throws Exception
     */
    WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws SystemException,PortalException;

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    List getWorkflowMasterByProjectionId(DynamicQuery dynamicQuery) throws SystemException;

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    List getMailNotificationMaster(DynamicQuery dynamicQuery) throws SystemException ;
}
