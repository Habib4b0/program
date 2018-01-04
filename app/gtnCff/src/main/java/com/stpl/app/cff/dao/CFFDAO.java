/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import java.util.List;

/**
 *
 * @author Manasa
 */
public interface CFFDAO {    

    /**
     * Executes update query
     * 
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */
    public Object executeUpdateQuery(String query) throws SystemException, PortalException;
    
    /**
     * Executes select query
     * 
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception 
     */    
    public Object executeSelectQuery(String query) throws SystemException, PortalException;
    
    /**
     * Gets Helper table list
     * 
     * @param query
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    
    List<HelperTable> getHelperTableList(DynamicQuery query)
			throws PortalException, SystemException;
    
    /**
     * Gets helper table details using list name
     * 
     * @param listName
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    List<HelperTable> getHelperTableDetailsByListName(String listName)
            throws PortalException, SystemException;
    /**
     * Adds the cff master.
     *
     * @param cffMaster the cff master
     * @return the cff master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
     CffMaster addCffMaster(final CffMaster cffMaster)throws SystemException , PortalException;
     /**
     * Gets the cff master.
     *
     * @param cffMasterSid the cff master sid
     * @return the cff master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
     CffMaster getCffMaster(final int cffMasterSid)throws SystemException,PortalException;
     
     /**
     * Update cff master.
     *
     * @param cffMaster the cff master
     * @return the cff master
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
     CffMaster updateCffMaster(final CffMaster cffMaster) throws SystemException , PortalException;
     
     /**
     * Adds the cff details.
     *
     * @param details the details
     * @return the cff details
     * @throws SystemException the system exception
     */
     CffDetails addCffDetails(final CffDetails details)throws SystemException ;
     
     /**
     * Adds the cff approval details.
     *
     * @param approvalDetails the approval details
     * @return the cff approval details
     * @throws SystemException the system exception
     */
     CffApprovalDetails addCffApprovalDetails(final CffApprovalDetails approvalDetails) throws SystemException;
    
     /**
      * To get Cff Details of the particular CFF
      * @param cffSid
      * @return
      * @throws SystemException 
      */
    public List<CffDetails> getCffDetails(final int cffSid) throws SystemException;
    
    /**
     * To get Approval details of the specific CFF
     * @param cffSid
     * @return
     * @throws SystemException 
     */
    public List<CffApprovalDetails> getApprovalDetails(final int cffSid) throws SystemException;
    
    /**
     * To Update Approval Details
     * @param approvalDetails
     * @return
     * @throws SystemException 
     */
    public CffApprovalDetails updateCffApprovalDetails(final CffApprovalDetails approvalDetails) throws SystemException;
    
    /**
     * To update Cff Details
     * @param cffDetails
     * @return
     * @throws SystemException 
     */
    public CffDetails updateCffDetails(final CffDetails cffDetails) throws SystemException;
    
}
