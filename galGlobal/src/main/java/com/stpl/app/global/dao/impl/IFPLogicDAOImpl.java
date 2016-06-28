/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;


import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.domain.global.itemfamilyplan.ItemFamilyplanDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.jboss.logging.Logger;

/**
 * DAO operations for Item Family Plan Logic
 * @author shrihariharan
 */
public class IFPLogicDAOImpl implements ItemFamilyplanDAO{
	/**
     * The Constant LOGGER.
     */
       private static final Logger LOGGER = Logger.getLogger(IFPLogicDAOImpl.class);
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemMaster
     * @return list of ItemMaster
     * @throws SystemException 
     */
    public List<ItemMaster> getItemMasterList(final DynamicQuery query)throws SystemException
     {
         LOGGER.info("Query hit for getItemMasterList, DynamicQuery object");
         return ItemMasterLocalServiceUtil.dynamicQuery(query);
     }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of IfpModel
     * @return list of IfpModel
     * @throws SystemException 
     */
    public List<IfpModel> getItemFamilyplanMasterList(final DynamicQuery query) throws SystemException {
        LOGGER.info("Query Hit For getItemFamilyplanMasterList ,DynamicQuery object");
        return IfpModelLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the item master with the primary key.
     * @param itemMasterSystemId - primary key
     * @return Item Master modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemMaster getItemMasterBysystemId(final int itemMasterSystemId)throws SystemException,PortalException
    {
        LOGGER.info("Query Hit For getitemMasterBysystemId with itemMasterSystemId = "+itemMasterSystemId);
        return ItemMasterLocalServiceUtil.getItemMaster(itemMasterSystemId);
    }
    
    /**
     * Returns the item familyplan master with the primary key.
     * @param itemFamilyplanMasterSystemId - primary key
     * @return Item Familyplan Master - modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public IfpModel getItemFamilyplanMasterBySystemId(final int itemFamilyplanMasterSystemId) throws SystemException,PortalException {
        LOGGER.info("Query Hit For getItemFamilyplanMasterBySystemId with itemFamilyplanMasterSystemId ="+itemFamilyplanMasterSystemId);
        return IfpModelLocalServiceUtil.getIfpModel(itemFamilyplanMasterSystemId);
    }

    /**
     * Adds the item familyplan master to the database. Also notifies the appropriate model listeners.
     * @param itemFamilyplanMaster -  modal object
     * @return saved IfpModel modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public IfpModel saveItemFamilyplanMaster(final IfpModel itemFamilyplanMaster) throws SystemException, PortalException {
        LOGGER.info("Query Hit For saveItemFamilyplanMaster ,IfpModel");
        return IfpModelLocalServiceUtil.addIfpModel(itemFamilyplanMaster);
    }

    /**
     * Adds the item familyplan details to the database. Also notifies the appropriate model listeners.
     * @param itemFamilyplanDetails - modal object
     * @return saved IfpDetails modal object
     * @throws SystemException 
     */
    public IfpDetails saveIfpDetails(final IfpDetails itemFamilyplanDetails) throws SystemException{
        LOGGER.info("Query Hit For saveIfpDetails ,IfpDetails");
        return IfpDetailsLocalServiceUtil.addIfpDetails(itemFamilyplanDetails);
    }

    /**
     * Returns the list of IfpDetails
     * @param ItemFamilyplanSystemId
     * @return list of IfpDetails
     * @throws SystemException 
     */
    public List<IfpDetails> getItemFamilyplanDetailsListByItemFamilyplanSystemId(final int itemFamilyplanSystemId) throws SystemException {
        LOGGER.info("Query Hit For getItemFamilyplanDetailsListByItemFamilyplanSystemId with ItemFamilyplanSystemId ="+itemFamilyplanSystemId);
        return IfpDetailsLocalServiceUtil.findByItemFamilyPlanDetails(itemFamilyplanSystemId);
    }

    /**
     * Deletes the item familyplan master from the database. Also notifies the appropriate model listeners.
     * @param itemFamilyplanMasterSystemId - primary key
     * @return deleted IfpModel modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public IfpModel deleteItemFamilyplanMasterBySystemId(final int itemFamilyplanMasterSystemId) throws SystemException, PortalException {
        LOGGER.info("Query Hit For deleteItemFamilyplanMasterBySystemId with itemFamilyplanMasterSystemId= "+itemFamilyplanMasterSystemId);
        return IfpModelLocalServiceUtil.deleteIfpModel(itemFamilyplanMasterSystemId);
    }

    /**
     * Deletes the item familyplan details from the database. Also notifies the appropriate model listeners.
     * @param itemFamilyplanDetails - modal object
     * @return deleted IfpDetails modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public IfpDetails deleteIfpDetails(final IfpDetails itemFamilyplanDetails) throws SystemException, PortalException {
        LOGGER.info("Query Hit For deleteIfpDetails ,IfpDetails ");
       return IfpDetailsLocalServiceUtil.deleteIfpDetails(itemFamilyplanDetails);
    }
    /**
     * Returns the list on helperTable by list name
     * @param listName
     * @return drop down name
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableDetailsByListName(final String listName) throws SystemException {
        LOGGER.info("Query Hit For getHelperTableDetailsByListName by passing StringName ="+listName);
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }

    /**
     *  Performs a dynamic query on the database and returns the matching rows
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableDetailsList(final DynamicQuery query) throws SystemException {
        LOGGER.info("Query Hit For getHelperTableDetailsList ");
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     * @param query - dynamic query of IfpModel
     * @return count
     * @throws SystemException 
     */
    public int getItemFamilyplanMasterQueryCount(final DynamicQuery query) throws SystemException {
        LOGGER.info("Query Hit For getItemFamilyplanMasterQueryCount ");
        return (int) IfpModelLocalServiceUtil.dynamicQueryCount(query);
    }
    
    /**
     * Returns the number of item familyplan masters.
     * @return total count
     * @throws SystemException 
     */
    public int getItemFamilyplanMasterTotalCount()throws SystemException{
        LOGGER.info("Query Hit For getItemFamilyplanMasterTotalCount ");
        return IfpModelLocalServiceUtil.getIfpModelsCount();
    }
    
    /**
     * Returns a range of all the item familyplan masters.
     * @param startIndex - start limit
     * @param endindex - end limit
     * @return list of IfpModel
     * @throws SystemException 
     */
    public List<IfpModel> getItemFamilyplanMasterByLimit(final int startIndex,final int endindex) throws SystemException {
        LOGGER.info("Query Hit For getItemFamilyplanMasterByLimit with Start Index ="+startIndex+", and endIndex ="+endindex);
      return IfpModelLocalServiceUtil.getIfpModels(startIndex, endindex);
    }
	
    /**
     * Updates the item familyplan master in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     * @param itemFamilyplanMaster
     * @return updated IfpModel
     * @throws SystemException
     * @throws PortalException
     */
    public IfpModel updateItemfamilyplanMaster(final IfpModel itemFamilyplanMaster)throws SystemException,PortalException{
    	return IfpModelLocalServiceUtil.updateIfpModel(itemFamilyplanMaster);
    }
    
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemMaster
     * @return list of ItemMaster
     * @throws SystemException 
     */
    public List getItemMasterCount(final DynamicQuery query)throws SystemException
     {
         return ItemMasterLocalServiceUtil.dynamicQuery(query);
     }

    /**
     * 
     * @param itemFamilyplanDetails
     * @return
     * @throws SystemException 
     */
    public IfpDetails updateIfpDetails(final IfpDetails itemFamilyplanDetails) throws SystemException{
        LOGGER.info("Query Hit For updateIfpDetails ,IfpDetails");
        return IfpDetailsLocalServiceUtil.updateIfpDetails(itemFamilyplanDetails);
    }
    
    @Override
    /**
     *
     */
    public List<IfpContract> getItemFamilyplanContractList(DynamicQuery query) throws SystemException {
        LOGGER.info("Dynamic Query hit for getItemFamilyplanContractList, DynamicQuery object");
        return IfpContractLocalServiceUtil.dynamicQuery(query);
    }
		

}
