/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;

import com.stpl.app.NoSuchItemQualifierException;
import com.stpl.app.NoSuchItemPricingQualifierException;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.model.Udcs;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ItemPricingLocalServiceUtil;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO operations for ItemSearchLogic
 * @author shrihariharan
 */
public class ItemSearchLogicDAOImpl implements ItemDAO {
    
    /**
     * Returns the number of item irt qualifiers.
     * @return total count
     * @throws SystemException 
     */
    public int getItemIrtQualifiersTotalCount() throws PortalException,SystemException {
        return ItemQualifierLocalServiceUtil.getItemQualifiersCount();
    }

    /**
     * Returns a range of all the item irt qualifiers.
     * @param startIndex - start limit
     * @param endindex - end limit
     * @return list of ItemQualifier
     * @throws SystemException 
     */
    public List<ItemQualifier> getItemIrtQualifiersByLimit(final int startIndex, final int endindex) throws PortalException,SystemException {
        return ItemQualifierLocalServiceUtil.getItemQualifiers(startIndex, endindex);
    }

    /**
     * Deletes the item irt qualifier from the database. Also notifies the appropriate model listeners.
     * @param qualifierId
     * @return ItemQualifier modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemQualifier deleteItemIrtQualifierByQualifierId(final int qualifierId) throws SystemException,PortalException {
        return ItemQualifierLocalServiceUtil.deleteItemQualifier(qualifierId);
    }

    /**
     * Adds the item irt qualifier to the database. Also notifies the appropriate model listeners.
     * @param itemIrtQualifier
     * @return ItemQualifier modal object
     * @throws SystemException 
     */
    public ItemQualifier saveItemIrtQualifier(final ItemQualifier itemIrtQualifier) throws PortalException,SystemException {
        return ItemQualifierLocalServiceUtil.addItemQualifier(itemIrtQualifier);
    }

    /**
     * Updates the item irt qualifier in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     * @param itemIrtQualifier
     * @return ItemQualifier modal object
     * @throws SystemException 
     */
    public ItemQualifier updateItemIrtQualifier(final ItemQualifier itemIrtQualifier) throws PortalException,SystemException {
       return ItemQualifierLocalServiceUtil.updateItemQualifier(itemIrtQualifier);
    }

    /**
     * Returns the number of item pricing qualifiers.
     * @return total count
     * @throws SystemException 
     */
    public int getItemPricingQualifierTotalCount() throws SystemException {
        return ItemPricingQualifierLocalServiceUtil.getItemPricingQualifiersCount();
    }

    /**
     * Returns a range of all the item pricing qualifiers.
     * @param startIndex - start limit
     * @param endindex - end limit
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List<ItemPricingQualifier> getItemPricingQualifierByLimit(final int startIndex,final int endindex) throws PortalException,SystemException {
        return ItemPricingQualifierLocalServiceUtil.getItemPricingQualifiers(startIndex, endindex);
    }

    /**
     * Deletes the item pricing qualifier from the database. Also notifies the appropriate model listeners.
     * @param qualifierId
     * @return ItemPricingQualifier modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemPricingQualifier deleteItemPricingQualifierByQualifierId(final int qualifierId) throws SystemException,PortalException{
       return ItemPricingQualifierLocalServiceUtil.deleteItemPricingQualifier(qualifierId);
    }

    /**
     * Adds the item pricing qualifier to the database. Also notifies the appropriate model listeners.
     * @param itemPricingQualifier
     * @return ItemPricingQualifier modal object
     * @throws SystemException 
     */
    public ItemPricingQualifier saveItemPricingQualifier(final ItemPricingQualifier itemPricingQualifier) throws PortalException,SystemException{
        return ItemPricingQualifierLocalServiceUtil.addItemPricingQualifier(itemPricingQualifier);
    }

