/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.common.CommonQuery;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.List;

/**
 *
 * @author alok.v
 */
public class BaseRateDAOImpl implements BaseRateDAO {


    /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public Object executeSelectQuery(String query) throws SystemException, PortalException, Exception {

        return CompanyMasterLocalServiceUtil.executeSelectQuery(query, null, null);
    }

    /**
     * Get count of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws SystemException
     * @throws Exception
     */
    @Override
    public int getCompaniesCount(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }

    /**
     * Gets list of Companies
     *
     * @param dynamicQuery
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public List<Object[]> getCompanies(DynamicQuery dynamicQuery) throws SystemException, Exception {
        return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public int executeUpdateQuery(String query) throws Exception {

        return CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    /**
     * Sets the workflow for the base rate
     *
     * @param status
     * @param masterId
     * @throws Exception
     */
    public void setWorkflow(String status, int masterId) throws Exception {

    }

    /**
     * Retrieves base rate value for that systemId
     *
     * @param dynamicQuery
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public List<AccClosureMaster> getBRMaster(DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return AccClosureMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    /**
     * update the workflow status for the base rate master Sid
     *
     * @param master
     * @return
     * @throws PortalException
     * @throws SystemException
     * @throws Exception
     */
    public AccClosureMaster updateBRMaster(final AccClosureMaster master) throws PortalException, SystemException, Exception {
        return AccClosureMasterLocalServiceUtil.updateAccClosureMaster(master);
    }

    /**
     * Retrieves helper table Sid for workflow status
     *
     * @param dynamicQuery
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public List<HelperTable> getHelperSid(DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

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
     * Gets the user from userID
     *
     * @param systemId
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public User getUser(Long systemId) throws SystemException, PortalException, Exception {
        return UserLocalServiceUtil.getUser(systemId);
    }    
    
    /**
     * Saves the master records by updating the flag
     * @param sid
     * @throws Exception 
     */
    public void saveLogic(int sid) throws Exception{
       AccClosureMaster master = AccClosureMasterLocalServiceUtil.getAccClosureMaster(sid);
        master.setSaveFlag(true);
        AccClosureMasterLocalServiceUtil.updateAccClosureMaster(master);
        
    }
    
      /**
     *
     * @param workflowMaster
     * @throws Exception
     */
    @Override
    public WorkflowMaster addBRWorkflowMaster(WorkflowMaster workflowMaster, boolean flag) throws Exception {
        if (flag) {
            return WorkflowMasterLocalServiceUtil.addWorkflowMaster(workflowMaster);
        } else {
            return WorkflowMasterLocalServiceUtil.updateWorkflowMaster(workflowMaster);
        }
    }
}
