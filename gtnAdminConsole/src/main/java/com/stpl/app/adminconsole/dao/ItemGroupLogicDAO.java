/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.dao;

import java.util.List;

import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ItemGroupLogicDAO.
 *
 * @author sriram
 */
public interface ItemGroupLogicDAO {

    /**
     * Gets the item groups list.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    List getItemGroupsList(DynamicQuery query) throws SystemException;

    /**
     * Gets the item groups list count.
     *
     * @param query the query
     * @return the item groups list
     * @throws SystemException the system exception
     */
    int getItemGroupsListCount(DynamicQuery query) throws SystemException;

    /**
     * Gets the items list.
     *
     * @param query the query
     * @return the items list
     * @throws SystemException the system exception
     */
    List getItemsList(DynamicQuery query) throws SystemException;

    /**
     * Gets the item group.
     *
     * @param systemId the system id
     * @return the item group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    ItemGroup getItemGroup(int systemId) throws PortalException, SystemException;

    /**
     * Adds the item group.
     *
     * @param item the item
     * @return the item group
     * @throws SystemException the system exception
     */
    ItemGroup addItemGroup(ItemGroup item) throws SystemException;

    /**
     * Update item group.
     *
     * @param item the item
     * @return the item group
     * @throws SystemException the system exception
     */
    ItemGroup updateItemGroup(ItemGroup item) throws SystemException;

    /**
     * Delete item group.
     *
     * @param systemId the system id
     * @return the item group
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    ItemGroup deleteItemGroup(int systemId) throws PortalException, SystemException;

    /**
     * Gets the item group details list.
     *
     * @param query the query
     * @return the item group details list
     * @throws SystemException the system exception
     */
    List getItemGroupDetailsList(DynamicQuery query) throws SystemException;

    /**
     * Update item group details.
     *
     * @param item the item
     * @return the item group details
     * @throws SystemException the system exception
     */
    ItemGroupDetails updateItemGroupDetails(ItemGroupDetails item) throws SystemException;

    /**
     * Adds the item group details.
     *
     * @param item the item
     * @return the item group details
     * @throws SystemException the system exception
     */
    ItemGroupDetails addItemGroupDetails(ItemGroupDetails item) throws SystemException;

    /**
     * Delete item group details.
     *
     * @param item the item
     * @throws SystemException the system exception
     */
    void deleteItemGroupDetails(ItemGroupDetails item) throws SystemException;

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query
     * @return list of CompanyMaster
     * @throws SystemException the system exception
     */
    List<String> getCompanyMasterList(final DynamicQuery query) throws SystemException;

    /**
     * Gets the item groups history list.
     *
     * @param query the query
     * @return the item groups history list
     * @throws SystemException the system exception
     */
    List getItemGroupsHistoryList(final DynamicQuery query) throws SystemException;

    /**
     * Gets the item groups history list count.
     *
     * @param query the query
     * @return the item groups history list
     * @throws SystemException the system exception
     */
    int getItemGroupsHistoryListCount(final DynamicQuery query) throws SystemException;

    /**
     * Gets the item groups details history list.
     *
     * @param query the query
     * @return the item groups details history list
     * @throws SystemException the system exception
     */
    List getItemGroupsDetailsHistoryList(final DynamicQuery query) throws SystemException;

    /**
     * get Brand
     *
     * @param query
     * @return
     * @throws SystemException
     */
    List getBrandNameandId(final DynamicQuery query) throws SystemException;

    /**
     * get the projection count which is associated with item group
     *
     * @param query
     * @return
     * @throws SystemException
     */
    int getProjectionCount(final DynamicQuery query) throws SystemException;
        
    
    List getItemMasterRecords(String query[]) throws SystemException;
}
