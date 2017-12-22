/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import java.util.List;

/**
 *
 * @author mohamed.hameed
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
    RelationshipLevelDefinition getRelationshipLevel(int relationshipLevelSid) throws SystemException, PortalException;

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
    CffCustomViewMaster addCustomView(CffCustomViewMaster customViewMaster) throws SystemException;

    /**
     * Delete the Custom View.
     *
     * @param customViewMaster
     * @return customViewMaster
     * @throws SystemException
     */
    CffCustomViewMaster deleteCustomView(CffCustomViewMaster customViewMaster) throws SystemException;

    /**
     * Delete the Custom View.
     *
     * @param customViewMasterId
     * @return customViewMaster
     * @throws SystemException
     * @throws PortalException
     */
    CffCustomViewMaster deleteCustomView(int customViewMasterId) throws SystemException, PortalException;

    /**
     * Gets the Custom View.
     *
     * @param customViewMasterId
     * @return customViewMaster
     * @throws SystemException
     * @throws PortalException
     */
    CffCustomViewMaster getCustomView(int customViewMasterId) throws SystemException, PortalException;

    /**
     * Update the Custom View.
     *
     * @param customViewMaster
     * @return customViewMaster
     * @throws SystemException
     */
    CffCustomViewMaster updateCustomView(CffCustomViewMaster customViewMaster) throws SystemException;

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
    CffCustomViewDetails addCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException;

    /**
     * Delete the Custom View Details.
     *
     * @param customViewDetails
     * @return customViewDetails
     * @throws SystemException
     */
    CffCustomViewDetails deleteCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException;

    /**
     * Delete the Custom View Details.
     *
     * @param customViewDetailsId
     * @return customViewDetails
     * @throws SystemException
     * @throws PortalException
     */
    CffCustomViewDetails deleteCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException;

    /**
     * Gets the Custom View.
     *
     * @param customViewDetailsId
     * @return customViewDetails
     * @throws SystemException
     * @throws PortalException
     */
    CffCustomViewDetails getCustomViewDetails(int customViewDetailsId) throws SystemException, PortalException;

    /**
     * Update the Custom View.
     *
     * @param customViewDetails
     * @return customViewDetails
     * @throws SystemException
     */
    CffCustomViewDetails updateCustomViewDetails(CffCustomViewDetails customViewDetails) throws SystemException;

    /**
     * Gets the DiscountNo.
     *
     * @param projectionId
     * @param priceGroupType
     * @return
     */
    Object executeSelectQuery(String query);

}
