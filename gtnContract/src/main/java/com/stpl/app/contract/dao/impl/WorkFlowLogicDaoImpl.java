/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dao.impl;

import com.stpl.app.contract.dao.WorkFlowLogicDao;
import com.stpl.app.model.DocDetails;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.DocDetailsLocalServiceUtil;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
    public void addWorkflowMaster(WorkflowMaster workflowMaster) throws SystemException {
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
     * @throws com.stpl.portal.kernel.exception.SystemException
     * @throws com.stpl.portal.kernel.exception.PortalException
     * @throws Exception
     */
    @Override
    public WorkflowMaster getWorkflowMaster(int workflowMasterSystemId) throws SystemException,PortalException {
        return WorkflowMasterLocalServiceUtil.getWorkflowMaster(workflowMasterSystemId);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    @Override
    public List getWorkflowMasterByContractSid(DynamicQuery dynamicQuery) throws SystemException {
        return WorkflowMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     * @throws Exception
     */
    @Override
    public List getMailNotificationMaster(DynamicQuery dynamicQuery) throws SystemException {
        return MailNotificationMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }
    
    
    /**
     * 
     * @param docDetails
     * @return
     * @throws Exception 
     */
    @Override
    public DocDetails addDocDetails(DocDetails docDetails) throws SystemException {
       return DocDetailsLocalServiceUtil.addDocDetails(docDetails);
    }
}