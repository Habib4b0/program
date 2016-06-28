/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dao;

import com.stpl.app.model.DocDetails;
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
    
    /**
     *
     * @param docDetails
     * @throws Exception
     */
    DocDetails addDocDetails(DocDetails docDetails) throws Exception;
}