    /**
     * Updates the item pricing qualifier in the database or adds it if it does not yet exist. 
     * Also notifies the appropriate model listeners.
     * @param itemPricingQualifier
     * @return ItemPricingQualifier modal object
     * @throws SystemException 
     */
    public ItemPricingQualifier updateItemPricingQualifier(final ItemPricingQualifier itemPricingQualifier) throws PortalException,SystemException {
        return ItemPricingQualifierLocalServiceUtil.updateItemPricingQualifier(itemPricingQualifier);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemMaster
     * @return list of ItemMaster
     * @throws SystemException 
     */
    public List<ItemMaster> getItemMasterList(final DynamicQuery query) throws PortalException,SystemException{
        return ItemMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the list of Item Master
     * @param itemId
     * @param itemNo
     * @param itemName
     * @param itemStatus
     * @param itemType
     * @param itemDesc
     * @param manufacturerId
     * @param qualifierId
     * @param itemIdentifier
     * @param brand
     * @return list of item Master
     */
    public List<ItemMaster> getItemMasterListByQualifierId(final String itemId, final String itemNo,final String itemName,final String itemStatus, final String itemType,final String itemDesc, final String manufacturerId, final int qualifierId, final String itemIdentifier, final String brand, String orderByColumn, Boolean sortOrder,final Map<String, Object> parameters) {
        return ItemMasterLocalServiceUtil.findItemMaster(itemId,itemNo, itemName, itemStatus, itemType, itemDesc,
                    manufacturerId, qualifierId, itemIdentifier, brand, orderByColumn, sortOrder, parameters);
    }

    /**
     * Returns the Item irt qualifier
     * @param qualifierName
     * @return ItemQualifier modal object
     * @throws SystemException
     * @throws NoSuchItemIrtQualifierException 
     */
    public ItemQualifier getItemIrtQualifierByName(final String qualifierName) throws SystemException,NoSuchItemQualifierException {
      return ItemQualifierLocalServiceUtil.findByItemIrtQualifierByName(qualifierName);
    }

    /**
     * Returns the ItemPricingQualifier
     * @param codeQualifierName
     * @return ItemPricingQualifier modal object
     * @throws SystemException
     * @throws NoSuchItemPricingQualifierException 
     */
    public ItemPricingQualifier getItemPricingQualifierByCodeQualifierName(final String codeQualifierName) throws SystemException, NoSuchItemPricingQualifierException {
      return ItemPricingQualifierLocalServiceUtil.findByitemPricingCodeQualifierByName(codeQualifierName);
    }

    /**
     * Returns the item master with the primary key.
     * @param itemSystemId - primary key
     * @return ItemMaster
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemMaster getItemMasterBySystemId(final int itemSystemId) throws SystemException, PortalException {
        return ItemMasterLocalServiceUtil.getItemMaster(itemSystemId);
    }

    /**
     * Adds the item master to the database. Also notifies the appropriate model listeners.
     * @param itemMaster
     * @return ItemMaster modal object
     * @throws SystemException 
     */
    public ItemMaster saveItemMaster(final ItemMaster itemMaster) throws PortalException,SystemException {
        return ItemMasterLocalServiceUtil.addItemMaster(itemMaster);
    }

    /**
     * Adds the item master to the database. Also notifies the appropriate model listeners.
     * @param itemMaster
     * @return ItemMaster modal object
     * @throws SystemException 
     */
    public ItemMaster updateItemMaster(final ItemMaster itemMaster) throws PortalException,SystemException {
       return ItemMasterLocalServiceUtil.updateItemMaster(itemMaster);
    }

    /**
     * Returns the list of ItemIdentifier
     * @param itemSystemId
     * @return list of ItemIdentifier
     * @throws SystemException
     * @throws PortalException 
     */
    public List<ItemIdentifier> getItemIrtIdentifierByItemSystemId(final int itemSystemId) throws SystemException, PortalException {
       return ItemIdentifierLocalServiceUtil.findByItemIrtIdentifierDetails(itemSystemId);
    }

    /**
     * Returns the list of ItemPricing
     * @param itemSystemId
     * @return list ItemPricing
     * @throws SystemException
     * @throws PortalException 
     */
    public List<ItemPricing> getItemPricingByItemSystemId(final int itemSystemId) throws SystemException, PortalException {
       return ItemPricingLocalServiceUtil.findByItemPricingDetails(itemSystemId);
    }

    /**
     * Deletes the item irt identifier from the database. Also notifies the appropriate model listeners.
     * @param itemIrtIdentifier
     * @return deleted ItemIdentifier
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemIdentifier deleteItemIrtIdentifier(final ItemIdentifier itemIrtIdentifier) throws SystemException, PortalException {
      return ItemIdentifierLocalServiceUtil.deleteItemIdentifier(itemIrtIdentifier);
    }

    /**
     * Deletes the item pricing from the database. Also notifies the appropriate model listeners.
     * @param itemPricing
     * @return deleted ItemPricing
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemPricing deleteItemPricing(final ItemPricing itemPricing) throws SystemException, PortalException {
        return ItemPricingLocalServiceUtil.deleteItemPricing(itemPricing);
    }

    /**
     * Adds the item irt identifier to the database. Also notifies the appropriate model listeners.
     * @param itemIrtIdentifier
     * @return saved ItemIdentifier
     * @throws SystemException 
     */
    public ItemIdentifier saveItemIrtIdentifier(final ItemIdentifier itemIrtIdentifier) throws PortalException,SystemException {
     return ItemIdentifierLocalServiceUtil.addItemIdentifier(itemIrtIdentifier);
    }

    /**
     * Adds the item pricing to the database. Also notifies the appropriate model listeners.
     * @param itemPricing
     * @return ItemPricing - modal object
     * @throws SystemException 
     */
    public ItemPricing saveItemPricing(final ItemPricing itemPricing) throws PortalException,SystemException {
        return ItemPricingLocalServiceUtil.addItemPricing(itemPricing);
    }

    /**
     * Adds the item pricing to the database. Also notifies the appropriate model listeners.
     * @param companySystemId
     * @return CompanyMaster modal object
     * @throws SystemException
     * @throws PortalException 
     */
    public CompanyMaster getCompanyMasterBySystemId(final int companySystemId) throws SystemException, PortalException {
        return CompanyMasterLocalServiceUtil.getCompanyMaster(companySystemId);
    }

    /**
     * Returns the item irt qualifier with the primary key.
     * @param qualifierId
     * @return ItemQualifier
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemQualifier getItemIrtQualifierByQualifierId(final int qualifierId) throws SystemException, PortalException {
        return ItemQualifierLocalServiceUtil.getItemQualifier(qualifierId);
    }

    /**
     * Returns the item pricing qualifier with the primary key.
     * @param qualifierId
     * @return ItemPricingQualifier
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemPricingQualifier getItemPricingQualifierByQualifierId(final int qualifierId) throws SystemException, PortalException {
        return ItemPricingQualifierLocalServiceUtil.getItemPricingQualifier(qualifierId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of CompanyMaster
     * @return list of CompanyMaster
     * @throws SystemException 
     */
    public List<CompanyMaster> getCompanyMasterList(final DynamicQuery query) throws PortalException,SystemException{
       return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Deletes the item master from the database. Also notifies the appropriate model listeners.
     * @param itemSystemId
     * @return deleted ItemMaster
     * @throws SystemException
     * @throws PortalException 
     */
    public ItemMaster deleteItemMasterBySystemId(final int itemSystemId) throws SystemException,PortalException {
        return ItemMasterLocalServiceUtil.deleteItemMaster(itemSystemId);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemQualifier
     * @return list of ItemQualifier
     * @throws SystemException 
     */
    public List<ItemQualifier> getItemIrtQualifierList(final DynamicQuery query) throws PortalException,SystemException {
       return ItemQualifierLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemPricingQualifier
     * @return list of ItemPricingQualifier
     * @throws SystemException 
     */
    public List<ItemPricingQualifier> getItemPricingQualifierList(final DynamicQuery query) throws PortalException,SystemException {
       return ItemQualifierLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws SystemException 
     */
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws PortalException,SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of BrandMaster
     * @return list of BrandMaster
     * @throws SystemException 
     */
    public List<BrandMaster> getBrandMasterList(final DynamicQuery query) throws PortalException,SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Returns the count according to the result of the query
     * @param query - dynamic query of ItemMaster
     * @return count
     * @throws SystemException 
     */
    public int getItemMasterCountByQuery(final DynamicQuery query) throws PortalException,SystemException {
       return Integer.valueOf(String.valueOf(ItemMasterLocalServiceUtil.dynamicQueryCount(query)));
    }

    /**
     * Returns the number of item masters.
     * @return Total count
     * @throws SystemException 
     */
    public int getItemMasterTotalCount() throws PortalException,SystemException {
        return ItemMasterLocalServiceUtil.getItemMastersCount();
    }
    
    /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of ItemIdentifier
     * @return list of ItemIdentifier
     * @throws SystemException 
     */
    public List<ItemIdentifier> getItemIrtIdentifierList(final DynamicQuery query)throws PortalException,SystemException {
        return ItemIdentifierLocalServiceUtil.dynamicQuery(query);
    }
    
    /**
     * Returns a range of all the item masters. 
     * @param startIndex - start limit
     * @param endindex - end limit
     * @return list of ItemMaster
     * @throws SystemException
     */
    public List<ItemMaster> getItemMasterByLimit(final int startIndex, final int endindex) throws PortalException,SystemException {
        return ItemMasterLocalServiceUtil.getItemMasters(startIndex, endindex);
    }
    
    public List getBrandList(final DynamicQuery ifpDynamicQuery) throws PortalException, SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }

    public List itemIrtQualifierNameList(final DynamicQuery ifpDynamicQuery) throws PortalException,SystemException{
        return ItemQualifierLocalServiceUtil.dynamicQuery(ifpDynamicQuery);
    }

     /**
     * Performs a dynamic query on the database and returns the matching rows.
     * @param query - dynamic query of CompanyMaster
     * @return list of CompanyMaster
     * @throws SystemException 
     */
    public List getManufactureIdList(final DynamicQuery query) throws PortalException,SystemException{
       return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    public List getItemPricingTypeList(final DynamicQuery query) throws SystemException {
        return ItemPricingQualifierLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * 
     * @param itemPricing
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    public ItemPricing updateItemPricing(final ItemPricing itemPricing) throws PortalException,SystemException {
        return ItemPricingLocalServiceUtil.updateItemPricing(itemPricing);
    }
    
    /**
     * 
     * @param itemIrtIdentifier
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
    public ItemIdentifier updateItemIrtIdentifier(final ItemIdentifier itemIrtIdentifier) throws PortalException,SystemException {
     return ItemIdentifierLocalServiceUtil.updateItemIdentifier(itemIrtIdentifier);
    }

    public Map<Integer, Udcs> getUDCS(String type) throws SystemException {
        Map<Integer,Udcs> udcMap = new HashMap<>();
        final DynamicQuery udcsDynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class);
        udcsDynamicQuery.add(RestrictionsFactoryUtil.eq("udcType", type));
        List<Udcs> udcsList = UdcsLocalServiceUtil.dynamicQuery(udcsDynamicQuery);
        for(Udcs udcs: udcsList){
            udcMap.put(udcs.getUdcsSid(), udcs);
        }
        return udcMap;
    }

    public Udcs getUDCS(int udcsSid) throws SystemException, PortalException {
return UdcsLocalServiceUtil.getUdcs(udcsSid);

    }

    public Udcs saveUdcs(Udcs udcs) throws SystemException {
    return UdcsLocalServiceUtil.addUdcs(udcs);    

    }

    public Udcs updateUdcs(Udcs udcs) throws SystemException {
        return UdcsLocalServiceUtil.updateUdcs(udcs);
    }

    public List<HelperTable> getHelperTableDetailsByListName() throws PortalException, SystemException {
        return HelperTableLocalServiceUtil.getHelperTables(0, HelperTableLocalServiceUtil.getHelperTablesCount());
    }
    
   /**
    * To get the count of itemIdentifier associated with given qualifierID
    * 
    * @param query
    * @return List
    * @throws PortalException
    * @throws SystemException 
    */
    public List getItemIdentifierList(final DynamicQuery query) throws PortalException, SystemException {
         return ItemIdentifierLocalServiceUtil.dynamicQuery(query);
    }
    /**
     * To get the ID of itemPricing associated with the Item
     * 
     * @param query
     * @return
     * @throws PortalException
     * @throws SystemException 
     */
     public List getItemPricingList(final DynamicQuery query) throws PortalException, SystemException {
         return ItemPricingLocalServiceUtil.dynamicQuery(query);
    }
    
}
