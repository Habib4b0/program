/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;


import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.PsModel;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.domain.global.priceschedule.PriceScheduleDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;

/**
 * DAO operations for PriceSchedule logic
 * @author shrihariharan
 */
public class PSLogicDAOImpl implements PriceScheduleDAO{
    
    /**
     * Returns the item master with the primary key.
     * @param itemMasterSystemId
     * @return ItemMaster
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemMaster getitemMasterBysystemId(final int itemMasterSystemId)throws SystemException,PortalException
    {
        return ItemMasterLocalServiceUtil.getItemMaster(itemMasterSystemId);
    }
    /**
     * Returns the list of ItemFamilyplanDetails
     * @param ItemFamilyplanSystemId
     * @return list of ItemFamilyplanDetails
     * @throws SystemException 
     */
    public List<IfpDetails> getItemFamilyplanDetailsListByItemFamilyplanSystemId(final int itemFamilyplanSystemId) throws SystemException {
        return IfpDetailsLocalServiceUtil.findByItemFamilyPlanDetails(itemFamilyplanSystemId);
    }
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of IfpModel
     * @return list of IfpModel 
     * @throws SystemException 
     */
    public List<IfpModel> getItemFamilyplanMasterList(final DynamicQuery query) throws SystemException {
        return IfpModelLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
     public List<HelperTable> getHelperTableList(final DynamicQuery query) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }
     /**
      * Returns list of HelprTable by list name
      * @param listName
      * @return list of HelperTable
      * @throws SystemException 
      */
    public List<HelperTable> getHelperTableDetailsByListName(final String listName) throws SystemException {
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List<ItemPricingQualifier> getItemPricingQualifierList(final DynamicQuery query) throws SystemException {
       return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of PriceScheduleMaster
     * @return list of PriceScheduleMaster
     * @throws SystemException 
     */
    public List<PsModel> getPriceScheduleMasterList(final DynamicQuery query) throws SystemException {
        return PsModelLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * Returns the price schedule master with the primary key.
     * @param priceSchecduleMasterSystemId
     * @return PriceScheduleMaster - modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public PsModel getPriceScheduleMasterBySystemId(final int priceSchecduleMasterSystemId) throws SystemException,PortalException {
        return PsModelLocalServiceUtil.getPsModel(priceSchecduleMasterSystemId);
    }
    /**
     * Adds the price schedule master to the database. Also notifies the appropriate model listeners
     * @param priceScheduleMaster
     * @return saved PriceScheduleMaster modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public PsModel savePriceScheduleMaster(final PsModel priceScheduleMaster) throws SystemException, PortalException {
        return PsModelLocalServiceUtil.addPsModel(priceScheduleMaster);
    }
    /**
     * Returns the list of PriceScheduleDetails
     * @param systemId
     * @return list of PriceScheduleDetails
     * @throws SystemException 
     */
    public List<PsDetails> getPriceScheduleDetailsByPriceScheduleMasterSystemId(final int systemId) throws SystemException {
        return PsDetailsLocalServiceUtil.findByPriceScheduleMasterDetails(systemId);
    }
    /**
     * Deletes the price schedule details from the database. Also notifies the appropriate model listeners.
     * @param priceScheduleDetails
     * @return deleted PriceScheduleDetails modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public PsDetails deletePriceScheduleDetails(final PsDetails priceScheduleDetails) throws SystemException, PortalException {
        return PsDetailsLocalServiceUtil.deletePsDetails(priceScheduleDetails);
    }
    /**
     * Adds the price schedule details to the database. Also notifies the appropriate model listeners.
     * @param priceScheduleDetails
     * @return saved PriceScheduleDetails modal object
     * @throws SystemException 
     */
    public PsDetails savePriceScheduleDetails(final PsDetails priceScheduleDetails)throws SystemException
    {
        return PsDetailsLocalServiceUtil.addPsDetails(priceScheduleDetails);
    }
    /**
     * Updates the price schedule details in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     * @param priceScheduleDetails
     * @return updated PriceScheduleDetails modal object
     * @throws SystemException 
     */
    public PsDetails updatePriceScheduleDetails(final PsDetails priceScheduleDetails)throws SystemException
    {
        return PsDetailsLocalServiceUtil.updatePsDetails(priceScheduleDetails);
    }
    /**
     * Returns the item familyplan master with the primary key.
     * @param itemFamilyplanMasterSystemId
     * @return IfpModel modal object
     * @throws SystemException
     * @throws PortalException 
     */
     public IfpModel getItemFamilyplanMasterBySystemId(final int itemFamilyplanMasterSystemId) throws SystemException,PortalException {
        return IfpModelLocalServiceUtil.getIfpModel(itemFamilyplanMasterSystemId);
    }
     /**
      * Returns list of Item 
      * @param ifpId
      * @return list of items
      * @throws SystemException 
      */
    public List getItemAndPricingForPs(final int ifpId) throws SystemException {
        return PsDetailsLocalServiceUtil.getItemAndPricingForPs(ifpId);
    }
    /**
     * Deletes the price schedule master from the database. Also notifies the appropriate model listeners.
     * @param priceScheduleSystemId
     * @return PriceScheduleMaster modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public PsModel deletePriceScheduleMaster(final int priceScheduleSystemId) throws SystemException,PortalException {
        return PsModelLocalServiceUtil.deletePsModel(priceScheduleSystemId);
    }
    /**
     * Returns a range of all the price schedule masters.
     * @param startIndex - satrt limit
     * @param endIndex - end limit
     * @return list of PriceScheduleMaster
     * @throws SystemException 
     */
    public List<PsModel> getPriceScheduleMasterListByLimit(final int startIndex, final int endIndex) throws SystemException {
        return PsModelLocalServiceUtil.getPsModels(startIndex, endIndex);
    }
    /**
     * Returns the count of IfpModel
     * @param query - dynamic query of IfpModel
     * @return count
     * @throws SystemException 
     */
    public int getItemFamilyplanMasterQueryCount(final DynamicQuery query) throws SystemException {
        return (int) IfpModelLocalServiceUtil.dynamicQueryCount(query);
    }
    /**
     * Returns the number of item familyplan masters.
     * @return Total count
     * @throws SystemException 
     */
    public int getItemFamilyplanMasterTotalCount()throws SystemException{
        return IfpModelLocalServiceUtil.getIfpModelsCount();
    }
    /**
     * Returns a range of all the item familyplan masters.
     * @param startIndex - start limit
     * @param endindex - end limit
     * @return list of IfpModel
     * @throws SystemException 
     */
    public List<IfpModel> getItemFamilyplanMasterByLimit(final int startIndex, final int endindex) throws SystemException {
      return IfpModelLocalServiceUtil.getIfpModels(startIndex, endindex);
    }
    
    /**
     * Updates the price schedule master in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     * @param priceScheduleMaster
     * @return PriceScheduleMaster modal object
     * @throws SystemException
     * @throws PortalException
     */
    public PsModel updatePriceScheduleMaster(final PsModel priceScheduleMaster)throws SystemException,PortalException{
    	return PsModelLocalServiceUtil.updatePsModel(priceScheduleMaster);
    }
    
    /**
     * Returns the count of PriceScheduleMaster
     * @param query - dynamic query of PriceScheduleMaster
     * @return count
     * @throws SystemException 
     */
    public int getPriceScheduleMasterQueryCount(final DynamicQuery query) throws SystemException {
        return (int) PsModelLocalServiceUtil.dynamicQueryCount(query);
    }
    /**
     * Returns the number of PriceScheduleMaster.
     * @return Total count
     * @throws SystemException 
     */
    public int getPriceScheduleMasterTotalCount()throws SystemException{
        return PsModelLocalServiceUtil.getPsModelsCount();
    }
     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List getItemPricingTypeList(final DynamicQuery query) throws SystemException {
       return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of IfpModel
     * @return list of IfpModel 
     * @throws SystemException 
     */
    public int getItemFamilyplanMasterCount(final DynamicQuery query) throws SystemException {
        return (int) IfpModelLocalServiceUtil.dynamicQueryCount(query);
    }
     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of IfpModel
     * @return list of IfpModel 
     * @throws SystemException 
     */
    public List getIFPList(final DynamicQuery query) throws SystemException {
        return IfpModelLocalServiceUtil.dynamicQuery(query);
    }
}
