/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import com.stpl.app.model.DeductionGroup;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.model.HelperTable;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.List;

/**
 *
 * @author Nandhakumar
 */
public interface DiscountLogicDAO {
    
       /**
     * Gets the HELPER TABLE list.
     *
     * @param query the query
     * @return the helper table list
     * @throws SystemException the system exception
     */
   List<HelperTable> getHelperTableDetailsByListName(String listName)
			throws SystemException; 
    
      /**
     * Gets the deduction group  list.
     *
     * @param query the query
     * @return the deduction group  list
     * @throws SystemException the system exception
     */
    DeductionGroup addDeductionGroup(DeductionGroup rebate) throws SystemException;
    
       /**
     * Gets the deduction group details list.
     *
     * @param query the query
     * @return the deduction group details list
     * @throws SystemException the system exception
     */
     DeductionGroupDetails addDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException;
     
     
      /**
     * Gets the deduction group details list.
     *
     * @param query the query
     * @return the deduction group details list
     * @throws SystemException the system exception
     */
    List getDeductionGroupDetailsList(DynamicQuery query) throws SystemException;
     
    
     /**
     * Update deduction group details.
     *
     * @param deduction the deduction
     * @return the deduction group details
     * @throws SystemException the system exception
     */
    DeductionGroupDetails updateDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException;
    
        /**
     * Delete deduction group details.
     *
     * @param deduction the deduction
     * @throws SystemException the system exception
     */
    void deleteDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException;
    
    
     /**
     * Gets the deduction group.
     *
     * @param systemId the system id
     * @return the deduction group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    DeductionGroup getDeductionGroup(int systemId) throws PortalException, SystemException;
    
      /**
     * Update deduction group.
     *
     * @param deduction the deduction
     * @return the deduction group
     * @throws SystemException the system exception
     */
    DeductionGroup updateDeductionGroup(DeductionGroup rebate) throws SystemException;
    
      /**
     * Gets the deduction groups details  list.
     *
     * @param query the query
     * @return the deduction groups details  list
     * @throws SystemException the system exception
     */
    List getDeductionGroupsDetailsList(final DynamicQuery query) throws SystemException;
    
    
       /**
     * Gets the deduction list.
     *
     * @param query the query
     * @return the deduction list
     * @throws SystemException the system exception
     */
    List getRebateScheduleList(DynamicQuery query) throws SystemException;
    
      /**
     * get the projection count which is associated with deduction group
     *
     * @param query
     * @return
     * @throws SystemException
     */
    int getProjectionCount(final DynamicQuery query) throws SystemException;
    
    
    
     /**
     * Gets the deduction group details list.
     *
     * @param query the query
     * @return the deduction group details list
     * @throws SystemException the system exception
     */
    List getDeductionDetailsList(DynamicQuery query) throws SystemException;
    
    
     /**
     * Delete deduction group details.
     *
     * @param deduction the deduction
     * @throws SystemException the system exception
     */
    void deleteDeductionDetails(DeductionGroupDetails item) throws SystemException;
    
    
     /**
     * Delete deduction group.
     *
     * @param systemId the system id
     * @return the deduction group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    DeductionGroup deleteDeductionGroup(int systemId) throws PortalException, SystemException;
    
    
}
