/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dao;

import com.stpl.app.model.DocDetails;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author Asha.Ravi
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
    void updateWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException;

    /**
     *
     * @param workflowMasterSystemId
     * @return
     * @throws Exception
     */
    WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws SystemException, PortalException;

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
    List getMailNotificationMaster(DynamicQuery dynamicQuery) throws SystemException;

    /**
     *
     * @param docDetails
     * @throws Exception
     */
    DocDetails addDocDetails(DocDetails docDetails) throws SystemException;

}
