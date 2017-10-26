/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.ItemFamilyplanDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;

/**
 * DAO Operation for ItemFamilyPlan Logic.
 *
 * @author
 */
public class IFPLogicDAOImpl implements ItemFamilyplanDAO {

	/**
	 * Performs a dynamic query on the database based on Item Details and
	 * returns the matching rows.
	 * 
	 * @param dynamicQuery
	 *            - returns records from ItemMaster based on ItemDetails and
	 *            value.
	 * @return List of ItemMaster
	 * @throws SystemException
	 */
	public List dynamicQuery(final DynamicQuery dynamicQuery) throws SystemException {
		return ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

   /**
    * To get the Query count for lazy load in Item Addition tab
    * 
    * @param dynamicQuery
    * @return
    * @throws SystemException 
    */
    public long itemAddtionDynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException {
      
       return ItemMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }
    
    /**
     * To get the query count for lazy load in Brand Master
     * @param dynamicQuery
     * @return count
     * @throws SystemException
     */
    public int getBrandMasterCount(DynamicQuery dynamicQuery) throws SystemException {
    	
    	return (int)BrandMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }
    
    /**
     * To get query list for the lazy load in Brand Master
     * @param dynamicQuery
     * @return brand name list
     * @throws SystemException
     */
    public List<BrandMaster> getBrandMasterList(DynamicQuery dynamicQuery) throws SystemException {
		return BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

    
    /**
     * This method will retrieve the values from Helper Table based on the
     * listName
     *
     * @param listName
     * @return list of type HelperTable
     * @throws SystemException
     */
        @Override
    public List<HelperTable> getHelperTableDetailsByListName(final String listName)
            throws SystemException {
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }

}
