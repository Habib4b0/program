/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.model.HelperTable;
import java.util.List;
import java.util.Map;


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
    List<String> getPMPYTradingPartnerList(String tpName) throws  PortalException;

    /**
     * Gets the pmpy contract holder.
     *
     * @return the pmpy contract holder
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    List<String> getPMPYContractHolder(DynamicQuery dynamicQuery) throws  PortalException;
/**
 * 
 * @param query
 * @return
 * @throws SystemException
 * @throws PortalException
 * @throws Exception
 */
    public List getSalesProjection(Object[] inputs) throws  PortalException;
/**
 * 
 * @param query
 * @return
 * @throws SystemException
 * @throws PortalException
 * @throws Exception
 */
    public List getSalesProjectionResult(DynamicQuery query) throws  PortalException;


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
    				throws PortalException;
    
    /** Gets the group list.
     *
     * @param query the query
     * @return the group list
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    List<String> getGroupList(DynamicQuery query) throws SystemException;
    public List getSalesProjectionResults(Object[] inputs) throws PortalException;
    public List getSalesProjectionResultLevels(Object[] inputs) throws PortalException;
    
        /**
     *
     * @param parameters
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public List executeQuery(Map<String, Object> parameters) throws  PortalException;

   public void executeUpdateQuery(List<StringBuilder> queryList) throws PortalException;
    
    public Object executeSelectQuery(String query) throws PortalException;
    
    public List executeUpdateQuery(String queryList) throws PortalException;
    
    List<HelperTable> getHelperTableList(DynamicQuery query) throws SystemException;

}
