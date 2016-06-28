/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dao;

import com.stpl.app.model.HelperTable;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface SalesProjectionDAO.
 *
 * @author lokeshwari
 */
public interface SalesProjectionDAO {

       /**
     * Gets the pmpy trading partner list.
     *
     * @param tpName the tp name
     * @return the pmpy trading partner list
     * @throws Exception the exception
     */
    List<String> getPMPYTradingPartnerList(String tpName) throws SystemException, PortalException, Exception;

    /**
     * Gets the pmpy contract holder.
     *
     * @return the pmpy contract holder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    List<String> getPMPYContractHolder(DynamicQuery dynamicQuery) throws SystemException, PortalException, Exception;
/**
 * 
 * @param query
 * @return
 * @throws SystemException
 * @throws PortalException
 * @throws Exception
 */
    public List getSalesProjection(Object[] inputs) throws SystemException, PortalException, Exception;
/**
 * 
 * @param query
 * @return
 * @throws SystemException
 * @throws PortalException
 * @throws Exception
 */
    public List getSalesProjectionResult(DynamicQuery query) throws SystemException, PortalException, Exception;


    /**
     * Gets the sales contract holder summary.
     *
     * @param projectionId the projection id
     * @param indicator the indicator
     * @param comparisonProjections the comparison projections
     * @param frequency the frequency
     * @param level the level
     * @param discountLevel the discount level
     * @return the contract holder summary
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    List getContractHolderSummary(final int projectionId, final String indicator,
    		final List<Integer> comparisonProjections, final String frequency, final String level, final String discountLevel)
    				throws SystemException, PortalException, Exception;
    
    /** Gets the group list.
     *
     * @param query the query
     * @return the group list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<String> getGroupList(DynamicQuery query) throws SystemException, Exception;
    public List getSalesProjectionResults(Object[] inputs) throws SystemException, PortalException, Exception;
    public List getSalesProjectionResultLevels(Object[] inputs) throws SystemException, PortalException, Exception;
    
        /**
     *
     * @param parameters
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public List executeQuery(Map<String, Object> parameters) throws SystemException, PortalException, Exception;

   public void executeUpdateQuery(List<StringBuilder> queryList) throws SystemException, PortalException, Exception;
    
    public Object executeSelectQuery(String query) throws SystemException, PortalException, Exception;
    
    public List executeUpdateQuery(String queryList) throws SystemException, PortalException, Exception;
    
    List<HelperTable> getHelperTableList(DynamicQuery query) throws PortalException, SystemException;

}
