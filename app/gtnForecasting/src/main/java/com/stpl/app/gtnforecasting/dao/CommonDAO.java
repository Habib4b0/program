/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.RelationshipLevelDefinition;
import java.util.List;

/**
 *
 * @author Abhiram
 */
public interface CommonDAO {

    
    /**
     * Gets the Relationship level list.
     *
     * @param query
     * @return the relationship level list
     * @throws SystemException
     */
    
    List getRelationshipLevels(DynamicQuery query) throws SystemException;
    
    /**
     * Gets the Relationship Level.
     *
     * @param relationshipLevelSid
     * @return RelationshipLevelDefinition
     * @throws SystemException
     * @throws PortalException
     */
    RelationshipLevelDefinition getRelationshipLevel(int relationshipLevelSid) throws PortalException;
    /**
     * Gets the Custom View list.
     *
     * @param query
     * @return custom view list
     * @throws SystemException
     */
    List getCustomViewList(DynamicQuery query) throws SystemException;

    /**
     * Add the Custom View.
     *
     * @param customViewMaster
     * @return customViewMaster
     * @throws SystemException
     */
    CustomViewMaster addCustomView(CustomViewMaster customViewMaster) throws SystemException;

    /**
     * Delete the Custom View.
     *
     * @param customViewMaster
     * @return customViewMaster
     * @throws SystemException
     */
    CustomViewMaster deleteCustomView(CustomViewMaster customViewMaster) throws SystemException;

    /**
     * Delete the Custom View.
     *
     * @param customViewMasterId
     * @return customViewMaster
     * @throws SystemException
     * @throws PortalException
     */
    CustomViewMaster deleteCustomView(int customViewMasterId) throws PortalException;

    /**
     * Gets the Custom View.
     *
     * @param customViewMasterId
     * @return customViewMaster
     * @throws SystemException
     * @throws PortalException
     */
    CustomViewMaster getCustomView(int customViewMasterId) throws PortalException;

    /**
     * Update the Custom View.
     *
     * @param customViewMaster
     * @return customViewMaster
     * @throws SystemException
     */
    CustomViewMaster updateCustomView(CustomViewMaster customViewMaster) throws SystemException;

    /**
     * Gets the Custom View list.
     *
     * @param query
     * @return custom view list
     * @throws SystemException
     */
    List getCustomViewDetailsList(DynamicQuery query) throws SystemException;

    /**
     * Add the Custom View Details.
     *
     * @param customViewDetails
     * @return customViewDetails
     * @throws SystemException
     */
    CustomViewDetails addCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException;

    /**
     * Delete the Custom View Details.
     *
     * @param customViewDetails
     * @return customViewDetails
     * @throws SystemException
     */
    CustomViewDetails deleteCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException;

    /**
     * Delete the Custom View Details.
     *
     * @param customViewDetailsId
     * @return customViewDetails
     * @throws SystemException
     * @throws PortalException
     */
    CustomViewDetails deleteCustomViewDetails(int customViewDetailsId) throws PortalException;

    /**
     * Gets the Custom View.
     *
     * @param customViewDetailsId
     * @return customViewDetails
     * @throws SystemException
     * @throws PortalException
     */
    CustomViewDetails getCustomViewDetails(int customViewDetailsId) throws PortalException;

    /**
     * Update the Custom View.
     *
     * @param customViewDetails
     * @return customViewDetails
     * @throws SystemException
     */
    CustomViewDetails updateCustomViewDetails(CustomViewDetails customViewDetails) throws SystemException;
    
     /**Gets the Hierarchy Levels.
      * 
      * @param projectionId
      * @param hierarchyIndicator
      * @param levelNo
      * @param hierarchyLevelDefId
      * @return 
      */
   
    List getDiscountNoList(int projectionId,List<String> priceGroupType);
    /** Gets the DiscountNo.
     * 
     * @param projectionId
     * @param priceGroupType
     * @return 
     */
    Object executeSelectQuery(String query);
    /** Gets the DiscountNo.
     * 
     * @param projectionId
     * @param priceGroupType
     * @return 
     */
    Object executeBulkUpdateQuery(String query);
    /** Gets the DiscountNo.
     * 
     * @param projectionId
     * @param priceGroupType
     * @return 
     */
    Object executeUpdateQuery(List<?> nmSalesList);
}
