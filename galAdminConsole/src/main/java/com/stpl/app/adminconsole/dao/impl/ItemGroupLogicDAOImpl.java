/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.dao.ItemGroupLogicDAO;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HistItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.HistItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemGroupLogicDAOImpl.
 *
 * @author sriram
 */
public class ItemGroupLogicDAOImpl implements ItemGroupLogicDAO {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemGroupLogicDAOImpl.class);

    /**
     * To get the list of ItemGroup from the ItemGroup query.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    public List getItemGroupsList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In query-getItemGroupsList started with P1:DynamicQuery query");
        return ItemGroupLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the list of ItemGroup from the ItemGroup query.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    public int getItemGroupsListCount(final DynamicQuery query) throws SystemException {
        LOGGER.info("In query-getItemGroupsListcount started with P1:DynamicQuery query");
        return (int) ItemGroupLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * To get the ItemGroup for the corresponding systemId from ItemGroup table.
     *
     * @param systemId the system id
     * @return the item group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public ItemGroup getItemGroup(final int systemId) throws PortalException, SystemException {
        LOGGER.info("In query-getItemGroup started with P1:int systemId=" + systemId);
        return ItemGroupLocalServiceUtil.getItemGroup(systemId);
    }

    /**
     * To add data to ItemGroup table.
     *
     * @param item the item
     * @return the item group
     * @throws SystemException the system exception
     */
    public ItemGroup addItemGroup(final ItemGroup item) throws SystemException {
        LOGGER.info("In query-addItemGroup started with P1:ItemGroup item");
        return ItemGroupLocalServiceUtil.addItemGroup(item);
    }

    /**
     * To update ItemGroup in ItemGroup table.
     *
     * @param item the item
     * @return the item group
     * @throws SystemException the system exception
     */
    public ItemGroup updateItemGroup(final ItemGroup item) throws SystemException {
        LOGGER.info("In query-updateItemGroup started with P1:ItemGroup item");
        return ItemGroupLocalServiceUtil.updateItemGroup(item);
    }

    /**
     * To delete ItemGroup from ItemGroup table.
     *
     * @param systemId the system id
     * @return the item group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public ItemGroup deleteItemGroup(final int systemId) throws PortalException, SystemException {
        LOGGER.info("In query-deleteItemGroup started with P1:int systemId=" + systemId);
        return ItemGroupLocalServiceUtil.deleteItemGroup(systemId);
    }

    /**
     * To get the list of ItemGroupDetails corresponding to the query from
     * ItemGroupDetails table.
     *
     * @param query the query
     * @return the item group details list
     * @throws SystemException the system exception
     */
    public List getItemGroupDetailsList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In query-getItemGroupDetailsList started with P1:DynamicQuery query");
        return ItemGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To update data in ItemGroupDetails table.
     *
     * @param item the item
     * @return the item group details
     * @throws SystemException the system exception
     */
    public ItemGroupDetails updateItemGroupDetails(final ItemGroupDetails item) throws SystemException {
        LOGGER.info("In query-updateItemGroupDetails started with P1:ItemGroupDetails item");
        return ItemGroupDetailsLocalServiceUtil.updateItemGroupDetails(item);
    }

    /**
     * To add data in ItemGroupDetails table.
     *
     * @param item the item
     * @return the item group details
     * @throws SystemException the system exception
     */
    public ItemGroupDetails addItemGroupDetails(final ItemGroupDetails item) throws SystemException {
        return ItemGroupDetailsLocalServiceUtil.addItemGroupDetails(item);
    }

    /**
     * To delete data from ItemGroupDetails table.
     *
     * @param item the item
     * @throws SystemException the system exception
     */
    public void deleteItemGroupDetails(final ItemGroupDetails item) throws SystemException {
        LOGGER.info("In query-deleteItemGroupDetails started with P1:ItemGroupDetails item");
        ItemGroupDetailsLocalServiceUtil.deleteItemGroupDetails(item);
    }

    /**
     * To get the list of items from ItemMaster table.
     *
     * @param query the query
     * @return the items list
     * @throws SystemException the system exception
     */
    public List getItemsList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In query-getItemsList started with P1:DynamicQuery query");
        return ItemMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query
     * @return list of companyName
     * @throws SystemException the system exception
     */
    public List<String> getCompanyMasterList(final DynamicQuery query) throws SystemException {
        return CompanyMasterLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the list of ItemGroupHistory from the ItemGroup query.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    public List getItemGroupsHistoryList(final DynamicQuery query) throws SystemException {
        return HistItemGroupLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * To get the list of ItemGroupHistory from the ItemGroup query count.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */

    public int getItemGroupsHistoryListCount(final DynamicQuery query) throws SystemException {
        return (int) HistItemGroupLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * (non-Javadoc)
     * getItemGroupsDetailsHistoryList(com.stpl.portal.kernel.dao.orm.DynamicQuery)
     */
    public List getItemGroupsDetailsHistoryList(final DynamicQuery query) throws SystemException {
        return HistItemGroupDetailsLocalServiceUtil.dynamicQuery(query);
    }

    public List getBrandNameandId(final DynamicQuery query) throws SystemException {
        return BrandMasterLocalServiceUtil.dynamicQuery(query);
    }

    public int getProjectionCount(DynamicQuery query) throws SystemException {
        return (int) ProjectionMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     *
     * @param query
     * @return
     * @throws SystemException
     */
    public List getItemMasterRecords(String query[]) throws SystemException {
        return ItemGroupDetailsLocalServiceUtil.getItemMasterRecords(query);
    }
}
