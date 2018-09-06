/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import com.stpl.app.adminconsole.dao.DiscountLogicDAO;
import com.stpl.app.model.DeductionGroup;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nandhakumar
 */
public class DiscountLogicDAOImpl implements DiscountLogicDAO{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountLogicDAOImpl.class);
    
     /**
     * This method will retrieve the values from Helper Table based on the
     * listName
     *
     * @param listName
     * @return list of type HelperTable
     * @throws SystemException
     */


    public List<HelperTable> getHelperTableDetailsByListName(String listName) throws SystemException {
       return new ArrayList<>();
    }

    public DeductionGroup addDeductionGroup(DeductionGroup rebate) throws SystemException {
       LOGGER.debug("In query-adddeductionGroup started with P1:DeductionGroup");
		return DeductionGroupLocalServiceUtil.addDeductionGroup(rebate);
    }

    public DeductionGroupDetails addDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException {
       return DeductionGroupDetailsLocalServiceUtil.addDeductionGroupDetails(rebate);
    }

    public List getDeductionGroupDetailsList(DynamicQuery query) throws SystemException {
       return DeductionGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    public DeductionGroupDetails updateDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException {
       LOGGER.debug("In query-updatedeductionGroupDetails started with P1:DeductionGroupDetails deduction");
		return DeductionGroupDetailsLocalServiceUtil.updateDeductionGroupDetails(rebate);
    }

    public void deleteDeductionGroupDetails(DeductionGroupDetails rebate) throws SystemException {
       LOGGER.debug("In query-deletedeductionGroupDetails started with P1:DeductionGroupDetails deduction");
		DeductionGroupDetailsLocalServiceUtil.deleteDeductionGroupDetails(rebate);
    }

    public DeductionGroup getDeductionGroup(int systemId) throws PortalException {
       LOGGER.debug("In query-getdeductionGroup started with P1:int systemId= {}" , systemId);
		return DeductionGroupLocalServiceUtil.getDeductionGroup(systemId);
    }

    public DeductionGroup updateDeductionGroup(DeductionGroup rebate) throws SystemException {
       LOGGER.debug("In query-updatedeductionGroup started with P1:deductionGroup deduction");
		return DeductionGroupLocalServiceUtil.updateDeductionGroup(rebate);
    }

    public List getDeductionGroupsDetailsList(DynamicQuery query) throws SystemException {
    return DeductionGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    public List getRebateScheduleList(DynamicQuery query) throws SystemException {
       return RsModelLocalServiceUtil.dynamicQuery(query);
    }

    public int getProjectionCount(DynamicQuery query) throws SystemException {
        return (int) ProjectionMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    public List getDeductionDetailsList(DynamicQuery query) throws SystemException {
       return DeductionGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    public void deleteDeductionDetails(DeductionGroupDetails item) throws SystemException {
       DeductionGroupDetailsLocalServiceUtil.deleteDeductionGroupDetails(item);
    }

    public DeductionGroup deleteDeductionGroup(int systemId) throws PortalException {
        return DeductionGroupLocalServiceUtil.deleteDeductionGroup(systemId);
    }
    
      
}
